package com.example.excel.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.excel.service.*;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author 张钧诚
 * @date 2021/12/17
 */
@SuppressWarnings("unused")
@Component
public class ExcelUtils {

    private static BasiInformationService basiInformationService;

    @Autowired
    public void setBasiInformationService(BasiInformationService basiInformationService){
        ExcelUtils.basiInformationService = basiInformationService;
    }

    private static SubCustomerInformationService subCustomerInformationService;

    @Autowired
    public void setSubCustomerInformationService(SubCustomerInformationService subCustomerInformationService){
        ExcelUtils.subCustomerInformationService = subCustomerInformationService;
    }

    private static ServiceagreementBasicinformationService serviceagreementBasicinformationService;

    @Autowired
    public void setServiceagreementBasicinformationService( ServiceagreementBasicinformationService serviceagreementBasicinformationService){
        ExcelUtils.serviceagreementBasicinformationService = serviceagreementBasicinformationService;
    }

    private static CustomerBillGenerationConditionsService customerBillGenerationConditionsService;

    @Autowired
    public void setSCustomerBillGenerationConditionsService( CustomerBillGenerationConditionsService customerBillGenerationConditionsService){
        ExcelUtils.customerBillGenerationConditionsService = customerBillGenerationConditionsService;
    }


    private static final String XLSX = ".xlsx";
    private static final String XLS = ".xls";
    public static final String ROW_MERGE = "row_merge";
    public static final String COLUMN_MERGE = "column_merge";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String ROW_NUM = "rowNum";
    private static final String ROW_DATA = "rowData";
    private static final String ROW_TIPS = "rowTips";
    private static final int CELL_OTHER = 0;
    private static final int CELL_ROW_MERGE = 1;
    private static final int CELL_COLUMN_MERGE = 2;
    private static final int IMG_HEIGHT = 30;
    private static final int IMG_WIDTH = 30;
    private static final char LEAN_LINE = '/';
    private static final int BYTES_DEFAULT_LENGTH = 10240;
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();


    public static <T> List<T> readFile(File file, Class<T> clazz) throws Exception {
        JSONArray array = readFile(file);
        return getBeanList(array, clazz);
    }

    public static <T> List<T> readMultipartFile(MultipartFile mFile, Class<T> clazz) throws Exception {
        JSONArray array = readMultipartFile(mFile);
        return getBeanList(array, clazz);
    }
    public static <T> List<T> readMultipartFiles(JSONArray array, Class<T> clazz) throws Exception {
        return getBeanList(array, clazz);
    }

    public static JSONArray readFile(File file) throws Exception {
        return readExcel(null, file);
    }

    public static JSONArray readMultipartFile(MultipartFile mFile) throws Exception {
        return readExcel(mFile, null);
    }

    public static Map<String, JSONArray> readFileManySheet(File file) throws Exception {
        return readExcelManySheet(null, file);
    }

    public static Map<String, JSONArray> readFileManySheet(MultipartFile file) throws Exception {
        return readExcelManySheet(file, null);
    }

    private static <T> List<T> getBeanList(JSONArray array, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
       // Map<Integer, String> uniqueMap = new HashMap<>(16);
        MultiValuedMap<Integer, String> uniqueMap = new ArrayListValuedHashMap<>(16);
        //获取 C06服务协议名称 对应的 协议价
        MultiValuedMap<String, String> ServiceAgreementNameMap = new ArrayListValuedHashMap<>(16);
        //获取 C06服务协议名称 对应的 服务项目
        MultiValuedMap<String, String> ServiceItemsMap = new ArrayListValuedHashMap<>(16);
        //根据C07 合同/协议级别 存取 合同名称
        MultiValuedMap<String, String> contractLevelTitleMap = new ArrayListValuedHashMap<>(16);
        for (int i = 0; i < array.size(); i++) {
            list.add(getBean(clazz, array.getJSONObject(i), uniqueMap , ServiceAgreementNameMap , ServiceItemsMap, contractLevelTitleMap));
        }
        return list;
    }

    /**
     * 获取每个对象的数据
     */
    private static <T> T getBean(Class<T> c, JSONObject obj, MultiValuedMap<Integer,
            String> uniqueMap ,MultiValuedMap<String, String> ServiceAgreementNameMap ,
                                 MultiValuedMap<String, String> ServiceItemsMap,
                                 MultiValuedMap<String, String> contractLevelTitleMap) throws Exception {
        T t = c.newInstance();
        Field[] fields = c.getDeclaredFields();
        List<String> errMsgList = new ArrayList<>();

        //存放服务协议名称
        Set<String> setServiceAgreementName = new HashSet<>();

        //存放C06中服务项目
        Set<String> setserviceItems = new HashSet<>();

        boolean hasRowTipsField = false;

        StringBuilder uniqueBuilder = new StringBuilder();
        //存放服务协议名称
        StringBuilder ServiceAgreementNameBuilder = new StringBuilder();
        //存放协议价
        StringBuilder negotiatedPriceBuilder = new StringBuilder();
        //存放服务项目
        StringBuilder serviceItemsBuilder = new StringBuilder();

        //存放 C07合同名称
        StringBuilder contractTitleBuilder = new StringBuilder();
        //存放 C07 合同/协议级别
        StringBuilder contractLevelBuilder = new StringBuilder();
        //存放 C07 一级合同名称
        StringBuilder firstContractNameBuilder = new StringBuilder();


        List uniqueList = new ArrayList();


        //用于判断C05中的是否
        List Yes = new ArrayList();

        int rowNum = 0;
        for (Field field : fields) {
            // 行号
            if (field.getName().equals(ROW_NUM)) {
                rowNum = obj.getInteger(ROW_NUM);
                field.setAccessible(true);
                field.set(t, rowNum);
                continue;
            }
            // 是否需要设置异常信息
            if (field.getName().equals(ROW_TIPS)) {
                hasRowTipsField = true;
                continue;
            }
            // 原始数据
            if (field.getName().equals(ROW_DATA)) {
                field.setAccessible(true);
                field.set(t, obj.toString());
                continue;
            }
            // 设置对应属性值
            setFieldValue(t, field, obj, uniqueBuilder, errMsgList,Yes,
                    setServiceAgreementName,setserviceItems,ServiceAgreementNameBuilder,
                    negotiatedPriceBuilder,serviceItemsBuilder,contractTitleBuilder,
                    contractLevelBuilder,firstContractNameBuilder);
        }

        //处理一列服务协议名称
        if (ServiceAgreementNameBuilder.length() > 0){
            String ServiceAgreement = ServiceAgreementNameBuilder.toString();
            String[] Split = ServiceAgreement.split(",");
            //数组转集合 C06中的服务协议名称
            List<String> collect = Arrays.stream(Split).collect(Collectors.toList());
            //C05中的服务协议名称
            List<String> collectService = serviceagreementBasicinformationService.SelectserviceAgreementName();
            if (!collect.containsAll(collectService) && !collectService.containsAll(collect)){
                errMsgList.add(String.format("C-06服务协议名称必须与C-05一致"));
            }
        }

        //处理协议价
        if (negotiatedPriceBuilder.length() > 0 && ServiceAgreementNameBuilder.length() > 0){
            //协议价
            String negotiatedPrice = negotiatedPriceBuilder.toString();
            String[] Split = negotiatedPrice.split(",");
            //数组转集合 C06中的协议价
            List<String> collect = Arrays.stream(Split).collect(Collectors.toList());

            //服务协议名称
            String ServiceAgreement = ServiceAgreementNameBuilder.toString();
            String[] Split1 = ServiceAgreement.split(",");
            //数组转集合 C06中的服务协议名称
            List<String> collect1 = Arrays.stream(Split1).collect(Collectors.toList());

            for (int i = 0; i < collect.size(); i++){
                String SP = collect1.get(i);
                String NP = collect.get(i);
                ServiceAgreementNameMap.put(SP,NP);
            }

            List<String> serviceAgreementName = serviceagreementBasicinformationService.SelectServiceAgreementNameByChargeServiceFee("0");

            List<Integer> SizesOf = new ArrayList<>();
            for (String SA : serviceAgreementName){
               List<String> values = (List<String>) ServiceAgreementNameMap.get(SA);
                for (String SIZE : values){
                    int size = Integer.parseInt(SIZE);
                    if (size > 0){
                        SizesOf.add(size);
                    }
                }
            }
            if (SizesOf.isEmpty()){
                errMsgList.add(String.format("若C-05中“按整体费用和比例收取服务费“选”否“，则C-06中应至少有一项含服务费(不好意思暂时有BUG，收费的那个先写第一个😘)"));
            }

        }

        //C-06的“服务项目“注意“薪资核算”和“薪资核算与发放”只能选择一个
        if (serviceItemsBuilder.length() > 0 && ServiceAgreementNameBuilder.length() > 0){
            //服务项目
            String negotiatedPrice = serviceItemsBuilder.toString();
            String[] Split = negotiatedPrice.split(",");
            //数组转集合 C06中的服务项目
            List<String> NPs = Arrays.stream(Split).collect(Collectors.toList());

            //服务协议名称
            String ServiceAgreement = ServiceAgreementNameBuilder.toString();
            String[] Split1 = ServiceAgreement.split(",");
            //数组转集合 C06中的服务协议名称
            List<String> collect1 = Arrays.stream(Split1).collect(Collectors.toList());

            for (int i = 0; i < NPs.size(); i++){
                String SP = collect1.get(i);
                String NP = NPs.get(i);
                ServiceItemsMap.put(SP,NP);
            }


            List<String> SizesOf = new ArrayList<>();
            for (String SA : collect1){
                List<String> values = (List<String>) ServiceItemsMap.get(SA);
                for (String SIZE : values){
                    if (SIZE.contains("薪资核算") || SIZE.contains("薪资核算与发放")){
                        SizesOf.add(SIZE);
                    }
                }
            }
            if (SizesOf.size() > 1){
                errMsgList.add(String.format("C-06的“服务项目“注意“薪资核算”和“薪资核算与发放”只能选择一个"));
            }


        }

        //C-07合同协议等级为“一级“时不写一级合同名称  C-07合同/协议级别为二级或者三级的，需填写表中有的所属的一级合同名称
        if (contractLevelBuilder.length() > 0 && contractTitleBuilder.length() > 0){

            //获取合同协议等级
            String contractLevelB = contractLevelBuilder.toString();

            //获取合同名称
            String contractT = contractTitleBuilder.toString();

            //获取一级合同名称
            String firstContractN = firstContractNameBuilder.toString();

            //C-07合同协议等级为“一级“时不写一级合同名称
            if ("一级".equals(contractLevelB) && !firstContractN.isEmpty()){
                errMsgList.add(String.format("C-07合同协议等级为“一级“时不写一级合同名称"));
            }
            //根据C07 合同/协议级别 存取 合同名称
            contractLevelTitleMap.put(contractLevelB,contractT);
            List<String> values = (List<String>) contractLevelTitleMap.get("一级");
            if ("二级".equals(contractLevelB) || "三级".equals(contractLevelB)){
                 if (values != null && !values.contains(firstContractN)) {
                     errMsgList.add(String.format("C-07合同/协议级别为二级或者三级的，需填写表中有的所属的一级合同名称"));
                }
            }
        }




        // 数据唯一性校验
        if (uniqueBuilder.length() > 0) {
            String unique = uniqueBuilder.toString();
            String[] split = unique.split(",");
            //数组转集合
            List<String> collect = Arrays.stream(split).collect(Collectors.toList());
            for (int i = 0;i<collect.size();i++){

                if (uniqueMap.containsValue(collect.get(i))) {
                    Set<Integer> rowNumKeys = uniqueMap.keySet();
                    for (Integer num : rowNumKeys) {
                        if (uniqueMap.get(num).contains(collect.get(i))) {
                            errMsgList.add(String.format("数据唯一性校验失败,(%s)与第%s行重复)", collect.get(i), num));
                        }
                    }
                } else {
                    uniqueMap.put(rowNum, collect.get(i));
                }
            }
        }



        // 失败处理
        if (errMsgList.isEmpty() && !hasRowTipsField) {
            return t;
        }
        StringBuilder sb = new StringBuilder();
        int size = errMsgList.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                sb.append(errMsgList.get(i));
            } else {
                sb.append(errMsgList.get(i)).append(";");
            }
        }
        // 设置错误信息
        for (Field field : fields) {
            if (field.getName().equals(ROW_TIPS)) {
                field.setAccessible(true);
                field.set(t, sb.toString());
            }
        }
        return t;
    }

    private static <T> void setFieldValue(T t, Field field, JSONObject obj, StringBuilder uniqueBuilder,
                                          List<String> errMsgList,List Yes,Set<String> setServiceAgreementName,
                                          Set<String> setserviceItems,StringBuilder ServiceAgreementNameBuilder,
                                          StringBuilder negotiatedPriceBuilder,StringBuilder serviceItemsBuilder,
                                          StringBuilder contractTitleBuilder, StringBuilder contractLevelBuilder,
                                          StringBuilder firstContractNameBuilder) {

        // 获取 ExcelImport 注解属性
        ExcelImport annotation = field.getAnnotation(ExcelImport.class);
        if (annotation == null) {
            return;
        }
        String cname = annotation.value();
        if (cname.trim().length() == 0) {
            return;
        }
        // 获取具体值
        String val = null;
        if (obj.containsKey(cname)) {
            val = getString(obj.getString(cname));
        }
        if (val == null) {
            return;
        }
        field.setAccessible(true);
        // 判断是否必填
        boolean require = annotation.required();
        if (require && val.isEmpty()) {
            errMsgList.add(String.format("[%s]不能为空", cname));
            return;
        }
        // 数据唯一性获取
        boolean unique = annotation.unique();
        if (unique) {
            if (uniqueBuilder.length() > 0) {
                uniqueBuilder.append(",").append(val);
            } else {
                uniqueBuilder.append(val);
            }
        }
        //获取服务协议名称
        boolean ListServiceAgreementNameBuilder = annotation.ListServiceAgreementNameBuilder();
        if (ListServiceAgreementNameBuilder){
            if (ServiceAgreementNameBuilder.length() > 0) {
                ServiceAgreementNameBuilder.append(",").append(val);
            } else {
                ServiceAgreementNameBuilder.append(val);
            }
        }
        //获取协议价
        Boolean ListnegotiatedPriceBuilder = annotation.negotiatedPrice();
        if (ListnegotiatedPriceBuilder){
            if (negotiatedPriceBuilder.length() > 0) {
                negotiatedPriceBuilder.append(",").append(val);
            } else {
                negotiatedPriceBuilder.append(val);
            }
        }
        //获取服务项目
        Boolean ListserviceItemsBuilder = annotation.serviceItems();
        if (ListserviceItemsBuilder){
            if (serviceItemsBuilder.length() > 0) {
                serviceItemsBuilder.append(",").append(val);
            } else {
                serviceItemsBuilder.append(val);
            }
        }


        //判断是否带关键字
        String keyWord = annotation.notKeyWord();
        if (keyWord != null && keyWord.length() != 0){
            //字符串第一个字符最后出现的下标
            int lastIndex=val.lastIndexOf(keyWord);
            if(lastIndex == -1) {
            }
            else {
                errMsgList.add(String.format("[%s]不需要写省", cname));
            }
        }

        //指系统中业务员的岗位名称，详见sheet4  业务二部专员2
        boolean departmentCommissioner = annotation.departmentCommissioner();
        if (departmentCommissioner && !val.contains("专员")){
                 errMsgList.add(String.format("[%s]指系统中业务员的岗位名称，详见sheet4", cname));
        }

        //判断日期格式是否规范 2019-08-23
        boolean dateTime = annotation.dateTime();
        if (dateTime){
            if (val != null && val.length() != 0){
                if (!isLegalDate(val.length(),val,"yyyy-MM-dd")){
                    errMsgList.add(String.format("[%s]日期格式不规范 eg:2019-08-23", cname));
                }
            }
        }

        //判断日期格式是否规范 2019-08
        boolean dateTime2 = annotation.dateTime2();
        if (dateTime2){
            if (val != null && val.length() != 0){
                if (!isLegalDate(val.length(),val,"yyyy-MM")){
                    errMsgList.add(String.format("[%s]日期格式不规范 eg:2019-08", cname));
                }
            }
        }

        //判断数据是否符合规定（简称需要与C-01中填写的客户简称保持一致）
        boolean dataCompliance = annotation.dataCompliance();
        if (dataCompliance && !"".equals(val)){
            if (basiInformationService.findBasiInformationByCustomerAbbreviation(val) == null){
                errMsgList.add(String.format("[%s]填写的简称需要与C-01中填写的客户简称保持一致", cname));
            }
        }
        //判断数据是否符合规定（编号需要与C-01中填写的客户编号保持一致）
        boolean customerNumber = annotation.customerNumber();
        if (customerNumber && !"".equals(val)){
            if (basiInformationService.findBasiInformationByCustomerNumber(val) == null){
                errMsgList.add(String.format("[%s]填写的编号需要与C-01中填写的客户编号保持一致", cname));
            }
        }

        //判断数据是否符合规定（简称需要与C-04中填写的客户简称保持一致）
        boolean dataCompliance04 = annotation.dataCompliance04();
        if (dataCompliance04 && !"".equals(val)){
            if (subCustomerInformationService.findSubCustomerInformationByCustomerAbbreviation(val) == null){
                errMsgList.add(String.format("[%s]填写的简称需要与C-04中填写的子客户简称保持一致", cname));
            }
        }
        //判断数据是否符合规定（编号需要与C-04中填写的客户编号保持一致）
        boolean customerNumber04 = annotation.customerNumber04();
        if (customerNumber04 && !"".equals(val)){
            if (subCustomerInformationService.findSubCustomerInformationByCustomerNumber(val) == null){
                errMsgList.add(String.format("[%s]填写的编号需要与C-04中填写的子客户编号保持一致", cname));
            }
        }

        //判断C05 按整体费用和比例收取服务费 内容 当此项选择“是”时，黄色背景列每一项都需要填写；当此项选择“否”时，黄色背景列留空
        boolean co5TF = annotation.co5TF();
        if (co5TF && "是".equals(val)){
            Yes.add(1);
        }
        boolean c05required = annotation.c05required();
        if (c05required  && val.isEmpty() && null != Yes && Yes.size() != 0){
            errMsgList.add(String.format("[%s]不能为空", cname));
        }

        /**
         * C02 2表
         */

        //如员工没有该项服务，账单生成条件选择“人工核算”，不能留空
        Boolean Serve = annotation.Serve();
        if (Serve && "".equals(val)){
            errMsgList.add(String.format("如员工没有[%s],账单生成条件选择“人工核算”，不能留空", cname));
        }

        /**
         * C06规则
         */
        //C06中的服务协议名称与C05中服务协议名称一致 （C05中有的C06必须有)

        //服务产品和服务套餐只能选择一个
        boolean serviceProducts = annotation.serviceProducts();
        if (serviceProducts){
            setServiceAgreementName.add(val);
            setServiceAgreementName.removeAll(Collections.singleton(""));
        }
        boolean servicePackage = annotation.servicePackage();
        if (servicePackage && !setServiceAgreementName.isEmpty() && !"".equals(val)){
            errMsgList.add(String.format("[%s]和[服务产品]只能选择一个", cname));
        }
        //选择服务产品时服务项目必须填写
        boolean serviceItems = annotation.serviceItems();
        if(serviceItems && !setServiceAgreementName.isEmpty() && val.isEmpty()){
            errMsgList.add(String.format("选择[服务产品]时[%s]必须填写", cname));
        }
        //1、服务产品选择一次性服务时，收费方式只能选择一次性收费
        boolean chargingMethod = annotation.chargingMethod();
        if (chargingMethod){
            List<String> list = new ArrayList<>();
            list.add("按人月");
            list.add("按人年");
            list.add("按人次");

            List<String> list2 = new ArrayList<>();
            list.add("按人月");
            list.add("按人年");
            //服务产品
            for (String Ps : setServiceAgreementName){
                if ("一次性服务产品".equals(Ps) && !"一次性收费".equals(val)){
                    errMsgList.add(String.format("[服务产品]选择一次性服务时[%s]只能选择一次性收费", cname));
                }
            }

            //服务项目
            for (String Is : setserviceItems){
                if ("薪资核算及发放".equals(Is) || "薪资核算".equals(Is)){
                    if (!list.contains(val)){
                        errMsgList.add(String.format("[服务项目]选择薪资核算或薪资核算及发放时[%s]可选择按人月/按人年/按人次收费", cname));
                    }
                }
            }
        }
        //2、服务项目”选择薪资核算或薪资核算及发放时，可选择按人月/按人年/按人次收费；
        boolean serviceItem = annotation.serviceItems();
        if (serviceItem){
            setserviceItems.add(val);
            setserviceItems.removeAll(Collections.singleton(""));
        }


        // 判断是否超过最大长度
        int maxLength = annotation.maxLength();
        if (maxLength > 0 && val.length() > maxLength) {
            errMsgList.add(String.format("[%s]长度不能超过%s个字符(当前%s个字符)", cname, maxLength, val.length()));
        }
        // 判断当前属性是否有映射关系
        LinkedHashMap<String, String> kvMap = getKvMap(annotation.kv());
        if (!kvMap.isEmpty()) {
            boolean isMatch = false;
            for (String key : kvMap.keySet()) {
                if (kvMap.get(key).equals(val)) {
                    val = key;
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch) {
                errMsgList.add(String.format("[%s]的值不正确(当前值为%s)", cname, val));
                return;
            }
        }
        // 其余情况根据类型赋值
        String fieldClassName = field.getType().getSimpleName();
        try {
            if ("String".equalsIgnoreCase(fieldClassName)) {
                field.set(t, val);
            } else if ("boolean".equalsIgnoreCase(fieldClassName)) {
                field.set(t, Boolean.valueOf(val));
            } else if ("int".equalsIgnoreCase(fieldClassName) || "Integer".equals(fieldClassName)) {
                try {
                    field.set(t, Integer.valueOf(val));
                } catch (NumberFormatException e) {
                    errMsgList.add(String.format("[%s]的值格式不正确(当前值为%s)", cname, val));
                }
            } else if ("double".equalsIgnoreCase(fieldClassName)) {
                field.set(t, Double.valueOf(val));
            } else if ("long".equalsIgnoreCase(fieldClassName)) {
                field.set(t, Long.valueOf(val));
            } else if ("BigDecimal".equalsIgnoreCase(fieldClassName)) {
                field.set(t, new BigDecimal(val));
            } else if ("Date".equalsIgnoreCase(fieldClassName)) {
                try {
                    field.set(t, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(val));
                } catch (Exception e) {
                    field.set(t, new SimpleDateFormat("yyyy-MM-dd").parse(val));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        /**
         * C07规则
         */
        //获取合同名称
        Boolean contractTitle = annotation.contractTitle();
        if (contractTitle){
            if (contractTitleBuilder.length() > 0) {
                contractTitleBuilder.append(",").append(val);
            } else {
                contractTitleBuilder.append(val);
            }
        }

        //获取C07 合同/协议级别
        Boolean contractLevel = annotation.contractLevel();
        if (contractLevel){
            if (contractLevelBuilder.length() > 0) {
                contractLevelBuilder.append(",").append(val);
            } else {
                contractLevelBuilder.append(val);
            }
        }

        //获取C07 一级合同名称
        Boolean firstContractName = annotation.firstContractName();
        if (firstContractName){
            if (firstContractNameBuilder.length() > 0) {
                firstContractNameBuilder.append(",").append(val);
            } else {
                firstContractNameBuilder.append(val);
            }
        }
        // C07 当合同状态为“已失效”时，终止方式、终止日期、末次费用收取时间、终止原因，必填
        boolean co7TF = annotation.co7TF();
        if (co7TF && "已失效".equals(val)){
            Yes.add(1);
        }
        boolean c07required = annotation.c07required();
        if (c07required  && val.isEmpty() && null != Yes && Yes.size() != 0){
            errMsgList.add(String.format("当合同状态为“已失效”时[%s]不能为空", cname));
        }
        //当合同的签订类型为“续签”时，前合同名称必填



        // C-07中账单生成条件必须与C-02 2表一致
        boolean billGenerationConditionName = annotation.billGenerationConditionName();
        if (billGenerationConditionName && !"".equals(val) && customerBillGenerationConditionsService.findCustomerAbbreviationByBillGenerationConditionName(val) ==null){
            errMsgList.add(String.format("[%s]必须与C-02 2表一致", cname));
        }

    }

    /**
     * 根据时间 和时间格式 校验是否正确
     * @param length 校验的长度
     * @param sDate 校验的日期
     * @param format 校验的格式
     * @return
     */
    public static boolean isLegalDate(int length, String sDate,String format) {
        int legalLen = length;
        if ((sDate == null) || (sDate.length() != legalLen)) {
            return false;
        }
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(sDate);
            return sDate.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    private static Map<String, JSONArray> readExcelManySheet(MultipartFile mFile, File file) throws IOException {
        Workbook book = getWorkbook(mFile, file);
        if (book == null) {
            return Collections.emptyMap();
        }
        Map<String, JSONArray> map = new LinkedHashMap<>();
        for (int i = 0; i < book.getNumberOfSheets(); i++) {
            Sheet sheet = book.getSheetAt(i);
            JSONArray arr = readSheet(sheet);
            map.put(sheet.getSheetName(), arr);
        }
        book.close();
        return map;
    }

    private static JSONArray readExcel(MultipartFile mFile, File file) throws IOException {
        Workbook book = getWorkbook(mFile, file);
        if (book == null) {
            return new JSONArray();
        }
        JSONArray array = readSheet(book.getSheetAt(0));
        book.close();
        return array;
    }

    private static Workbook getWorkbook(MultipartFile mFile, File file) throws IOException {
        boolean fileNotExist = (file == null || !file.exists());
        if (mFile == null && fileNotExist) {
            return null;
        }
        // 解析表格数据
        InputStream in;
        String fileName;
        if (mFile != null) {
            // 上传文件解析
            in = mFile.getInputStream();
            fileName = getString(mFile.getOriginalFilename()).toLowerCase();
        } else {
            // 本地文件解析
            in = new FileInputStream(file);
            fileName = file.getName().toLowerCase();
        }
        Workbook book;
        if (fileName.endsWith(XLSX)) {
            book = new XSSFWorkbook(in);
        } else if (fileName.endsWith(XLS)) {
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(in);
            book = new HSSFWorkbook(poifsFileSystem);
        } else {
            return null;
        }
        in.close();
        return book;
    }

    private static JSONArray readSheet(Sheet sheet) {
        // 首行下标
        int rowStart = sheet.getFirstRowNum();
        // 尾行下标
        int rowEnd = sheet.getLastRowNum();
        // 获取表头行
        Row headRow = sheet.getRow(rowStart);
        if (headRow == null) {
            return new JSONArray();
        }
        int cellStart = headRow.getFirstCellNum();
        int cellEnd = headRow.getLastCellNum();
        Map<Integer, String> keyMap = new HashMap<>(16);
        for (int j = cellStart; j < cellEnd; j++) {
            // 获取表头数据
            String val = getCellValue(headRow.getCell(j));
            if (val != null && val.trim().length() != 0) {
                keyMap.put(j, val);
            }
        }
        // 如果表头没有数据则不进行解析
        if (keyMap.isEmpty()) {
            return (JSONArray) Collections.emptyList();
        }
        // 获取每行JSON对象的值
        JSONArray array = new JSONArray();
        // 如果首行与尾行相同，表明只有一行，返回表头数据
        if (rowStart == rowEnd) {
            JSONObject obj = new JSONObject();
            // 添加行号
            obj.put(ROW_NUM, 1);
            for (int i : keyMap.keySet()) {
                obj.put(keyMap.get(i), "");
            }
            array.add(obj);
            return array;
        }
        for (int i = rowStart + 1; i <= rowEnd; i++) {
            Row eachRow = sheet.getRow(i);
            JSONObject obj = new JSONObject();
            // 添加行号
            obj.put(ROW_NUM, i + 1);
            StringBuilder sb = new StringBuilder();
            for (int k = cellStart; k < cellEnd; k++) {
                if (eachRow != null) {
                    String val = getCellValue(eachRow.getCell(k));
                    // 所有数据添加到里面，用于判断该行是否为空
                    sb.append(val);
                    obj.put(keyMap.get(k), val);
                }
            }
            if (sb.length() > 0) {
                array.add(obj);
            }
        }
        return array;
    }

    private static String getCellValue(Cell cell) {
        // 空白或空
        if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
            return "";
        }
        // String类型
        if (cell.getCellTypeEnum() == CellType.STRING) {
            String val = cell.getStringCellValue();
            if (val == null || val.trim().length() == 0) {
                return "";
            }
            return val.trim();
        }
        // 数字类型
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            String s = cell.getNumericCellValue() + "";
            // 去掉尾巴上的小数点0
            if (Pattern.matches(".*\\.0*", s)) {
                return s.split("\\.")[0];
            } else {
                return s;
            }
        }
        // 布尔值类型
        if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue() + "";
        }
        // 错误类型
        return cell.getCellFormula();
    }

    public static <T> void exportTemplate(HttpServletResponse response, String fileName, Class<T> clazz) {
        exportTemplate(response, fileName, fileName, clazz, false);
    }

    public static <T> void exportTemplate(HttpServletResponse response, String fileName, String sheetName,
                                          Class<T> clazz) {
        exportTemplate(response, fileName, sheetName, clazz, false);
    }

    public static <T> void exportTemplate(HttpServletResponse response, String fileName, Class<T> clazz,
                                          boolean isContainExample) {
        exportTemplate(response, fileName, fileName, clazz, isContainExample);
    }

    public static <T> void exportTemplate(HttpServletResponse response, String fileName, String sheetName,
                                          Class<T> clazz, boolean isContainExample) {
        // 获取表头字段
        List<ExcelClassField> headFieldList = getExcelClassFieldList(clazz);
        // 获取表头数据和示例数据
        List<List<Object>> sheetDataList = new ArrayList<>();
        List<Object> headList = new ArrayList<>();
        List<Object> exampleList = new ArrayList<>();
        Map<Integer, List<String>> selectMap = new LinkedHashMap<>();
        for (int i = 0; i < headFieldList.size(); i++) {
            ExcelClassField each = headFieldList.get(i);
            headList.add(each.getName());
            exampleList.add(each.getExample());
            LinkedHashMap<String, String> kvMap = each.getKvMap();
            if (kvMap != null && kvMap.size() > 0) {
                selectMap.put(i, new ArrayList<>(kvMap.values()));
            }
        }
        sheetDataList.add(headList);
        if (isContainExample) {
            sheetDataList.add(exampleList);
        }
        // 导出数据
        export(response, fileName, sheetName, sheetDataList, selectMap);
    }

    private static <T> List<ExcelClassField> getExcelClassFieldList(Class<T> clazz) {
        // 解析所有字段
        Field[] fields = clazz.getDeclaredFields();
        boolean hasExportAnnotation = false;
        Map<Integer, List<ExcelClassField>> map = new LinkedHashMap<>();
        List<Integer> sortList = new ArrayList<>();
        for (Field field : fields) {
            ExcelClassField cf = getExcelClassField(field);
            if (cf.getHasAnnotation() == 1) {
                hasExportAnnotation = true;
            }
            int sort = cf.getSort();
            if (map.containsKey(sort)) {
                map.get(sort).add(cf);
            } else {
                List<ExcelClassField> list = new ArrayList<>();
                list.add(cf);
                sortList.add(sort);
                map.put(sort, list);
            }
        }
        Collections.sort(sortList);
        // 获取表头
        List<ExcelClassField> headFieldList = new ArrayList<>();
        if (hasExportAnnotation) {
            for (Integer sort : sortList) {
                for (ExcelClassField cf : map.get(sort)) {
                    if (cf.getHasAnnotation() == 1) {
                        headFieldList.add(cf);
                    }
                }
            }
        } else {
            headFieldList.addAll(map.get(0));
        }
        return headFieldList;
    }

    private static ExcelClassField getExcelClassField(Field field) {
        ExcelClassField cf = new ExcelClassField();
        String fieldName = field.getName();
        cf.setFieldName(fieldName);
        ExcelExport annotation = field.getAnnotation(ExcelExport.class);
        // 无 ExcelExport 注解情况
        if (annotation == null) {
            cf.setHasAnnotation(0);
            cf.setName(fieldName);
            cf.setSort(0);
            return cf;
        }
        // 有 ExcelExport 注解情况
        cf.setHasAnnotation(1);
        cf.setName(annotation.value());
        String example = getString(annotation.example());
        if (!example.isEmpty()) {
            if (isNumeric(example) && example.length() < 8) {
                cf.setExample(Double.valueOf(example));
            } else {
                cf.setExample(example);
            }
        } else {
            cf.setExample("");
        }
        cf.setSort(annotation.sort());
        // 解析映射
        String kv = getString(annotation.kv());
        cf.setKvMap(getKvMap(kv));
        return cf;
    }

    private static LinkedHashMap<String, String> getKvMap(String kv) {
        LinkedHashMap<String, String> kvMap = new LinkedHashMap<>();
        if (kv.isEmpty()) {
            return kvMap;
        }
        String[] kvs = kv.split(";");
        if (kvs.length == 0) {
            return kvMap;
        }
        for (String each : kvs) {
            String[] eachKv = getString(each).split("-");
            if (eachKv.length != 2) {
                continue;
            }
            String k = eachKv[0];
            String v = eachKv[1];
            if (k.isEmpty() || v.isEmpty()) {
                continue;
            }
            kvMap.put(k, v);
        }
        return kvMap;
    }

    /**
     * 导出表格到本地
     *
     * @param file      本地文件对象
     * @param sheetData 导出数据
     */
    public static void exportFile(File file, List<List<Object>> sheetData) {
        if (file == null) {
            System.out.println("文件创建失败");
            return;
        }
        if (sheetData == null) {
            sheetData = new ArrayList<>();
        }
        Map<String, List<List<Object>>> map = new HashMap<>(16);
        map.put(file.getName(), sheetData);
        export(null, file, file.getName(), map, null);
    }

    /**
     * 导出表格到本地
     *
     * @param <T>      导出数据类似，和K类型保持一致
     * @param filePath 文件父路径（如：D:/doc/excel/）
     * @param fileName 文件名称（不带尾缀，如：学生表）
     * @param list     导出数据
     * @throws IOException IO异常
     */
    public static <T> File exportFile(String filePath, String fileName, List<T> list) throws IOException {
        File file = getFile(filePath, fileName);
        List<List<Object>> sheetData = getSheetData(list);
        exportFile(file, sheetData);
        return file;
    }

    /**
     * 获取文件
     *
     * @param filePath filePath 文件父路径（如：D:/doc/excel/）
     * @param fileName 文件名称（不带尾缀，如：用户表）
     * @return 本地File文件对象
     */
    private static File getFile(String filePath, String fileName) throws IOException {
        String dirPath = getString(filePath);
        String fileFullPath;
        if (dirPath.isEmpty()) {
            fileFullPath = fileName;
        } else {
            // 判定文件夹是否存在，如果不存在，则级联创建
            File dirFile = new File(dirPath);
            if (!dirFile.exists()) {
                boolean mkdirs = dirFile.mkdirs();
                if (!mkdirs) {
                    return null;
                }
            }
            // 获取文件夹全名
            if (dirPath.endsWith(String.valueOf(LEAN_LINE))) {
                fileFullPath = dirPath + fileName + XLSX;
            } else {
                fileFullPath = dirPath + LEAN_LINE + fileName + XLSX;
            }
        }
        System.out.println(fileFullPath);
        File file = new File(fileFullPath);
        if (!file.exists()) {
            boolean result = file.createNewFile();
            if (!result) {
                return null;
            }
        }
        return file;
    }

    private static <T> List<List<Object>> getSheetData(List<T> list) {
        // 获取表头字段
        List<ExcelClassField> excelClassFieldList = getExcelClassFieldList(list.get(0).getClass());
        List<String> headFieldList = new ArrayList<>();
        List<Object> headList = new ArrayList<>();
        Map<String, ExcelClassField> headFieldMap = new HashMap<>(16);
        for (ExcelClassField each : excelClassFieldList) {
            String fieldName = each.getFieldName();
            headFieldList.add(fieldName);
            headFieldMap.put(fieldName, each);
            headList.add(each.getName());
        }
        // 添加表头名称
        List<List<Object>> sheetDataList = new ArrayList<>();
        sheetDataList.add(headList);
        // 获取表数据
        for (T t : list) {
            Map<String, Object> fieldDataMap = getFieldDataMap(t);
            Set<String> fieldDataKeys = fieldDataMap.keySet();
            List<Object> rowList = new ArrayList<>();
            for (String headField : headFieldList) {
                if (!fieldDataKeys.contains(headField)) {
                    continue;
                }
                Object data = fieldDataMap.get(headField);
                if (data == null) {
                    rowList.add("");
                    continue;
                }
                ExcelClassField cf = headFieldMap.get(headField);
                // 判断是否有映射关系
                LinkedHashMap<String, String> kvMap = cf.getKvMap();
                if (kvMap == null || kvMap.isEmpty()) {
                    rowList.add(data);
                    continue;
                }
                String val = kvMap.get(data.toString());
                if (isNumeric(val)) {
                    rowList.add(Double.valueOf(val));
                } else {
                    rowList.add(val);
                }
            }
            sheetDataList.add(rowList);
        }
        return sheetDataList;
    }

    private static <T> Map<String, Object> getFieldDataMap(T t) {
        Map<String, Object> map = new HashMap<>(16);
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                field.setAccessible(true);
                Object object = field.get(t);
                map.put(fieldName, object);
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void exportEmpty(HttpServletResponse response, String fileName) {
        List<List<Object>> sheetDataList = new ArrayList<>();
        List<Object> headList = new ArrayList<>();
        headList.add("导出无数据");
        sheetDataList.add(headList);
        export(response, fileName, sheetDataList);
    }

    public static void export(HttpServletResponse response, String fileName, List<List<Object>> sheetDataList) {
        export(response, fileName, fileName, sheetDataList, null);
    }

    public static void exportManySheet(HttpServletResponse response, String fileName, Map<String, List<List<Object>>> sheetMap) {
        export(response, null, fileName, sheetMap, null);
    }


    public static void export(HttpServletResponse response, String fileName, String sheetName,
                              List<List<Object>> sheetDataList) {
        export(response, fileName, sheetName, sheetDataList, null);
    }

    public static void export(HttpServletResponse response, String fileName, String sheetName,
                              List<List<Object>> sheetDataList, Map<Integer, List<String>> selectMap) {

        Map<String, List<List<Object>>> map = new HashMap<>(16);
        map.put(sheetName, sheetDataList);
        export(response, null, fileName, map, selectMap);
    }

    public static <T, K> void export(HttpServletResponse response, String fileName, List<T> list, Class<K> template) {
        // list 是否为空
        boolean lisIsEmpty = list == null || list.isEmpty();
        // 如果模板数据为空，且导入的数据为空，则导出空文件
        if (template == null && lisIsEmpty) {
            exportEmpty(response, fileName);
            return;
        }
        // 如果 list 数据，则导出模板数据
        if (lisIsEmpty) {
            exportTemplate(response, fileName, template);
            return;
        }
        // 导出数据
        List<List<Object>> sheetDataList = getSheetData(list);
        export(response, fileName, sheetDataList);
    }

    public static void export(HttpServletResponse response, String fileName, List<List<Object>> sheetDataList, Map<Integer, List<String>> selectMap) {
        export(response, fileName, fileName, sheetDataList, selectMap);
    }

    private static void export(HttpServletResponse response, File file, String fileName,
                               Map<String, List<List<Object>>> sheetMap, Map<Integer, List<String>> selectMap) {
        // 整个 Excel 表格 book 对象
        SXSSFWorkbook book = new SXSSFWorkbook();
        // 每个 Sheet 页
        Set<Entry<String, List<List<Object>>>> entries = sheetMap.entrySet();
        for (Entry<String, List<List<Object>>> entry : entries) {
            List<List<Object>> sheetDataList = entry.getValue();
            Sheet sheet = book.createSheet(entry.getKey());
            Drawing<?> patriarch = sheet.createDrawingPatriarch();
            // 设置表头背景色（灰色）
            CellStyle headStyle = book.createCellStyle();
            headStyle.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.index);
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headStyle.setAlignment(HorizontalAlignment.CENTER);
            headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
            // 设置表身背景色（默认色）
            CellStyle rowStyle = book.createCellStyle();
            rowStyle.setAlignment(HorizontalAlignment.CENTER);
            rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            // 设置表格列宽度（默认为15个字节）
            sheet.setDefaultColumnWidth(15);
            // 创建合并算法数组
            int rowLength = sheetDataList.size();
            int columnLength = sheetDataList.get(0).size();
            int[][] mergeArray = new int[rowLength][columnLength];
            for (int i = 0; i < sheetDataList.size(); i++) {
                // 每个 Sheet 页中的行数据
                Row row = sheet.createRow(i);
                List<Object> rowList = sheetDataList.get(i);
                for (int j = 0; j < rowList.size(); j++) {
                    // 每个行数据中的单元格数据
                    Object o = rowList.get(j);
                    int v = 0;
                    if (o instanceof URL) {
                        // 如果要导出图片的话, 链接需要传递 URL 对象
                        setCellPicture(book, row, patriarch, i, j, (URL) o);
                    } else {
                        Cell cell = row.createCell(j);
                        if (i == 0) {
                            // 第一行为表头行，采用灰色底背景
                            v = setCellValue(cell, o, headStyle);
                        } else {
                            // 其他行为数据行，默认白底色
                            v = setCellValue(cell, o, rowStyle);
                        }
                    }
                    mergeArray[i][j] = v;
                }
            }
            // 合并单元格
            mergeCells(sheet, mergeArray);
            // 设置下拉列表
            setSelect(sheet, selectMap);
        }
        // 写数据
        if (response != null) {
            // 前端导出
            try {
                write(response, book, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 本地导出
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file);
                ByteArrayOutputStream ops = new ByteArrayOutputStream();
                book.write(ops);
                fos.write(ops.toByteArray());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 合并当前Sheet页的单元格
     *
     * @param sheet      当前 sheet 页
     * @param mergeArray 合并单元格算法
     */
    private static void mergeCells(Sheet sheet, int[][] mergeArray) {
        // 横向合并
        for (int x = 0; x < mergeArray.length; x++) {
            int[] arr = mergeArray[x];
            boolean merge = false;
            int y1 = 0;
            int y2 = 0;
            for (int y = 0; y < arr.length; y++) {
                int value = arr[y];
                if (value == CELL_COLUMN_MERGE) {
                    if (!merge) {
                        y1 = y;
                    }
                    y2 = y;
                    merge = true;
                } else {
                    merge = false;
                    if (y1 > 0) {
                        sheet.addMergedRegion(new CellRangeAddress(x, x, (y1 - 1), y2));
                    }
                    y1 = 0;
                    y2 = 0;
                }
            }
            if (y1 > 0) {
                sheet.addMergedRegion(new CellRangeAddress(x, x, (y1 - 1), y2));
            }
        }
        // 纵向合并
        int xLen = mergeArray.length;
        int yLen = mergeArray[0].length;
        for (int y = 0; y < yLen; y++) {
            boolean merge = false;
            int x1 = 0;
            int x2 = 0;
            for (int x = 0; x < xLen; x++) {
                int value = mergeArray[x][y];
                if (value == CELL_ROW_MERGE) {
                    if (!merge) {
                        x1 = x;
                    }
                    x2 = x;
                    merge = true;
                } else {
                    merge = false;
                    if (x1 > 0) {
                        sheet.addMergedRegion(new CellRangeAddress((x1 - 1), x2, y, y));
                    }
                    x1 = 0;
                    x2 = 0;
                }
            }
            if (x1 > 0) {
                sheet.addMergedRegion(new CellRangeAddress((x1 - 1), x2, y, y));
            }
        }
    }

    private static void write(HttpServletResponse response, SXSSFWorkbook book, String fileName) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String name = new String(fileName.getBytes("GBK"), "ISO8859_1") + XLSX;
        response.addHeader("Content-Disposition", "attachment;filename=" + name);
        ServletOutputStream out = response.getOutputStream();
        book.write(out);
        out.flush();
        out.close();
    }

    private static int setCellValue(Cell cell, Object o, CellStyle style) {
        // 设置样式
        cell.setCellStyle(style);
        // 数据为空时
        if (o == null) {
            cell.setCellType(CellType.STRING);
            cell.setCellValue("");
            return CELL_OTHER;
        }
        // 是否为字符串
        if (o instanceof String) {
            String s = o.toString();
            // 当数字类型长度超过8位时，改为字符串类型显示（Excel数字超过一定长度会显示为科学计数法）
            if (isNumeric(s) && s.length() < 8) {
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(Double.parseDouble(s));
                return CELL_OTHER;
            } else {
                cell.setCellType(CellType.STRING);
                cell.setCellValue(s);
            }
            if (s.equals(ROW_MERGE)) {
                return CELL_ROW_MERGE;
            } else if (s.equals(COLUMN_MERGE)) {
                return CELL_COLUMN_MERGE;
            } else {
                return CELL_OTHER;
            }
        }
        // 是否为字符串
        if (o instanceof Integer || o instanceof Long || o instanceof Double || o instanceof Float) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Double.parseDouble(o.toString()));
            return CELL_OTHER;
        }
        // 是否为Boolean
        if (o instanceof Boolean) {
            cell.setCellType(CellType.BOOLEAN);
            cell.setCellValue((Boolean) o);
            return CELL_OTHER;
        }
        // 如果是BigDecimal，则默认3位小数
        if (o instanceof BigDecimal) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(((BigDecimal) o).setScale(3, RoundingMode.HALF_UP).doubleValue());
            return CELL_OTHER;
        }
        // 如果是Date数据，则显示格式化数据
        if (o instanceof Date) {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(formatDate((Date) o));
            return CELL_OTHER;
        }
        // 如果是其他，则默认字符串类型
        cell.setCellType(CellType.STRING);
        cell.setCellValue(o.toString());
        return CELL_OTHER;
    }

    private static void setCellPicture(SXSSFWorkbook wb, Row sr, Drawing<?> patriarch, int x, int y, URL url) {
        // 设置图片宽高
        sr.setHeight((short) (IMG_WIDTH * IMG_HEIGHT));
        // （jdk1.7版本try中定义流可自动关闭）
        try (InputStream is = url.openStream(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buff = new byte[BYTES_DEFAULT_LENGTH];
            int rc;
            while ((rc = is.read(buff, 0, BYTES_DEFAULT_LENGTH)) > 0) {
                outputStream.write(buff, 0, rc);
            }
            // 设置图片位置
            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, y, x, y + 1, x + 1);
            // 设置这个，图片会自动填满单元格的长宽
            anchor.setAnchorType(AnchorType.MOVE_AND_RESIZE);
            patriarch.createPicture(anchor, wb.addPicture(outputStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }

    private static void setSelect(Sheet sheet, Map<Integer, List<String>> selectMap) {
        if (selectMap == null || selectMap.isEmpty()) {
            return;
        }
        Set<Entry<Integer, List<String>>> entrySet = selectMap.entrySet();
        for (Entry<Integer, List<String>> entry : entrySet) {
            int y = entry.getKey();
            List<String> list = entry.getValue();
            if (list == null || list.isEmpty()) {
                continue;
            }
            String[] arr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i);
            }
            DataValidationHelper helper = sheet.getDataValidationHelper();
            CellRangeAddressList addressList = new CellRangeAddressList(1, 65000, y, y);
            DataValidationConstraint dvc = helper.createExplicitListConstraint(arr);
            DataValidation dv = helper.createValidation(dvc, addressList);
            if (dv instanceof HSSFDataValidation) {
                dv.setSuppressDropDownArrow(false);
            } else {
                dv.setSuppressDropDownArrow(true);
                dv.setShowErrorBox(true);
            }
            sheet.addValidationData(dv);
        }
    }

    private static boolean isNumeric(String str) {
        if (Objects.nonNull(str) && "0.0".equals(str)) {
            return true;
        }
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static String getString(String s) {
        if (s == null) {
            return "";
        }
        if (s.isEmpty()) {
            return s;
        }
        return s.trim();
    }

}

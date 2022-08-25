package com.example.excel.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.excel.entity.*;
import com.example.excel.service.*;
import com.example.excel.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("import")
public class ImportController {

    @Autowired
    private BasiInformationService basiInformationService;

    @Autowired
    private CustomerInformationService customerInformationService;

    @Autowired
    private CustomerSettlementUnitService customerSettlementUnitService;

    @Autowired
    private SubCustomerInformationService subCustomerInformationService;

    @Autowired
    private ServiceagreementBasicinformationService serviceagreementBasicinformationService;

    @Autowired
    private ServiceAgreementItemsService serviceAgreementItemsService;

    @Autowired
    private CustomerContractBasicInformationService customerContractBasicInformationService;

    @Autowired
    private CustomerBillGenerationConditionsService customerBillGenerationConditionsService;

    @Autowired
    private PayrollInitializationService payrollInitializationService;

    @Autowired
    private CustomerFirstlevelService customerFirstlevelService;

    @RequestMapping("/")
    public String hello(){
        return "excel";
    }

    /**
     * 导入解析为JSON
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/JSON")
    @ResponseBody
    public JSONArray importJSON(@RequestPart("file") MultipartFile file) throws Exception {
        JSONArray array = ExcelUtils.readMultipartFile(file);
        System.out.println("导入数据为:" + array);
        return array;
    }

    /**
     * 多个sheet页导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public void upload(@RequestPart("file") MultipartFile file) throws Exception {
        Map<String, JSONArray> map = ExcelUtils.readFileManySheet(file);
        map.forEach((key, value) -> {
            System.out.println("Sheet名称：" + key);
            System.out.println("Sheet数据：" + value);
            System.out.println("----------------------");
        });
        //C02
        JSONArray jsonArray = map.get("客户成本往来设定表");
        map.get("客户账单生成条件");
        map.get("成本往来设定模板");
        ExcelUtils.readMultipartFiles(jsonArray,CustomerInformation.class);
    }

    /**
     * BasiInformation
     * com.example.excel.entity.BasiInformation
     * 解析为对象
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC01")
    @ResponseBody
    public void importClassC01(HttpServletResponse response,@RequestPart("file")MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<BasiInformation> users = ExcelUtils.readMultipartFile(file,BasiInformation.class);
        //创建一个集合来存放错误信息
        List<String> Basis = new ArrayList<>();
        //循环遍历向list中添加错误信息
        for (BasiInformation user : users) {
            Basis.add(user.getRowTips());
        }
        //移除list中所用空的信息
        Basis.removeAll(Collections.singleton(""));
        //如果list为空
        if (Basis.isEmpty()){
            //循环遍历导入数据库
            for (BasiInformation user : users) {
                basiInformationService.insertBasiInformation(user);
                System.out.println(user.getRowNum() + "导入成功");
            }
        }else {
                // 导出数据
                ExcelUtils.export(response,"C01错误提示", users ,BasiInformation.class);
            //循环遍历导入数据库
            for (BasiInformation user : users) {
                System.out.println(user.getRowNum() + user.getRowTips() + "导入失败");
            }
        }
    }

    /**
     * CustomerInformation
     * com.example.excel.entity.CustomerInformation
     * @param response
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC02")
    @ResponseBody
    public void importClassC02(HttpServletResponse response,@RequestPart("file")MultipartFile file) throws Exception {

        Map<String, JSONArray> map = ExcelUtils.readFileManySheet(file);
        JSONArray CustomerInformation = map.get("客户成本往来设定表");

        System.out.println("客户成本往来设定表");
        //获取处理完Excel的数据
        List<CustomerInformation> users = ExcelUtils.readMultipartFiles(CustomerInformation,CustomerInformation.class);
        //创建一个集合来存放错误信息
        List<String> Customer = new ArrayList<>();
        //循环遍历向list中添加错误信息
        for (CustomerInformation user : users) {
            Customer.add(user.getRowTips());
        }
        //移除list中所用空的信息
        Customer.removeAll(Collections.singleton(""));
        //如果list为空
        if (Customer.isEmpty()){
            //循环遍历导入数据库
            for (CustomerInformation user : users) {
                customerInformationService.insertCustomerInformation(user);
                System.out.println("客户成本往来设定表" + user.getRowNum() + "导入成功");
            }
        }else {
            // 导出数据
            ExcelUtils.export(response,"C02 客户成本往来设定表 错误提示", users ,CustomerInformation.class);
            for (CustomerInformation user : users) {
                System.out.println(user.getRowTips() + "导入失败");
            }
        }
    }

    /**
     * CustomerBillGenerationConditions
     * com.example.excel.entity.CustomerBillGenerationConditions
     * @param response
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC0202")
    @ResponseBody
    public void importClassC0202(HttpServletResponse response,@RequestPart("file")MultipartFile file) throws Exception {

        Map<String, JSONArray> map = ExcelUtils.readFileManySheet(file);
        JSONArray CustomerBillGenerationConditions =  map.get("客户账单生成条件");

        System.out.println("客户账单生成条件");
        //获取处理完Excel的数据
        List<CustomerBillGenerationConditions> CustomerBill = ExcelUtils.readMultipartFiles(CustomerBillGenerationConditions,CustomerBillGenerationConditions.class);
        //创建一个集合来存放错误信息
        List<String> CBGC = new ArrayList<>();
        //循环遍历向list中添加错误信息
        for (CustomerBillGenerationConditions user : CustomerBill) {
            CBGC.add(user.getRowTips());
        }
        //移除list中所用空的信息
        CBGC.removeAll(Collections.singleton(""));
        //如果list为空
        if (CBGC.isEmpty()){
            //循环遍历导入数据库
            for (CustomerBillGenerationConditions user : CustomerBill) {
                customerBillGenerationConditionsService.insertCustomerBillGenerationConditions(user);
                System.out.println("客户账单生成条件" + user.getRowNum() + "导入成功");
            }
        }else {
            // 导出数据
            ExcelUtils.export(response,"C02 客户账单生成条件 错误提示", CustomerBill ,CustomerBillGenerationConditions.class);
            for (CustomerBillGenerationConditions user : CustomerBill) {
                System.out.println(user.getRowNum() + user.getRowTips() + "导入失败");
            }
        }
    }

    /**
     * CustomerSettlementUnit
     * com.example.excel.entity.CustomerSettlementUnit
     * @param response
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC03")
    @ResponseBody
    public void importClassC03(HttpServletResponse response,@RequestPart("file")MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<CustomerSettlementUnit> users = ExcelUtils.readMultipartFile(file, CustomerSettlementUnit.class);
        //创建一个集合来存放错误信息
        List<String> Customer = new ArrayList<>();
        //循环遍历向list中添加错误信息
        for (CustomerSettlementUnit user : users) {
            Customer.add(user.getRowTips());
        }
        //移除list中所用空的信息
        Customer.removeAll(Collections.singleton(""));
        //如果list为空
        if (Customer.isEmpty()){
            //循环遍历导入数据库
            for (CustomerSettlementUnit user : users) {
                customerSettlementUnitService.insertCustomerSettlementUnit(user);
                System.out.println(user.getRowNum() + "导入成功");
            }
        }else {
            // 导出数据
            ExcelUtils.export(response,"C03错误提示.xlsx", users ,CustomerSettlementUnit.class);
            for (CustomerSettlementUnit user : users) {
                System.out.println(user.getRowTips() + "导入失败");
            }
        }
    }

    /**
     * SubCustomerInformation
     * com.example.excel.entity.SubCustomerInformation
     * @param response
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC04")
    @ResponseBody
    public void importClassC04(HttpServletResponse response,@RequestPart("file")MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<SubCustomerInformation> users = ExcelUtils.readMultipartFile(file, SubCustomerInformation.class);
        //创建一个集合来存放错误信息
        List<String> Customer = new ArrayList<>();
        //循环遍历向list中添加错误信息
        for (SubCustomerInformation user : users) {
            Customer.add(user.getRowTips());
        }
        //移除list中所用空的信息
        Customer.removeAll(Collections.singleton(""));
        //如果list为空
        if (Customer.isEmpty()){
            //循环遍历导入数据库
            for (SubCustomerInformation user : users) {
                subCustomerInformationService.insertSubCustomerInformation(user);
                System.out.println(user.getRowNum() + "导入成功");
            }
        }else {
            // 导出数据
            ExcelUtils.export(response,"C04错误提示", users ,SubCustomerInformation.class);
            for (SubCustomerInformation user : users) {
                System.out.println(user.getRowNum() + user.getRowTips() + "导入失败");
            }
        }
    }

    /**
     * ServiceagreementBasicinformation
     * com.example.excel.entity.ServiceagreementBasicinformation
     * @param response
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC05")
    @ResponseBody
    public void importClassC05(HttpServletResponse response,@RequestPart("file")MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<ServiceagreementBasicinformation> users = ExcelUtils.readMultipartFile(file, ServiceagreementBasicinformation.class);
        //创建一个集合来存放错误信息
        List<String> Customer = new ArrayList<>();
        //循环遍历向list中添加错误信息
        for (ServiceagreementBasicinformation user : users) {
            Customer.add(user.getRowTips());
        }
        //移除list中所用空的信息
        Customer.removeAll(Collections.singleton(""));
        //如果list为空
        if (Customer.isEmpty()){
            //循环遍历导入数据库
            for (ServiceagreementBasicinformation user : users) {
                serviceagreementBasicinformationService.insertServiceagreementBasicinformation(user);
                System.out.println(user.getRowNum() + "导入成功");
            }
        }else {
            // 导出数据
            ExcelUtils.export(response,"C05错误提示", users ,ServiceagreementBasicinformation.class);
            for (ServiceagreementBasicinformation user : users) {
                System.out.println(user.getRowNum() + user.getRowTips() + "导入失败");
            }
        }
    }

    /**
     * ServiceAgreementItems
     * com.example.excel.entity.ServiceAgreementItems
     * @param response
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC06")
    @ResponseBody
    public void importClassC06(HttpServletResponse response,@RequestPart("file")MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<ServiceAgreementItems> users = ExcelUtils.readMultipartFile(file, ServiceAgreementItems.class);
        //创建一个集合来存放错误信息
        List<String> Customer = new ArrayList<>();
        //循环遍历向list中添加错误信息
        for (ServiceAgreementItems user : users) {
            Customer.add(user.getRowTips());
        }
        //移除list中所用空的信息
        Customer.removeAll(Collections.singleton(""));
        //如果list为空
        if (Customer.isEmpty()){
            //循环遍历导入数据库
            for (ServiceAgreementItems user : users) {
                serviceAgreementItemsService.insertServiceAgreementItems(user);
                System.out.println(user.getRowNum() + "导入成功");
            }
        }else {
            // 导出数据
            ExcelUtils.export(response,"C06错误提示", users ,ServiceAgreementItems.class);
            for (ServiceAgreementItems user : users) {
                System.out.println(user.getRowNum() + user.getRowTips() + "导入失败");
            }
        }
    }

    /**
     * CustomerContractBasicInformation
     * com.example.excel.entity.CustomerContractBasicInformation
     * @param response
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC07")
    @ResponseBody
    public void importClassC07(HttpServletResponse response,@RequestPart("file")MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<CustomerContractBasicInformation> users = ExcelUtils.readMultipartFile(file, CustomerContractBasicInformation.class);
        //创建一个集合来存放错误信息
        List<String> Customer = new ArrayList<>();
        //循环遍历向list中添加错误信息
        for (CustomerContractBasicInformation user : users) {
            Customer.add(user.getRowTips());
        }
        //移除list中所用空的信息
        Customer.removeAll(Collections.singleton(""));
        //如果list为空
        if (Customer.isEmpty()){
            //循环遍历导入数据库
            for (CustomerContractBasicInformation user : users) {
                customerContractBasicInformationService.insertCustomerContractBasicInformation(user);
                System.out.println(user.getRowNum() + "导入成功");
            }
        }else {
            // 导出数据
            ExcelUtils.export(response,"C07错误提示", users ,CustomerContractBasicInformation.class);
            for (CustomerContractBasicInformation user : users) {
                System.out.println(user.getRowNum() + user.getRowTips() + "导入失败");
            }
        }
    }

    /**
     * CustomerFirstlevel
     * com.example.excel.entity.CustomerFirstlevel
     * @param response
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC08")
    @ResponseBody
    public void importClassC08(HttpServletResponse response,@RequestPart("file")MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<CustomerFirstlevel> users = ExcelUtils.readMultipartFile(file, CustomerFirstlevel.class);
        //创建一个集合来存放错误信息
        List<String> Customer = new ArrayList<>();
        //循环遍历向list中添加错误信息
        for (CustomerFirstlevel user : users) {
            Customer.add(user.getRowTips());
        }
        //移除list中所用空的信息
        Customer.removeAll(Collections.singleton(""));
        //如果list为空
        if (Customer.isEmpty()){
            //循环遍历导入数据库
            for (CustomerFirstlevel user : users) {
                customerFirstlevelService.insertCustomerFirstlevel(user);
                System.out.println(user.getRowNum() + "导入成功");
            }
        }else {
            // 导出数据
            ExcelUtils.export(response,"C08错误提示", users ,CustomerFirstlevel.class);
            for (CustomerFirstlevel user : users) {
                System.out.println(user.getRowNum() + user.getRowTips() + "导入失败");
            }
        }
    }

    /**
     * PayrollInitialization
     * com.example.excel.entity.PayrollInitialization
     * @param response
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC016")
    @ResponseBody
    public void importClassC016(HttpServletResponse response,@RequestPart("file")MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<PayrollInitialization> users = ExcelUtils.readMultipartFile(file, PayrollInitialization.class);
        //创建一个集合来存放错误信息
        List<String> Customer = new ArrayList<>();
        //循环遍历向list中添加错误信息
        for (PayrollInitialization user : users) {
            Customer.add(user.getRowTips());
        }
        //移除list中所用空的信息
        Customer.removeAll(Collections.singleton(""));
        //如果list为空
        if (Customer.isEmpty()){
            //循环遍历导入数据库
            for (PayrollInitialization user : users) {
                payrollInitializationService.insertPayrollInitialization(user);
                System.out.println(user.getRowNum() + "导入成功");
            }
        }else {
            // 导出数据
            ExcelUtils.export(response,"C016错误提示", users ,PayrollInitialization.class);
            for (PayrollInitialization user : users) {
                System.out.println(user.getRowNum() + user.getRowTips() + "导入失败");
            }
        }
    }






}

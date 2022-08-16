package com.example.excel.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.excel.entity.*;
import com.example.excel.service.*;
import com.example.excel.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
     * 解析为对象
     * @param file
     * @throws Exception
     */
    @PostMapping("/classC01")
    @ResponseBody
    public void importClassC01(@RequestPart("file")MultipartFile file) throws Exception {
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
            //循环遍历输出行数和错误信息
            for (BasiInformation user : users) {
                System.out.println(user.getRowNum() + user.getRowTips() );
            }
        }
    }

    @PostMapping("/classC02")
    @ResponseBody
    public void importClassC02(@RequestPart("file")MultipartFile file) throws Exception {

        Map<String, JSONArray> map = ExcelUtils.readFileManySheet(file);
        JSONArray CustomerInformation = map.get("客户成本往来设定表");
        JSONArray CustomerBillGenerationConditions =  map.get("客户账单生成条件");

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
            //循环遍历输出行数和错误信息
            for (CustomerInformation user : users) {
                System.out.println("客户成本往来设定表" + user.getRowNum() + user.getRowTips());
            }
        }
        System.out.println("-----------------------------------------------------");
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
            //循环遍历输出行数和错误信息
            for (CustomerBillGenerationConditions user : CustomerBill) {
                System.out.println("客户账单生成条件" + user.getRowNum() + user.getRowTips());
            }
        }





    }

    @PostMapping("/classC03")
    @ResponseBody
    public void importClassC03(@RequestPart("file")MultipartFile file) throws Exception {
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
            //循环遍历输出行数和错误信息
            for (CustomerSettlementUnit user : users) {
                System.out.println(user.getRowNum() + user.getRowTips());
            }
        }
    }

    @PostMapping("/classC04")
    @ResponseBody
    public void importClassC04(@RequestPart("file")MultipartFile file) throws Exception {
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
            //循环遍历输出行数和错误信息
            for (SubCustomerInformation user : users) {
                System.out.println(user.getRowNum() + user.getRowTips());
            }
        }
    }

    @PostMapping("/classC05")
    @ResponseBody
    public void importClassC05(@RequestPart("file")MultipartFile file) throws Exception {
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
            //循环遍历输出行数和错误信息
            for (ServiceagreementBasicinformation user : users) {
                System.out.println("第" + user.getRowNum() +"行："+ user.getRowTips());
            }
        }
    }


    @PostMapping("/classC06")
    @ResponseBody
    public void importClassC06(@RequestPart("file")MultipartFile file) throws Exception {
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
            //循环遍历输出行数和错误信息
            for (ServiceAgreementItems user : users) {
                System.out.println("第" + user.getRowNum() +"行："+ user.getRowTips());
            }
        }
    }



    @PostMapping("/classC07")
    @ResponseBody
    public void importClassC07(@RequestPart("file")MultipartFile file) throws Exception {
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
//                customerContractBasicInformationService.insertCustomerContractBasicInformation(user);
                System.out.println(user.getRowNum() + "导入成功");
            }
        }else {
            //循环遍历输出行数和错误信息
            for (CustomerContractBasicInformation user : users) {
                System.out.println("第" + user.getRowNum() +"行："+ user.getRowTips());
            }
        }
    }



}

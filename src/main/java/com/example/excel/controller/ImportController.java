package com.example.excel.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerInformation;
import com.example.excel.entity.CustomerSettlementUnit;
import com.example.excel.entity.SubCustomerInformation;
import com.example.excel.service.BasiInformationService;
import com.example.excel.service.CustomerInformationService;
import com.example.excel.service.CustomerSettlementUnitService;
import com.example.excel.service.SubCustomerInformationService;
import com.example.excel.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

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
        //获取处理完Excel的数据
        List<CustomerInformation> users = ExcelUtils.readMultipartFile(file, CustomerInformation.class);
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
                System.out.println(user.getRowNum() + "导入成功");
            }
        }else {
            //循环遍历输出行数和错误信息
            for (CustomerInformation user : users) {
                System.out.println(user.getRowNum() + user.getRowTips());
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


}

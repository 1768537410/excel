package com.example.excel.controller;

import com.example.excel.entity.*;
import com.example.excel.util.ExcelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("export")
public class ExportController {

    @PostMapping("/classC01")
    @ResponseBody
    public void importClassC01(HttpServletResponse response, @RequestPart("file") MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<BasiInformation> users = ExcelUtils.readMultipartFile(file,BasiInformation.class);
        // 导出数据
        ExcelUtils.export(response,"C01错误提示", users ,BasiInformation.class);

    }
    @PostMapping("/classC02")
    @ResponseBody
    public void importClassC02(HttpServletResponse response, @RequestPart("file") MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<CustomerInformation> users = ExcelUtils.readMultipartFile(file,CustomerInformation.class);
        // 导出数据
        ExcelUtils.export(response,"C02错误提示", users ,CustomerInformation.class);

    }
    @PostMapping("/classC0202")
    @ResponseBody
    public void importClassC0202(HttpServletResponse response, @RequestPart("file") MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<CustomerBillGenerationConditions> users = ExcelUtils.readMultipartFile(file,CustomerBillGenerationConditions.class);
        // 导出数据
        ExcelUtils.export(response,"C0202错误提示", users ,CustomerBillGenerationConditions.class);

    }
    @PostMapping("/classC03")
    @ResponseBody
    public void importClassC03(HttpServletResponse response, @RequestPart("file") MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<CustomerSettlementUnit> users = ExcelUtils.readMultipartFile(file,CustomerSettlementUnit.class);
        // 导出数据
        ExcelUtils.export(response,"C03错误提示", users ,CustomerSettlementUnit.class);

    }
    @PostMapping("/classC04")
    @ResponseBody
    public void importClassC04(HttpServletResponse response, @RequestPart("file") MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<SubCustomerInformation> users = ExcelUtils.readMultipartFile(file,SubCustomerInformation.class);
        // 导出数据
        ExcelUtils.export(response,"C04错误提示", users ,SubCustomerInformation.class);

    }
    @PostMapping("/classC05")
    @ResponseBody
    public void importClassC05(HttpServletResponse response, @RequestPart("file") MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<ServiceagreementBasicinformation> users = ExcelUtils.readMultipartFile(file,ServiceagreementBasicinformation.class);
        // 导出数据
        ExcelUtils.export(response,"C05错误提示", users ,ServiceagreementBasicinformation.class);

    }
    @PostMapping("/classC06")
    @ResponseBody
    public void importClassC06(HttpServletResponse response, @RequestPart("file") MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<ServiceAgreementItems> users = ExcelUtils.readMultipartFile(file,ServiceAgreementItems.class);
        // 导出数据
        ExcelUtils.export(response,"C06错误提示", users ,ServiceAgreementItems.class);

    }
    @PostMapping("/classC07")
    @ResponseBody
    public void importClassC07(HttpServletResponse response, @RequestPart("file") MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<CustomerContractBasicInformation> users = ExcelUtils.readMultipartFile(file,CustomerContractBasicInformation.class);
        // 导出数据
        ExcelUtils.export(response,"C07错误提示", users ,CustomerContractBasicInformation.class);

    }
    @PostMapping("/classC08")
    @ResponseBody
    public void importClassC08(HttpServletResponse response, @RequestPart("file") MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<CustomerFirstlevel> users = ExcelUtils.readMultipartFile(file,CustomerFirstlevel.class);
        // 导出数据
        ExcelUtils.export(response,"C08错误提示", users ,CustomerFirstlevel.class);

    }
    @PostMapping("/classC016")
    @ResponseBody
    public void importClassC016(HttpServletResponse response, @RequestPart("file") MultipartFile file) throws Exception {
        //获取处理完Excel的数据
        List<PayrollInitialization> users = ExcelUtils.readMultipartFile(file,PayrollInitialization.class);
        // 导出数据
        ExcelUtils.export(response,"C016错误提示", users ,PayrollInitialization.class);

    }




}

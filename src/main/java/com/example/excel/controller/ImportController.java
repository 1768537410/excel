package com.example.excel.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerInformation;
import com.example.excel.mapper.BasiInformationMapper;
import com.example.excel.service.BasiInformationService;
import com.example.excel.service.CustomerInformationService;
import com.example.excel.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("import")
public class ImportController {

    @Autowired
    private BasiInformationService basiInformationService;

    @Autowired
    private CustomerInformationService customerInformationService;
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
        List<BasiInformation> users = ExcelUtils.readMultipartFile(file,BasiInformation.class);
        for (BasiInformation user : users) {
        if (user.getRowTips().length() == 0){
            System.out.println(user.toString());
            basiInformationService.insertBasiInformation(user);
        }else {
            System.out.println(user.getRowTips());
        }
        }
    }

    @PostMapping("/classC02")
    @ResponseBody
    public void importClassC02(@RequestPart("file")MultipartFile file) throws Exception {
        List<CustomerInformation> users = ExcelUtils.readMultipartFile(file, CustomerInformation.class);
        for (CustomerInformation user : users) {
            if (user.getRowTips().length() == 0){

            }else {
                System.out.println(user.getRowTips());
            }
        }
    }
}

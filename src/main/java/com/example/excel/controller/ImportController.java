package com.example.excel.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.excel.entity.BasiInformation;
import com.example.excel.util.ExcelUtils;
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
    @PostMapping("/class")
    @ResponseBody
    public void importClass(@RequestPart("file")MultipartFile file) throws Exception {
        List<BasiInformation> users = ExcelUtils.readMultipartFile(file,BasiInformation.class);
        for (BasiInformation user : users) {
            System.out.println(user.toString());
            System.out.println(user.getRowTips());
        }
    }
}

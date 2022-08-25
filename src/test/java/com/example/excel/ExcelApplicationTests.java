package com.example.excel;

import com.example.excel.entity.BasiInformation;
import com.example.excel.service.BasiInformationService;
import com.example.excel.service.CustomerInformationService;
import com.example.excel.util.ExcelUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
@Component
class ExcelApplicationTests {

    @Autowired
    private BasiInformationService basiInformationService;
    private static BasiInformationService basiInformationServices;

//    @Autowired
//    private CustomerInformationService customerInformationService;
//    private static CustomerInformationService customerInformationServices;

    //    public ExcelApplicationTests(){}
//    public static ExcelApplicationTests excelApplicationTests;
    @PostConstruct
    public void init() {
        basiInformationServices = basiInformationService;
        System.out.println("basiInformationService");
    }

    @Test
    void contextLoads() {
    }

    @Test
    public boolean test() {

        return true;
    }

    public static void main(String[] args) throws Exception {
//            // 创建一个 HashMap
//            HashMap<Integer, String> sites = new HashMap<>();
//
//            // 往 HashMap 添加一些元素
//            sites.put(1, "Google--Runoob");
//            sites.put(2, "Runoob--adc--23a");
//            sites.put(3, "Taobao--Runoob--sadc");
//            System.out.println("sites HashMap: " + sites);
//
//            //检查映射中值value是否有Java
//            if(sites.containsValue("Runoob--Google")) {
//                System.out.println("Runoob 存在于 sites 中");
//            }


//            String keyWord = "中国邮政集团有限公司长春市分公司";
//            int lastIndex=keyWord.lastIndexOf("省");//字符串第一个字符最后出现的下标
//            if(lastIndex==-1) {
//                System.out.println("不存在字符串 省");
//            }else {
//                System.out.println("字符串省最后一次出现的位置："+lastIndex);
//            }


//            String str = "业务二部专员2";
//            boolean status = str.contains("专员");
//            if(status){
//                System.out.println("包含");
//            }else{
//                System.out.println("不包含");
//            }

//            //1、验证 yyyy-MM-dd HH:mm:dd 格式的日期
//            String date = "2020-01-25 12:36:45";
//            System.out.println("date "+isLegalDate(date.length(),date,"yyyy-MM-dd HH:mm:ss"));
//
//            //2、验证 yyyy-MM-dd 格式的日期
//            String yearMonthday = "2020-01-01";
//            System.out.println("yearMonthday: "+isLegalDate(yearMonthday.length(),yearMonthday,"yyyy-MM-dd"));
//
//            //3、验证 yyyy-MM 格式的日期
//            String yearMonth = "2020-02";
//            System.out.println("yearMonth: "+isLegalDate(yearMonth.length(),yearMonth,"yyyy-MM"));
//
//            //4、验证 yyyy 格式的日期
//            String year = "2020";
//            System.out.println("year: "+isLegalDate(year.length(),year,"yyyy"));
//
//            //5、验证 HH:mm:ss 格式的日期
//            String hms = "12:36:89";
//            System.out.println("hms: "+isLegalDate(hms.length(),hms,"HH:mm:ss"));

//            BasiInformation basiInformation = basiInformationServices.findBasiInformationByCustomerAbbreviation("长春市邮政局");
//            basiInformation.getCustomerName();
//            System.out.println(basiInformation.getCustomerName());


//        int i1 = 1;
//        int i2 = 1;
//        // true||true && false  执行
//        if ((i1 == 1) || (i1 == 1) && (i2 == 2)) {
//            System.out.println("||,&& 执行");
//        }
//        // true | true & false   执行
//        if ((i1 == 1) | (i1 == 1) & (i2 == 2)) {
//            System.out.println("|,&执行");
//        }
//        // true && false | true   执行
//        if ((i1 == 1) && (i2 == 2) | (i1 == 1)) {
//            System.out.println("&& |执行");
//        }
//        // true & false || true   执行
//        if ((i1 == 1) & (i2 == 2) || (i1 == 1)) {
//            System.out.println("& ||执行");
//        }

//        List<String> list = new ArrayList();
//        list.add("首签");
//        list.add("新签");
//        list.add("续签");
//        if (list.contains("签") || list.contains("首")){
//                System.out.println("没新签首签");
//
//        }
//        //服务项目
//        String Is = "薪资核算及发放";
//        if (Is.equals("薪资核算") || Is.equals("薪资核算及发放")) {
//            System.out.println("1");
//            if (!list.contains("按人")){
//                System.out.println(true);
//            }
//        }else {
//            System.out.println(false);
//
//}

//        String SIZE = "薪资核算与发放";
//        if (SIZE.contains("薪资核算") || SIZE.contains("薪资核算与发放")){
//            System.out.println(true);
//        }else {
//            System.out.println(false);
//        }


//        String val = "1231241.12";
//        String[] Split = val.split("\\.");
//        System.out.println("Split长度:" + Split.length);
////        System.out.println(Split[1]);
//        int num = 0;
//        if (Split.length > 1 && !Split[1].isEmpty()) {
//            for (int i = 0; i < Split[1].length(); i++) {
//                num++;
//            }
//            System.out.println("num : " + num);
//            if (num > 2) {
//                System.out.println("不对");
//            }
//        }
//        long time = System.currentTimeMillis();//获取当前时间精确到毫秒级的时间戳，例：1525849325942
//        System.out.println(System.currentTimeMillis());
//        System.out.println(timeStamp2Date("39813"));

//        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
//        String time = "2008-12-31";
//        Date date = null;
//        try {
//            date = sdf.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long time1 = date.getTime();
//        System.out.println("时间戳格式："+time1);
//        //输出：时间戳格式：1602384121000


        //设置时间格式，将该时间格式的时间转换为时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("39813");
        long time = date.getTime();
         String res = String.valueOf(time);
        System.out.println(res);
        }

    public static String dateToStamp(String s) throws Exception {
        String res;
        //设置时间格式，将该时间格式的时间转换为时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long time = date.getTime();
        res = String.valueOf(time);
        return res;

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

    public static String timeStamp2Date(String time) {
        Long timeLong = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//要转换的时间格式
        return sdf.format(timeLong);
    }
}

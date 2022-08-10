package com.example.excel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class ExcelApplicationTests {

    @Test
    void contextLoads() {
    }

        public static void main(String[] args) {
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

            //1、验证 yyyy-MM-dd HH:mm:dd 格式的日期
            String date = "2020-01-25 12:36:45";
            System.out.println("date "+isLegalDate(date.length(),date,"yyyy-MM-dd HH:mm:ss"));

            //2、验证 yyyy-MM-dd 格式的日期
            String yearMonthday = "2020-01-01";
            System.out.println("yearMonthday: "+isLegalDate(yearMonthday.length(),yearMonthday,"yyyy-MM-dd"));

            //3、验证 yyyy-MM 格式的日期
            String yearMonth = "2020-02";
            System.out.println("yearMonth: "+isLegalDate(yearMonth.length(),yearMonth,"yyyy-MM"));

            //4、验证 yyyy 格式的日期
            String year = "2020";
            System.out.println("year: "+isLegalDate(year.length(),year,"yyyy"));

            //5、验证 HH:mm:ss 格式的日期
            String hms = "12:36:89";
            System.out.println("hms: "+isLegalDate(hms.length(),hms,"HH:mm:ss"));



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
}

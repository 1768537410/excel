package com.example.excel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
@MapperScan("com.example.excel.mapper")
public class ExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelApplication.class, args);
    }

}

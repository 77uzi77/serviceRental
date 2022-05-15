package com.lzc.serviceRental;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement    //启动事务
@MapperScan(basePackages = "com.lzc.serviceRental.mapper")
public class ServiceRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRentalApplication.class, args);
    }

}

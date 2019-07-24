package com.hao.logstarter;

import com.hao.commonunits.CommonModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogStarterApplication {

    public static void main(String[] args) {
        CommonModel commonModel = new CommonModel();
        SpringApplication.run(LogStarterApplication.class, args);
    }

}

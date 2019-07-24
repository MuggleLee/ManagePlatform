package com.hao.commonunits;

import com.hao.commonmodel.Model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonUnitsApplication {

    public static void main(String[] args) {
        User user = new User();
        SpringApplication.run(CommonUnitsApplication.class, args);
    }

}

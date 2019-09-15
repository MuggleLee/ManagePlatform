package com.hao.usercenter;

import com.hao.commonmodel.User.AppUser;
import com.hao.usercenter.Service.Impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserserviceApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void UserTest() {
//        AppUser appUser = new AppUser();
//        appUser.setId(12L);
//        appUser.setUsername("hao");
//        appUser.setPassword("123456");
//        appUser.setEnabled(Boolean.TRUE);
//        appUser.setType("hao");
//        userService.updateAppUser(appUser);
//        Set set = new TreeSet();
//        set.add("1");
//        set.add("2");
//        set.add("3");
//        String setString = set.toString();
//        System.out.println(setString.substring(1,setString.length()-1));
        System.out.println("Test: " + userService.findByUserName("MuggleLee").toString());
    }
}

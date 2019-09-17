package com.hao.notificationcenter;

import com.hao.notificationcenter.service.VerificationCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationCenterApplicationTests {

    @Autowired
    VerificationCodeService verificationCodeService;

    @Test
    public void test() {
        System.out.println(verificationCodeService.generateCode("18320628585").toString());
    }

}

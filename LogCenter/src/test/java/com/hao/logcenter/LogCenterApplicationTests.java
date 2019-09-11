package com.hao.logcenter;

import com.hao.commonmodel.Log.Log;
import com.hao.logcenter.Mapper.LogMapper;
import com.hao.logcenter.Service.LogService;
import com.hao.logcenter.Service.impl.LogServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogCenterApplicationTests {

//    @Autowired
//    private LogService service;

    @Test
    public void saveLog(){
        Log log = new Log();
        log.setUsername("MuggleLee");
        log.setModule("登陆");
        log.setFlag(true);
        log.setCreateTime(new Date());
        LogServiceImpl service = new LogServiceImpl();
//        service.saveLog(log);
//        service.saveLog(log);
//        boolean flag = log.insert();
//        System.out.println(flag);
    }

}

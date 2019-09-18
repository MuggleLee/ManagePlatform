package com.hao.logcenter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hao.logcenter.service.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogCenterApplicationTests {

    @Autowired
    private LogService logService;

    @Test
    public void test(){
        Map map = new HashMap();
        map.put("draw","4");
        IPage iPage = logService.findLogs(map);
        System.out.println(iPage);
    }
}

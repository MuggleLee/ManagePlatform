package com.hao.managebackend;

import com.hao.managebackend.model.Menu;
import com.hao.managebackend.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageBackendApplicationTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void test() {
        Set<Long> set = new TreeSet();
        set.add(1L);
//        set.add(2L);
//        set.add(3L);
        menuService.findByRoles(set);
    }

}

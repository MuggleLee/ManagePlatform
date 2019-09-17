package com.hao.usercenter;

import com.hao.commonmodel.user.SysPermission;
import com.hao.usercenter.service.SysPermissionService;
import com.hao.usercenter.service.SysRoleService;
import com.hao.usercenter.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserserviceApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    SysPermissionService sysPermissionService;

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void UserTest() throws IllegalAccessException {
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
//
//        Set set2 = new TreeSet();
//        set2.add("2");
//        set2.add("3");
//        set2.add("4");
//        System.out.println(CollectionUtils.union(set,set2));
//        String setString = set.toString();
//        System.out.println(setString.substring(1,setString.length()-1));
//        System.out.println("Test: " + userService.findByUserName("MuggleLee").toString());

//        System.out.println("findById():   " + userService.findById(3));

//        Set<Integer> set = new TreeSet<>();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//        userService.setRoleToUser(2,set);

//        userService.updatePassword(1L,"666666","123456");
//        userService.bindingPhone(1L,"18320628585");
//        System.out.println(sysPermissionService.findByRoleIds(set));
//        System.out.println(sysPermissionService.findByRoleIds(set).size());

//        SysPermission sysPermission = new SysPermission();
//        sysPermission.setId(36L);
//        sysPermission.setName("Test1");
//        sysPermission.setPermission("Test1");
//        sysPermissionService.update(sysPermission);
//        sysPermissionService.delete(36L);
//        sysRoleService.setPermissionToRole(1L,null);
        Set<Long> set = new TreeSet();
        set.add(36L);
        set.add(37L);
        sysRoleService.setPermissionToRole(2L,set);
//        sysRoleService.findPermissionsByRoleId(1L);
    }
}

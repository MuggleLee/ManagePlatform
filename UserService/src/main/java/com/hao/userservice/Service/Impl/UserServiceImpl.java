package com.hao.userservice.Service.Impl;

import com.hao.commonmodel.Model.User.AppUser;
import com.hao.commonmodel.Model.User.LoginAppUser;
import com.hao.commonmodel.Model.User.SysRole;
import com.hao.userservice.Dao.AppUserDao;
import com.hao.userservice.Dao.UserCredentialsDao;
import com.hao.userservice.Dao.UserRoleDao;
import com.hao.userservice.Service.SysPermissionService;
import com.hao.userservice.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @author MuggleLee
 * @date 2019/7/21
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppUserDao appUserDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserCredentialsDao userCredentialsDao;

    @Override
    public void addAppUser(AppUser appUser) {

    }

    @Override
    public void updateAppUser(AppUser appUser) {

    }

    @Override
    public LoginAppUser findByUserName(String userName) {
        return null;
    }

    @Override
    public AppUser findById(Long id) {
        return null;
    }

    @Override
    public void setRoletToUser(Long id, Set<Long> roleIds) {

    }

    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {

    }

    @Override
    public Page<AppUser> findUsers(Map<String, Object> params) {
        return null;
    }

    @Override
    public Set<SysRole> findRolesByUserId(Long id) {
        return null;
    }

    @Override
    public void bindingPhone(Long userId, String phone) {

    }
}

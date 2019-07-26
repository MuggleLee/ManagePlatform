package com.hao.userservice.Service.Impl;

import com.hao.commonmodel.Model.User.AppUser;
import com.hao.commonmodel.Model.User.LoginAppUser;
import com.hao.commonmodel.Model.User.SysRole;
import com.hao.commonmodel.Model.User.UserCredential;
import com.hao.commonmodel.Model.User.constants.CredentialType;
import com.hao.commonmodel.Model.User.constants.UserType;
import com.hao.commonunits.utils.PhoneUtil;
import com.hao.userservice.Dao.AppUserDao;
import com.hao.userservice.Dao.UserCredentialsDao;
import com.hao.userservice.Dao.UserRoleDao;
import com.hao.userservice.Service.SysPermissionService;
import com.hao.userservice.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
//    @Autowired
//    private SysPermissionService sysPermissionService;
//    @Autowired
//    private UserRoleDao userRoleDao;
    @Autowired
    private UserCredentialsDao userCredentialsDao;

    @Override
    public void addAppUser(AppUser appUser) {
        String username = appUser.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        if (PhoneUtil.checkPhone(appUser.getPhone())) {
            throw new IllegalArgumentException("手机号码不正确");
        }

        if (username.contains("@")) {
            throw new IllegalArgumentException("用户名不能包含@");
        }

        if (username.contains("|")) {
            throw new IllegalArgumentException("用户名不能包含|字符");
        }

        if (StringUtils.isBlank(appUser.getPassword())) {
            throw new IllegalArgumentException("密码不能为空");
        }

        if (StringUtils.isBlank(appUser.getNickname())) {
            appUser.setNickname(username);
        }

        if (StringUtils.isBlank(appUser.getType())) {
            appUser.setType(UserType.APP.name());
        }

        UserCredential userCredential = userCredentialsDao.findByUsername(appUser.getUsername());
        if (userCredential != null) {
            throw new IllegalArgumentException("用户名已存在");
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword())); // 加密密码
        appUser.setEnabled(Boolean.TRUE);
        appUser.setCreateTime(new Date());
        appUser.setUpdateTime(appUser.getCreateTime());

        appUserDao.save(appUser);
        userCredentialsDao
                .save(new UserCredential(appUser.getUsername(), CredentialType.USERNAME.name(), appUser.getId()));
        log.info("添加用户：{}", appUser);
    }

    @Override
    public void updateAppUser(AppUser appUser) {

    }

    @Override
    public AppUser findByUserName(String userName) {
        return appUserDao.findByUsername(userName);
    }

    @Override
    public AppUser findById(Long id) {
        return appUserDao.findById(id);
    }

    @Override
    public void setRoletToUser(Long id, Set<Long> roleIds) {

    }

    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        AppUser appUser = appUserDao.findById(id);
        if(StringUtils.isNoneBlank(oldPassword)){
            if(!passwordEncoder.matches(oldPassword,appUser.getPassword())){
                throw new IllegalArgumentException("旧密码错误");
            }
        }

        AppUser user = new AppUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setId(id);
        appUserDao.update(user);
        log.info("修改密码：{}", user);
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

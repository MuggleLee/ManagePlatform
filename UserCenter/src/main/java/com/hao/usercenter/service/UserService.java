package com.hao.usercenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.commonmodel.user.*;
import com.hao.commonmodel.user.constants.CredentialType;
import com.hao.commonmodel.user.constants.UserType;
import com.hao.commonunits.utils.PhoneUtil;
import com.hao.usercenter.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MuggleLee
 * @date 2019/7/21 实现基本功能
 * @update 2019/9/16 优化：将原本 Mybatis 框架升级为 Mybatis-Plus
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private UserCredentialMapper userCredentialMapper;

    @Transactional
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

        // 查询是否已存在用户名
        UserCredential userCredential = new UserCredential().selectOne(new QueryWrapper<UserCredential>().eq("username", appUser.getUsername()));
        if (userCredential != null) {
            throw new IllegalArgumentException("用户名已存在");
        }

        // 加密密码
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setEnabled(Boolean.TRUE);
        appUser.setCreateTime(new Date());
        appUser.setUpdateTime(appUser.getCreateTime());

        appUser.insert();
        UserCredential.builder()
                .username(username)
                .type(CredentialType.USERNAME.name())
                .userId(appUser.getId())
                .build()
                .insert();
        log.info("添加用户：{}", appUser);
    }

    @Transactional
    public void updateAppUser(AppUser appUser) {
        appUser.setUpdateTime(new Date());
        appUser.updateById();
        log.info("修改用户：{}", appUser);
    }

    /**
     * @param userName 用户名 or 微信openid or 手机号码
     * @return
     */
    @Transactional
    public LoginAppUser findByUserName(String userName) {
        // 微信登录
        WechatUserInfo wechatUserInfo = new WechatUserInfo().selectOne(new QueryWrapper<WechatUserInfo>().eq("openid", userName));
        if (wechatUserInfo != null) {
            UserCredential userCredential = new UserCredential().selectOne(new QueryWrapper<UserCredential>().eq("userName", userName));
            Long userId = userCredential.getUserId();
            AppUser appUser = new AppUser().selectOne(new QueryWrapper<AppUser>().eq("id", userId));
            userName = appUser.getUsername();
        }
        // 手机号码登录
        if (PhoneUtil.checkPhone(userName)) {
            AppUser appUser = new AppUser().selectOne(new QueryWrapper<AppUser>().eq("phone", userName));
            userName = appUser.getUsername();
        }

        AppUser appUser = new AppUser().selectOne(new QueryWrapper<AppUser>().eq("username", userName));
        if (appUser != null) {
            LoginAppUser loginAppUser = new LoginAppUser();
            BeanUtils.copyProperties(appUser, loginAppUser);
            long roleId = 1;
            if(appUser.getId()>1){
                roleId = 2;
            }
            Set<SysRole> sysRoles = findRolesByUserId(roleId);
            //设置角色
            loginAppUser.setSysRoles(sysRoles);
            if (!CollectionUtils.isEmpty(sysRoles)) {
                Set<Long> roleIds = sysRoles.parallelStream().map(SysRole::getId).collect(Collectors.toSet());
                Set<SysPermission> sysPermissions = sysPermissionMapper.findByRoleIds(new QueryWrapper<SysRolePermission>().in("roleId", roleIds));
                if (!CollectionUtils.isEmpty(sysPermissions)) {
                    Set<String> permissions = sysPermissions.parallelStream().map(SysPermission::getPermission).collect(Collectors.toSet());
                    loginAppUser.setPermissions(permissions);
                }
            }
            log.info("LoginAppUser：{}", loginAppUser);
            return loginAppUser;
        }
        return null;
    }

    public AppUser findById(Long id) {
        return appUserMapper.selectOne(new QueryWrapper<AppUser>().eq("id", id));
    }

    @Transactional
    public void setRoleToUser(Long id, Set<Integer> roleIds) {
        AppUser appUser = findById(id);
        if (appUser == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        // 删除用户旧有的角色，重新将更新后的角色保存到数据库
        sysRoleUserMapper.delete(new QueryWrapper<SysRoleUser>().eq("userId", id));
        if (!CollectionUtils.isEmpty(roleIds)) {
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserId(id);
            roleIds.forEach(roleId -> {
                sysRoleUser.setRoleId(roleId);
                sysRoleUser.insert();
            });
        }
        log.info("修改用户：{}的角色，{}", appUser.getUsername(), roleIds);
    }

    public void updatePassword(Long id, String oldPassword, String newPassword) {
        AppUser appUser = findById(id);
        if (StringUtils.isNoneBlank(oldPassword)) {
            if (!passwordEncoder.matches(oldPassword, appUser.getPassword())) {
                throw new IllegalArgumentException("旧密码错误");
            }
        }
        AppUser user = AppUser.builder()
                .id(id)
                .password(passwordEncoder.encode(newPassword))
                .updateTime(new Date())
                .build();
        user.updateById();
        log.info("修改密码：{}", user);
    }

    public IPage<AppUser> findUsers(Map<String, Object> params) {
        // 获取页面传来的页数
        long pageNum = null == params.get("draw") ? 1 : Long.valueOf(params.get("draw").toString());
        Page<AppUser> page = new Page<>(pageNum, 10);
        IPage<AppUser> iPage = new AppUser().selectPage(page, null);
        return iPage;
    }

    public Set<SysRole> findRolesByUserId(Long id) {
        return sysRoleMapper.getSysRoles(new QueryWrapper<SysRole>().eq("id", id));
    }

    @Transactional
    public void bindingPhone(Long userId, String phone) {
        // 根据参数 phone 查询 user_credential 表是否已绑定手机号
        UserCredential userCredential = userCredentialMapper.selectOne(new QueryWrapper<UserCredential>().eq("username", phone));
        if (userCredential != null) {
            throw new IllegalArgumentException("手机号已被绑定");
        }
        // 根据 userId 绑定手机号
        AppUser.builder().id(userId).phone(phone).build().updateById();
        log.info("绑定手机号成功,UserId:{}，phone:{}", userId, phone);
        // 绑定成功后，将手机号存到用户凭证表，后续可通过手机号+密码或者手机号+短信验证码登陆
        userCredentialMapper.insert(UserCredential.builder().userId(userId).type(CredentialType.PHONE.name()).username(phone).build());
    }
}

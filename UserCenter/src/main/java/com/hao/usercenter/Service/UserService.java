package com.hao.usercenter.Service;

import com.hao.commonmodel.User.AppUser;
import com.hao.commonmodel.User.LoginAppUser;
import com.hao.commonmodel.User.SysRole;
import com.hao.commonmodel.Common.Page;

import java.util.Map;
import java.util.Set;

/**
 * Created by hao on 2019/7/21.
 */
public interface UserService {

    void addAppUser(AppUser appUser);

    void updateAppUser(AppUser appUser);

    LoginAppUser findByUserName(String userName);

    AppUser findById(Long id);

    void setRoleToUser(Long id, Set<Long> roleIds);

    void updatePassword(Long id, String oldPassword, String newPassword);

    Page<AppUser> findUsers(Map<String, Object> params);

    Set<SysRole> findRolesByUserId(Long id);

    void bindingPhone(Long userId, String phone);
}

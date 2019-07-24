package com.hao.userservice.Service;

import com.hao.commonmodel.Model.User.AppUser;
import com.hao.commonmodel.Model.User.LoginAppUser;
import com.hao.commonmodel.Model.User.SysRole;
import org.springframework.data.domain.Page;

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

    void setRoletToUser(Long id, Set<Long> roleIds);

    void updatePassword(Long id, String oldPassword, String newPassword);

    Page<AppUser> findUsers(Map<String, Object> params);

    Set<SysRole> findRolesByUserId(Long id);

    void bindingPhone(Long userId, String phone);
}

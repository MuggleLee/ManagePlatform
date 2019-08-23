package com.hao.usercenter.Service;

import com.hao.commonmodel.User.SysPermission;
import com.hao.commonmodel.Common.Page;

import java.util.Map;
import java.util.Set;

/**
 * @author MuggleLee
 * @date 2019/7/22
 */
public interface SysPermissionService {

    Set<SysPermission> findByRoleIds(Set<Long> roleIds);

    void save(SysPermission sysPermission) throws IllegalAccessException;

    void update(SysPermission sysPermission);

    void delete(Long id);

    Page<SysPermission> findPermissions(Map<String, Object> params);
}

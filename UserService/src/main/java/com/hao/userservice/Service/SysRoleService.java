package com.hao.userservice.Service;


import com.hao.commonmodel.User.SysPermission;
import com.hao.commonmodel.User.SysRole;
import com.hao.commonmodel.Common.Page;

import java.util.Map;
import java.util.Set;

public interface SysRoleService {

    void save(SysRole sysRole);

    void update(SysRole sysRole);

    void deleteRole(Long id);

    void setPermissionToRole(Long id, Set<Long> permissionIds);

    SysRole findById(Long id);

    Page<SysRole> findRoles(Map<String, Object> params);

    Set<SysPermission> findPermissionsByRoleId(Long roleId);
}

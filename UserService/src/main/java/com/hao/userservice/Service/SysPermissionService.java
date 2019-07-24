package com.hao.userservice.Service;

import com.hao.commonmodel.Model.User.SysPermission;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Set;

/**
 * @author MuggleLee
 * @date 2019/7/22
 */
public interface SysPermissionService {

    Set<SysPermission> findByRoleIds(Set<Long> roleIds);

    void save(SysPermission sysPermission);

    void update(SysPermission sysPermission);

    void delete(Long id);

    Page<SysPermission> findPermissions(Map<String, Object> params);
}

package com.hao.userservice.Dao;

import com.hao.commonmodel.User.SysPermission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * 角色权限关系
 * 角色和权限是多对多关系，sys_role_permission是中间表
 */
@Mapper
public interface RolePermissionDao {

    @Insert("insert into sys_role_permission(roleId, permissionId) values(#{roleId}, #{permissionId})")
    int saveRolePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    int deleteRolePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    Set<SysPermission> findPermissionsByRoleIds(@Param("roleIds") Set<Long> roleIds);

}

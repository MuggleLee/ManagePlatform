package com.hao.usercenter.Mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.hao.commonmodel.User.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {


//    /**
//     * 多表联查
//     * 根据 roleIds 查询中间表 sys_role_permission 所有该 roleIds 的所有权限
//     * @param roleIds
//     * @return
//     */
    @Select("select * from sys_role_permission srp join sys_permission sp on srp.permissionId = sp.id ${ew.customSqlSegment}")
    Set<SysPermission> findByRoleIds(@Param(Constants.WRAPPER) Wrapper wrapper);
}

package com.hao.usercenter.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.hao.commonmodel.user.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author Muggle Lee
 * @Date: 2019/9/15 22:33
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select r.* from sys_role_user ru inner join sys_role r on r.id = ru.roleId ${ew.customSqlSegment}")
    Set<SysRole> getSysRoles(@Param(Constants.WRAPPER) Wrapper wrapper);

}

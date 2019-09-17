package com.hao.managebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.managebackend.model.RoleMenu;
import org.apache.ibatis.annotations.Insert;

import java.sql.Wrapper;

/**
 * @author Muggle Lee
 * @Date: 2019/9/17 17:26
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Insert("insert into role_menu (roleId,menuId) values ")
    int insertBatch(String sql);
}

package com.hao.managebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.managebackend.model.RoleMenu;
import com.hao.managebackend.service.MenuService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.sql.Wrapper;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

/**
 * @author Muggle Lee
 * @Date: 2019/9/17 17:26
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @InsertProvider(type = MenuService.class,method = "insert")
    int insertBatch(Long roleId,Set<Long> roleMenuIds);

}

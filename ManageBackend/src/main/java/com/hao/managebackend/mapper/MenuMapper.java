package com.hao.managebackend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.hao.managebackend.model.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author Muggle Lee
 * @Date: 2019/9/17 16:43
 */
public interface MenuMapper extends BaseMapper<Menu> {

    @Delete("DELETE rm ,m from menu m,role_menu rm where m.id = #{id} and rm.menuId = #{id}")
    void deleteByMenuId(Long id);

    @Select("select m.* from menu m join role_menu rm on m.id = rm.menuId ${ew.customSqlSegment}")
    List<Menu> findMenusByRoleIds(@Param(Constants.WRAPPER) Wrapper wrapper);
}

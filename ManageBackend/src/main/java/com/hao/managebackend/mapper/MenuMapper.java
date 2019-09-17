package com.hao.managebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.managebackend.model.Menu;
import org.apache.ibatis.annotations.Delete;

/**
 * @author Muggle Lee
 * @Date: 2019/9/17 16:43
 */
public interface MenuMapper extends BaseMapper<Menu> {

    @Delete("DELETE rm ,m from menu m,role_menu rm where m.id = #{id} and rm.menuId = #{id}")
    void deleteByMenuId(Long id);
}

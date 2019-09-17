package com.hao.managebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hao.managebackend.mapper.MenuMapper;
import com.hao.managebackend.mapper.RoleMenuMapper;
import com.hao.managebackend.model.Menu;
import com.hao.managebackend.model.RoleMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Transactional
    public void save(Menu menu) {
        menu.setCreateTime(new Date());
        menu.setUpdateTime(menu.getCreateTime());
        menu.insert();
        log.info("新增菜单：{}", menu);
    }

    @Transactional
    public void update(Menu menu) {
        menu.setUpdateTime(new Date());
        menu.updateById();
        log.info("修改菜单：{}", menu);
    }

    /**
     * 通过 roleId 删除角色菜单
     * 需要删除菜单表（menu）和中间表（role_menu）对应的数据
     */
    @Transactional
    public void delete(Long id) {
        Menu menu = menuMapper.selectOne(new QueryWrapper<Menu>().eq("id", id));
        menuMapper.deleteByMenuId(id);
        log.info("删除菜单：{}", menu);
    }

    /**
     * 给角色设置菜单<br>
     * 采用先删除后插入，这样更简单
     *
     * @param roleId
     * @param menuIds
     */
    @Transactional
    public void setMenuToRole(Long roleId, Set<Long> menuIds) {
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("roleId", roleId));
        if (!CollectionUtils.isEmpty(menuIds)) {
            roleMenuMapper.insertBatch(roleId, menuIds);
        }
    }

    public List<Menu> findByRoles(Set<Long> roleIds) {
        return menuMapper.findMenusByRoleIds(new QueryWrapper<RoleMenu>().in("roleId", roleIds));
    }

    public List<Menu> findAll() {
        return menuMapper.selectList(new QueryWrapper());
    }

    public Menu findById(Long id) {
        Menu menu = Menu.builder().id(id).build();
        return menu.selectById();
    }

    /**
     * 根据 roleId 查询 role_menu 表的 menuId 集合
     * @param roleId
     * @return
     */
    public Set<Long> findMenuIdsByRoleId(Long roleId) {
        List<RoleMenu> list = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().select("menuId").eq("roleId",roleId));
        return list.stream().map(p->p.getMenuId()).collect(Collectors.toSet());
    }

    public String insert(Long roleId, Set<Long> roleMenuIds) {
        StringBuilder builder = new StringBuilder();
        builder.append("insert into role_menu (roleId,menuId) VALUES");
        String message = "({0},{1})";
        int i = 1;
        for (Long r : roleMenuIds) {
            String s = MessageFormat.format(message, roleId, r);
            builder.append(s);
            if (i == roleMenuIds.size()) {
                break;
            }
            builder.append(",");
            i++;
        }
        return builder.toString();
    }
}

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

import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class MenuService {

//    @Autowired
//    private MenuDao menuDao;
//
//    @Autowired
//    private RoleMenuDao roleMenuDao;

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
        Menu menu = menuMapper.selectOne(new QueryWrapper<Menu>().eq("id",id));
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
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("roleId",roleId));
//        roleMenuDao.delete(roleId, null);

        if (!CollectionUtils.isEmpty(menuIds)) {
            QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<RoleMenu>();
            StringBuilder sb = new StringBuilder();
            menuIds.forEach(menuId -> {
                sb.append("(").append(roleId).append(",").append(menuId).append("),");
//                roleMenuDao.save(roleId, menuId);
            });
            String sql = sb.substring(0,sb.length()-2);
            roleMenuMapper.insertBatch(sql);
        }
    }

    public List<Menu> findByRoles(Set<Long> roleIds) {
        return roleMenuDao.findMenusByRoleIds(roleIds);
    }

    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    public Menu findById(Long id) {
        return menuDao.findById(id);
    }

    public Set<Long> findMenuIdsByRoleId(Long roleId) {
        return roleMenuDao.findMenuIdsByRoleId(roleId);
    }

}

package com.hao.managebackend.Service;

import com.hao.managebackend.Model.Menu;

import java.util.List;
import java.util.Set;

public interface MenuService {

    void save(Menu menu);

    void update(Menu menu);

    void delete(Long id);

    void setMenuToRole(Long roleId, Set<Long> menuIds);

    List<Menu> findByRoles(Set<Long> roleIds);

    List<Menu> findAll();

    Menu findById(Long id);

    Set<Long> findMenuIdsByRoleId(Long roleId);
}

package com.hao.usercenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.commonmodel.user.*;
import com.hao.usercenter.mapper.SysPermissionMapper;
import com.hao.usercenter.mapper.SysRoleMapper;
import com.hao.usercenter.mapper.SysRolePermissionMapper;
import com.hao.usercenter.mapper.SysRoleUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MuggleLee
 * @date 2019/7/22 实现基本功能
 * @update 2019/9/16 优化：将原本 Mybatis 框架升级为 Mybatis-Plus
 */
@Slf4j
@Service
public class SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Transactional
    // 保存角色
    public void save(SysRole sysRole) {
        SysRole role = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("code", sysRole.getCode()));
        if (role != null) {
            throw new IllegalArgumentException("角色code已存在");
        }
        sysRole.setCreateTime(new Date());
        sysRole.setUpdateTime(sysRole.getCreateTime());
        sysRole.insert();
        log.info("保存角色：{}", sysRole);
    }

    @Transactional
    public void update(SysRole sysRole) {
        sysRole.setUpdateTime(new Date());
        sysRole.updateById();
        log.info("修改角色：{}", sysRole);
    }

    @Transactional
    public void deleteRole(Long id) {
        SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("id", id));
        sysRole.deleteById();
        sysRolePermissionMapper.delete(new QueryWrapper<SysRolePermission>().eq("roleId", id));
        sysRoleUserMapper.delete(new QueryWrapper<SysRoleUser>().eq("roleId", id));
        log.info("删除角色：{}", sysRole);
    }

    @Transactional
    // 给角色设置权限
    public void setPermissionToRole(Long roleId, Set<Long> permissionIds) {
        SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("id", roleId));
        if (sysRole == null) {
            throw new IllegalArgumentException("角色不存在");
        }
        // 查出角色对应的old权限
        Set<SysPermission> sysPermissions = sysPermissionMapper.findByRoleIds(new QueryWrapper<SysRolePermission>().eq("roleId",roleId));
        Set<Long> oldPermissionIds = sysPermissions.stream().map(p -> p.getId()).collect(Collectors.toSet());
        // 需要添加的权限
        Collection<Long> addPermissionIds = CollectionUtils.subtract(permissionIds, oldPermissionIds);
        if (!CollectionUtils.isEmpty(addPermissionIds)) {
            addPermissionIds.forEach(permissionId -> {
                SysRolePermission sysRolePermission = SysRolePermission.builder().roleId(roleId.intValue()).permissionId(permissionId.intValue()).build();
                sysRolePermission.insert();
            });
        }
        // 需要移除的权限
        Collection<Long> deletePermissionIds = CollectionUtils.subtract(oldPermissionIds, permissionIds);
        if (!CollectionUtils.isEmpty(deletePermissionIds)) {
            deletePermissionIds.forEach(permissionId -> {
                SysRolePermission sysRolePermission = SysRolePermission.builder().roleId(roleId.intValue()).permissionId(permissionId.intValue()).build();
                sysRolePermission.deleteById();
            });
        }

        log.info("给角色id：{}，分配权限：{}", roleId, permissionIds);
    }

    // 根据 id 查询角色
    public SysRole findById(Long id) {
        return sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("id",id));
    }

    // 分页查询
    public IPage<SysRole> findRoles(Map<String, Object> params) {
        // 获取页面传来的页数
        long pageNum = null == params.get("draw") ? 1 : Long.valueOf(params.get("draw").toString());
        Page<SysRole> page = new Page<>(pageNum, 10);
        IPage<SysRole> iPage = new SysRole().selectPage(page, null);
        return iPage;
    }

    // 根据 roleId 查询该角色拥有的全部权限
    public Set<SysPermission> findPermissionsByRoleId(Long roleId) {
        return sysPermissionMapper.findByRoleIds(new QueryWrapper<SysRolePermission>().eq("roleId",roleId));
    }
}

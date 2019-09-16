package com.hao.usercenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Sets;
import com.hao.commonmodel.user.SysPermission;
import com.hao.commonmodel.user.SysRole;
import com.hao.commonmodel.user.SysRolePermission;
import com.hao.commonmodel.user.SysRoleUser;
import com.hao.commonunits.utils.PageUtil;
import com.hao.usercenter.mapper.SysRoleMapper;
import com.hao.usercenter.mapper.SysRolePermissionMapper;
import com.hao.usercenter.mapper.SysRoleUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MuggleLee
 * @date 2019/7/22 实现基本功能
 * @update 2019/9/16 优化：将原本 Mybatis 框架升级为 Mybatis-Plus
 */
@Slf4j
@Service
public class SysRoleService{

//    @Autowired
//    private AmqpTemplate amqpTemplate;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    /**
     * 保存角色
     * @param sysRole
     */
    @Transactional
    public void save(SysRole sysRole) {
        SysRole role = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("code",sysRole.getCode()));
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
        SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("id",id));
        sysRole.deleteById();
        sysRolePermissionMapper.delete(new QueryWrapper<SysRolePermission>().eq("roleId",id));
        sysRoleUserMapper.delete(new QueryWrapper<SysRoleUser>().eq("roleId",id));
        log.info("删除角色：{}", sysRole);
        // 发布role删除的消息
//        amqpTemplate.convertAndSend(UserCenterMq.MQ_EXCHANGE_USER, UserCenterMq.ROUTING_KEY_ROLE_DELETE, id);
    }

    /**
     * 给角色设置权限
     * @param roleId
     * @param permissionIds
     */
    @Transactional
    public void setPermissionToRole(Long roleId, Set<Long> permissionIds) {
        SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("id",roleId));
        if (sysRole == null) {
            throw new IllegalArgumentException("角色不存在");
        }

        // 查出角色对应的old权限
//        sysRolePermissionMapper.selectOne(new QueryWrapper<SysRolePermission>().eq("roleId",roleId));
        Set<Long> oldPermissionIds = rolePermissionDao.findPermissionsByRoleIds(Sets.newHashSet(roleId)).stream()
                .map(p -> p.getId()).collect(Collectors.toSet());

        // 需要添加的权限
        Collection<Long> addPermissionIds = org.apache.commons.collections4.CollectionUtils.subtract(permissionIds,
                oldPermissionIds);
        if (!CollectionUtils.isEmpty(addPermissionIds)) {
            addPermissionIds.forEach(permissionId -> {
                rolePermissionDao.saveRolePermission(roleId, permissionId);
            });
        }
        // 需要移除的权限
        Collection<Long> deletePermissionIds = org.apache.commons.collections4.CollectionUtils
                .subtract(oldPermissionIds, permissionIds);
        if (!CollectionUtils.isEmpty(deletePermissionIds)) {
            deletePermissionIds.forEach(permissionId -> {
                rolePermissionDao.deleteRolePermission(roleId, permissionId);
            });
        }

        log.info("给角色id：{}，分配权限：{}", roleId, permissionIds);
    }

//    @Override
//    public SysRole findById(Long id) {
//        return sysRoleDao.findById(id);
//    }
//
//    @Override
//    public Page<SysRole> findRoles(Map<String, Object> params) {
//        int total = sysRoleDao.count(params);
//        List<SysRole> list = Collections.emptyList();
//        if (total > 0) {
//            PageUtil.pageParamConver(params, false);
//
//            list = sysRoleDao.findData(params);
//        }
//        return new Page<>(total, list);
//    }
//
//    @Override
//    public Set<SysPermission> findPermissionsByRoleId(Long roleId) {
//        return rolePermissionDao.findPermissionsByRoleIds(Sets.newHashSet(roleId));
//    }
}

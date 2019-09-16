package com.hao.usercenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.commonmodel.user.SysPermission;
import com.hao.commonmodel.user.SysRolePermission;
import com.hao.usercenter.mapper.SysPermissionMapper;
import com.hao.usercenter.mapper.SysRolePermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author MuggleLee
 * @date 2019/7/22 实现基本功能
 * @update 2019/9/16 优化：将原本 Mybatis 框架升级为 Mybatis-Plus
 */
@Slf4j
@Service
public class SysPermissionService{

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    /**
     * 查找用户权限
     * @param roleIds
     * @return
     */
    public Set<SysPermission> findByRoleIds(Set<Long> roleIds) {
        return sysPermissionMapper.findByRoleIds(new QueryWrapper<SysRolePermission>().in("roleId", roleIds));
    }

    /**
     * 添加权限
     * @param sysPermission
     * @throws IllegalAccessException
     */
    @Transactional
    public void save(SysPermission sysPermission) throws IllegalAccessException {
        SysPermission permission = sysPermissionMapper.selectOne(new QueryWrapper<SysPermission>().eq("permission",sysPermission.getPermission()));
        if(permission != null){
            throw new IllegalAccessException("权限标识已存在");
        }
        sysPermission.setCreateTime(new Date());
        sysPermission.setUpdateTime(sysPermission.getCreateTime());
        sysPermission.insert();
        log.info("保存权限标识：{}", sysPermission);
    }

    @Transactional
    public void update(SysPermission sysPermission) {
        sysPermission.setUpdateTime(new Date());
        sysPermission.updateById();
        log.info("修改权限标识：{}", sysPermission);
    }

    @Transactional
    public void delete(Long id) {
        SysPermission sysPermission = sysPermissionMapper.selectOne(new QueryWrapper<SysPermission>().eq("id",id));
        if(sysPermission == null){
            throw new IllegalArgumentException("权限标识不存在");
        }
        sysPermissionMapper.delete(new QueryWrapper<SysPermission>().eq("id",id));
        sysRolePermissionMapper.delete(new QueryWrapper<SysRolePermission>().eq("permissionId",id));
        log.info("删除权限标识：{}", sysPermission);
    }

    public IPage<SysPermission> findPermissions(Map<String, Object> params) {
        // 获取页面传来的页数
        long pageNum = null == params.get("draw") ? 1 : Long.valueOf(params.get("draw").toString());
        Page<SysPermission> page = new Page<>(pageNum, 10);
        IPage<SysPermission> iPage = new SysPermission().selectPage(page, null);
        return iPage;
    }
}

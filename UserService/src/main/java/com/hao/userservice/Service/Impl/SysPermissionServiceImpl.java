package com.hao.userservice.Service.Impl;

import com.hao.commonmodel.Model.User.SysPermission;
import com.hao.commonmodel.common.Page;
import com.hao.commonunits.utils.PageUtil;
import com.hao.userservice.Dao.RolePermissionDao;
import com.hao.userservice.Dao.SysPermissionDao;
import com.hao.userservice.Service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author MuggleLee
 * @date 2019/7/22
 */
@Slf4j
@Service
public class SysPermissionServiceImpl implements SysPermissionService{

    @Autowired
    private SysPermissionDao sysPermissionDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public Set<SysPermission> findByRoleIds(Set<Long> roleIds) {
        return rolePermissionDao.findPermissionsByRoleIds(roleIds);
    }

    @Transactional
    @Override
    public void save(SysPermission sysPermission) throws IllegalAccessException {
        SysPermission permission = sysPermissionDao.findByPermission(sysPermission.getPermission());
        if(permission != null){
            throw new IllegalAccessException("权限标识已存在");
        }
        sysPermission.setCreateTime(new Date());
        sysPermission.setUpdateTime(sysPermission.getCreateTime());
        sysPermissionDao.save(sysPermission);
        log.info("保存权限标识：{}", sysPermission);
    }

    @Transactional
    @Override
    public void update(SysPermission sysPermission) {
        sysPermission.setUpdateTime(new Date());
        sysPermissionDao.update(sysPermission);
        log.info("修改权限标识：{}", sysPermission);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        SysPermission sysPermission = sysPermissionDao.findById(id);
        if(sysPermission == null){
            throw new IllegalArgumentException("权限标识不存在");
        }
        sysPermissionDao.delete(id);
        rolePermissionDao.deleteRolePermission(null,id);
        log.info("删除权限标识：{}", sysPermission);
    }

    @Override
    public Page<SysPermission> findPermissions(Map<String, Object> params) {
        int total = sysPermissionDao.count(params);
        List<SysPermission> list = Collections.emptyList();
        if(total > 0){
            PageUtil.pageParamConver(params,false);
            list = sysPermissionDao.findData(params);
        }
        return new Page<>(total,list);
    }
}

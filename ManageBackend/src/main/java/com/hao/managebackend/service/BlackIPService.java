package com.hao.managebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.managebackend.mapper.BlackIpMapper;
import com.hao.managebackend.model.BlackIp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
public class BlackIPService{

//    @Autowired
//    private BlackIPDao blackIPDao;

    @Autowired
    private BlackIpMapper blackIpMapper;

    @Transactional
    public void save(BlackIp blackIp) {
        BlackIp ip = blackIpMapper.selectOne(new QueryWrapper<BlackIp>().eq("ip",blackIp.getIp()));
        if (ip != null) {
            throw new IllegalArgumentException(blackIp.getIp() + "已存在");
        }
        blackIp.insert();
        log.info("添加黑名单ip:{}", blackIp);
    }

    @Transactional
    public void delete(String ip) {
        int n = blackIpMapper.delete(new QueryWrapper<BlackIp>().eq("ip",ip));
        if (n > 0) {
            log.info("删除黑名单ip:{}", ip);
        }
    }

    public IPage<BlackIp> findBlackIPs(Map<String, Object> params) {
        // 获取页面传来的页数
        long pageNum = null == params.get("draw") ? 1 : Long.valueOf(params.get("draw").toString());
        Page<BlackIp> page = new Page<>(pageNum, 10);
        IPage<BlackIp> iPage = new BlackIp().selectPage(page, null);
        return iPage;
    }
}

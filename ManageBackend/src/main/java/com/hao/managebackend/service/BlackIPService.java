package com.hao.managebackend.service;

import com.hao.commonmodel.common.Page;
import com.hao.commonunits.utils.PageUtil;
import com.hao.managebackend.dao.BlackIPDao;
import com.hao.managebackend.model.BlackIp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BlackIPService implements BlackIPService {

    @Autowired
    private BlackIPDao blackIPDao;

    @Transactional
    @Override
    public void save(BlackIp blackIp) {
        BlackIp ip = blackIPDao.findByIp(blackIp.getIp());
        if (ip != null) {
            throw new IllegalArgumentException(blackIp.getIp() + "已存在");
        }

        blackIPDao.save(blackIp);
        log.info("添加黑名单ip:{}", blackIp);
    }

    @Transactional
    @Override
    public void delete(String ip) {
        int n = blackIPDao.delete(ip);
        if (n > 0) {
            log.info("删除黑名单ip:{}", ip);
        }
    }

    @Override
    public Page<BlackIp> findBlackIPs(Map<String, Object> params) {
        int total = blackIPDao.count(params);
        List<BlackIp> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, false);

            list = blackIPDao.findData(params);
        }
        return new Page<>(total, list);
    }
}

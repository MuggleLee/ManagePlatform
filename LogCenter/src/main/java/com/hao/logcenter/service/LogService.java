package com.hao.logcenter.Service;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.commonmodel.log.Log;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 日志存储到mysql实现
 */
//@Primary
@Service
public class LogService {

    /**
     * 将日志保存到数据库<br>
     * 注解@Async是开启异步执行
     *
     * @param log
     */
    @Async
    public void save(Log log) {
        if (log.getCreateTime() == null) {
            log.setCreateTime(new Date());
        }
        if (log.getFlag() == null) {
            log.setFlag(Boolean.TRUE);
        }
        log.insert();
    }

    public IPage<Log> findLogs(Map<String, Object> params) {
        Log log = new Log();
        long pageNum = null == params.get("draw") ? 1 :Long.valueOf(params.get("draw").toString());
        Page<Log> page = new Page<>(pageNum,10);
        IPage<Log> iPage = log.selectPage(page,null);
        return iPage;
    }
}
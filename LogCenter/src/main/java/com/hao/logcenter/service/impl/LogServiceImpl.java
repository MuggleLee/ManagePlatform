package com.hao.logcenter.Service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hao.commonmodel.Log.Log;
import com.hao.commonmodel.Common.Page;
import com.hao.commonunits.utils.PageUtil;
import com.hao.logcenter.Dao.LogDao;
import com.hao.logcenter.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 日志存储到mysql实现
 */
//@Primary
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;

	/**
	 * 将日志保存到数据库<br>
	 * 注解@Async是开启异步执行
	 *
	 * @param log
	 */
	@Async
	@Override
	public void save(Log log) {
		if (log.getCreateTime() == null) {
			log.setCreateTime(new Date());
		}
		if (log.getFlag() == null) {
			log.setFlag(Boolean.TRUE);
		}

		logDao.save(log);
	}

	@Override
	public Page<Log> findLogs(Map<String, Object> params) {
		int total = logDao.count(params);
		List<Log> list = Collections.emptyList();
		if (total > 0) {
			PageUtil.pageParamConver(params, true);
			list = logDao.findData(params);
		}
		return new Page<>(total, list);
	}
}

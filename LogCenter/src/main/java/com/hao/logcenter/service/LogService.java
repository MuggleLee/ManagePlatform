package com.hao.logcenter.service;

import com.hao.commonmodel.Model.Log.Log;
import com.hao.commonmodel.common.Page;

import java.util.Map;


public interface LogService {

	/**
	 * 保存日志
	 *
	 * @param log
	 */
	void save(Log log);

	Page<Log> findLogs(Map<String, Object> params);

}

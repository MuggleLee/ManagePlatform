package com.hao.logcenter.Service;

import com.hao.commonmodel.Log.Log;
import com.hao.commonmodel.Common.Page;

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

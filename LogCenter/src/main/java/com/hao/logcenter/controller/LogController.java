package com.hao.logcenter.controller;

import com.hao.commonmodel.Model.Log.Log;
import com.hao.commonmodel.common.Page;
import com.hao.logcenter.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LogController {

	@Autowired
	private LogService logService;

	@PostMapping("/logs-anon/internal")
	public void save(@RequestBody Log log) {
		logService.save(log);
	}

	/**
	 * 日志模块<br>
	 * 2018.07.29作废
	 */
	@Deprecated
	@PreAuthorize("hasAuthority('log:query')")
	@GetMapping("/logs-modules")
	public Map<String, String> logModule() {
		return com.hao.commonmodel.Model.Log.constants.LogModule.MODULES;
	}

	/**
	 * 日志查询
	 * 
	 * @param params
	 * @return
	 */
	@PreAuthorize("hasAuthority('log:query')")
	@GetMapping("/logs")
	public Page<Log> findLogs(@RequestParam Map<String, Object> params) {
		return logService.findLogs(params);
	}

}
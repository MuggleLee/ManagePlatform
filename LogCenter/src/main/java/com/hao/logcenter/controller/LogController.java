package com.hao.logcenter.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hao.commonmodel.Log.Log;
import com.hao.logcenter.Service.LogService;
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
		return com.hao.commonmodel.Log.constants.LogModule.MODULES;
	}

	/**
	 * 日志查询
	 *
	 * @param params
	 * @return
	 */
	@PreAuthorize("hasAuthority('log:query')")
	@GetMapping("/logs")
	public IPage<Log> findLogs(@RequestParam Map<String, Object> params) {
		return logService.findLogs(params);
	}
}

package com.hao.oauthcenter.Feigin;

import com.hao.commonmodel.Log.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Muggle Lee
 * @Date: 2019/7/24 17:54
 */
@FeignClient("LogCenter")
public interface LogClient {
    @PostMapping("/logs-anon/internal")
    void save(@RequestBody Log log);
}

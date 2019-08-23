package com.hao.gatewaycenter.Feign;

import com.hao.commonmodel.Log.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("LogCenter")
public interface LogClient {

    @PostMapping("/logs-anon/internal")
    void save(@RequestBody Log log);
}

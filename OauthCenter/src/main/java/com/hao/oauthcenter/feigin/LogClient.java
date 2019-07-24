package com.hao.oauthcenter.feigin;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Muggle Lee
 * @Date: 2019/7/24 17:54
 */
@FeignClient("LogCenter")
public interface LogClient {
}

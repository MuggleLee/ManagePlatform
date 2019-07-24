package com.hao.oauthcenter.feigin;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Muggle Lee
 * @Date: 2019/7/24 17:53
 */
@FeignClient("UserService")
public interface UserClient {



}

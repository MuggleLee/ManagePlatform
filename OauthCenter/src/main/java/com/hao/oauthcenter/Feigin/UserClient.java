package com.hao.oauthcenter.Feigin;

import com.hao.commonmodel.User.LoginAppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Muggle Lee
 * @Date: 2019/7/24 17:53
 */
@FeignClient("UserService")
public interface UserClient {

    @GetMapping(value = "/users-anon/internal",params = "username")
    LoginAppUser findByUsername(@RequestParam("username") String username);

    @GetMapping("/wechat/login-check")
    public void wechatLoginCheck(@RequestParam("tempCode") String tempCode, @RequestParam("openid") String openid);
}

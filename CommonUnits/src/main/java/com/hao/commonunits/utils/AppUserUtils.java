package com.hao.commonunits.utils;
import com.alibaba.fastjson.JSONObject;
import com.hao.commonmodel.Model.User.LoginAppUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

/**
 * @author MuggleLee
 * @date 2019/7/22
 */
public class AppUserUtils {

    /**
     * 获取登陆的loginAppUser
     */
    public static LoginAppUser getLoginAppUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof OAuth2Authentication){
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            authentication = oAuth2Authentication.getUserAuthentication();

            if(authentication instanceof UsernamePasswordAuthenticationToken){
                UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
                Object princical = authentication.getPrincipal();
                if(princical instanceof LoginAppUser){
                    return (LoginAppUser) princical;
                }
            }

            Map map = (Map) authentication.getDetails();
            map = (Map) map.get("princical");
            // 使用JSONObject将map对象转换成loginAppUser对象
            return JSONObject.parseObject(JSONObject.toJSONString(map),LoginAppUser.class);
        }
        return null;
    }

}

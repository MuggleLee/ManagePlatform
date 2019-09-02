package com.hao.usercenter.Controller;

import com.hao.commonmodel.User.AppUser;
import com.hao.commonmodel.User.WechatUserInfo;
import com.hao.commonunits.utils.AppUserUtils;
import com.hao.usercenter.Service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WechatService wechatService;

    /**
     * 引导到授权
     *
     * @param app
     * @param request
     * @param toUrl   授权后，跳转的页面url，注意url要转义
     * @return
     */
    @GetMapping("/{app}")
    public RedirectView toWechatAuthorize(@PathVariable String app, HttpServletRequest request,
                                          @RequestParam String toUrl) throws UnsupportedEncodingException {
        String url = wechatService.getWechatAuthorizeUrl(app, request, toUrl);
        System.out.println(url);
        return new RedirectView(url);
    }

    /**
     * 授权后，微信跳转到此接口
     *
     * @return
     */
    @GetMapping(value = "/{app}/back", params = {"code", "state"})
    public RedirectView wechatBack(HttpServletRequest request, @PathVariable String app, String code, String state,
                                   @RequestParam String toUrl) {
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("code不能为空");
        }

        if (StringUtils.isBlank(state)) {
            throw new IllegalArgumentException("state不能为空");
        }

        WechatUserInfo wechatUserInfo = wechatService.getWechatUserInfo(app, request, code, state);

        toUrl = wechatService.getToUrl(toUrl, wechatUserInfo);

        return new RedirectView(toUrl);
    }

    /**
     * 微信绑定用户
     *
     * @param tempCode
     * @param openid
     */
    @PostMapping(value = "/binding-user", params = {"tempCode", "openid"})
    public void bindingUser(String tempCode, String openid) {
        AppUser appUser = AppUserUtils.getLoginAppUser();
        if (appUser == null) {
            throw new IllegalArgumentException("非法请求");
        }

        log.info("绑定微信和用户：{},{},{}", appUser, openid, tempCode);
        wechatService.bindingUser(appUser, tempCode, openid);
    }

    /**
     * 微信登陆校验
     *
     * @param tempCode
     * @param openid
     */
    @GetMapping(value = "/login-check", params = {"tempCode", "openid"})
    public void wechatLoginCheck(String tempCode, String openid) {
//        https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx95514b72588bcf0f &redirect_uri=http%3A%2F%2F2631a8815w.wicp.vip%3A29867%2Fwechat%2Fapp1%2Fback%3FtoUrl%3Dhttp%253A%252F%252F127.0.0.1%253A8093%252Fapi-b%252Fpages%252Fwechat%252Findex.html&response_type=code &scope=snsapi_userinfo&state=8b893277-6aea-45d1-a346-d2e33273e0d0 &uin=MTAwMjE0NzcyMA%3D%3D&key=9d288677f073f000f151e7a3792d31f456f923a241786d56f979065abb5bbfa06e2be6dbb614d9a24d74101b915976f9 &pass_ticket=JGVGBl4IHPit8SFkraV4Y2BXs8/CGuX8rRWLGYeFnB+7JSEFJtFjxE3HDVkSLBS//e5S6i3iIJdefLzUz31iVQ==
        wechatService.checkAndGetWechatUserInfo(tempCode, openid);
    }
}

package com.hao.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.commonmodel.user.WechatUserInfo;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author Muggle Lee
 * @Date: 2019/9/17 13:28
 */
public interface WechatUserInfoMapper extends BaseMapper<WechatUserInfo> {

    @Select("select * from t_wechat t where t.unionid = #{unionid}")
    Set<WechatUserInfo> findByUniond(String unionid);
}

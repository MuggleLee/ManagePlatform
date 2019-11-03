package com.hao.commonmodel.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信个人用户信息
 */
@Getter
@Setter
@ToString
@TableName("t_wechat")
public class WechatUserInfo extends Model<WechatUserInfo> implements Serializable {

    private static final long serialVersionUID = 6750304307961875043L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String openid;
    private String unionid;
    @TableField("userId")
    private Long userId;
    private String app;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    @TableField("headimgurl")
    private String headimgurl;
    @TableField("createTime")
    private Date createTime;
    @TableField("updateTime")
    private Date updateTime;
}

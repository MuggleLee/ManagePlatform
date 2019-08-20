package com.hao.commonmodel.User;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hao on 2019/7/21.
 */
@Data
public class AppUser implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String headImgUrl;
    private String phone;
    private Integer sex;
    /**
     * 状态
     */
    private Boolean enabled;
    private String type;
    private Date createTime;
    private Date updateTime;
}

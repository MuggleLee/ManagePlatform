package com.hao.commonmodel.Model.User;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MuggleLee
 * @date 2019/7/22
 */
@Data
public class SysPermission implements Serializable {

    private Long id;
    private String permission;
    private String name;
    private Date createTime;
    private Date updateTime;
}

package com.hao.commonmodel.User;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hao on 2019/7/21.
 */
@Data
public class SysRole implements Serializable {
    private Long id;
    private String code;
    private String name;
    private Date createTime;
    private Date updateTime;
}

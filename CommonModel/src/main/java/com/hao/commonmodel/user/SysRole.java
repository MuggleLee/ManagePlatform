package com.hao.commonmodel.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hao on 2019/7/21.
 */
@Data
@TableName("sys_role")
public class SysRole extends Model<SysRole> implements Serializable {
    private Long id;
    private String code;
    private String name;
    @TableField("createTime")
    private Date createTime;
    @TableField("updateTime")
    private Date updateTime;
}

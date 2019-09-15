package com.hao.commonmodel.User;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MuggleLee
 * @date 2019/7/22
 */
@Data
@TableName("sys_permission")
public class SysPermission extends Model<SysPermission> implements Serializable {

    private Long id;
    private String permission;
    private String name;
    @TableField("createTime")
    private Date createTime;
    @TableField("updateTime")
    private Date updateTime;
}

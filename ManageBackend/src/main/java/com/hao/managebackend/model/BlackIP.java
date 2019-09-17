package com.hao.managebackend.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * IP黑名单
 */
@Data
@TableName("black_ip")
public class BlackIp extends Model<BlackIp> implements Serializable {

    private static final long serialVersionUID = -2064187103535072261L;

    private String ip;
    @TableField("createTime")
    private Date createTime;
}

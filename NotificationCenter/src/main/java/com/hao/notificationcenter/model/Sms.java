package com.hao.notificationcenter.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("t_sms")
public class Sms extends Model<Sms> implements Serializable {

	private static final long serialVersionUID = -1084513813698387690L;

	@TableId(type = IdType.AUTO)
	private Long id;
	private String phone;
	@TableField("signName")
	private String signName;
	@TableField("templateCode")
	private String templateCode;
	private String params;
	@TableField("bizId")
	private String bizId;
	private String code;
	private String message;
	private Date day;
	@TableField("createTime")
	private Date createTime;
	@TableField("updateTime")
	private Date updateTime;
}

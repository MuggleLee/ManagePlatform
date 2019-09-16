package com.hao.commonmodel.log;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志对象
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_log")
public class Log extends Model<Log> implements Serializable {

	private static final long serialVersionUID = -5398795297842978376L;

	@TableId(type = IdType.AUTO)
	private Long id;
	/** 用户名 */
	private String username;
	/** 模块 */
	private String module;
	/** 参数值 */
	private String params;
	private String remark;
	/** 是否执行成功 */
	private Boolean flag;
	@TableField("createTime")
	private Date createTime;
}

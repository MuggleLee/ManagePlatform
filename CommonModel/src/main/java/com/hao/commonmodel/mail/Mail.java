package com.hao.commonmodel.mail;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件
 */
@Data
@TableName("t_mail")
public class Mail extends Model<Mail> implements Serializable {

    private static final long serialVersionUID = 4885093464493499448L;

    private Long id;
    @TableField("userId")
    private Long userId;
    /**
     * 发送人用户名
     */
    private String username;
    /**
     * 收件人邮件地址
     */
    @TableField("toEmail")
    private String toEmail;
    /**
     * 标题
     */
    private String subject;
    /**
     * 正文
     */
    private String content;
    /**
     * 发送状态
     *
     * @see com.hao.commonmodel.mail.constants.MailStatus
     */
    private Integer status;
    /**
     * 发送时间
     */
    @TableField("sendTime")
    private Date sendTime;
    @TableField("createTime")
    private Date createTime;
    @TableField("updateTime")
    private Date updateTime;

}

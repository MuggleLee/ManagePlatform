package com.hao.commonmodel.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hao on 2019/7/21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("app_user")
@Builder
public class AppUser extends Model<AppUser> implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    @TableField("headImgUrl")
    private String headImgUrl;
    private String phone;
    private Integer sex;
    /**
     * 状态
     */
    private Boolean enabled;
    private String type;
    @TableField("createTime")
    private Date createTime;
    @TableField("updateTime")
    private Date updateTime;
}

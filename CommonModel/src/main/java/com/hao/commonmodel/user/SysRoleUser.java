package com.hao.commonmodel.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Muggle Lee
 * @Date: 2019/9/16 10:33
 */
@Data
@Setter
@Getter
@TableName("sys_role_user")
public class SysRoleUser extends Model<SysRoleUser> {

    @TableField("userId")
    private long userId;

    @TableField("roleId")
    private int roleId;
}

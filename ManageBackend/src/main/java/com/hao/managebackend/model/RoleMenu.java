package com.hao.managebackend.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Muggle Lee
 * @Date: 2019/9/17 17:24
 */
@Data
@TableName("role_menu")
public class RoleMenu extends Model<RoleMenu> implements Serializable {

    @TableField("roleId")
    private Long roleId;

    @TableField("menuId")
    private Long menuId;
}

package com.hao.commonmodel.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
@TableName("sys_role_permission")
public class SysRolePermission extends Model<SysRolePermission> {

    @TableField("roleId")
    private int roleId;
    @TableField("permissionId")
    private int permissionId;
}

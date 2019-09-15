package com.hao.commonmodel.User;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

/**
 * 用户账号类型
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user_credentials")
public class UserCredential extends Model<UserCredential> implements Serializable {

    private static final long serialVersionUID = -958701617717204974L;

    private String username;
    private String type;
    @TableField("userId")
    private Long userId;

}

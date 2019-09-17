package com.hao.managebackend.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("menu")
public class Menu extends Model<Menu> implements Serializable {

    private static final long serialVersionUID = 749360940290141180L;

    private Long id;
    @TableField("parentId")
    private Long parentId;
    private String name;
    private String css;
    private String url;
    private Integer sort;
    @TableField("createTime")
    private Date createTime;
    @TableField("updateTime")
    private Date updateTime;

    private List<Menu> child;
}

package com.hao.managebackend.Model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 749360940290141180L;

    private Long id;
    private Long parentId;
    private String name;
    private String css;
    private String url;
    private Integer sort;
    private Date createTime;
    private Date updateTime;

    private List<Menu> child;
}

package com.hao.filecenter.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("file_info")
public class FileInfo extends Model<FileInfo> implements Serializable {

    private static final long serialVersionUID = -1438078028040922174L;

    /**
     * 文件的md5
     */
    private String id;
    /**
     * 原始文件名
     */
    private String name;
    /**
     * 是否是图片
     */
    @TableField("isImg")
    private Boolean isImg;
    @TableField("contentType")
    private String contentType;
    private long size;
    private String path;
    private String url;
    /**
     * 文件来源
     *
     * @see FileSource
     */
    private String source;
    @TableField("createTime")
    private Date createTime;
}

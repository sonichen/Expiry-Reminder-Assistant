package com.cyj.dto.record;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class RecordInfoDto {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Long userId;

    @ApiModelProperty(value = "物品名称")
    @TableField("itemName")
    private String itemName;

    @ApiModelProperty(value = "临近任务编码")
    @TableField("closeTask")
    private String closeTask;

    @ApiModelProperty(value = "临近提醒时间")
    @TableField("closeTime")
    private String closeTime;

    @ApiModelProperty(value = "到期时间")
    @TableField("expireTask")
    private String expireTask;

    @ApiModelProperty(value = "到期时间")
    @TableField("expireTime")
    private String expireTime;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "描述")
    private String description;


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Long state;
}

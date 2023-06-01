package com.cyj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyj
 * @since 2022-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Record对象", description="")
public class Record implements Serializable {

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

    @TableLogic
    private Long deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Long state;

    @TableField("categoriesId")
    private Long categoriesId;
    @TableField("timeGap")
    private String timeGap;
    @TableField("categoriesName")
    private String categoriesName;

}

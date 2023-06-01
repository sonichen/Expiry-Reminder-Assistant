package com.cyj.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {
    private static final long serialVersionUID = 1L;
    @ExcelProperty("分类名称")
    private String categoriesName;

    @ExcelProperty("事件")
    private String itemName;
    @ExcelProperty(value = "临期提醒时间")
    private String closeTime;
    @ExcelProperty(value = "到期提醒时间")
    private String expireTime;
    @ExcelProperty("状态")
    private String state;
    @ExcelProperty("备注")
    private String description;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("修改时间")
    private Date updateTime;

}

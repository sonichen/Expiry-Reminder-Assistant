package com.cyj.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyj
 * @since 2022-04-29
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Logs对象", description="")
public class LogsDto implements Serializable {

    private static final long serialVersionUID = 1L;

//

    @ApiModelProperty(value = "行为")
    private String action;

    @ApiModelProperty(value = "ip地址")
    private String ip;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public LogsDto(String action, String ip, Date createTime) {
        this.action = action;
        this.ip = ip;
        this.createTime = createTime;
    }
}

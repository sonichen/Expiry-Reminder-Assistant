package com.cyj.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDto  implements Comparable<CategoriesDto>{

    @ApiModelProperty(value = "分类名字")
    private String name;
    private Integer value;

    @Override
    public int compareTo(@NotNull CategoriesDto o) {
        return o.getValue()-value;
    }


//    @Override
//    public int compareTo(@NotNull CategoriesDto o) {
//        return Integer.valueOf(o.getCount())-Integer.valueOf(count);
//    }

}

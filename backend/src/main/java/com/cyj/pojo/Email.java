package com.cyj.pojo;

import com.alibaba.druid.filter.AutoLoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String title;
    private String content;
    private String attachmentName;
    private String attachmentPath;
}

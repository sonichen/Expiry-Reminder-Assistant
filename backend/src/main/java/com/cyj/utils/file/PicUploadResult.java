package com.cyj.utils.file;

import lombok.Data;

import java.util.List;

@Data
public class PicUploadResult
{
    private boolean isLegal;

    private String imgPath;

    private List<String> imgPahts;

}

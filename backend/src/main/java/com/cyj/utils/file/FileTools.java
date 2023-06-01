package com.cyj.utils.file;

import java.io.File;

public class FileTools {
    public static  boolean deleteFile(String filePath){
        File file = new File(filePath);

       return file.delete();
    }
}

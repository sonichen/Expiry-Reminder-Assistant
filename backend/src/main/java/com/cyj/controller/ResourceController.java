package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.pojo.Categories;
import com.cyj.utils.Constants;
import com.cyj.utils.JsonObject;
import com.cyj.utils.UploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Api(tags = {"前端资源管理"})
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @ApiOperation(value = "上传图片至服务器",notes = "文件名改成英文")
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        try{
            String result = UploadUtils.upload("http://8.130.8.164:8080/img/re/resource/", multipartFileToFile(file));
            return  result;
        }catch (Exception e){
            return "上传失败";
        }
    }
    public static File multipartFileToFile(@RequestParam MultipartFile file) throws Exception {

        java.io.File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

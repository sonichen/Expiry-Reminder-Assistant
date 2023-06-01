package com.cyj;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.mapper.RecordMapper;
import com.cyj.mapper.ScheduletableMapper;
import com.cyj.pojo.Scheduletable;
import com.cyj.utils.HttpUtils;
import com.cyj.utils.MarkdownUtils;
import com.youbenzi.md2.export.FileFactory;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@SpringBootTest
class InFuzhouApplicationTests {
    @Autowired
    ScheduletableMapper scheduletableMapper;
    @Autowired
    RecordMapper recordMapper;
    @Test
    public void test(){
        List list=new ArrayList();
        for(int i=0;i<10;i++){
            list.add(new DemoData("111",new Date(),22.2));
        }
        // 写法1
        String fileName = "EasyTest.xlsx";
        //write(fileName,格式类)
        //sheet(表名)
        //doWrite(数据)
        EasyExcel.write(fileName,DemoData.class).sheet("模板").doWrite(data());

    }
    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
    @Test
    public void simpleWrite() throws IOException {
        String fileName=null;

        // 写法2，方法二需要手动关闭流
        fileName = System.currentTimeMillis() + ".xlsx";
        File file=new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("写入方法二").build();
        excelWriter.write(data(), writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }



}

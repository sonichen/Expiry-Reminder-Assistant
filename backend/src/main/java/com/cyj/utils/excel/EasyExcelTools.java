package com.cyj.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.cyj.dto.RecordDto;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyExcelTools {
    public static void main(String[] args) {

                List<RecordDto> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
           RecordDto recordDto=new RecordDto();
            recordDto.setCategoriesName("1l");
            recordDto.setItemName("事件"+i);
            recordDto.setCloseTime("时间1"+i);
            recordDto.setExpireTime("时间2"+i);
            recordDto.setDescription("备注"+i);
            recordDto.setCreateTime(new Date());
            recordDto.setUpdateTime(new Date());
            list.add(recordDto);
        }
        EasyExcelTools easyExcelTools =new EasyExcelTools(list);
        easyExcelTools.simpleWrite();
    }
    private List<RecordDto> recordDtoList;

    public EasyExcelTools(List<RecordDto> recordDtoList) {
        this.recordDtoList = recordDtoList;
    }
    private List<RecordDto> data() {
        List<RecordDto> list=recordDtoList;
        return list;
    }

    // 最简单的写
    @Test
    public String simpleWrite() {
        // 写法1
//        String fileName = "EasyExcel.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
        String fileName=System.currentTimeMillis()+".xlsx";
        EasyExcel.write(fileName, RecordDto.class).sheet("模板").doWrite(data());
        return fileName;
    }

//    // 最简单的读
//    @Test
//    public void simpleRead() {
//        String fileName = path+"EasyExcel.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
//    }

}

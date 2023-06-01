package com.cyj.utils;


import cn.hutool.core.date.DateTime;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname ExcelWrite
 * @Description TODO
 * @Date 2022/1/7 12:41
 * @Created by 闲言
 */
public class ExcelWrite {

   static String  PATH = "G:\\";


    /**
     * 写工作簿 03版本
     */
    @Test
    public static void Write03() throws Exception {
        //1.创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        //2.创建 一个工作表
        Sheet sheet = workbook.createSheet("闲言粉丝统计表");
        //3.创建一行
        Row row1 = sheet.createRow(0);
        //4.创建一个单元格
        //(1,1)
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("今日新增观众");
        //(1,2)
        Cell cell2 = row1.createCell(1);
        cell2.setCellValue(666);

        //创建第二行
        Row row2 = sheet.createRow(1);
        //(2,1)
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        //(2,2)
        Cell cell22 = row2.createCell(1);
        String datetime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(datetime);

        //生成一张表(IO流)，03版本就是使用xls结尾
        FileOutputStream fos = new FileOutputStream(PATH + "闲言观众统计表03.xls");
        //输出
        workbook.write(fos);
        //关闭流
        fos.close();
        System.out.println("文件生成完毕");
    }

    /**
     * 写工作簿 07版本
     */
    @Test
    public static void Write07(List <Demo>list) throws Exception {
        //1.创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        //2.创建 一个工作表
        Sheet sheet = workbook.createSheet("闲言粉丝统计表");
        //3.创建一行
        Row row1 = sheet.createRow(0);
        //4.创建一个单元格
        //(1,1)
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("id");
        //(1,2)
        Cell cell2 = row1.createCell(1);
        cell2.setCellValue("值");

        //创建第二行
        Row row2 = sheet.createRow(1);
        //(2,1)
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        //(2,2)
        Cell cell22 = row2.createCell(1);
        String datetime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(datetime);
        for(int i=0;i<list.size();i++){
            Row row = sheet.createRow(i+1);

                Cell cell = row.createCell(i);
                cell.setCellValue(list.get(i).getId());

        }

        //生成一张表(IO流)，03版本就是使用xlsx结尾
        FileOutputStream fos = new FileOutputStream(PATH + "闲言观众统计表07.xlsx");
        //输出
        workbook.write(fos);
        //关闭流
        fos.close();
        System.out.println("文件生成完毕");
    }

    public static void main(String[] args) throws Exception {
        List<Demo> demoList=new ArrayList<>(
        );
        for(int i=0;i<10;i++){
            demoList.add(new Demo(i,"测试"+i));
        }
        Write07(demoList);
    }
}


package com.cyj.utils.number;

import com.cyj.utils.DateFormatTest;

import java.text.DateFormat;
import java.text.DecimalFormat;

public class NumberTools {
    public static   Double getPreciseTwoBit(double count){
        DecimalFormat dateFormat=new DecimalFormat("######0.00");

        return  Double.valueOf(dateFormat.format(count));
    }

    public static void main(String[] args) {
        System.out.println(getPreciseTwoBit(2.21111111111131));
    }
}

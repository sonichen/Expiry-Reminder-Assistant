package com.cyj;


import com.cyj.utils.KeyUtils;
import com.cyj.utils.Sm2Util;
import org.junit.Test;

import java.util.Base64;

/**
 * @author WangJing
 * @Description Sm2Util 的测试类
 * @date 2021/11/24 16:10
 */
public class Sm2UtilTest {

    private String testStr = "chenyijun";

    java.security.PublicKey publicKey = null;
    java.security.PrivateKey privateKey = null;


    @Test
    public void test() throws Exception {
        int a=2;
        int b=8;
        Double doubleValueObject = new Double((100*((double)a/(double) b)));
        int result=doubleValueObject.intValue();
        System.out.println(doubleValueObject);
    }
}

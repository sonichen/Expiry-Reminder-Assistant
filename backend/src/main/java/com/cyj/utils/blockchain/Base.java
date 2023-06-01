//package com.cyj.utils.blockchain;
//
//import cn.tdchain.cipher.Cipher;
//import cn.tdchain.jbcc.Connection;
//import cn.tdchain.jbcc.ConnectionFactory;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class Base {
//    protected static String[] iptables = new String[]{
//            "open-tdcb-node1.tdchain.cn",
//            "open-tdcb-node2.tdchain.cn",
//            "open-tdcb-node3.tdchain.cn",
//            "open-tdcb-node4.tdchain.cn"};
//
//    protected final static String keystorePath = "D:\\04-dev\\keys\\rsa\\87f1a3e2-39d2-4e3b-acbe-73932808d47b.pfx";
//    protected final static String keystorePasswd = "123456";
//
//    //# Access to Tiandeyun block chain port, default is18088
//    protected final static int port = 18088;
//    //# Connection timeout, default 3 seconds
//    protected final static long timeout = 3000;
//    //# Visit token of Tiandeyun Block Chain and obtain valid certificate after successful application token
//    protected final static String token = "32581b83-705d-40ef-9f62-1f9a4118fbe1";
////# Authorization files needed to access Tiandeyun block chain can be downloaded after successful login, and keystore Path is the certificate storage path.
//
//    //# Set SM or RSA cipher
//    Cipher.Type type = Cipher.Type.RSA;//or Cipher.Type.SM
//
//    //# Declare a global connector
//    public static Connection connection = null;
//
//    static {
//        try {
//            //# Building configuration information
//            ConnectionFactory factory = ConnectionFactory.ConnectionConfig.builder()
//                    .cipherType(type)
//                    .iptables(iptables)
//                    .port(port)  //# No configuration even with default value 18088
//                    .timeout(timeout)  //# Use default 3 seconds without configuration
//                    .token(token)
//                    //.showPrint()
//                    .keystorePath(keystorePath)
//                    .keystorePassword(keystorePasswd).build();
//
//            connection = factory.getConnection();
//        } catch (Exception e) {
//            log.info("Please check the configuration===> error: {}", e);
//        }
//    }
//
//}

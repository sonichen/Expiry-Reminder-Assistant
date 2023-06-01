package com.cyj.utils;


import com.cyj.pojo.Email;
import com.cyj.pojo.User;
import com.cyj.utils.file.FileTools;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.mail.javamail.MimeMessageHelper;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;


/*
网站3秒原则：用户体验

让用户不等待，事情也还做？
用户执行完这个方法，它还走它的东西，我走我的页面
通过多线程，实现用户体验！（异步处理）

 */
public class SendMail extends Thread{
    public static void main(String[] args) {
        User user=new User();
        user.setUsername("soni");
        user.setEmail("1606673007@qq.com");
        Email email=new Email();
        email.setTitle("Re数据报表");
        email.setContent("来自Re的数据报表，请查收。");
        email.setAttachmentName("data.xlsx");
        email.setAttachmentPath("G:\\EasyExcel.xlsx");
        SendMail sendMail=new SendMail(user,email);
        sendMail.start();
    }
    //用于给用户发送邮件的邮箱
    private String from = "1606673007@qq.com" ;
    //邮箱的用户名
    private String username = " 1606673007@qq.com" ;
    //邮箱的密码
    private String password = "yvqkttsniawmjdeh";
    //发送邮件的服务器地址
    private String host ="smtp.qq.com" ;

    //导入一个类
    private User user;
    private Email email;
    public SendMail(User user,Email email){
        this.user = user;
        this.email=email;
    }


    @Override
    public void run() {

        try {
            Properties prop = new Properties();

            prop.setProperty("mail.host",host);///设置QQ邮件服务器
            prop.setProperty("mail.transport.protocol","smtp");///邮件发送协议
            prop.setProperty("mail.smtp.auth","true");//需要验证用户密码
            //QQ邮箱需要设置SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable","true");
            prop.put("mail.smtp.ssl.socketFactory",sf);
            //使用javaMail发送邮件的5个步骤
            //1.创建定义整个应用程序所需要的环境信息的session对象
            Session session= Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from,password);
                }
            });
            //开启session的debug模式，这样可以查看到程序发送Email的运行状态
            session.setDebug(true);

            //2.通过session得到transport对象
            Transport ts = session.getTransport();

            //3.使用邮箱的用户名和授权码连上邮件服务器
            ts.connect(host,username,password);

            //4.创建邮件：写文件

            //注意需要传递session
            MimeMessage message = new MimeMessage(session);

            //指明邮件的发件人
            message.setFrom(new InternetAddress(from));
            //指明邮件的收件人
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));//从前端接收的
            //邮件标题
            message.setSubject(email.getTitle());
            //邮件的文本内容

//            String info  = "恭喜你("+user.getUsername()+")成功注册！"+"密码："+user.getPassword();
//            message.setContent(info,"text/html;charset=UTF-8");


            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart mimeBodyPartText = new MimeBodyPart();
            mimeBodyPartText.setContent(email.getContent(), "text/html;charset=UTF-8");
            multipart.addBodyPart(mimeBodyPartText);
            DataHandler handler = new DataHandler(new FileDataSource(email.getAttachmentPath()));

//            DataHandler handler = new DataHandler(new FileDataSource("C:\\Users\\lenovo\\Desktop\\Centos7.6-2022-05-18-15-49-41.png"));
            MimeBodyPart mimeBodyPartFile = new MimeBodyPart();
            mimeBodyPartFile.setDataHandler(handler);
//对文件名进行编码，防止出现乱码
            String fileName = MimeUtility.encodeWord(email.getAttachmentName(), "utf-8", "B");
            mimeBodyPartFile.setFileName(fileName);
            multipart.addBodyPart(mimeBodyPartFile);
            message.setContent(multipart);

            //5.发送邮件
            ts.sendMessage(message,message.getAllRecipients());

            //6.关闭连接
            ts.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }finally {
            FileTools.deleteFile(email.getAttachmentPath());
        }



    }
}


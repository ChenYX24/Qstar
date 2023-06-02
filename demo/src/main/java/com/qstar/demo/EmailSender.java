package com.qstar.demo;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import java.security.SecureRandom;
// import com.sun.mail.util.MailSSLSocketFactory;
// import java.net.InetAddress;
// import java.net.Socket;


public class EmailSender {
    Map<String, String> codes = new HashMap<>();
    private String host = "smtp.qq.com";//邮件服务器地址
    private String SenderName = "MetaQ <2502160023@qq.com>";//发送者姓名
    private String SenderEmail = "2502160023@qq.com";//发送者邮箱
    private String Passwd = "isuljpvdejrieajj";//授权码
    public void sendcode(String Email){
        //验证码生成
        SecureRandom random = new SecureRandom();
        int code1 = random.nextInt(8) + 1;
        int code2 = random.nextInt(8) + 1;
        int code3 = random.nextInt(8) + 1;
        int code4 = random.nextInt(8) + 1;
        int code5 = random.nextInt(8) + 1;
        int code6 = random.nextInt(8) + 1;
        int code = code1 + code2*10 + code3*100 + code4*1000 + code5*10000 + code6 * 100000;
        String codeString = Integer.toString(code);
        System.out.println("验证码：" + codeString);
        //把验证码放进map里
        codes.put(Email, codeString);

        // 设置QQ 邮箱 SMTP服务器属性
        Properties props = new Properties();
        props.setProperty("mail.host", host);    // 设置QQ邮件服务器
        props.setProperty("mail.transport.protocol", "smtp");      // 邮件发送协议
        props.setProperty("mail.smtp.auth", "true");      // 需要验证用户名和密码
        //获取SMTP会话对象
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SenderEmail, Passwd);
            }
        });
        
        try {
        Transport ts = session.getTransport();
        ts.connect(host, SenderEmail, Passwd);
        // 创建一个MimeMessage对象
        Message message = new MimeMessage(session);
        // 设置发件人，收件人和主题
        message.setFrom(new InternetAddress(SenderName));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(Email));
        message.setSubject("MetaQ验证码");
        // 设置邮件正文
        message.setText("以下是您的验证码:" + codeString + "。请勿告诉他人。若非本人操作，请忽略本邮件。");
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
        System.out.println("Email sent successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
   }
   public String checkcode(String Email,String Code){
        String success = "false";
        String code = codes.get(Email);
        if(code.hashCode() == Code.hashCode())
        {
            success = "true";
        }
        return success;
   }
}

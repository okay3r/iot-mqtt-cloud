package yx.graduation.elec.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yx.graduation.elec.service.EmailService;

import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${email.host}")
    private String hostName;

    @Value("${email.port}")
    private Integer port;

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Value("${email.fromAddress}")
    private String fromAddress;

    @Value("${email.subject}")
    private String subject;

    @Override
    public void send(String toAddress, String msg) {
        try {

            msg += "\n\n\n系统自动发送，请勿回复";

            //创建电子邮件对象
            SimpleEmail email = new SimpleEmail();
            email.setSSL(true);
            email.setDebug(true);
            //设置发送电子邮件使用的服务器主机名
            email.setHostName(hostName);
            //设置发送电子邮件使用的邮件服务器的TCP端口地址
            email.setSmtpPort(port);
            //邮件服务器身份验证
            email.setAuthenticator(new DefaultAuthenticator(username, password));//邮件服务器身份验证
            //设置发信人邮箱
            email.setFrom(fromAddress);
            //设置邮件主题
            email.setSubject("杨雪毕业设计-电力设备管理系统-报警信息");
            //设置邮件文本内容
            email.setMsg(msg);
            //设置收件人
            email.addTo(toAddress);

            //发送邮件
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
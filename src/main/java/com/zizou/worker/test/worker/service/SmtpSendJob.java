package com.zizou.worker.test.worker.service;

import com.zizou.worker.test.worker.thread.Job;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by zizou on 2017-08-23.
 */
public class SmtpSendJob implements Job{

    @Override
    public void execute(){
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "172.22.1.103");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom("thecarlos@daou.co.kr");
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("test3489@alpha.terracetech.co.kr"));
            msg.setSubject("bulk test subject");
            msg.setContent("bulk test content", "text/html; charset=utf-8");

            Transport.send(msg);
        }catch (MessagingException e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        this.execute();
    }
}

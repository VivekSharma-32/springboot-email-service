package com.emailSender.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.emailSender.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

    
    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("vivek.wealthmax@gmail.com");
        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent...");
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
        
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {
        
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
        
    }
    
}

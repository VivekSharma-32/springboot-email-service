package com.emailSender.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.emailSender.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

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
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("vivek.wealthmax@gmail.com");
        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent...");
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {

        MimeMessage simpleMailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage,true,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("vivek.wealthmax@gmail.com");
            helper.setText(htmlContent,true);
            mailSender.send(simpleMailMessage);
            logger.info("Email has been sent...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, InputStream is) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // true indicates multipart message
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message,true);
            File file = new File("src/main/resources/email/test.png");
            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(), file); // Check this line
            helper.setFrom("vivek.wealthmax@gmail.com");
            mailSender.send(mimeMessage);
            logger.info("Email with attachment has been sent to {}", to);

        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }

    }
    
}

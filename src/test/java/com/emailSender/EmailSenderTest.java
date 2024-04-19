package com.emailSender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emailSender.service.EmailService;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest() {
        System.out.println("Sending email");
        emailService.sendEmail("vivek.s@wealthmax.co.uk", "Email from springboot",
                "This email is sent using springboot while creating email service.");
    }

    @Test
    void sendHtmlInEmail() {
        String html = "" +
                "<h1 style='color:red; border:1px solid red;'>Welcome, this is html mail.</h1>" + "";
        emailService.sendEmailWithHtml("vivek.s@wealthmax.co.uk", "Email from springboot",
                html);
    }

    @Test
    void sendEmailWithFile() {
        File file = new File(
                "C:/Users/wealt/OneDrive/Desktop/spring-boot/Email Sender App/Email-Sender-App/src/main/resources/static/images/book.jpeg");
        InputStream is;
        try {
            is = new FileInputStream(file);
            emailService.sendEmailWithFile("vivek.s@wealthmax.co.uk", "Email from springboot",
                    "This email contains file", is);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

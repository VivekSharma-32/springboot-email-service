package com.emailSender;

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
        emailService.sendEmail("vivek.s@wealthmax.co.uk", "Email from springboot", "This email is sent using springboot while creating email service.");
    }
    
}

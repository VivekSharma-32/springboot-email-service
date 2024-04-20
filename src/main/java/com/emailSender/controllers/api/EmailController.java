package com.emailSender.controllers.api;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emailSender.helper.CustomResponse;
import com.emailSender.helper.EmailRequest;
import com.emailSender.service.EmailService;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
        return ResponseEntity.ok(CustomResponse.builder().message("Email sent successfully !!")
                .httpStatus(HttpStatus.OK).success(true).build());
    }

    @PostMapping(value = "/send-with-file", consumes = "multipart/form-data")
    public ResponseEntity<CustomResponse> sendWithFile(@RequestPart(name = "request") EmailRequest request,
            @RequestPart("file") MultipartFile file) throws IOException {
        emailService.sendEmailWithFile(request.getTo(), request.getSubject(), request.getMessage(),
                file.getInputStream());
        return ResponseEntity.ok(CustomResponse.builder().message("Email sent successfully with file !!")
                .httpStatus(HttpStatus.OK).success(true).build());
    }
}

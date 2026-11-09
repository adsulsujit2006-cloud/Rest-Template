package com.notification_services.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification_services.dto.request.LoginOtpRequest;
import com.notification_services.dto.response.Apiresponce;
import com.notification_services.services.EmailServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private EmailServices emailServices;

    @PostMapping("/send/signup-otp")
    public ResponseEntity<Apiresponce> sentOtpHandler(@Valid @RequestBody LoginOtpRequest req) {
        log.info("REST Request : send signup otp, received = {}", req);   // debug line

        emailServices.sendVerificationOtpEmail(
                req.getUserEmail(), req.getOtp(), req.getSubject(), req.getText());

        Apiresponce res = new Apiresponce();
        res.setMessage("otp sent successfully");
        return ResponseEntity.ok(res);
    }
}
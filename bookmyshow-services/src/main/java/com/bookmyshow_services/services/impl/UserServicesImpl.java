package com.bookmyshow_services.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bookmyshow_services.dto.request.UserRequest;
import com.bookmyshow_services.exception.NullPointerException;
import com.bookmyshow_services.exception.ResourceNotFoundException;
import com.bookmyshow_services.modal.User;
import com.bookmyshow_services.modal.VerificationCode;
import com.bookmyshow_services.repository.UserRepository;
import com.bookmyshow_services.repository.VerificationCodeRepository;
import com.bookmyshow_services.services.UserServices;
import com.bookmyshow_services.util.OtpUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    String url = "http://localhost:2002/notification/send/signup-otp";

    @Override
    @Transactional
    public void sentLoginOtp(String email) {

        log.info("sentLoginOtp called with email = {}", email);

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        // Delete all previous OTP records for this email
        List<VerificationCode> existingCodes =
                verificationCodeRepository.findAllByEmail(email);

        if (!existingCodes.isEmpty()) {
            log.info("Deleting previous OTP records for {}", email);
            verificationCodeRepository.deleteAll(existingCodes);
        }

        // Generate OTP
        String otp = OtpUtil.generateOtp();

        if (otp == null || otp.trim().isEmpty()) {
            log.error("OTP generation failed");
            throw new ResourceNotFoundException("OTP not found");
        }

        // Create VerificationCode object
        VerificationCode verificationCode = new VerificationCode();

        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);

        if (verificationCode.getEmail() == null ||
                verificationCode.getOtp() == null) {

            throw new NullPointerException(
                    "OTP and email must be required");
        }

        verificationCodeRepository.save(verificationCode);

        // Send OTP through notification service
        String subject = "Book My Show login/signup OTP";
        String text = "Your login/signup OTP is - " + otp;

        Map<String, String> request = new HashMap<>();

        request.put("userEmail", email);
        request.put("otp", otp);
        request.put("subject", subject);
        request.put("text", text);

        try {

            restTemplate.postForEntity(url, request, String.class);

            log.info("OTP sent successfully to {}", email);

        } catch (RestClientException e) {

            log.error("Notification service call failed: {}", e.getMessage());

            throw new RestClientException(
                    "Could not send OTP email, please try again", e);
        }
    }

    @Override
    @Transactional
    public void verifyEmail(String email) {

        log.info("verifyEmail called with email = {}", email);

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        // Delete all previous OTP records for this email
        List<VerificationCode> existingCodes =
                verificationCodeRepository.findAllByEmail(email);

        if (!existingCodes.isEmpty()) {
            log.info("Deleting previous OTP records for {}", email);
            verificationCodeRepository.deleteAll(existingCodes);
        }

        // Generate OTP
        String otp = OtpUtil.generateOtp();

        if (otp == null || otp.trim().isEmpty()) {
            log.error("OTP generation failed");
            throw new ResourceNotFoundException("OTP not found");
        }

        // Create VerificationCode object
        VerificationCode verificationCode = new VerificationCode();

        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);

        if (verificationCode.getEmail() == null ||
                verificationCode.getOtp() == null) {

            throw new NullPointerException(
                    "OTP and email must be required");
        }

        verificationCodeRepository.save(verificationCode);

        // Send verification OTP
        String subject = "Book My Show login/Email verify OTP";
        String text = "Your verify/email OTP is - " + otp;

        Map<String, String> request = new HashMap<>();

        request.put("userEmail", email);
        request.put("otp", otp);
        request.put("subject", subject);
        request.put("text", text);

        try {

            restTemplate.postForEntity(url, request, String.class);

            log.info("Verification OTP sent successfully to {}", email);

        } catch (RestClientException e) {

            log.error("Notification service call failed: {}", e.getMessage());

            throw new RestClientException(
                    "Could not send OTP email, please try again", e);
        }
    }

    @Override
    @Transactional
    public UserRequest registerUser(UserRequest userRequest) {

        log.info("registerUser called");

        if (userRequest == null) {
            throw new ResourceNotFoundException(
                    "Enter appropriate details");
        }

        if (userRequest.getEmail() == null ||
                userRequest.getEmail().trim().isEmpty()) {

            throw new ResourceNotFoundException("Email is required");
        }

        if (userRequest.getOtp() == null ||
                userRequest.getOtp().trim().isEmpty()) {

            throw new ResourceNotFoundException("Please enter OTP");
        }

        // Find OTP records using email
        List<VerificationCode> verificationCodes =
                verificationCodeRepository.findAllByEmail(
                        userRequest.getEmail());

        if (verificationCodes.isEmpty()) {
            throw new ResourceNotFoundException(
                    "OTP not found for this email");
        }

        // Verify OTP
        boolean isOtpValid = false;

        for (VerificationCode verificationCode : verificationCodes) {

            if (userRequest.getOtp().equals(verificationCode.getOtp())) {
                isOtpValid = true;
                break;
            }
        }

        if (!isOtpValid) {
            throw new ResourceNotFoundException(
                    "Enter appropriate OTP");
        }

        // Convert UserRequest into User entity
        User user = modelMapper.map(userRequest, User.class);

        // Set email verification status
        user.setEmailverfy(true);

        // Save user details
        userRepository.save(user);

        // Delete OTP after successful registration
        verificationCodeRepository.deleteAll(verificationCodes);

        log.info("User registered successfully with email = {}",
                userRequest.getEmail());

        return userRequest;
    }
}
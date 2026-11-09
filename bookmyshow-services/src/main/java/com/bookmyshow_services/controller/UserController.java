package com.bookmyshow_services.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_services.dto.request.SendOtpRequest;
import com.bookmyshow_services.dto.request.UserRequest;
import com.bookmyshow_services.dto.response.Apiresponce;
import com.bookmyshow_services.services.UserServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServices userServices;
	
	/*
	 * REST Api : send login otp
	 */

	@PostMapping("/send/login-signup-otp")
	public ResponseEntity<Apiresponce> sentLoginOtpHandler(@Valid @RequestBody SendOtpRequest req) {
		log.info("REST Request : send login/signup otp, email = {}", req.getEmail());

		userServices.sentLoginOtp(req.getEmail());

		Apiresponce res = new Apiresponce();
		res.setMessage("otp sent successfully");

		return ResponseEntity.ok(res);
	}
	
	
	@PostMapping("/send/verifyemail")
	public ResponseEntity<Apiresponce> verifyemail(@Valid @RequestBody SendOtpRequest req) {
		log.info("REST Request : send login/signup otp, email = {}", req.getEmail());

		userServices.verifyEmail(req.getEmail());

		Apiresponce res = new Apiresponce();
		res.setMessage("otp sent successfully");

		return ResponseEntity.ok(res);
	}
	@PostMapping("/register")
	public ResponseEntity<UserRequest> registerUser(@Valid @RequestBody UserRequest req){
		
		userServices.registerUser(req);
		
		return new ResponseEntity<UserRequest>(req,HttpStatus.CREATED);
	}
	
	
	
}
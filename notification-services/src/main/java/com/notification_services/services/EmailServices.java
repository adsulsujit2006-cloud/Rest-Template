package com.notification_services.services;

public interface EmailServices {
	
	public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text);

}

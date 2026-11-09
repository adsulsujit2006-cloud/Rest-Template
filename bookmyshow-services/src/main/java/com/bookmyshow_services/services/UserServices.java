package com.bookmyshow_services.services;

import com.bookmyshow_services.dto.request.UserRequest;

public interface UserServices {
	
	public void sentLoginOtp(String email);
	
	public void verifyEmail(String email);
	
	public UserRequest registerUser(UserRequest userRequest);

}

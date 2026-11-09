package com.bookmyshow_services.dto.request;

import javax.validation.constraints.Email;

public class EmailVerifyRequest {
	
	@Email(message = "Enter email proper form")
	private String email;

}

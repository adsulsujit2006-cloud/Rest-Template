package com.notification_services.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginOtpRequest {
	@NotBlank(message = "userEmail is required")
    @Email(message = "invalid email")
    private String userEmail;

    @NotBlank(message = "otp is required")
    private String otp;

    @NotBlank(message = "subject is required")
    private String subject;

    @NotBlank(message = "text is required")
    private String text;
}

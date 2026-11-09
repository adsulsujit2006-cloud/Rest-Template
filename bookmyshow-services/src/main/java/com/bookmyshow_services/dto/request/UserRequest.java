package com.bookmyshow_services.dto.request;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.bookmyshow_services.enumes.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	private String otp;

	private String fname;

	private String lName;

	private String email;

	private String mobailNumbar;

	private Date bDate;

	private Gender gender;
}

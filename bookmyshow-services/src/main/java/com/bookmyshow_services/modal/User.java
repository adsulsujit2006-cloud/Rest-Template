package com.bookmyshow_services.modal;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.bookmyshow_services.enumes.Gender;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "First_Name")
	private String fname;
	
	@Column(name = "Last_Name")
	private String lName;
	
	@Column(name = "Email_Address")
	private String email;
	
	private String mobailNumbar;
	
	@Column(name = "Birth_Date")
	private Date bDate;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private boolean emailverfy = false;
	
	@CreationTimestamp
	private LocalDate registerTime;
	
	private LocalDate loginDate;
	

}

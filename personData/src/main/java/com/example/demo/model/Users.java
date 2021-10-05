package com.example.demo.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.enums.Status;

import lombok.Data;

@Data
@Document
public class Users {
	/**
	 * User model class
	 */
	
	@Id
	private String id;
	private Status status;
	@NotBlank(message = "Invalid field")
	private String givenName;
	private String familiyName;
    private String birthDate;
    private Address address;
	private List<String> numbers;
}

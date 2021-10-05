package com.example.demo.model;

import com.example.demo.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
	/**
	 * Address model class
	 */
	
	private Type type;
    private Long zipCode;
	private String state;
	private String city;
	private String street;
	private Long number;
	private String complement;
	private String district;
}

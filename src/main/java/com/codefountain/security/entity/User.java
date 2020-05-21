package com.codefountain.security.entity;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@Document
public class User {

	@Id
	private String id;
	@NonNull
	private final String username;
	@NonNull
	private String password;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@Email
	@NonNull
	private String email;
	
}

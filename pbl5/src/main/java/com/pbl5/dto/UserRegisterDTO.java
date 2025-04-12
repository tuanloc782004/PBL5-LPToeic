package com.pbl5.dto;

public class UserRegisterDTO {

	private String username;
	private String email;
	private String password;
	private String confirmPassword;

	public UserRegisterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegisterDTO(String username, String email, String password, String confirmPassword) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}

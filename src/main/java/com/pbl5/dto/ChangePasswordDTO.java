package com.pbl5.dto;

public class ChangePasswordDTO {

	private String newPassword;
	private String confirmPassword;

	public ChangePasswordDTO() {
	}

	public ChangePasswordDTO(String newPassword, String confirmPassword) {
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
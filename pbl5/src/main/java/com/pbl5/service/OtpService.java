package com.pbl5.service;

public interface OtpService {

	public String generateOtp(String email);

	public boolean verifyOtp(String email, String otp);

	public void clearOtp(String email);

}

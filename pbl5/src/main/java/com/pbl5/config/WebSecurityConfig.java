package com.pbl5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.pbl5.security.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Phan quyen truy cap
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/", "/login", "/register", "/resend-otp", "/verify-otp")
						.permitAll().requestMatchers("/admin/**").hasAuthority("ADMIN").requestMatchers("/user/**")
						.hasAuthority("USER").anyRequest().authenticated() // Các yêu cầu khác cần xác thực
		).formLogin((form) -> form.loginPage("/login") // Trang đăng nhập tùy chỉnh
				.loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
				.successHandler(new CustomAuthenticationSuccessHandler()))
				.logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"));

		return http.build();
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {

		return (web) -> web.ignoring().requestMatchers("/login-form-asset/**", "/upload-dir/**", "/admin-asset/**",
				"/user-asset/**");

	}
}
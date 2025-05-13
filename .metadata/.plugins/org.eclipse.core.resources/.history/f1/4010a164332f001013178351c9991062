package com.pbl5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.pbl5.security.CustomAuthenticationFailureHandler;
import com.pbl5.security.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private CustomAuthenticationFailureHandler failureHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/login", "/register", "/resend-otp", "/verify-otp", "/vocabulary-lesson/**",
						"/grammar-lesson/**", "/listening-exercise-card", "/reading-exercise-card",
						"/part1-listening-exercise/**", "/part2-listening-exercise/**", "/part3-listening-exercise/**",
						"/part4-listening-exercise/**", "/part5-reading-exercise/**", "/part6-reading-exercise/**",
						"/part7-reading-exercise/**", "/mock-exam-card")
				.permitAll().requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN").requestMatchers("/**")
				.hasAuthority("ADMIN").anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").loginProcessingUrl("/login").usernameParameter("username")
						.passwordParameter("password").successHandler(new CustomAuthenticationSuccessHandler())
						.failureHandler(failureHandler))
				.logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"));

		return http.build();
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/login-form-asset/**", "/upload-dir/**", "/admin-asset/**",
				"/user-asset/**");
	}

}
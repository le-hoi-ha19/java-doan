package com.example.fashion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.LogoutDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.fashion.config.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((auth) -> auth.requestMatchers("/**").permitAll()
						.requestMatchers("/shop/**").permitAll()
						.requestMatchers("/cart/**").permitAll()
						.requestMatchers("/admin/login/**").permitAll()
						.requestMatchers("/admin/**")
						.hasAuthority("ADMIN").anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/admin/login").loginProcessingUrl("/admin/login")
						.usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/admin", true))
				.logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/admin/login"))
				.logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/admin/login?logout"));
		return http.build();

	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.debug(true).ignoring().requestMatchers("/static/**", "/assets/**", "/admun/**",
				"/uploads/**");

	}
}
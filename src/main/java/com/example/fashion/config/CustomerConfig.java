// package com.example.fashion.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @Order(2)
// @EnableWebSecurity
// public class CustomerConfig {

//     @Autowired
//     private CustomUserDetailService customUserDetailService;

//     @Autowired 
// 	private CustomSuccessHandler customSuccessHandler;

//     @Bean
//     public PasswordEncoder customerPasswordEncoder() {
//         return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//     }

//     @Bean(name = "customSecurityFilterChain")
// 	SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
// 		// http.csrf(csrf -> csrf.disable())
// 		// 		.authorizeHttpRequests((auth) -> auth.requestMatchers("/**").permitAll()
// 		// 				.requestMatchers("/login/**")
// 		// 				.hasAuthority("CUSTOMER").anyRequest().authenticated())
// 		// 		.formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login")
//         //         .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/", true)
// 		// 				.permitAll())
// 		// 		.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"))
// 		// 		.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"));
// 		return http.build();

// 	}
// }

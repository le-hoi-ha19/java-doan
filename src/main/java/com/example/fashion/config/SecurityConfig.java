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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.fashion.config.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private CustomSuccessHandler successHandler;

    //mã hóa mật khẩu của người dùng trước khi lưu vào cơ sở dữ liệu
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Cấu hình các chính sách bảo mật cơ bản cho ứng dụng, 
    //bao gồm xác thực người dùng, phân quyền, đăng nhập, đăng xuất, và xử lý lỗi truy cập không hợp lệ.
    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeRequests(authorize -> authorize
                .requestMatchers("/*", "/products/**", "/product/**", "/login/**","/register/**", "/contact/**", "/product-details/**","/products-category/**","/products-branch/**", "/blog-detail/**").permitAll() //Các trang này được phép truy cập công khai (không cần đăng nhập).
                .requestMatchers("/admin/**").hasAuthority("ADMIN") 
                .anyRequest().authenticated()) 
            .formLogin(login -> login
                .loginPage("/login") 
                .loginProcessingUrl("/login") 
                .successHandler(successHandler) 
                .permitAll()) 
            .logout(logout -> logout
                .logoutUrl("/logout") //URL để người dùng đăng xuất.
                .logoutSuccessUrl("/login") //Sau khi đăng xuất thành công, người dùng sẽ được chuyển hướng về trang đăng nhập với tham số ?logout
                .invalidateHttpSession(true) //Hủy bỏ session sau khi đăng xuất
                .clearAuthentication(true)) //Xóa thông tin xác thực khi người dùng đăng xuất.
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/access-denied") //Nếu người dùng cố gắng truy cập vào một trang mà họ không có quyền, họ sẽ được chuyển hướng tới trang lỗi access-denied
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/not-found"); // Nếu trang không tồn tại hoặc không được phép, chuyển hướng đến trang 404
                }));

        return http.build();
    }

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.debug(true).ignoring().requestMatchers("/static/**", "/assets/**", "/admun/**",
				"/uploads/**");

	}
}

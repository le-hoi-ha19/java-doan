package com.example.fashion.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.fashion.models.User;

// Lớp cung cấp thông tin người dùng cho Spring Security để xác thực và phân quyền.
public class CustomUserDetails implements UserDetails {
    private User user;
    private Collection<? extends GrantedAuthority> authorities;
    
    public CustomUserDetails() {
        // Constructor mặc định
    }

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.user = user;
        this.authorities = authorities;
    }

	// Trả về quyền của người dùng
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

	// Trả về mật khẩu của người dùng
    @Override
    public String getPassword() {
        return user.getPassword();
    }

	// Trả về tên người dùng
    @Override
    public String getUsername() {
        return user.getUsername();
    }

	// Tài khoản không hết hạn
    @Override
    public boolean isAccountNonExpired() {
		return true;
    }

	// Tài khoản không bị khóa
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

	// Thông tin xác thực không hết hạn
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

	// Tài khoản được kích hoạt
    @Override
    public boolean isEnabled() {
        return true;
    }
}


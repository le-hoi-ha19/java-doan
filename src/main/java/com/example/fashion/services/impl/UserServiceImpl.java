package com.example.fashion.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fashion.models.User;
import com.example.fashion.models.UserRole;
import com.example.fashion.repository.RoleRepository;
import com.example.fashion.repository.UserRepository;
import com.example.fashion.repository.UserRoleRepository;
import com.example.fashion.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional // Thêm annotation này để đảm bảo tính toàn vẹn giao dịch
    public Boolean createWithUserRole(User user) {
        try {
            // Lưu User vào cơ sở dữ liệu
            this.userRepository.save(user);

            // Tạo một đối tượng UserRole
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(roleRepository.findByName("ADMIN"));

            // Lưu UserRole vào cơ sở dữ liệu
            this.userRoleRepository.save(userRole);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    @Override
    public User findByID(Long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Boolean createWithUserRoleCUSTOMER(User user) {
        try {
            // Lưu User vào cơ sở dữ liệu
            this.userRepository.save(user);

            // Tạo một đối tượng UserRole
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(roleRepository.findByName("CUSTOMER"));

            // Lưu UserRole vào cơ sở dữ liệu
            this.userRoleRepository.save(userRole);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    @Override
    public List<User> getCustomerUsers() {
        return userRepository.findCustomerUsers();
    }

    @Override
    public Boolean delete(Long id) {
        try {
            // Xóa các vai trò của người dùng từ bảng liên kết
            User user = findByID(id);
            Set<UserRole> userRoles = user.getUserRoles();
            for (UserRole userRole : userRoles) {
                userRoleRepository.delete(userRole);
            }

            // Xóa người dùng
            userRepository.delete(user);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public long countTotalUsers() {
        return this.userRepository.countCustomerUsers();

    }

}
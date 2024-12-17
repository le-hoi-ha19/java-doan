package com.example.fashion.utils;

import java.text.Normalizer;

public class SlugUtils {
    public static String createSlug(String name) {
        // Chuyển thành chữ thường
        String slug = name.toLowerCase();
        
        // Loại bỏ các ký tự đặc biệt
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = slug.replaceAll("[^\\p{ASCII}]", "");
        
        // Thay thế dấu cách bằng dấu gạch ngang
        slug = slug.replaceAll("\\s+", "-");
        
        // Loại bỏ các ký tự không phải là chữ cái và số
        slug = slug.replaceAll("[^a-z0-9-]", "");
        
        // Tránh trùng lặp gạch ngang liên tiếp
        slug = slug.replaceAll("-+", "-");
        
        // Loại bỏ dấu gạch ngang ở đầu và cuối
        slug = slug.replaceAll("^-", "").replaceAll("-$", "");
        
        return slug;
    } 
}

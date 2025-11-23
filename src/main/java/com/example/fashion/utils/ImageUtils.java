package com.example.fashion.utils;

public class ImageUtils {
    
    private static final String PLACEHOLDER_PRODUCT = "/images/placeholder-product.svg";
    private static final String PLACEHOLDER_BRAND = "/images/placeholder-brand.svg";
    private static final String PLACEHOLDER_POST = "/images/placeholder-post.svg";
    private static final String PLACEHOLDER_USER = "/images/placeholder-user.svg";
    
    public static String getProductImage(String avatar) {
        if (avatar == null || avatar.trim().isEmpty()) {
            return PLACEHOLDER_PRODUCT;
        }
        return "/uploads/" + avatar;
    }
    
    public static String getBrandLogo(String logo) {
        if (logo == null || logo.trim().isEmpty()) {
            return PLACEHOLDER_BRAND;
        }
        return "/uploads/" + logo;
    }
    
    public static String getPostThumbnail(String thumbnail) {
        if (thumbnail == null || thumbnail.trim().isEmpty()) {
            return PLACEHOLDER_POST;
        }
        return "/uploads/" + thumbnail;
    }
    
    public static String getUserAvatar(String avatar) {
        if (avatar == null || avatar.trim().isEmpty()) {
            return PLACEHOLDER_USER;
        }
        return "/uploads/" + avatar;
    }
    
    public static boolean hasImage(String imagePath) {
        return imagePath != null && !imagePath.trim().isEmpty();
    }
}

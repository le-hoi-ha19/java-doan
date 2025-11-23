-- Insert roles
INSERT INTO role (name) VALUES
('ROLE_ADMIN'),
('ROLE_USER'),
('ROLE_MANAGER');

-- Insert sample users (password: 123456 - BCrypt encoded)
INSERT INTO users (username, password, email, fullname, telephone, address, gender, enabled, images) VALUES
('admin', '$2a$10$X5wFWtliQ/Norm8XW2TNAeRiMJNNHPyY8YfUg2wT5C/Y5tZy0E87e', 'admin@fashion.com', 'Admin User', '0123456789', '123 Nguyen Trai, Thanh Xuan, Ha Noi', TRUE, TRUE, '/images/users/admin.jpg'),
('user1', '$2a$10$X5wFWtliQ/Norm8XW2TNAeRiMJNNHPyY8YfUg2wT5C/Y5tZy0E87e', 'user1@gmail.com', 'Nguyen Van A', '0987654321', '456 Le Loi, Quan 1, Ho Chi Minh', TRUE, TRUE, '/images/users/user1.jpg'),
('user2', '$2a$10$X5wFWtliQ/Norm8XW2TNAeRiMJNNHPyY8YfUg2wT5C/Y5tZy0E87e', 'user2@gmail.com', 'Tran Thi B', '0912345678', '789 Tran Phu, Hai Chau, Da Nang', FALSE, TRUE, '/images/users/user2.jpg'),
('user3', '$2a$10$X5wFWtliQ/Norm8XW2TNAeRiMJNNHPyY8YfUg2wT5C/Y5tZy0E87e', 'user3@gmail.com', 'Le Van C', '0901234567', '321 Hoang Dieu, Nha Trang, Khanh Hoa', TRUE, TRUE, NULL),
('manager', '$2a$10$X5wFWtliQ/Norm8XW2TNAeRiMJNNHPyY8YfUg2wT5C/Y5tZy0E87e', 'manager@fashion.com', 'Manager User', '0908888888', 'Ha Noi, Vietnam', TRUE, TRUE, NULL);

-- Insert users_roles
INSERT INTO users_roles (userId, roleId) VALUES
(1, 1), -- admin has ROLE_ADMIN
(1, 2), -- admin also has ROLE_USER
(2, 2), -- user1 has ROLE_USER
(3, 2), -- user2 has ROLE_USER
(4, 2), -- user3 has ROLE_USER
(5, 3), -- manager has ROLE_MANAGER
(5, 2); -- manager also has ROLE_USER

-- Insert brands
INSERT INTO Brands (BrandName, Slug, Logo, description) VALUES
('Nike', 'nike', '/images/brands/nike.png', 'Thương hiệu thể thao hàng đầu thế giới'),
('Adidas', 'adidas', '/images/brands/adidas.png', 'Thương hiệu thể thao nổi tiếng'),
('Zara', 'zara', '/images/brands/zara.png', 'Thời trang công sở và casual'),
('H&M', 'h-m', '/images/brands/hm.png', 'Thời trang giá rẻ chất lượng'),
('Uniqlo', 'uniqlo', '/images/brands/uniqlo.png', 'Thời trang tối giản Nhật Bản'),
('Local Brand', 'local-brand', '/images/brands/local.png', 'Thương hiệu thời trang Việt Nam');

-- Insert categories
INSERT INTO Categories (CatName, Slug, description, image_url, parent_id) VALUES
('Nam', 'nam', 'Thời trang nam', '/images/categories/nam.jpg', NULL),
('Nữ', 'nu', 'Thời trang nữ', '/images/categories/nu.jpg', NULL),
('Phụ kiện', 'phu-kien', 'Phụ kiện thời trang', '/images/categories/phu-kien.jpg', NULL),
('Áo thun nam', 'ao-thun-nam', 'Áo thun nam các loại', '/images/categories/ao-thun-nam.jpg', 1),
('Quần jean nam', 'quan-jean-nam', 'Quần jean nam', '/images/categories/quan-jean-nam.jpg', 1),
('Áo khoác nam', 'ao-khoac-nam', 'Áo khoác nam', '/images/categories/ao-khoac-nam.jpg', 1),
('Áo thun nữ', 'ao-thun-nu', 'Áo thun nữ các loại', '/images/categories/ao-thun-nu.jpg', 2),
('Váy đầm', 'vay-dam', 'Váy đầm nữ', '/images/categories/vay-dam.jpg', 2),
('Quần jean nữ', 'quan-jean-nu', 'Quần jean nữ', '/images/categories/quan-jean-nu.jpg', 2),
('Túi xách', 'tui-xach', 'Túi xách thời trang', '/images/categories/tui-xach.jpg', 3),
('Giày dép', 'giay-dep', 'Giày dép thời trang', '/images/categories/giay-dep.jpg', 3),
('Mũ nón', 'mu-non', 'Mũ nón thời trang', '/images/categories/mu-non.jpg', 3);

-- Insert products
INSERT INTO Products (ProductName, slug, Description, Price, SalePrice, Quantity, CatID, BrandID, Avatar, Img1, Img2, Img3, status, sku) VALUES
-- Áo thun nam
('Áo thun Nike Dri-FIT', 'ao-thun-nike-dri-fit', 'Áo thun thể thao Nike công nghệ Dri-FIT thấm hút mồ hôi tốt', 450000, 380000, 100, 4, 1, '/images/products/ao-thun-nam-1.jpg', '/images/products/ao-thun-nam-1-2.jpg', '/images/products/ao-thun-nam-1-3.jpg', '/images/products/ao-thun-nam-1-4.jpg', 'ACTIVE', 'SKU-ATN-001'),
('Áo thun Adidas Basic', 'ao-thun-adidas-basic', 'Áo thun cotton 100% basic logo Adidas', 350000, 300000, 80, 4, 2, '/images/products/ao-thun-nam-2.jpg', '/images/products/ao-thun-nam-2-2.jpg', NULL, NULL, 'ACTIVE', 'SKU-ATN-002'),
('Áo thun Local Brand oversized', 'ao-thun-local-brand-oversized', 'Áo thun form rộng local brand chất cotton co giãn', 280000, 250000, 120, 4, 6, '/images/products/ao-thun-nam-3.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-ATN-003'),
('Áo polo Uniqlo', 'ao-polo-uniqlo', 'Áo polo cổ bẻ công sở Uniqlo', 390000, NULL, 60, 4, 5, '/images/products/ao-polo-nam-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-ATN-004'),

-- Quần jean nam
('Quần jean Nike Slim Fit', 'quan-jean-nike-slim-fit', 'Quần jean nam dáng slim fit co giãn', 750000, 650000, 50, 5, 1, '/images/products/quan-jean-nam-1.jpg', '/images/products/quan-jean-nam-1-2.jpg', NULL, NULL, 'ACTIVE', 'SKU-QJN-001'),
('Quần jean Zara Skinny', 'quan-jean-zara-skinny', 'Quần jean skinny ôm body Zara', 680000, 580000, 45, 5, 3, '/images/products/quan-jean-nam-2.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-QJN-002'),
('Quần jean H&M Regular', 'quan-jean-hm-regular', 'Quần jean regular fit H&M', 550000, 480000, 70, 5, 4, '/images/products/quan-jean-nam-3.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-QJN-003'),

-- Áo khoác nam
('Áo khoác Nike Windrunner', 'ao-khoac-nike-windrunner', 'Áo khoác gió Nike chống nước', 1200000, 1000000, 35, 6, 1, '/images/products/ao-khoac-nam-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-AKN-001'),
('Áo khoác Adidas Bomber', 'ao-khoac-adidas-bomber', 'Áo bomber jacket Adidas phong cách thể thao', 950000, 850000, 40, 6, 2, '/images/products/ao-khoac-nam-2.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-AKN-002'),
('Áo khoác jeans Local Brand', 'ao-khoac-jeans-local-brand', 'Áo khoác jeans unisex local brand', 680000, 600000, 55, 6, 6, '/images/products/ao-khoac-nam-3.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-AKN-003'),

-- Áo thun nữ
('Áo thun Nike nữ Basic', 'ao-thun-nike-nu-basic', 'Áo thun nữ Nike cotton thoáng mát', 380000, 320000, 90, 7, 1, '/images/products/ao-thun-nu-1.jpg', '/images/products/ao-thun-nu-1-2.jpg', NULL, NULL, 'ACTIVE', 'SKU-ATNU-001'),
('Áo thun Zara nữ họa tiết', 'ao-thun-zara-nu-hoa-tiet', 'Áo thun nữ Zara họa tiết thời trang', 420000, 360000, 75, 7, 3, '/images/products/ao-thun-nu-2.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-ATNU-002'),
('Áo croptop H&M', 'ao-croptop-hm', 'Áo croptop nữ H&M năng động', 280000, 250000, 100, 7, 4, '/images/products/ao-croptop-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-ATNU-003'),

-- Váy đầm
('Váy hoa nhí Zara', 'vay-hoa-nhi-zara', 'Váy hoa nhí dáng xòe Zara', 580000, 500000, 40, 8, 3, '/images/products/vay-1.jpg', '/images/products/vay-1-2.jpg', '/images/products/vay-1-3.jpg', NULL, 'ACTIVE', 'SKU-VD-001'),
('Váy công sở H&M', 'vay-cong-so-hm', 'Váy công sở H&M thanh lịch', 650000, NULL, 35, 8, 4, '/images/products/vay-2.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-VD-002'),
('Đầm maxi Zara', 'dam-maxi-zara', 'Đầm maxi dạ hội Zara cao cấp', 1200000, 1000000, 25, 8, 3, '/images/products/dam-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-VD-003'),

-- Quần jean nữ
('Quần jean nữ Zara Skinny', 'quan-jean-nu-zara-skinny', 'Quần jean nữ skinny Zara co giãn', 680000, 580000, 60, 9, 3, '/images/products/quan-jean-nu-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-QJNU-001'),
('Quần jean nữ H&M Boyfriend', 'quan-jean-nu-hm-boyfriend', 'Quần jean nữ boyfriend H&M', 590000, 520000, 50, 9, 4, '/images/products/quan-jean-nu-2.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-QJNU-002'),

-- Phụ kiện
('Túi xách mini Zara', 'tui-xach-mini-zara', 'Túi xách mini đeo chéo Zara', 450000, 380000, 70, 10, 3, '/images/products/tui-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-TX-001'),
('Balo Nike Sportswear', 'balo-nike-sportswear', 'Balo Nike đựng laptop thể thao', 890000, 750000, 45, 10, 1, '/images/products/balo-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-TX-002'),
('Túi tote H&M', 'tui-tote-hm', 'Túi tote canvas H&M đi học đi làm', 280000, 250000, 80, 10, 4, '/images/products/tui-tote-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-TX-003'),
('Giày Nike Air Force 1', 'giay-nike-air-force-1', 'Giày sneaker Nike Air Force 1 classic', 2500000, 2200000, 30, 11, 1, '/images/products/giay-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-GD-001'),
('Giày Adidas Superstar', 'giay-adidas-superstar', 'Giày Adidas Superstar iconic', 2200000, 1900000, 35, 11, 2, '/images/products/giay-2.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-GD-002'),
('Dép Adidas Adilette', 'dep-adidas-adilette', 'Dép đi trong nhà Adidas', 450000, 400000, 100, 11, 2, '/images/products/dep-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-GD-003'),
('Mũ lưỡi trai Nike', 'mu-luoi-trai-nike', 'Mũ lưỡi trai Nike thêu logo', 380000, 320000, 90, 12, 1, '/images/products/mu-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-MN-001'),
('Mũ bucket Adidas', 'mu-bucket-adidas', 'Mũ bucket Adidas phong cách streetwear', 420000, 380000, 75, 12, 2, '/images/products/mu-2.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-MN-002'),
('Nón len Local Brand', 'non-len-local-brand', 'Nón len local brand mùa đông', 280000, 250000, 60, 12, 6, '/images/products/non-len-1.jpg', NULL, NULL, NULL, 'ACTIVE', 'SKU-MN-003');

-- Insert Carts
INSERT INTO Carts (userId, TotalsItem, TotalsPrice) VALUES
(2, 3, 1280000),
(3, 2, 1140000),
(4, 2, 3200000);

-- Insert CartItems
INSERT INTO CartItems (CartID, ProductID, Quantity, TotalsPrice) VALUES
(1, 2, 2, 600000),
(1, 6, 1, 580000),
(2, 11, 1, 320000),
(2, 19, 2, 760000),
(3, 8, 1, 1000000),
(3, 22, 1, 2200000);

-- Insert Orders
INSERT INTO Orders (userId, OrderDate, DeliveryDate, TotalsPrice, ShippingFee, OrderStatus, Notes) VALUES
(2, '2024-01-01', '2024-01-05', 950000, 30000, 'COMPLETED', 'Giao hàng giờ hành chính'),
(3, '2024-01-01', NULL, 1150000, 50000, 'SHIPPING', 'Gọi trước khi giao'),
(4, '2024-01-01', NULL, 1000000, 30000, 'PENDING', NULL),
(2, '2024-01-02', '2024-01-03', 2580000, 0, 'COMPLETED', 'Đơn hàng VIP');

-- Insert OrderDetail
INSERT INTO OrderDetail (OrderID, ProductID, Quantity, Price, TotalPrice) VALUES
(1, 1, 1, 380000, 380000),
(1, 13, 1, 250000, 250000),
(1, 25, 1, 320000, 320000),
(2, 5, 1, 650000, 650000),
(2, 14, 1, 500000, 500000),
(3, 8, 1, 1000000, 1000000),
(4, 22, 1, 2200000, 2200000),
(4, 1, 1, 380000, 380000);

-- Insert Comments
INSERT INTO Comments (ProductID, fullname, email, comment, rating, createDate) VALUES
(1, 'Nguyen Van A', 'user1@gmail.com', 'Áo rất đẹp, chất liệu tốt, đúng như mô tả', 5, '2024-01-05 10:30:00'),
(13, 'Nguyen Van A', 'user1@gmail.com', 'Áo đẹp nhưng hơi nhỏ, nên lấy size lớn hơn', 4, '2024-01-05 10:35:00'),
(5, 'Tran Thi B', 'user2@gmail.com', 'Quần jean đẹp, co giãn tốt, rất hài lòng', 5, '2024-01-03 14:20:00'),
(14, 'Tran Thi B', 'user2@gmail.com', 'Váy xinh lắm, chất vải mềm mại', 5, '2024-01-03 14:25:00'),
(8, 'Le Van C', 'user3@gmail.com', 'Áo khoác đẹp nhưng giá hơi cao', 4, '2024-01-02 09:15:00'),
(22, 'Nguyen Van A', 'user1@gmail.com', 'Giày Nike xịn, đi rất êm chân', 5, '2024-01-04 16:40:00'),
(2, 'Pham Thi D', 'user4@gmail.com', 'Áo Adidas chất lượng tốt, giao hàng nhanh', 5, '2024-01-06 11:00:00'),
(11, 'Hoang Van E', 'user5@gmail.com', 'Áo thun nữ đẹp, form chuẩn', 4, '2024-01-07 15:30:00');

-- Insert Posts
INSERT INTO Posts (Title, Slug, Description, Contents, Thumnail, CreatedDate) VALUES
('Top 10 xu hướng thời trang 2024', 'top-10-xu-huong-thoi-trang-2024', 'Khám phá những xu hướng thời trang hot nhất năm 2024', 
'<h2>Xu hướng thời trang 2024</h2><p>Năm 2024 hứa hẹn mang đến nhiều xu hướng thời trang mới mẻ và độc đáo...</p>', 
'/images/posts/post-1.jpg', '2024-01-01'),

('Cách phối đồ công sở thanh lịch', 'cach-phoi-do-cong-so-thanh-lich', 'Hướng dẫn cách phối đồ công sở chuyên nghiệp', 
'<h2>Phối đồ công sở</h2><p>Phong cách công sở luôn đòi hỏi sự thanh lịch và chuyên nghiệp...</p>', 
'/images/posts/post-2.jpg', '2024-01-05'),

('Bí quyết chọn giày phù hợp', 'bi-quyet-chon-giay-phu-hop', 'Hướng dẫn chọn giày phù hợp với từng dáng chân', 
'<h2>Chọn giày đúng cách</h2><p>Việc chọn giày phù hợp không chỉ giúp bạn thoải mái mà còn tôn lên vẻ đẹp...</p>', 
'/images/posts/post-3.jpg', '2024-01-10'),

('Xu hướng streetwear 2024', 'xu-huong-streetwear-2024', 'Phong cách streetwear đang làm mưa làm gió', 
'<h2>Streetwear 2024</h2><p>Phong cách streetwear tiếp tục khẳng định vị thế của mình trong làng thời trang...</p>', 
'/images/posts/post-4.jpg', '2024-01-15'),

('Cách bảo quản quần áo đúng cách', 'cach-bao-quan-quan-ao-dung-cach', 'Hướng dẫn bảo quản quần áo để luôn mới', 
'<h2>Bảo quản quần áo</h2><p>Việc bảo quản quần áo đúng cách giúp chúng giữ được độ bền và màu sắc...</p>', 
'/images/posts/post-5.jpg', '2024-01-20');

-- Insert Contact
INSERT INTO contact (fullname, email, telephone, subject, message) VALUES
('Nguyen Van X', 'nguyenvanx@gmail.com', '0901234567', 'Hỏi về sản phẩm', 'Cho tôi hỏi sản phẩm giày Nike có size 42 không ạ?'),
('Tran Thi Y', 'tranthiy@gmail.com', '0912345678', 'Phản hồi dịch vụ', 'Dịch vụ giao hàng rất tốt, tôi rất hài lòng'),
('Le Van Z', 'levanz@gmail.com', '0923456789', 'Yêu cầu đổi trả', 'Tôi muốn đổi size áo đã mua'),
('Pham Thi T', 'phamthit@gmail.com', '0934567890', 'Hỏi về khuyến mãi', 'Shop có chương trình khuyến mãi nào không ạ?');

-- Insert Notifications
INSERT INTO notifications (user_id, title, message, is_read) VALUES
(2, 'Đơn hàng đã được xác nhận', 'Đơn hàng ORD20240101001 của bạn đã được xác nhận và đang được chuẩn bị', FALSE),
(2, 'Đơn hàng đã giao thành công', 'Đơn hàng ORD20240101001 đã được giao thành công', TRUE),
(3, 'Đơn hàng đang giao', 'Đơn hàng ORD20240101002 đang trên đường giao đến bạn', FALSE),
(4, 'Đơn hàng chờ xác nhận', 'Cảm ơn bạn đã đặt hàng. Đơn hàng ORD20240101003 đang chờ xác nhận', FALSE),
(2, 'Khuyến mãi đặc biệt', 'Flash sale giảm 50% tất cả sản phẩm Nike - Chỉ hôm nay!', FALSE),
(3, 'Sản phẩm yêu thích có khuyến mãi', 'Sản phẩm trong danh sách yêu thích của bạn đang giảm giá', FALSE);

-- Insert Shipping Methods
INSERT INTO shipping_methods (name, description, fee, estimated_days, status) VALUES
('Giao hàng tiêu chuẩn', 'Giao hàng trong 5-7 ngày', 30000, 6, 'ACTIVE'),
('Giao hàng nhanh', 'Giao hàng trong 2-3 ngày', 50000, 2, 'ACTIVE'),
('Giao hàng hỏa tốc', 'Giao hàng trong 24h', 100000, 1, 'ACTIVE'),
('Miễn phí vận chuyển', 'Miễn phí cho đơn hàng trên 500k', 0, 5, 'ACTIVE');

-- Insert Coupons
INSERT INTO coupons (code, description, discount_type, discount_value, min_order_amount, max_discount_amount, usage_limit, start_date, end_date, status) VALUES
('WELCOME10', 'Giảm 10% cho khách hàng mới', 'PERCENTAGE', 10, 200000, 100000, 100, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE'),
('FREESHIP', 'Miễn phí vận chuyển đơn hàng trên 500k', 'FIXED_AMOUNT', 30000, 500000, 30000, 1000, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE'),
('SUMMER50', 'Giảm 50k cho mùa hè', 'FIXED_AMOUNT', 50000, 300000, 50000, 200, '2024-06-01 00:00:00', '2024-08-31 23:59:59', 'ACTIVE'),
('FLASH20', 'Flash sale giảm 20%', 'PERCENTAGE', 20, 500000, 200000, 50, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'ACTIVE'),
('NEWYEAR2024', 'Khuyến mãi năm mới 2024', 'PERCENTAGE', 15, 300000, 150000, 500, '2024-01-01 00:00:00', '2024-01-31 23:59:59', 'ACTIVE');

-- Insert Coupon Usage (các coupon đã được sử dụng)
INSERT INTO coupon_usage (coupon_id, user_id, order_id, discount_amount) VALUES
(1, 2, 1, 95000),
(4, 3, 2, 230000);

-- Insert Wishlists
INSERT INTO wishlists (user_id, product_id) VALUES
(2, 22), -- user1 thích Giày Nike Air Force 1
(2, 23), -- user1 thích Giày Adidas Superstar
(2, 14), -- user1 thích Váy hoa nhí Zara
(3, 1),  -- user2 thích Áo thun Nike Dri-FIT
(3, 5),  -- user2 thích Quần jean Nike Slim Fit
(3, 8),  -- user2 thích Áo khoác Nike Windrunner
(4, 11), -- user3 thích Áo thun Nike nữ Basic
(4, 14), -- user3 thích Váy hoa nhí Zara
(4, 19); -- user3 thích Túi xách mini Zara

-- Insert roles
INSERT INTO role (name) VALUES
('ADMIN'),
('USER');

-- Insert sample users (password giống dungnhinminh - BCrypt encoded)
INSERT INTO users (username, password, email, fullname, telephone, address, gender, enabled, images) VALUES
('admin', '$2a$10$K.XEb18nbVMenDLP.w1kb.nTR0YN2DjqzsRedbt/64s2l1H5dinOW', 'admin@fashion.com', 'Admin User', '0123456789', '123 Nguyen Trai, Thanh Xuan, Ha Noi', TRUE, TRUE, '/images/users/admin.jpg'),
('user1', '$2a$10$K.XEb18nbVMenDLP.w1kb.nTR0YN2DjqzsRedbt/64s2l1H5dinOW', 'user1@gmail.com', 'Nguyen Van A', '0987654321', '456 Le Loi, Quan 1, Ho Chi Minh', TRUE, TRUE, '/images/users/user1.jpg'),
('user2', '$2a$10$K.XEb18nbVMenDLP.w1kb.nTR0YN2DjqzsRedbt/64s2l1H5dinOW', 'user2@gmail.com', 'Tran Thi B', '0912345678', '789 Tran Phu, Hai Chau, Da Nang', FALSE, TRUE, '/images/users/user2.jpg'),
('user3', '$2a$10$K.XEb18nbVMenDLP.w1kb.nTR0YN2DjqzsRedbt/64s2l1H5dinOW', 'user3@gmail.com', 'Le Van C', '0901234567', '321 Hoang Dieu, Nha Trang, Khanh Hoa', TRUE, TRUE, NULL),
('manager', '$2a$10$K.XEb18nbVMenDLP.w1kb.nTR0YN2DjqzsRedbt/64s2l1H5dinOW', 'manager@fashion.com', 'Manager User', '0908888888', 'Ha Noi, Vietnam', TRUE, TRUE, NULL);

-- Insert users_roles
INSERT INTO users_roles (userId, roleId) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 1),
(5, 2);

-- Insert brands
INSERT INTO brands (BrandName, Slug, Logo) VALUES
('Nike', 'nike', '/images/brands/nike.png'),
('Adidas', 'adidas', '/images/brands/adidas.png'),
('Zara', 'zara', '/images/brands/zara.png'),
('H&M', 'h-m', '/images/brands/hm.png'),
('Uniqlo', 'uniqlo', '/images/brands/uniqlo.png'),
('Local Brand', 'local-brand', '/images/brands/local.png');

-- Insert categories
INSERT INTO categories (CatName, Slug) VALUES
('Nam', 'nam'),
('Nữ', 'nu'),
('Phụ kiện', 'phu-kien'),
('Áo thun nam', 'ao-thun-nam'),
('Quần jean nam', 'quan-jean-nam'),
('Áo khoác nam', 'ao-khoac-nam'),
('Áo thun nữ', 'ao-thun-nu'),
('Váy đầm', 'vay-dam'),
('Quần jean nữ', 'quan-jean-nu'),
('Túi xách', 'tui-xach'),
('Giày dép', 'giay-dep'),
('Mũ nón', 'mu-non');

-- Insert products
INSERT INTO products (ProductName, Description, Price, SalePrice, Quantity, CatID, BrandID, Avatar, Img1, Img2, Img3) VALUES
-- Áo thun nam
('Áo thun Nike Dri-FIT', 'Áo thun thể thao Nike công nghệ Dri-FIT thấm hút mồ hôi tốt', 450000, 380000, 100, 4, 1, '/images/products/ao-thun-nam-1.jpg', '/images/products/ao-thun-nam-1-2.jpg', '/images/products/ao-thun-nam-1-3.jpg', '/images/products/ao-thun-nam-1-4.jpg'),
('Áo thun Adidas Basic', 'Áo thun cotton 100% basic logo Adidas', 350000, 300000, 80, 4, 2, '/images/products/ao-thun-nam-2.jpg', '/images/products/ao-thun-nam-2-2.jpg', NULL, NULL),
('Áo thun Local Brand oversized', 'Áo thun form rộng local brand chất cotton co giãn', 280000, 250000, 120, 4, 6, '/images/products/ao-thun-nam-3.jpg', NULL, NULL, NULL),
('Áo polo Uniqlo', 'Áo polo cổ bẻ công sở Uniqlo', 390000, NULL, 60, 4, 5, '/images/products/ao-polo-nam-1.jpg', NULL, NULL, NULL),

-- Quần jean nam
('Quần jean Nike Slim Fit', 'Quần jean nam dáng slim fit co giãn', 750000, 650000, 50, 5, 1, '/images/products/quan-jean-nam-1.jpg', '/images/products/quan-jean-nam-1-2.jpg', NULL, NULL),
('Quần jean Zara Skinny', 'Quần jean skinny ôm body Zara', 680000, 580000, 45, 5, 3, '/images/products/quan-jean-nam-2.jpg', NULL, NULL, NULL),
('Quần jean H&M Regular', 'Quần jean regular fit H&M', 550000, 480000, 70, 5, 4, '/images/products/quan-jean-nam-3.jpg', NULL, NULL, NULL),

-- Áo khoác nam
('Áo khoác Nike Windrunner', 'Áo khoác gió Nike chống nước', 1200000, 1000000, 35, 6, 1, '/images/products/ao-khoac-nam-1.jpg', NULL, NULL, NULL),
('Áo khoác Adidas Bomber', 'Áo bomber jacket Adidas phong cách thể thao', 950000, 850000, 40, 6, 2, '/images/products/ao-khoac-nam-2.jpg', NULL, NULL, NULL),
('Áo khoác jeans Local Brand', 'Áo khoác jeans unisex local brand', 680000, 600000, 55, 6, 6, '/images/products/ao-khoac-nam-3.jpg', NULL, NULL, NULL),

-- Áo thun nữ
('Áo thun Nike nữ Basic', 'Áo thun nữ Nike cotton thoáng mát', 380000, 320000, 90, 7, 1, '/images/products/ao-thun-nu-1.jpg', '/images/products/ao-thun-nu-1-2.jpg', NULL, NULL),
('Áo thun Zara nữ họa tiết', 'Áo thun nữ Zara họa tiết thời trang', 420000, 360000, 75, 7, 3, '/images/products/ao-thun-nu-2.jpg', NULL, NULL, NULL),
('Áo croptop H&M', 'Áo croptop nữ H&M năng động', 280000, 250000, 100, 7, 4, '/images/products/ao-croptop-1.jpg', NULL, NULL, NULL),

-- Váy đầm
('Váy hoa nhí Zara', 'Váy hoa nhí dáng xòe Zara', 580000, 500000, 40, 8, 3, '/images/products/vay-1.jpg', '/images/products/vay-1-2.jpg', '/images/products/vay-1-3.jpg', NULL),
('Váy công sở H&M', 'Váy công sở H&M thanh lịch', 650000, NULL, 35, 8, 4, '/images/products/vay-2.jpg', NULL, NULL, NULL),
('Đầm maxi Zara', 'Đầm maxi dạ hội Zara cao cấp', 1200000, 1000000, 25, 8, 3, '/images/products/dam-1.jpg', NULL, NULL, NULL),

-- Quần jean nữ
('Quần jean nữ Zara Skinny', 'Quần jean nữ skinny Zara co giãn', 680000, 580000, 60, 9, 3, '/images/products/quan-jean-nu-1.jpg', NULL, NULL, NULL),
('Quần jean nữ H&M Boyfriend', 'Quần jean nữ boyfriend H&M', 590000, 520000, 50, 9, 4, '/images/products/quan-jean-nu-2.jpg', NULL, NULL, NULL),

-- Phụ kiện
('Túi xách mini Zara', 'Túi xách mini đeo chéo Zara', 450000, 380000, 70, 10, 3, '/images/products/tui-1.jpg', NULL, NULL, NULL),
('Balo Nike Sportswear', 'Balo Nike đựng laptop thể thao', 890000, 750000, 45, 10, 1, '/images/products/balo-1.jpg', NULL, NULL, NULL),
('Túi tote H&M', 'Túi tote canvas H&M đi học đi làm', 280000, 250000, 80, 10, 4, '/images/products/tui-tote-1.jpg', NULL, NULL, NULL),
('Giày Nike Air Force 1', 'Giày sneaker Nike Air Force 1 classic', 2500000, 2200000, 30, 11, 1, '/images/products/giay-1.jpg', NULL, NULL, NULL),
('Giày Adidas Superstar', 'Giày Adidas Superstar iconic', 2200000, 1900000, 35, 11, 2, '/images/products/giay-2.jpg', NULL, NULL, NULL),
('Dép Adidas Adilette', 'Dép đi trong nhà Adidas', 450000, 400000, 100, 11, 2, '/images/products/dep-1.jpg', NULL, NULL, NULL),
('Mũ lưỡi trai Nike', 'Mũ lưỡi trai Nike thêu logo', 380000, 320000, 90, 12, 1, '/images/products/mu-1.jpg', NULL, NULL, NULL),
('Mũ bucket Adidas', 'Mũ bucket Adidas phong cách streetwear', 420000, 380000, 75, 12, 2, '/images/products/mu-2.jpg', NULL, NULL, NULL),
('Nón len Local Brand', 'Nón len local brand mùa đông', 280000, 250000, 60, 12, 6, '/images/products/non-len-1.jpg', NULL, NULL, NULL);

-- Insert Carts
INSERT INTO carts (userId, TotalsItem, TotalsPrice) VALUES
(2, 3, 1280000),
(3, 2, 1140000),
(4, 2, 3200000);

-- Insert CartItems
INSERT INTO cart_items (CartID, ProductID, Quantity, TotalsPrice) VALUES
(1, 2, 2, 600000),
(1, 6, 1, 580000),
(2, 11, 1, 320000),
(2, 19, 2, 760000),
(3, 8, 1, 1000000),
(3, 22, 1, 2200000);

-- Insert Orders
INSERT INTO orders (userId, OrderDate, DeliveryDate, TotalsPrice, ShippingFee, OrderStatus, Notes) VALUES
(2, '2024-01-01', '2024-01-05', 950000, 30000, 'COMPLETED', 'Giao hàng giờ hành chính'),
(3, '2024-01-01', NULL, 1150000, 50000, 'SHIPPING', 'Gọi trước khi giao'),
(4, '2024-01-01', NULL, 1000000, 30000, 'PENDING', NULL),
(2, '2024-01-02', '2024-01-03', 2580000, 0, 'COMPLETED', 'Đơn hàng VIP');

-- Insert OrderDetail
INSERT INTO order_detail (OrderID, ProductID, Quantity, Price, TotalPrice) VALUES
(1, 1, 1, 380000, 380000),
(1, 13, 1, 250000, 250000),
(1, 25, 1, 320000, 320000),
(2, 5, 1, 650000, 650000),
(2, 14, 1, 500000, 500000),
(3, 8, 1, 1000000, 1000000),
(4, 22, 1, 2200000, 2200000),
(4, 1, 1, 380000, 380000);

-- Insert Comments
INSERT INTO comments (ProductID, fullname, email, comment, rating, createDate) VALUES
(1, 'Nguyen Van A', 'user1@gmail.com', 'Áo rất đẹp, chất liệu tốt, đúng như mô tả', 5, '2024-01-05 10:30:00'),
(13, 'Nguyen Van A', 'user1@gmail.com', 'Áo đẹp nhưng hơi nhỏ, nên lấy size lớn hơn', 4, '2024-01-05 10:35:00'),
(5, 'Tran Thi B', 'user2@gmail.com', 'Quần jean đẹp, co giãn tốt, rất hài lòng', 5, '2024-01-03 14:20:00'),
(14, 'Tran Thi B', 'user2@gmail.com', 'Váy xinh lắm, chất vải mềm mại', 5, '2024-01-03 14:25:00'),
(8, 'Le Van C', 'user3@gmail.com', 'Áo khoác đẹp nhưng giá hơi cao', 4, '2024-01-02 09:15:00'),
(22, 'Nguyen Van A', 'user1@gmail.com', 'Giày Nike xịn, đi rất êm chân', 5, '2024-01-04 16:40:00'),
(2, 'Pham Thi D', 'user4@gmail.com', 'Áo Adidas chất lượng tốt, giao hàng nhanh', 5, '2024-01-06 11:00:00'),
(11, 'Hoang Van E', 'user5@gmail.com', 'Áo thun nữ đẹp, form chuẩn', 4, '2024-01-07 15:30:00');

-- Insert Posts
INSERT INTO posts (Title, Slug, Description, Contents, Thumnail, CreatedDate) VALUES
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


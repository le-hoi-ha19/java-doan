-- Insert sample users (password: 123456 - BCrypt encoded)
INSERT INTO users (username, password, email, full_name, phone, address, role) VALUES
('admin', '$2a$10$X5wFWtliQ/Norm8XW2TNAeRiMJNNHPyY8YfUg2wT5C/Y5tZy0E87e', 'admin@fashion.com', 'Admin User', '0123456789', 'Ha Noi, Vietnam', 'ADMIN'),
('user1', '$2a$10$X5wFWtliQ/Norm8XW2TNAeRiMJNNHPyY8YfUg2wT5C/Y5tZy0E87e', 'user1@gmail.com', 'Nguyen Van A', '0987654321', 'Ho Chi Minh, Vietnam', 'USER'),
('user2', '$2a$10$X5wFWtliQ/Norm8XW2TNAeRiMJNNHPyY8YfUg2wT5C/Y5tZy0E87e', 'user2@gmail.com', 'Tran Thi B', '0912345678', 'Da Nang, Vietnam', 'USER');

-- Insert categories
INSERT INTO categories (name, description, image_url) VALUES
('Áo thun', 'Áo thun nam nữ các loại', '/images/categories/ao-thun.jpg'),
('Quần jean', 'Quần jean thời trang', '/images/categories/quan-jean.jpg'),
('Váy đầm', 'Váy đầm nữ', '/images/categories/vay-dam.jpg'),
('Áo khoác', 'Áo khoác nam nữ', '/images/categories/ao-khoac.jpg'),
('Phụ kiện', 'Phụ kiện thời trang', '/images/categories/phu-kien.jpg');

-- Insert products
INSERT INTO products (name, description, price, discount_price, quantity, category_id, image_url, status) VALUES
('Áo thun basic trắng', 'Áo thun cotton 100% co giãn thoáng mát', 150000, 120000, 100, 1, '/images/products/ao-thun-1.jpg', 'ACTIVE'),
('Áo thun basic đen', 'Áo thun cotton 100% form rộng', 150000, 130000, 80, 1, '/images/products/ao-thun-2.jpg', 'ACTIVE'),
('Quần jean skinny', 'Quần jean skinny ôm body', 350000, 300000, 50, 2, '/images/products/quan-jean-1.jpg', 'ACTIVE'),
('Quần jean baggy', 'Quần jean baggy phong cách Hàn Quốc', 400000, 350000, 60, 2, '/images/products/quan-jean-2.jpg', 'ACTIVE'),
('Váy hoa nhí', 'Váy hoa nhí dáng xòe', 280000, 250000, 40, 3, '/images/products/vay-1.jpg', 'ACTIVE'),
('Váy công sở', 'Váy công sở thanh lịch', 320000, NULL, 35, 3, '/images/products/vay-2.jpg', 'ACTIVE'),
('Áo khoác denim', 'Áo khoác jean unisex', 450000, 400000, 45, 4, '/images/products/ao-khoac-1.jpg', 'ACTIVE'),
('Áo khoác bomber', 'Áo khoác bomber form rộng', 500000, 450000, 30, 4, '/images/products/ao-khoac-2.jpg', 'ACTIVE'),
('Túi xách mini', 'Túi xách mini đeo chéo', 180000, 150000, 70, 5, '/images/products/tui-1.jpg', 'ACTIVE'),
('Mũ lưỡi trai', 'Mũ lưỡi trai thêu logo', 120000, 100000, 90, 5, '/images/products/mu-1.jpg', 'ACTIVE');

-- Insert sample orders
INSERT INTO orders (user_id, order_number, total_amount, status, payment_method, shipping_address) VALUES
(2, 'ORD20240101001', 420000, 'COMPLETED', 'COD', 'Ho Chi Minh, Vietnam'),
(3, 'ORD20240101002', 650000, 'PENDING', 'BANKING', 'Da Nang, Vietnam');

-- Insert order items
INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
(1, 1, 2, 120000),
(1, 5, 1, 250000),
(2, 3, 1, 300000),
(2, 7, 1, 400000);

-- Create Role table
CREATE TABLE IF NOT EXISTS role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Create Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    fullname VARCHAR(100),
    telephone VARCHAR(20),
    address TEXT,
    images VARCHAR(255),
    gender BOOLEAN,
    enabled BOOLEAN DEFAULT TRUE
);

-- Create Users_Roles table
CREATE TABLE IF NOT EXISTS users_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId BIGINT NOT NULL,
    roleId BIGINT NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (roleId) REFERENCES role(id) ON DELETE CASCADE,
    UNIQUE KEY unique_user_role (userId, roleId)
);

-- Create Brands table
CREATE TABLE IF NOT EXISTS brands (
    BrandID BIGINT AUTO_INCREMENT PRIMARY KEY,
    BrandName VARCHAR(100) NOT NULL,
    Slug VARCHAR(100) UNIQUE,
    Logo VARCHAR(255)
);

-- Create Categories table
CREATE TABLE IF NOT EXISTS categories (
    CatID INT AUTO_INCREMENT PRIMARY KEY,
    CatName VARCHAR(100) NOT NULL,
    Slug VARCHAR(100) UNIQUE
);

-- Create Products table
CREATE TABLE IF NOT EXISTS products (
    ProductID BIGINT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(200) NOT NULL,
    Price DOUBLE NOT NULL,
    SalePrice DOUBLE,
    Quantity INT DEFAULT 0,
    CatID INT,
    BrandID BIGINT,
    Avatar VARCHAR(255),
    Img1 VARCHAR(255),
    Img2 VARCHAR(255),
    Img3 VARCHAR(255),
    Description TEXT,
    FOREIGN KEY (CatID) REFERENCES categories(CatID) ON DELETE SET NULL,
    FOREIGN KEY (BrandID) REFERENCES brands(BrandID) ON DELETE SET NULL
);

-- Create Carts table
CREATE TABLE IF NOT EXISTS carts (
    CartID BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId BIGINT NOT NULL,
    TotalsItem INT DEFAULT 0,
    TotalsPrice DOUBLE DEFAULT 0,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
);

-- Create CartItems table
CREATE TABLE IF NOT EXISTS cart_items (
    CI_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CartID BIGINT NOT NULL,
    ProductID BIGINT NOT NULL,
    Quantity INT NOT NULL DEFAULT 1,
    TotalsPrice DOUBLE NOT NULL,
    FOREIGN KEY (CartID) REFERENCES carts(CartID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES products(ProductID) ON DELETE CASCADE
);

-- Create Orders table
CREATE TABLE IF NOT EXISTS orders (
    OrderID BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId BIGINT NOT NULL,
    OrderDate DATE,
    DeliveryDate DATE,
    TotalsPrice DOUBLE NOT NULL,
    ShippingFee DOUBLE DEFAULT 0,
    OrderStatus VARCHAR(50) DEFAULT 'PENDING',
    Notes TEXT,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
);

-- Create OrderDetail table
CREATE TABLE IF NOT EXISTS order_detail (
    OD_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    OrderID BIGINT NOT NULL,
    ProductID BIGINT NOT NULL,
    Quantity INT NOT NULL,
    Price DOUBLE NOT NULL,
    TotalPrice DOUBLE NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES orders(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES products(ProductID) ON DELETE CASCADE
);

-- Create Comments table
CREATE TABLE IF NOT EXISTS comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ProductID BIGINT NOT NULL,
    fullname VARCHAR(100),
    email VARCHAR(100),
    comment TEXT,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ProductID) REFERENCES products(ProductID) ON DELETE CASCADE
);

-- Create Posts table
CREATE TABLE IF NOT EXISTS posts (
    PostID BIGINT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(200) NOT NULL,
    Slug VARCHAR(200) UNIQUE NOT NULL,
    Description TEXT,
    Contents LONGTEXT,
    Thumnail VARCHAR(255),
    CreatedDate DATE
);

-- Create Contact table
CREATE TABLE IF NOT EXISTS contact (
    ContactID BIGINT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(100),
    email VARCHAR(100) NOT NULL,
    telephone VARCHAR(20),
    subject VARCHAR(200),
    message TEXT
);

-- Create Notifications table
CREATE TABLE IF NOT EXISTS notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    title VARCHAR(200),
    message TEXT,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


# Hướng dẫn chạy ứng dụng với Docker

## Yêu cầu
- Docker Desktop đã cài đặt
- Docker Compose

## Chạy ứng dụng

### 1. Build và chạy với Docker Compose
```bash(build lại và chạy khi sửa code sẽ ghi đè image cũ)
docker-compose up --build


```

### 2. Chỉ chạy (không build lại)
```bash
docker-compose up
```

### 3. Chạy ở chế độ background
```bash
docker-compose up -d
```

### 4. Dừng ứng dụng
```bash
docker-compose down
```

### 5. Dừng và xóa volumes
```bash
docker-compose down -v
```

## Truy cập ứng dụng
- URL: http://localhost:9090
- MySQL: localhost:3307
  - Username: root
  - Password: root123
  - Database: fashion

## Thông tin đăng nhập mẫu
- Admin: admin / 123456
- User: user1 / 123456

## Lưu ý
- Flyway sẽ tự động migrate database khi khởi động
- Data mẫu đã được seed sẵn

docker logs --tail 100 fashion-app
# Hướng dẫn chạy ứng dụng với Docker

## Yêu cầu cài đặt
- Docker Desktop
-wsl 
-git bash

## bước 1: mở terminal: 
git pull origin develop






## Chạy ứng dụng

### bước 2. Build và chạy với Docker Compose
(build lại và chạy khi sửa code sẽ ghi đè image cũ)
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

sửa code thì nên xóa image của code thoi k tránh xóa cái khác làm tải lại tốn mạng
//xem tất cả image:
docker images
-->sẽ thấy:
REPOSITORY          TAG       IMAGE ID       CREATED         SIZE
my-backend          latest    a1b2c3d4e5f6   3 hours ago     300MB
my-frontend         latest    09db34ac12ef   2 days ago      250MB
mysql               8.0       7f1e2c88fd     3 months ago    450MB
nginx               latest    1a2b3c4d5e     1 month ago     100MB


-->Xác định image nào là code để xoá:
docker rmi my-backend
 hoặc 
docker rmi my-frontend

hoặc xóa theo id image:
docker rmi a1b2c3d4e5f6
hoặc có thể xóa nhiều id image cùng lúc:
docker rmi a1b2c3d4 aabbccdd eeff1122


⚠️ LƯU Ý QUAN TRỌNG:
Hot reload CHỈ hoạt động với:

✅ Thay đổi code Java (.java files)
✅ Thay đổi resources (.properties, .html, .css, .js)
✅ Thay đổi static files

Hot reload KHÔNG hoạt động với:

❌ Thay đổi pom.xml (cần rebuild)
❌ Thay đổi database schema (cần restart)
❌ Thay đổi Dockerfile/docker-compose.yml (cần rebuild)




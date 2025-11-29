# ✅ **STEP 1 — Mở MySQL bằng tài khoản root**

Gõ lệnh:

mysql -u root -p


Enter → MySQL sẽ hỏi mật khẩu.


# ✅ **STEP 2 — Nhập mật khẩu**

root123


> Lưu ý: Khi nhập mật khẩu **không hiện ký tự**, đó là bình thường.
> Nhấn **Enter** để login.



# ✅ **STEP 3 — Xem tất cả các database đang có**


SHOW DATABASES;


-->Nó sẽ liệt kê tất cả database trên server.


# ✅ **STEP 4 — Chọn database cần làm việc**

Ở đây database tên **fashion**, vậy gõ:

USE fashion;

Nếu thấy dòng-->Database changed


→ Nghĩa là đã chuyển vào đúng database.


# ✅ **STEP 5 — Xem các bảng trong database**

Gõ:

SHOW TABLES;

Nó sẽ hiện danh sách bảng, ví dụ:

```
users
orders
products
...
```

---

# ✅ **STEP 6 — Xem dữ liệu trong bảng `users`**

Gõ:


SELECT * FROM users;

Bạn sẽ thấy toàn bộ dữ liệu trong bảng(có thể dùng chat gpt để tạo lệnh thêm sửa xóa hoặc update gì đó).






# 🎯 **Toàn bộ quá trình tóm gọn lại**

mysql -u root -p
root123
SHOW DATABASES;
USE fashion;
SHOW TABLES;
SELECT * FROM users;

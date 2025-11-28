mysql -u root -p

root123

SHOW DATABASES;


SHOW TABLES;



USE fashion;



SELECT * FROM users;

roleId 2 la admin
INSERT INTO users_roles (userId, roleId) VALUES (6, 2);

DELETE FROM users_roles  WHERE userId = 6 AND roleId = 1; 



⚠️ LƯU Ý QUAN TRỌNG:
Hot reload CHỈ hoạt động với:

✅ Thay đổi code Java (.java files)
✅ Thay đổi resources (.properties, .html, .css, .js)
✅ Thay đổi static files

Hot reload KHÔNG hoạt động với:

❌ Thay đổi pom.xml (cần rebuild)
❌ Thay đổi database schema (cần restart)
❌ Thay đổi Dockerfile/docker-compose.yml (cần rebuild)



### 1. 准备MySQL数据库

```yml
version: '3.3'
services:
 mysql:
  container_name: mysql5
  image: daocloud.io/library/mysql:5.7.7
  restart: always
  command:
   - --character-set-server=utf8mb4
   - --collation-server=utf8mb4_general_ci
   - --default-time_zone=+8:00
  environment:
   TZ: Asis/Shanghai
   MYSQL_ROOT_PASSWORD: wangchao2303
  ports:
   - "3307:3306"
  volumes:
   - ./data/mysql:/var/lib/mysql
```

### 2.添加用户,创建数据库，分配权限

```sql
CREATE USER 'nacos'@'%' IDENTIFIED BY 'password1234';
create database nacos;
grant all on nacos.* to 'nacos'@'%';
```

### 3. 倒入数据库

[nacos-mysql.sql](./resource/nacos-mysql.sql)

### 4.修改配置文件

#### 4.1 application.properties

```
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://47.99.145.62:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=nacos
db.password.0=nacos
```

#### 4.2 cluster.conf

```
# 主机名：端口号
```

### 5. 启动测试

### 服务注册

```
curl -X POST 'http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=nacos.naming.serviceName&ip=20.18.7.10&port=8080'
```

### 服务发现

```
curl -X GET 'http://127.0.0.1:8848/nacos/v1/ns/instance/list?serviceName=nacos.naming.serviceName'
```

### 发布配置

```
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos.cfg.dataId&group=test&content=HelloWorld"
```

### 获取配置

```
curl -X GET "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos.cfg.dataId&group=test"
```
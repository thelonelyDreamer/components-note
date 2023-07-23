# docker-compose

## 一、引言

> - Docker运行一个镜像需要大量参数

> - 通过docker-compose.yml文件编写容器的参数并维护，还可以批量管理容器

## 二、安装

```bash
# 1. 下载Docker-Compose github一搜就有
wget https://github.com/docker/compose/releases/download/1.24.1/docker-compose-Linux-x86_64

# 2. 更改文件名，权限，拥有者信息
mv docker-compose-Linux-x86_64 docker-compose
chown docker:docker docker-compose
chmod 710 docker-compose

# 3. 为目录配置环境变量
vi /etc/local/bin
```

## 三、应用

### 1. 安装tomcat

```yml
version: "3.1"
services:
  tomcat:
    restart: always
    image: daocloud.io/library/tomcat:8.5.56-jdk8-openjdk-slim
    ccontainer_name: tomcat
    ports:
      - 8081:8080
    environment:
      TZ: Asia/Shanghai
    volumes:
      - /mysoftware/data/tomcat/webapps-8.5-56:/usr/local/tomcat/webapps
      - /mysoftware/data/tomcat/logs-8.5-56:/usr/local/tomcat/logs
```

### 2. 安装mysql

```yml
version: "3.1"
services:
  mysql:      #服务的名称
    restart: always  #跟随docker自动启动
    image: daocloud.io/library/mysql:8.0.19   #镜像地址
    container_name: mysql   #容器名称
    ports:
      - 3307:3306   #指定端口映射
    environment:
      MYSQL_ROOT_PASSWORD: wangchao #root账号密码
      TZ: Asia/Shanghai                #指定时区
    volumes:
      - /mysoftware/data/mysql/8-0-19:/var/lib/mysql  #数据卷映射
```

## 四、简单操作

### 1.
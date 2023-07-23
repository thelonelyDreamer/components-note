# Nginx 安装

## 一、docker-compose 安装

```yml
version: '3.1'
services:
  nginx:
    restart: always
    image: daocloud.io/library/nginx:1.13.2-perl
    container_name: nginx
    ports:
      - 80:80
    volumes:
      - ./conf.d:/etc/nginx/conf.d
      - ./resource:/var/nginx/resource
```




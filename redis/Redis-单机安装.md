# 前言

```

```



# 一、docker 安装

> docker-compose version 1.28.5
> Docker version 20.10.4

### 1. 准备yum文件

```yml
# docker-compose.yml
version: '3.1'
services:
  redis:
    image: daocloud.io/library/redis:6.0.5
    restart: always
    container_name: redis
    environment:
      - TZ=Asia/shanghai
    ports:
      - 6379:6379
    volumes:
      - ./data:/data
      - ./conf/redis.conf:/usr/local/etc/redis/redis.conf
    command: ["redis-server","/usr/local/etc/redis/redis.conf"]
```

```bash
# 相关命令
docker-compose up -d
docekr exec -it redis bash
redis-cli
```

```yml
version: '3.1'
services:
  redis:
    image: daocloud.io/library/redis:5.0.7
    restart: always
    container_name: redis
    environment:
      - TZ=Asia/shanghai
    ports:
      - 6379:6379
    volumes:
      - ./data:/data
      - ./conf/redis.conf:/usr/local/etc/redis/redis.conf
    command: ["redis-server","/usr/local/etc/redis/redis.conf"]
```

# 二、Linux 下安装

> cat /proc/version
> Linux version 3.10.0-1160.15.2.el7.x86_64 (mockbuild@kbuilder.bsys.centos.org) (gcc version 4.8.5 20150623 (Red Hat 4.8.5-44) (GCC) ) #1 SMP Wed Feb 3 15:06:38 UTC 2021

1. 下载安装包

   - 下载地址：https://github.com/redis/redis/releases

2.  编译

   > 可能要先升级编译器

   ```bash
   # 1. 安装gcc套装
   yum install cpp
   yum install binutils
   yum install glibc
   yum install glibc-kernheaders
   yum install glibc-common
   yum install glibc-devel
   yum install gcc
   yum install make
   
   # 2. 升级gcc
   yum -y install centos-release-scl
   yum -y install devtoolset-9-gcc devtoolset-9-gcc-c++ devtoolset-9-binutils
   scl enable devtoolset-9 bash
   
   # 3.设置永久升级：
   echo "source /opt/rh/devtoolset-9/enable" >>/etc/profile
   
   # 4. 编译/安装
   make
   
   ```

3.  执行

   ```bash
   ./src/redis-server redis.conf
   ```

# 三、Window 下安装

> 都是通过原码安装，应该跟Linux下差不多，时间有限，暂不做尝试
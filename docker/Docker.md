# Docker

## 一、Docker介绍

### 1.1 引言

### 1.2 Docker的由来

### 1.3 Docker的思想

1.集装箱

​	会将所有需要的内容放到不同的集装箱中，谁需要谁用

2.标准化

​	1.运输的标准化

​	2.命令的标准化

​	3.提供了REST的API；衍生出了很多的图形化界面 Rancher

3.隔离性

​	在运行集装箱内的内容时，会在Linux的内核中单独开辟一个空间

> 注册中心  镜像 容器

## 二 、 Docker的基本操作

### 2.1 安装Docker

```yml
# 1.下载关于Docker的依赖环境
yum -y install yum-utils device-mapper-persistent-data lvm2
# 2.设置下载Docker的镜像源
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
# 3.安装docker
yum makecache fast
yum -y install docker-ce
# 4.启动并设置为开机自动启动 测试
# 启动Docker服务
systemctl start docker
# 设置开机自动启动
systemctl enable docker
# 测试
docker run hello-world
```

### 2.2 Docker 的中央仓库

> 1.Docker官方的中央仓库：镜像最全，速度最慢 
>
> ​	https://hub.docker.com
>
> 2.国内的镜像网站
>
> ​	网易蜂巢：http://c.163.com/hub
>
> ​	daoCloud: http://hub.daocloud.io （推荐）
>
> 3.公司内部私服（添加配置）

```json
# 需要在/etc/docker/daemon.json
{
	"registy-mirrors":"https;;registry.docker-cn.com",
	"insecure-registries":["ip:port"]
}
# 重启两个服务
systemctl daemon-reload
systemcrl restart docker
```



### 2.3 镜像的操作

```yml
# 拉取镜像
docker pull 镜像名称[:tag]
docker pull daocloud.io/library/tomcat:8.5.15-jre8
# 查看全部镜像
docker images
# 删除本地镜像
docker rmi 镜像的标识
# 镜像的导入导出（不规范）
1. docker save -o 导出路径 镜像的标识
2. docker load  -i 导出路径 镜像的标识
# 修改名称
docker tag 镜像标识 镜像名称：版本
```

### 2.4 容器的操作

```sh
# 1.运行容器
docker run 镜像id 镜像标识[:tag]
# 2.常用参数
docker run -d -p 宿主机端口：容器端口 --name 容器名称 标志|镜像名称：tag
# -d 后台运行
# -p 端口映射
# --name 容器名称
docker run -d -p 8080:8080 --name tomcat 标志
```

```sh
# 2.查看容器
docker ps [-qa]
-a 包括没有运行的
-p 只看id
```

```sh
# 3.查看日志
docker logs -f 容器id
-f 可以滚动查看日志的最后几行
```

```sh
# 4.进到容器内部
docker exec -it 容器id bash|/bin/bash
-i 
-t
```

```sh
# 5.停止容器
docker stop 容器id
docker stop $(docker ps -qa)
# 6.删除容器
docker rm 容器id
# 7.启动容器
docker start 容器id
```

## 三、Docker  应用

### 3.1 准备ssm工程

### 3.2 准备MySQL 容器

```sh
docker run --name mysql -e MYSQL_ROOT_PASSWORD=wangchao -d -p 3306:3306 镜像id
```

### 3.3 准备Tomcat容器

```sh
# 通过命令将宿主机内的内容运行到容器内部
docker cp 文件名称 容器id:容器内部的路径
# 
docker cp ssm.war add:webapps
```

### 3.4 数据卷

> 数据卷：将宿主机的目录映射到容器的一个目录（内容一样）

```sh
# 1.创建数据卷
docker volume create 数据卷名称
# 默认位置：/var/lib/docker/volumes/数据卷名称/_data

# 2.查看数据卷的详细信息
docker volume inspect 数据卷名称

# 3.查看全部数据卷
docker volume ls

# 4.删除数据卷
docker volume rm 数据卷名称

# 5.使用数据卷
# 数据卷不存在，映射时默认创建
docker run -v 数据卷名称：容器内部的路径 容器id
# 指定数据卷的存仔位置
docker run -v 路径：容器内部的路径 容器id 
```

## 四、Docker自定义镜像

```sh
# 1.创建Dockerfile文件，并且指定自定义的镜像信息
# 文件常见内容：
from：
copy:将文件 放在镜像的路径下
workdir:声明镜像的默认工作目录
cmd:需要执行的命令
# 2.利用Dockerfile创建镜像
	docker build -t 名称：版本
```

## 五、Docker-Compose

> 可以通过Docker-Compose编写这些参数
>
> Docek-Compose 可以帮助我们批量管理容器。
>
> 我们只要通过docker-compose.yml去维护即可

### 5.1 下载Docker-Compose

```sh
# 1.去github官网下载版本
https://github.com/docker/compose/releases/tag/1.24.1
# 2.改名字，修改权限
mv 
chmod
# 3.设置环境变量
# docker-compose 文件移动到/usr/local/bin /etc/profile系统路径
# 4.再任意目录下输入docker-compose
```

### 5.2 管理Docker

> yml 文件规范
>
> ​	key:value
>
> 缩进区分
>
> 不要使用使用制表符

```yml
version: '3.1'
services:
  mysql:			        #
    restart: always			#
    image:
    container_name: mysql
    ports:
      - 3306:3306
    environment:
      MYSQL_ROOT_PASSWORD: wangchao
      TZ: Asia/Shanghai
    volumes:
      - /opt/docket_mysql/data: /var/lib/mysql
  tomcat:
    restart: always
    image:
    container_name: tomcat
    ports:
      - 8080:8080
    environment:
      TZ: Asia/Shanghai
    volumes:
      - webapps   :
      - logs:
```

> 使用docker-compose命令默认找docker-compoe.yml

### 5.3  运行docker



```sh
# 1.基于docker-compose.yml启动管理的容器
docker-compose up -d
# 2.关闭并删除容器
docker-compose down
# 3.开启|关闭|重启 已经存在的由docker-compose管理的容器
docker-compose start|stop|restart
# 4.查看由docker-compose 管理的容器
docker-compose ps
# 5.查看日志
docker-compose logs -f
```

### 5.4 docker-compose 配置Dockerfile使用

> 使用docker-compose .yml文件一级Dockerfile文件在生成镜像的同时启动当前镜像，并由docker-compose去管理容器

​	[docker-compose.yml]()

```yml
# yml
version: '3.1'
  services:
    ssm:
      restart: always
      build:
        context: 
        dockfile:
        image: ssm:1.0.1
        container_name: ssm
        ports:
          8081:8080
        environment:
          TZ: Asia/Shanghai
```

Dockfile 文件

```xml
from tomcat
copy ssm.war /usr/local/tomcat/webapps
```

可以直接启动：

docker-compose  build| docker-compose  up --build

## 七.Docker CI ,CD

### 7.1  引言

> 项目部署
>
> ​	1.打包
>
> ​	2.上传到服务器
>
> ​	3.将war包放在服务器中
>
> ​	4.通过Dockerfile将Tomcat和War包转成一个镜像，由docker-compose 去执行

### 7.2 CI（continuous intergration)持续集成

> 在编写代码时，完成一个功能后，立即提交代码到git仓库中，将项目重新构建并且测试。
>
> - ​	快速发现错误
> - ​    防止代码偏离主分支

7.3 搭建Gitlab 服务器

> 1.创建一个全新的虚拟机，并且指定至少4G的运行内存
>
> 2.安装docker and docker-compose
>
> 3.用docker-compose 安装Gitlab服务器
>
> ​		将sshd端口22 改为32822端口
>
> ​     vi /etc/ssh/sshd_config

[docker-compose.xml]()

```yml
version: '3.1'
services:
  gitlab:
    image: 'twang2218/gitlab-ce-zh:11.1.4'
    container_name: "gitlab"
    restart: always
    privileged: true
    hostname: 'gitlab'
    environment:
      TZ: 'Asia/Shanghai'
      GITLAB_OMNIBUS_CONFIG: |
        external_url 'http://192.168.1.254'
        gitlab_rails['timezone']='Asiz/Shanghai'
        gitlab_rails['smtp——enable']=true
        gitlab_rails['gitlab_shell_ssh_port']=22
    ports:
      - '80:80'
      - '443:443'
      - '22:22'
    volumes:
      - /opt/docker_gitlab/config:/etc/gitlab
      - /opt/docker_gitlab/data:/var/opt/gitlab
      - /opt/docker_gitlab/logs:var/log/gitlab
```

### 7.4 搭建github-Runner 服务器


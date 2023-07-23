# Docker 安装

## 一、CentOS 7 安装docker

### 1. Docker安装

```bash
# 1. 下载Docker的依赖环境
yum -y install yum-utils device-mapper-persistent-data lvm2

# 2. 设置下载Docker的镜像源
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# 3. 安装Docker
yum makecache fast
yum -y install docker-ce

# 4. 启动并设置开机自启
systemctl start docker
systemctl enable docker

# 5. 测试 
docker run hello-world
```

```sh
yum -y install yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum makecache fast
yum -y install docker-ce
systemctl start docker
systemctl enable docker
docker run hello-world
```

### 2. Docker-Compose的安装

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

## 二、卸载

```bash
systemctl stop  docker
yum -y remove docker-ce docker-ce-cli container.io
rm -rf /var/lib/docker
```

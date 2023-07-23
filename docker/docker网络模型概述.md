# 一、分类

## 1. Net模式（默认）

## 2. None

```bash
docker run --net=none
```

## 3. Host

```bash
docker run --net=host
```

## 4. 联合网络

> 多个容器共享一个ip地址

```bash
docker run --net=container:${container_id}
```


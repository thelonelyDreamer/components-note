### 1. ls 命令

```shell
# 查看某个节点下的子节点
ls /
# 查看节点状态
-s 
# 递归暴露路径
-R
# 监听节点路径变化
-w
```

### 2. create命令

```shell
# 命令格式 -s 有序序列 -e 临时序列
create [-s] [-e] path data acl
# 
```

### 3. get/set命令

```bash
get /app1
set /app1 value
```

### 4. delete/deleteall

```bash
# 删除没有子节点的节点
delete /path
# 删除节点极其子节点
deleteall /path 
```



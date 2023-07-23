# 一、命令详解

## 1. FROM

> FROM：指定基础镜像，必须为dockerfile中的第一个命令

格式：

```bash
FROM <image>
　　FROM <image>:<tag>
　　FROM <image>@<digest>
#示例：
FROM mysql:5.6
# tag或digest是可选的，如果不使用这两个值时，会使用latest版本的基础镜像
```

## 2. **MAINTAINER**

> 维护者信息

```bash
#格式：
    MAINTAINER <name>
#示例：
    MAINTAINER Jack
    MAINTAINER jack@163.com
    MAINTAINER Jack <jack@163.com>
```

## 3. RUN

> 构建镜像时执行的命令，一个文件中可以包含多个RUN命令(但是不建议过多)

```bash
#格式：
    RUN <command>
#格式：
    RUN ["executable", "param1", "param2"]
#要注意的是，executable是命令，后面的param是参数 
#示例： 
    RUN yum install -y nginx 
    RUN ["yum", "install", "-y", "nginx"] 
#注：RUN指令创建的中间镜像会被缓存，并会在下次构建中使用。如果不想使用这些缓存镜像，可以在构建时指定--no-cache参数，如：docker build --no-cache
```

## 4. ADD

> - 将本地文件添加到容器中，tar类型文件会自动解压(网络压缩资源不会被解压)，可以访问网络资源，类似wget
> 
> - 如果目的位置不存在，Docker会自动创建所需要的目录

```bash
#格式：
    ADD <src>... <dest>
    ADD ["<src>",... "<dest>"] 用于支持包含空格的路径
#示例：
    ADD hom* /mydir/          # 添加所有以"hom"开头的文件
    ADD hom?.txt /mydir/      # ? 替代一个单字符,例如："home.txt"
    ADD test relativeDir/     # 添加 "test" 到 `WORKDIR`/relativeDir/
    ADD test /absoluteDir/    # 添加 "test" 到 /absoluteDir/
#注意：需要复制的本地文件一定要放在Dockerfile文件的同级目录下
#原因：因为构建环境将会先上传到Docker守护进程，而复制是在Docker守护进程中进行的。任何位于构建环境之外的东西都是不可用的。ADD指令的目的的位置则必须是容器内部的一个绝对路径。
```

## 5. COPY

> 不能解压，不能访问网络资源，其他类似于add

## 6. **WORKDIR**

> 指定工作目录，类似于cd命令，之后的命令都是基于此工作目录

## 7. **LABEL**

> 用于为镜像添加元数据

```bash
#格式：
    LABEL <key>=<value> <key>=<value> <key>=<value> ...
#示例：
　　LABEL version="1.0" description="这是一测试工程"
#注：使用LABEL指定元数据时，一条LABEL指定可以指定一或多条元数据，指定多条元数据时不同元数据之间通过空格分隔。推荐将所有的元数据通过一条LABEL指令指定，以免生成过多的中间镜像。
```

## 8. ENV

> 设置环境变量

```bash
#格式：
     #<key>之后的所有内容均会被视为其<value>的组成部分，因此，一次只能设置一个变量
    ENV <key> <value>  
       #可以设置多个变量，每个变量为一个"<key>=<value>"的键值对，如果<key>中包含空格，可以使用\来进行转义，也可以通过""来进行标示；另外，反斜线也可以用于续行
    ENV <key>=<value> ...  
#示例：
    ENV myName John Doe
    ENV myDog Rex The Dog
    ENV myCat=fluffy
```

## 9. EXPOSE

> 指定暴露镜像的端口供主机做映射

```bash
#格式：
    EXPOSE <port> [<port>...]
#示例：
    EXPOSE 80 443
    EXPOSE 8080
    EXPOSE 11211/tcp 11211/udp
#注：EXPOSE并不会让容器的端口访问到主机。要使其可访问，需要在docker run运行容器时通过-p来发布这些端口，或通过-P参数来发布EXPOSE导出的所有端口
```

## 10. VOLUME

> 添加卷，用于指定持久化目录

```bash
#格式：
    VOLUME ["/path/to/dir"]
#示例：
    VOLUME ["/data"]
    VOLUME ["/var/www", "/var/log/apache2", "/etc/apache2"]
#注：一个卷可以存在于一个或多个容器的指定目录，该目录可以绕过联合文件系统，并具有以下功能：
#1 卷可以容器间共享和重用
#2 容器并不一定要和其它容器共享卷
#3 修改卷后会立即生效
#4 对卷的修改不会对镜像产生影响
#5 卷会一直存在，直到没有任何容器在使用它
```

## 11. USER

> 指定运行容器时的用户名或 UID，后续的操作都会使用指定用户。使用USER指定用户时，可以使用用户名、UID或GID，或是两者的组合。当服务不需要管理员权限时，可以通过该命令指定运行用户。并且可以在之前创建所需要的用户

```bash
# 格式:
　　USER user
　　USER user:group
　　USER uid
　　USER uid:gid
　　USER user:gid
　　USER uid:group

# 示例：
　　USER www

#注:使用USER指定用户后，Dockerfile中其后的命令RUN、CMD、ENTRYPOINT都将使用该用户。镜像构建完成后，通过docker run运行容器时，可以通过-u参数来覆盖所指定的用户。
```

## 12. ARG

> 用于指定传递给构建运行时的变量

```bash
#格式：
    ARG <name>[=<default value>]
#示例：
    ARG site
    ARG build_user=www
```

## 13. ONBUILD

> 用于设置镜像触发器

```bash
#格式：
　　ONBUILD [INSTRUCTION]
#示例：
　　ONBUILD ADD . /app/src
　　ONBUILD RUN /usr/local/bin/python-build --dir /app/src
#注：当所构建的镜像被用做其它镜像的基础镜像时（比如用户的镜像需要从某为准备好的位置添加源代码，或者用户需要执行特定于构建镜像的环境的构建脚本），该镜像中的触发器将会被钥触发
```

例如创建镜像image-A

```
 FROM ubuntu
 ...
 ONBUILD ADD . /var/www
 ...
```

然后创建镜像image-B，指定image-A为基础镜像，如

```
 FROM image-A
 ...
```

然后在构建image-B的时候，日志上显示如下:
 Step 0 : FROM image-A
 \# Execting 1 build triggers
 Step onbuild-0 : ADD . /var/www
 ...

## 14. CMD

> 构建容器后调用，也就是在容器启动时才进行调用，存在多个CMD时只有最后一个生效，也支持exec语法。

```bash
#格式：
    CMD ["executable","param1","param2"] (执行可执行文件，优先)
    CMD ["param1","param2"] (设置了ENTRYPOINT，则直接调用ENTRYPOINT添加参数)
    CMD command param1 param2 (执行shell内部命令)
#示例：
    CMD echo "This is a test." | wc -
    CMD ["/usr/bin/wc","--help"]
#注：CMD不同于RUN，CMD用于指定在容器启动时所要执行的命令，而RUN用于指定镜像构建时所要执行的命令。
```

## 15. ENTRYPOINT

> ENTRYPOINT：配置容器，使其可执行化。配合CMD可省去"application"，只使用参数。
> 
> 会执行一个脚本

```bash
#格式：
    ENTRYPOINT ["executable", "param1", "param2"] (可执行文件, 优先)
    ENTRYPOINT command param1 param2 (shell内部命令)
#示例：
    FROM ubuntu
    ENTRYPOINT ["top", "-b"]
    CMD ["-c"]
#注：ENTRYPOINT与CMD非常类似，不同的是通过docker run执行的命令不会覆盖ENTRYPOINT，而docker run命令中指定的任何参数，都会被当做参数再次传递给ENTRYPOINT。Dockerfile中只允许有一个ENTRYPOINT命令，多指定时会覆盖前面的设置，而只执行最后的ENTRYPOINT指令。
```

> **注意： CMD和ENTRYPOINT的区别**
> 
> - CMD的命令会被 docker run 的命令覆盖而ENTRYPOINT不会
> 
> - 如使用CMD ["/bin/bash"]或ENTRYPOINT ["/bin/bash"]后，再使用docker run -ti image启动容器，它会自动进入容器内部的交互终端，如同使用docker run -ti image /bin/bash。但是如果启动镜像的命令为docker run -ti image /bin/ps，使用CMD后面的命令就会被覆盖转而执行bin/ps命令，而ENTRYPOINT的则不会，而是会把docker run 后面的命令当做ENTRYPOINT执行命令的参数。
>   
>   放个例子

```
Dockerfile中为
ENTRYPOINT ["/user/sbin/nginx"]

然后通过启动build之后的容器
docker run -ti image -g "daemon off"

此时-g "daemon off"会被当成参数传递给ENTRYPOINT，最终的命令变成了
/user/sbin/nginx -g "daemon off"如果Dockerfile中定义的是CMD，则会被覆盖
```

CMD和ENTRYPOINT都存在时，CMD的指令就变成了ENTRYPOINT的参数，并且此CMD提供的参数也会被 docker run 后面的命令覆盖

```
Dockerfile中指令
..
ENTRYPOINT ["echo","hello","i am"]
CMD ["docker"]

之后启动构建之后的容器

使用docker run -ti image
输出“hello i am docker”

使用docker run -ti image world
输出“hello i am world”
```

# 二 、docker build 命令

**docker build** 命令用于使用 Dockerfile 创建镜像，语法

```
docker build [OPTIONS] PATH | URL |-
```

参数说明：

- **--build-arg=[] :**设置镜像创建时的变量；
- **--cpu-shares :**设置 cpu 使用权重；
- **--cpu-period :**限制 CPU CFS周期；
- **--cpu-quota :**限制 CPU CFS配额；
- **--cpuset-cpus :**指定使用的CPU id；
- **--cpuset-mems :**指定使用的内存 id；
- **--disable-content-trust :**忽略校验，默认开启；
- **-f :**指定要使用的Dockerfile路径；
- **--force-rm :**设置镜像过程中删除中间容器；
- **--isolation :**使用容器隔离技术；
- **--label=[] :**设置镜像使用的元数据；
- **-m :**设置内存最大值；
- **--memory-swap :**设置Swap的最大值为内存+swap，"-1"表示不限swap；
- **--no-cache :**创建镜像的过程不使用缓存；
- **--pull :**尝试去更新镜像的新版本；
- **--quiet, -q :**安静模式，成功后只输出镜像 ID；
- **--rm :**设置镜像成功后删除中间容器；
- **--shm-size :**设置/dev/shm的大小，默认值是64M；
- **--ulimit :**Ulimit配置。
- **--tag, -t:** 镜像的名字及标签，通常 name:tag 或者 name 格式；可以在一次构建中为一个镜像设置多个标签。
- **--network:** 默认 default。在构建期间设置RUN指令的网络模式

示例

```
使用当前目录的 Dockerfile 创建镜像，标签为 runoob/ubuntu:v1。
docker build -t runoob/ubuntu:v1 . 

使用URL github.com/creack/docker-firefox 的 Dockerfile 创建镜像。
docker build github.com/creack/docker-firefox

也可以通过 -f Dockerfile 文件的位置：
$ docker build -f /path/to/a/Dockerfile .

在 Docker 守护进程执行 Dockerfile 中的指令前，首先会对 Dockerfile 进行语法检查，有语法错误时会返回：
$ docker build -t test/myapp .
Sending build context to Docker daemon 2.048 kB
Error response from daemon: Unknown instruction: RUNCMD
```

# 三、示例

```dockerfile
FROM centos
MAINTAINER ****
ENV MYPATH /user/local
WORKDIR $MYPATH
RUN yum -y install vim
RUN yum -y net-tools

EXPOSE 80
CMD echo $MYPATH
CMD echo "hello docker"
CMD /bin/bash
```

```
docker build -f 文件名 -t 镜像名 .
```

# 参考文献

1. https://www.cnblogs.com/yanh0606/p/11360936.html

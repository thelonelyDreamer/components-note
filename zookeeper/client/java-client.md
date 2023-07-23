## 一、 通过原始zookeepr连接

```xml
<!-- https://mvnrepository.com/artifact/org.apache.zookeeper/zookeeper -->
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.6.3</version>
</dependency>
```

```java
public class Demo {

    // 连接地址
    // 集群地址：“42.192.49.180:2181，42.192.49.180:2181，42.192.49.180:2181”
    private static String ip = "42.192.49.180:2181";
    private static int session_timeout = 40000;
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(ip, session_timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    //确认已经连接完毕后再进行操作
                    latch.countDown();
                    System.out.println("已经获得了连接");
                }
            }
        });S
        //连接完成之前先等待
        latch.await();
        ZooKeeper.States states = zooKeeper.getState();
        System.out.println(states);
    }
```

## 二、通过zkClient连接

```xml
<!-- https://mvnrepository.com/artifact/com.101tec/zkclient -->
<dependency>
    <groupId>com.101tec</groupId>
    <artifactId>zkclient</artifactId>
    <version>0.11</version>
</dependency>
```

```java
public class ZkClientTest { 
    private static String ip = "192.168.0.101:2181"; 
    private static int  session_timeout = 40000; 
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(ip,session_timeout);
        System.out.println(zkClient.getChildren("/"));
    }
}
```

## 三、通过curator连接（推荐）

[curator 官网](https://curator.apache.org/)

```xml
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-recipes</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
</dependency>
```

### 3.1 连接客户端

```java
public static CuratorFramework getClient1() {
        /*
         * @param connectString       list of servers to connect to
         * @param sessionTimeoutMs    session timeout
         * @param connectionTimeoutMs connection timeout
         * @param retryPolicy         retry policy to use
         * @param zkClientConfig      ZKClientConfig
         */
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                "localhost:2181", 3000, 1000,
                new RetryForever(100), new ZKClientConfig());
        client.start();
        return client;
}

public static CuratorFramework getClient2() {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(addr)
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .namespace("test2")
                .build();
        client.start();
        return client;
    }
```

- 重试策略
  
  - 见源码

- ZKClientConfig内容
  
  - 

### 3.2 创建节点

```java
client.create().forPath("/test1");
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/","".getBytes(StandardCharsets.UTF_8));
        client.create().forPath("/test1","test");
        client.create().creatingParentsIfNeeded().forPath("/test")
```

### 3.3 查询节点

```java
byte[] bytes = client.getData().forPath("/test1");
System.out.println(new String(bytes));
List<String> strings = client.getChildren().forPath("/");
Stat stat = new Stat();
client.getData().storingStatIn(stat).forPath("/");
```

### 3.4 修改数据

```java
client.setData().forPath("/","".getBytes());
// 根据版本号 版本号不一致会报错
client.setData().withVersion(stat.version).forPath("/","".getBytes());
```

### 3.5 删除节点

```java
client.delete().forPath("");
client.delete().deleteChildIfNeed().forPath("");
client.delete().guaranteed();
```

### 3.6 监听节点

- NodeCache 监听某个节点

- PathChildrenCache 监听子节点

- TreeCache: 监听树上所有节点

```java
NodeCache nodeCache = new NodeCache();
 nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {

            }
        });
 nodeCache.start();
```

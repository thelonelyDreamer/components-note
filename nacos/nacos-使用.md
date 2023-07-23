# 一、Nacos客户端基本使用

> nacos 1.4.1

## 1. 导入依赖

```xml
<dependencies>
    <dependency>
        <groupId>com.alibaba.nacos</groupId>
        <artifactId>nacos-client</artifactId>
        <version>1.4.1</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.1.11</version>
    </dependency>
</dependencies>
```

## 2. 关键的类和接口

### 2.1 NacosFactory

> - 可以创建三个接口的实例
>   - ConfigService
>   - NamingService
>   - NamingMaintainService
> - 每个实例提供两种方法
>   - Properties properties(PropertyKeyConst,可配置的属性)
>     - dataId
>     - group
>     - serverAddr
>   - String serverAddr

### 2.2 ConfigService

> - 功能：
>   - 获取和发布配置
>   - 添加监听器
>   - 获取服务器状态

### 2.3 Listener

> 定义回掉函数

### 2.4 NamingMaintainService

### 2.5 NamingService



# 二、springboot 集成nacos

## 1. 添加依赖

```xml
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>nacos-config-spring-boot-starter</artifactId>
    <version>0.2.7</version>
</dependency>
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
     <version>2.3.9.RELEASE</version>
</dependency>

<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>nacos-discovery-spring-boot-starter</artifactId>
    <version>${latest.version}</version>
</dependency>
```

## 2. 配置

application.properties

```
nacos.config.server-addr=127.0.0.1:8848,127.0.0.1:8848,127.0.0.1:8848
```

## 3. 编码

### 1. 配置类

```java
@Configuration
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosConfig {


}
```

### 2. 功能类

```java
@Controller
@RequestMapping("config")
class ConfigController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }
}

@Controller
@RequestMapping("discovery")
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
}
```

## 4.测试

```
curl http://localhost:8080/config/get
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=example&group=DEFAULT_GROUP&content=useLocalCache=true"
curl http://localhost:8080/discovery/get?serviceName=example
curl -X PUT 'http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=example&ip=127.0.0.1&port=8080'
```


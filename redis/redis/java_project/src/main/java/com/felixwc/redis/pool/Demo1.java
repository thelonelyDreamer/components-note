package com.felixwc.redis.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * TODO
 * JedisPool
 *
 * @author lenovo
 * since 2020-12-04  16:32
 */
public class Demo1 {

    // 方法一：用简单的方法创建连接池
    @Test
    public void method1() {
        //1. 创建连接池
        JedisPool jedisPool = new JedisPool("47.99.145.62", 6379);
        //2. 通过连接池获取jedis
        Jedis jedis = jedisPool.getResource();
        //3. 操作
        jedis.set("name", "张三");
        String name = jedis.get("name");
        System.out.println(name);
        //4.释放资源
        jedis.close();
    }

    //方法二：配置连接池
    @Test
    public void method2() {
        //1.创建连接池配置信息
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(50);
        poolConfig.setMaxIdle(10);
        poolConfig.setMinIdle(5);
        poolConfig.setMaxWaitMillis(3000);
        //2. 创建连接池
        JedisPool jedisPool = new JedisPool(poolConfig,"47.99.145.62", 6379);
        //3. 通过连接池获取jedis
        Jedis jedis = jedisPool.getResource();
        //5. 操作
        jedis.set("name", "张三");
        String name = jedis.get("name");
        System.out.println(name);
        //5.释放资源
        jedis.close();
    }

}

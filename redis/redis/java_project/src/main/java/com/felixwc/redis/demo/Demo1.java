package com.felixwc.redis.demo;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * TODO
 * 第一次连接redis
 *
 * @author lenovo
 * since 2020-12-04  15:27
 */
public class Demo1 {
    @Test
    public void set(){
        //1. 连接Jedis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //2. 操作
        jedis.set("name","李四");
        //3. 释放资源
        jedis.close();}


    @Test
    public void get(){
        //1. 连接Jedis
        Jedis jedis = new Jedis("47.99.145.62", 6379);
        //2. 操作
        String name = jedis.get("name");
        System.out.println(name);
        //3. 释放资源
        jedis.close();
    }

}

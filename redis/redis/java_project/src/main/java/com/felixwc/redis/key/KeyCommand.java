package com.felixwc.redis.key;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * TODO
 * Key类型的指令
 *
 * @author lenovo
 * since 2020-12-05  10:26
 */
public class KeyCommand{
    private  static Jedis jedis= new Jedis("47.99.145.62",6379);
    static{
        jedis.auth("wangchao");
    }

    @Test
    // exists key 检查给定 key 是否存在。
    public void test1(){
        Boolean result1 = jedis.exists("name");
        System.out.println(result1);
        Long result2 = jedis.exists("name", "age");
        System.out.println(result2);
        Boolean result3 = jedis.exists("name".getBytes());
        System.out.println(result3);
        Long result4 = jedis.exists("name".getBytes(), "age".getBytes());
        System.out.println(result4);

    }

    @Test
    //DUMP key序列化给定 key ，并返回被序列化的值。
    public void test2(){
        byte[] names = jedis.dump("name");
        System.out.println(names);
        byte[] dump = jedis.dump("name".getBytes());
        System.out.println(dump);
        System.out.println("name".getBytes());
    }
}

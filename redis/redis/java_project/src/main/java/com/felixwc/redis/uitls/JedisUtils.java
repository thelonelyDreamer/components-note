package com.felixwc.redis.uitls;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.omg.PortableServer.THREAD_POLICY_ID;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * TODO
 * 工具类
 *
 * @author lenovo
 * since 2020-12-05  10:51
 */
public class JedisUtils {
    private static JedisPool jedisPool;
    private static ThreadLocal<Jedis> THREAD_LOCAL_JEDIS = new ThreadLocal<>();
    static {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6739,3000,"wangchao");
    }
    public static Jedis getConnection(){
        if(null!= THREAD_LOCAL_JEDIS.get()){
            return  THREAD_LOCAL_JEDIS.get();
        }
        Jedis resource = jedisPool.getResource();
        THREAD_LOCAL_JEDIS.set(resource);
        return resource;
    }
}

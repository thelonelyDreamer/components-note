package com.felixwc.redis.connection;

import com.felixwc.redis.uitls.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * TODO
 * 连接命令
 *
 * @author lenovo
 * since 2020-12-05  10:49
 */
public class ConnCommand {

    @Test
    public void test1(){
        Jedis jedis = JedisUtils.getConnection();
        String name = jedis.get("name");
        System.out.println(name);
        //String result = jedis.auth("wnagchao");
//        System.out.println(result);
    }
}

package com.felixwc.redis.demo;

import com.felixwc.redis.uitls.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class DemoPipeline {

    @Test
    public void sendOneByOne() {

        long start = System.currentTimeMillis();
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("age", "0");
        for (int i = 0; i < 10000; i++) {
            jedis.incr("age");
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        jedis.close();
    }

    @Test
    public void pipeline(){
        long start = System.currentTimeMillis();
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        redis.clients.jedis.Pipeline pipelined = jedis.pipelined();
        jedis.set("age", "0");
        for (int i = 0; i < 10000; i++) {
            pipelined.incr("age");
        }
        pipelined.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}

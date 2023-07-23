package com.felixwc.redis.cluster;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class Connection {

    @Test
    public void test1(){
        Set<HostAndPort> node = new HashSet<>();
        node.add(new HostAndPort("192.168.1.60",46301));
        node.add(new HostAndPort("192.168.1.60",46302));
        node.add(new HostAndPort("192.168.1.60",46303));
        node.add(new HostAndPort("192.168.1.60",46304));
        node.add(new HostAndPort("192.168.1.60",46305));
        node.add(new HostAndPort("192.168.1.60",46306));
        JedisCluster jedisCluster = new JedisCluster(node);
        jedisCluster.set("name","zhaFDGsan");
    }
}

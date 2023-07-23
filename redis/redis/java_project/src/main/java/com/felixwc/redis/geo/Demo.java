package com.felixwc.redis.geo;

import org.junit.Test;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 * 经纬度
 *
 * @author lenovo
 * since 2020-12-05  11:44
 */
public class Demo {

    private Jedis getJedis(){
        Jedis jedis = new Jedis("47.99.145.62", 6379);
        jedis.auth("wangchao");
        jedis.select(15);
        return jedis;
    }


    //geopos 获取某一个地址的位置
    @Test
    public void test1(){
        Jedis jedis = getJedis();
        List<GeoCoordinate> geopos = jedis.geopos("city", "beijing", "shanghai");
        for (GeoCoordinate geopo : geopos) {
            System.out.println(geopo);
        }
        List<GeoCoordinate> geopos1 = jedis.geopos("city".getBytes(), "beijing".getBytes(), "shanghai".getBytes());
        for (GeoCoordinate geopo : geopos1) {
            System.out.println(geopo);
        }
        jedis.close();
    }

    //geoadd 将指定的地理空间位置（纬度、经度、名称）添加到指定的key中
    @Test
    public void test2(){
        Jedis jedis = getJedis();
        Long geoadd = jedis.geoadd("city", 106.582391, 29.56228, "chongqing");
        System.out.println(geoadd);
        Map<String, GeoCoordinate> cities = new HashMap<>();
        cities.put("xian",new GeoCoordinate(108.971152,34.225619));
        cities.put("chengdu",new GeoCoordinate(104.081534,30.655821));
        Long city = jedis.geoadd("city", cities);
        System.out.println(city);
        jedis.close();
    }

    //geohash 返回一个或多个位置元素的 Geohash 表示
    @Test
    public void test3(){
        Jedis jedis = getJedis();
        List<String> geohash = jedis.geohash("city", "beijing","shanghai");
        for (String s : geohash) {
            System.out.println(s);
        }
        jedis.close();

    }

    //geodist 返回两个给定位置之间的距离
    @Test
    public void test4(){
        Jedis jedis = getJedis();
        Double geodist = jedis.geodist("city", "beijing", "shanghai", GeoUnit.KM);
        System.out.println(geodist);
        jedis.close();
    }

    //
    @Test
    public void test5(){
        Jedis jedis = getJedis();
        List<GeoRadiusResponse> city = jedis.georadius("city", 120, 30, 2000, GeoUnit.KM);
        for (GeoRadiusResponse geoRadiusResponse : city) {
            String s = new String(geoRadiusResponse.getMember());
            System.out.println(s);
        }

        List<GeoRadiusResponse> geoRadiusResponses = jedis.georadiusByMember("city", "beijing", 1500, GeoUnit.KM);
        for (GeoRadiusResponse geoRadiusRespons : geoRadiusResponses) {
            System.out.println(new String(geoRadiusRespons.getMember()));
        }
    }
}

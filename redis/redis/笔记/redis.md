# 一、连接Redis服务器

## 1. 通过图形化界面连接

```
WindowsGUI软件下载地址：https://github.com/lework/RedisDesktopManager-Windows/releases/download/2020.6/rdm-2020.6.zip
```

## 2. Redis 服务器自带的客户端

```bash
docker exec -it redisid bash
redis-cli
```

## 3. java 连接Redis

> Jedis连接Redis ,Lettuce连接Redis。

### 3.1  Jedis连接Redis

> 特点： 

1. 建立项目，导入依赖

```xml
<!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>3.1.0</version>
        </dependency>
```

2. 连接

```java
public void set(){
        //1. 连接Jedis
        Jedis jedis = new Jedis("47.99.145.62", 6379);
        //2. 操作
        jedis.set("name","李四");
        //3. 释放资源
        jedis.close();
    }
```

```java
public void get(){
        //1. 连接Jedis
        Jedis jedis = new Jedis("47.99.145.62", 6379);
        //2. 操作
        String name = jedis.get("name");
        System.out.println(name);
        //3. 释放资源
        jedis.close();
    }
```

### 3.2 Lettuce连接Redis

# 二、redis 的数据类型和命令

> 1. key-string: 最常用的，一般用于存储一个值。
> 2. key-hash: 存储一个数据对象的。
> 3. key-list: 使用list结构可以实现栈和队列结构
> 4. key-set: 交集，差集，并集
> 5. key-zset: 排行榜

> 1. HyperLogLog: 计算近似值
> 2. GEO: 地理位置
> 3. BIT: 一般存储的也是一个字符串，储存的是一个字节数组

## 1. key

- [Redis Type 命令](https://www.redis.net.cn/order/3543.html)
- [Redis PEXPIREAT 命令](https://www.redis.net.cn/order/3533.html)
- [Redis PEXPIREAT 命令](https://www.redis.net.cn/order/3534.html)
- [Redis Rename 命令](https://www.redis.net.cn/order/3541.html)
- [Redis PERSIST 命令](https://www.redis.net.cn/order/3537.html)
- [Redis Move 命令](https://www.redis.net.cn/order/3536.html)
- [Redis RANDOMKEY 命令](https://www.redis.net.cn/order/3540.html)
- [Redis Dump 命令](https://www.redis.net.cn/order/3529.html)
- [Redis TTL 命令](https://www.redis.net.cn/order/3539.html)
- [Redis Expire 命令](https://www.redis.net.cn/order/3531.html)
- [Redis DEL 命令](https://www.redis.net.cn/order/3528.html)
- [Redis Pttl 命令](https://www.redis.net.cn/order/3538.html)
- [Redis Renamenx 命令](https://www.redis.net.cn/order/3542.html)
- [Redis EXISTS 命令](https://www.redis.net.cn/order/3530.html)
- [Redis Expireat 命令](https://www.redis.net.cn/order/3532.html)
- [Redis Keys 命令](https://www.redis.net.cn/order/3535.html)

| 序号 | 命令及描述                                                   |
| ---- | ------------------------------------------------------------ |
| 1    | [DEL key](https://www.redis.net.cn/order/3528.html) 该命令用于在 key 存在是删除 key。 |
| 2    | [DUMP key](https://www.redis.net.cn/order/3529.html) 序列化给定 key ，并返回被序列化的值。 |
| 3    | [EXISTS key](https://www.redis.net.cn/order/3530.html) 检查给定 key 是否存在。 |
| 4    | [EXPIRE key](https://www.redis.net.cn/order/3531.html) seconds 为给定 key 设置过期时间。 |
| 5    | [EXPIREAT key timestamp](https://www.redis.net.cn/order/3532.html) EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。 |
| 6    | [PEXPIRE key milliseconds](https://www.redis.net.cn/order/3533.html) 设置 key 的过期时间亿以毫秒计。 |
| 7    | [PEXPIREAT key milliseconds-timestamp](https://www.redis.net.cn/order/3534.html) 设置 key 过期时间的时间戳(unix timestamp) 以毫秒计 |
| 8    | [KEYS pattern](https://www.redis.net.cn/order/3535.html) 查找所有符合给定模式( pattern)的 key 。 |
| 9    | [MOVE key db](https://www.redis.net.cn/order/3536.html) 将当前数据库的 key 移动到给定的数据库 db 当中。 |
| 10   | [PERSIST key](https://www.redis.net.cn/order/3537.html) 移除 key 的过期时间，key 将持久保持。 |
| 11   | [PTTL key](https://www.redis.net.cn/order/3538.html) 以毫秒为单位返回 key 的剩余的过期时间。 |
| 12   | [TTL key](https://www.redis.net.cn/order/3539.html) 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。 |
| 13   | [RANDOMKEY](https://www.redis.net.cn/order/3540.html) 从当前数据库中随机返回一个 key 。 |
| 14   | [RENAME key newkey](https://www.redis.net.cn/order/3541.html) 修改 key 的名称 |
| 15   | [RENAMENX key newkey](https://www.redis.net.cn/order/3542.html) 仅当 newkey 不存在时，将 key 改名为 newkey 。 |
| 16   | [TYPE key](https://www.redis.net.cn/order/3543.html) 返回 key 所储存的值的类型。 |

## 2. String

> 一个Key对应一个值

- [Redis Setnx 命令](https://www.redis.net.cn/order/3552.html)
- [Redis Getrange 命令](https://www.redis.net.cn/order/3546.html)
- [Redis Mset 命令](https://www.redis.net.cn/order/3555.html)
- [Redis Setex 命令](https://www.redis.net.cn/order/3551.html)
- [Redis SET 命令](https://www.redis.net.cn/order/3544.html)
- [Redis Get 命令](https://www.redis.net.cn/order/3545.html)
- [Redis Getbit 命令](https://www.redis.net.cn/order/3548.html)
- [Redis Setbit 命令](https://www.redis.net.cn/order/3550.html)
- [Redis Decr 命令](https://www.redis.net.cn/order/3561.html)
- [Redis Decrby 命令](https://www.redis.net.cn/order/3562.html)
- [Redis Strlen 命令](https://www.redis.net.cn/order/3554.html)
- [Redis Msetnx 命令](https://www.redis.net.cn/order/3556.html)
- [Redis Incrby 命令](https://www.redis.net.cn/order/3559.html)
- [Redis Incrbyfloat 命令](https://www.redis.net.cn/order/3560.html)
- [Redis Setrange 命令](https://www.redis.net.cn/order/3553.html)
- [Redis Psetex 命令](https://www.redis.net.cn/order/3557.html)
- [Redis Append 命令](https://www.redis.net.cn/order/3563.html)
- [Redis Getset 命令](https://www.redis.net.cn/order/3547.html)
- [Redis Mget 命令](https://www.redis.net.cn/order/3549.html)
- [Redis Incr 命令](https://www.redis.net.cn/order/3558.html)

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [SET key value](https://www.redis.net.cn/order/3544.html) 设置指定 key 的值 |
| 2    | [GET key](https://www.redis.net.cn/order/3545.html) 获取指定 key 的值。 |
| 3    | [GETRANGE key start end](https://www.redis.net.cn/order/3546.html) 返回 key 中字符串值的子字符 |
| 4    | [GETSET key value](https://www.redis.net.cn/order/3547.html) 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。 |
| 5    | [GETBIT key offset](https://www.redis.net.cn/order/3548.html) 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。 |
| 6    | [MGET key1 [key2..\]](https://www.redis.net.cn/order/3549.html) 获取所有(一个或多个)给定 key 的值。 |
| 7    | [SETBIT key offset value](https://www.redis.net.cn/order/3550.html) 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。 |
| 8    | [SETEX key seconds value](https://www.redis.net.cn/order/3551.html) 将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。 |
| 9    | [SETNX key value](https://www.redis.net.cn/order/3552.html) 只有在 key 不存在时设置 key 的值。 |
| 10   | [SETRANGE key offset value](https://www.redis.net.cn/order/3553.html) 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。 |
| 11   | [STRLEN key](https://www.redis.net.cn/order/3554.html) 返回 key 所储存的字符串值的长度。 |
| 12   | [MSET key value [key value ...\]](https://www.redis.net.cn/order/3555.html) 同时设置一个或多个 key-value 对。 |
| 13   | [MSETNX key value [key value ...\]](https://www.redis.net.cn/order/3556.html) 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。 |
| 14   | [PSETEX key milliseconds value](https://www.redis.net.cn/order/3557.html) 这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。 |
| 15   | [INCR key](https://www.redis.net.cn/order/3558.html) 将 key 中储存的数字值增一。 |
| 16   | [INCRBY key increment](https://www.redis.net.cn/order/3559.html) 将 key 所储存的值加上给定的增量值（increment） 。 |
| 17   | [INCRBYFLOAT key increment](https://www.redis.net.cn/order/3560.html) 将 key 所储存的值加上给定的浮点增量值（increment） 。 |
| 18   | [DECR key](https://www.redis.net.cn/order/3561.html) 将 key 中储存的数字值减一。 |
| 19   | [DECRBY key decrement](https://www.redis.net.cn/order/3562.html) key 所储存的值减去给定的减量值（decrement） 。 |
| 20   | [APPEND key value](https://www.redis.net.cn/order/3563.html) 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。 |

## 3. List

> 一个Key 对应一个列表

- [Redis Lindex 命令](https://www.redis.net.cn/order/3580.html)
- [Redis Rpush 命令](https://www.redis.net.cn/order/3592.html)
- [Redis Lrange 命令](https://www.redis.net.cn/order/3586.html)
- [Redis Rpoplpush 命令](https://www.redis.net.cn/order/3591.html)
- [Redis Blpop 命令](https://www.redis.net.cn/order/3577.html)
- [Redis Brpop 命令](https://www.redis.net.cn/order/3578.html)
- [Redis Brpoplpush 命令](https://www.redis.net.cn/order/3579.html)
- [Redis Lrem 命令](https://www.redis.net.cn/order/3587.html)
- [Redis Llen 命令](https://www.redis.net.cn/order/3582.html)
- [Redis Ltrim 命令](https://www.redis.net.cn/order/3589.html)
- [Redis Lpop 命令](https://www.redis.net.cn/order/3583.html)
- [Redis Lpushx 命令](https://www.redis.net.cn/order/3585.html)
- [Redis Linsert 命令](https://www.redis.net.cn/order/3581.html)
- [Redis Rpop 命令](https://www.redis.net.cn/order/3590.html)
- [Redis Lset 命令](https://www.redis.net.cn/order/3588.html)
- [Redis Lpush 命令](https://www.redis.net.cn/order/3584.html)
- [Redis Rpushx 命令](https://www.redis.net.cn/order/3593.html)

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [BLPOP key1 [key2 \] timeout](https://www.redis.net.cn/order/3577.html) 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 2    | [BRPOP key1 [key2 \] timeout](https://www.redis.net.cn/order/3578.html) 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 3    | [BRPOPLPUSH source destination timeout](https://www.redis.net.cn/order/3579.html) 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 4    | [LINDEX key index](https://www.redis.net.cn/order/3580.html) 通过索引获取列表中的元素 |
| 5    | [LINSERT key BEFORE\|AFTER pivot value](https://www.redis.net.cn/order/3581.html) 在列表的元素前或者后插入元素 |
| 6    | [LLEN key](https://www.redis.net.cn/order/3582.html) 获取列表长度 |
| 7    | [LPOP key](https://www.redis.net.cn/order/3583.html) 移出并获取列表的第一个元素 |
| 8    | [LPUSH key value1 [value2\]](https://www.redis.net.cn/order/3584.html) 将一个或多个值插入到列表头部 |
| 9    | [LPUSHX key value](https://www.redis.net.cn/order/3585.html) 将一个或多个值插入到已存在的列表头部 |
| 10   | [LRANGE key start stop](https://www.redis.net.cn/order/3586.html) 获取列表指定范围内的元素 |
| 11   | [LREM key count value](https://www.redis.net.cn/order/3587.html) 移除列表元素 |
| 12   | [LSET key index value](https://www.redis.net.cn/order/3588.html) 通过索引设置列表元素的值 |
| 13   | [LTRIM key start stop](https://www.redis.net.cn/order/3589.html) 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。 |
| 14   | [RPOP key](https://www.redis.net.cn/order/3590.html) 移除并获取列表最后一个元素 |
| 15   | [RPOPLPUSH source destination](https://www.redis.net.cn/order/3591.html) 移除列表的最后一个元素，并将该元素添加到另一个列表并返回 |
| 16   | [RPUSH key value1 [value2\]](https://www.redis.net.cn/order/3592.html) 在列表中添加一个或多个值 |
| 17   | [RPUSHX key value](https://www.redis.net.cn/order/3593.html) 为已存在的列表添加值 |

## 4. Hash

> 一个Key对应一个值

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [HDEL key field2 [field2\]](https://www.redis.net.cn/order/3564.html) 删除一个或多个哈希表字段 |
| 2    | [HEXISTS key field](https://www.redis.net.cn/order/3565.html) 查看哈希表 key 中，指定的字段是否存在。 |
| 3    | [HGET key field](https://www.redis.net.cn/order/3566.html) 获取存储在哈希表中指定字段的值/td> |
| 4    | [HGETALL key](https://www.redis.net.cn/order/3567.html) 获取在哈希表中指定 key 的所有字段和值 |
| 5    | [HINCRBY key field increment](https://www.redis.net.cn/order/3568.html) 为哈希表 key 中的指定字段的整数值加上增量 increment 。 |
| 6    | [HINCRBYFLOAT key field increment](https://www.redis.net.cn/order/3569.html) 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。 |
| 7    | [HKEYS key](https://www.redis.net.cn/order/3570.html) 获取所有哈希表中的字段 |
| 8    | [HLEN key](https://www.redis.net.cn/order/3571.html) 获取哈希表中字段的数量 |
| 9    | [HMGET key field1 [field2\]](https://www.redis.net.cn/order/3572.html) 获取所有给定字段的值 |
| 10   | [HMSET key field1 value1 [field2 value2 \]](https://www.redis.net.cn/order/3573.html) 同时将多个 field-value (域-值)对设置到哈希表 key 中。 |
| 11   | [HSET key field value](https://www.redis.net.cn/order/3574.html) 将哈希表 key 中的字段 field 的值设为 value 。 |
| 12   | [HSETNX key field value](https://www.redis.net.cn/order/3575.html) 只有在字段 field 不存在时，设置哈希表字段的值。 |
| 13   | [HVALS key](https://www.redis.net.cn/order/3576.html) 获取哈希表中所有值 |
| 14   | HSCAN key cursor [MATCH pattern] [COUNT count] 迭代哈希表中的键值对。 |

## 5. Set

> 一个Key对应一个集合，不容许重复

| 序号 | 命令及描述                                                   |
| ---- | ------------------------------------------------------------ |
| 1    | [ SADD key member1 [member2\]](https://www.redis.net.cn/order/3594.html) 向集合添加一个或多个成员 |
| 2    | [SCARD key](https://www.redis.net.cn/order/3595.html) 获取集合的成员数 |
| 3    | [SDIFF key1 [key2\]](https://www.redis.net.cn/order/3596.html) 返回给定所有集合的差集 |
| 4    | [SDIFFSTORE destination key1 [key2\]](https://www.redis.net.cn/order/3597.html) 返回给定所有集合的差集并存储在 destination 中 |
| 5    | [SINTER key1 [key2\]](https://www.redis.net.cn/order/3598.html) 返回给定所有集合的交集 |
| 6    | [SINTERSTORE destination key1 [key2\]](https://www.redis.net.cn/order/3599.html) 返回给定所有集合的交集并存储在 destination 中 |
| 7    | [SISMEMBER key member](https://www.redis.net.cn/order/3600.html) 判断 member 元素是否是集合 key 的成员 |
| 8    | [SMEMBERS key](https://www.redis.net.cn/order/3601.html) 返回集合中的所有成员 |
| 9    | [SMOVE source destination member](https://www.redis.net.cn/order/3602.html) 将 member 元素从 source 集合移动到 destination 集合 |
| 10   | [SPOP key](https://www.redis.net.cn/order/3603.html) 移除并返回集合中的一个随机元素 |
| 11   | [SRANDMEMBER key [count\]](https://www.redis.net.cn/order/3604.html) 返回集合中一个或多个随机数 |
| 12   | [SREM key member1 [member2\]](https://www.redis.net.cn/order/3605.html) 移除集合中一个或多个成员 |
| 13   | [SUNION key1 [key2\]](https://www.redis.net.cn/order/3606.html) 返回所有给定集合的并集 |
| 14   | [SUNIONSTORE destination key1 [key2\]](https://www.redis.net.cn/order/3607.html) 所有给定集合的并集存储在 destination 集合中 |
| 15   | [SSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.redis.net.cn/order/3608.html) 迭代集合中的元素 |

## 6. sorted set

> 一个Key对应一个有序的集合

| 序号 | 命令及描述                                                   |
| ---- | ------------------------------------------------------------ |
| 1    | [ ZADD key score1 member1 [score2 member2\]](https://www.redis.net.cn/order/3609.html) 向有序集合添加一个或多个成员，或者更新已存在成员的分数 |
| 2    | [ZCARD key](https://www.redis.net.cn/order/3610.html) 获取有序集合的成员数 |
| 3    | [ZCOUNT key min max](https://www.redis.net.cn/order/3611.html) 计算在有序集合中指定区间分数的成员数 |
| 4    | [ZINCRBY key increment member](https://www.redis.net.cn/order/3612.html) 有序集合中对指定成员的分数加上增量 increment |
| 5    | [ZINTERSTORE destination numkeys key [key ...\]](https://www.redis.net.cn/order/3613.html) 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中 |
| 6    | [ZLEXCOUNT key min max](https://www.redis.net.cn/order/3614.html) 在有序集合中计算指定字典区间内成员数量 |
| 7    | [ZRANGE key start stop [WITHSCORES\]](https://www.redis.net.cn/order/3615.html) 通过索引区间返回有序集合成指定区间内的成员 |
| 8    | [ZRANGEBYLEX key min max [LIMIT offset count\]](https://www.redis.net.cn/order/3616.html) 通过字典区间返回有序集合的成员 |
| 9    | [ZRANGEBYSCORE key min max [WITHSCORES\] [LIMIT]](https://www.redis.net.cn/order/3617.html) 通过分数返回有序集合指定区间内的成员 |
| 10   | [ZRANK key member](https://www.redis.net.cn/order/3618.html) 返回有序集合中指定成员的索引 |
| 11   | [ZREM key member [member ...\]](https://www.redis.net.cn/order/3619.html) 移除有序集合中的一个或多个成员 |
| 12   | [ZREMRANGEBYLEX key min max](https://www.redis.net.cn/order/3620.html) 移除有序集合中给定的字典区间的所有成员 |
| 13   | [ZREMRANGEBYRANK key start stop](https://www.redis.net.cn/order/3621.html) 移除有序集合中给定的排名区间的所有成员 |
| 14   | [ZREMRANGEBYSCORE key min max](https://www.redis.net.cn/order/3622.html) 移除有序集合中给定的分数区间的所有成员 |
| 15   | [ZREVRANGE key start stop [WITHSCORES\]](https://www.redis.net.cn/order/3623.html) 返回有序集中指定区间内的成员，通过索引，分数从高到底 |
| 16   | [ZREVRANGEBYSCORE key max min [WITHSCORES\]](https://www.redis.net.cn/order/3624.html) 返回有序集中指定分数区间内的成员，分数从高到低排序 |
| 17   | [ZREVRANK key member](https://www.redis.net.cn/order/3625.html) 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序 |
| 18   | [ZSCORE key member](https://www.redis.net.cn/order/3626.html) 返回有序集中，成员的分数值 |
| 19   | [ZUNIONSTORE destination numkeys key [key ...\]](https://www.redis.net.cn/order/3627.html) 计算给定的一个或多个有序集的并集，并存储在新的 key 中 |
| 20   | [ZSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.redis.net.cn/order/3628.html) 迭代有序集合中的元素（包括元素成员和元素分值） |

## 7. 连接

| 1    | [AUTH password](https://www.redis.net.cn/order/3649.html) 验证密码是否正确 |
| ---- | ------------------------------------------------------------ |
| 2    | [ECHO message](https://www.redis.net.cn/order/3650.html) 打印字符串 |
| 3    | [PING](https://www.redis.net.cn/order/3651.html) 查看服务是否运行 |
| 4    | [QUIT](https://www.redis.net.cn/order/3652.html) 关闭当前连接 |
| 5    | [SELECT index](https://www.redis.net.cn/order/3653.html) 切换到指定的数据库 |

## 8. 服务器

| 1    | [BGREWRITEAOF](https://www.redis.net.cn/order/3654.html) 异步执行一个 AOF（AppendOnly File） 文件重写操作 |
| ---- | ------------------------------------------------------------ |
| 2    | [BGSAVE](https://www.redis.net.cn/order/3655.html) 在后台异步保存当前数据库的数据到磁盘 |
| 3    | [CLIENT KILL [ip:port\] [ID client-id]](https://www.redis.net.cn/order/3656.html) 关闭客户端连接 |
| 4    | [CLIENT LIST](https://www.redis.net.cn/order/3657.html) 获取连接到服务器的客户端连接列表 |
| 5    | [CLIENT GETNAME](https://www.redis.net.cn/order/3658.html) 获取连接的名称 |
| 6    | [CLIENT PAUSE timeout](https://www.redis.net.cn/order/3659.html) 在指定时间内终止运行来自客户端的命令 |
| 7    | [CLIENT SETNAME connection-name](https://www.redis.net.cn/order/3660.html) 设置当前连接的名称 |
| 8    | [CLUSTER SLOTS](https://www.redis.net.cn/order/3661.html) 获取集群节点的映射数组 |
| 9    | [COMMAND](https://www.redis.net.cn/order/3662.html) 获取 Redis 命令详情数组 |
| 10   | [COMMAND COUNT](https://www.redis.net.cn/order/3663.html) 获取 Redis 命令总数 |
| 11   | [COMMAND GETKEYS](https://www.redis.net.cn/order/3664.html) 获取给定命令的所有键 |
| 12   | [TIME](https://www.redis.net.cn/order/3665.html) 返回当前服务器时间 |
| 13   | [COMMAND INFO command-name [command-name ...\]](https://www.redis.net.cn/order/3666.html) 获取指定 Redis 命令描述的数组 |
| 14   | [CONFIG GET parameter](https://www.redis.net.cn/order/3667.html) 获取指定配置参数的值 |
| 15   | [CONFIG REWRITE](https://www.redis.net.cn/order/3668.html) 对启动 Redis 服务器时所指定的 redis.conf 配置文件进行改写 |
| 16   | [CONFIG SET parameter value](https://www.redis.net.cn/order/3669.html) 修改 redis 配置参数，无需重启 |
| 17   | [CONFIG RESETSTAT](https://www.redis.net.cn/order/3670.html) 重置 INFO 命令中的某些统计数据 |
| 18   | [DBSIZE](https://www.redis.net.cn/order/3671.html) 返回当前数据库的 key 的数量 |
| 19   | [DEBUG OBJECT key](https://www.redis.net.cn/order/3672.html) 获取 key 的调试信息 |
| 20   | [DEBUG SEGFAULT](https://www.redis.net.cn/order/3673.html) 让 Redis 服务崩溃 |
| 21   | [FLUSHALL](https://www.redis.net.cn/order/3674.html) 删除所有数据库的所有key |
| 22   | [FLUSHDB](https://www.redis.net.cn/order/3675.html) 删除当前数据库的所有key |
| 23   | [INFO [section\]](https://www.redis.net.cn/order/3676.html) 获取 Redis 服务器的各种信息和统计数值 |
| 24   | [LASTSAVE](https://www.redis.net.cn/order/3677.html) 返回最近一次 Redis 成功将数据保存到磁盘上的时间，以 UNIX 时间戳格式表示 |
| 25   | [MONITOR](https://www.redis.net.cn/order/3678.html) 实时打印出 Redis 服务器接收到的命令，调试用 |
| 26   | [ROLE](https://www.redis.net.cn/order/3679.html) 返回主从实例所属的角色 |
| 27   | [SAVE](https://www.redis.net.cn/order/3680.html) 异步保存数据到硬盘 |
| 28   | [SHUTDOWN [NOSAVE\] [SAVE]](https://www.redis.net.cn/order/3681.html) 异步保存数据到硬盘，并关闭服务器 |
| 29   | [SLAVEOF host port](https://www.redis.net.cn/order/3682.html) 将当前服务器转变为指定服务器的从属服务器(slave server) |
| 30   | [SLOWLOG subcommand [argument\]](https://www.redis.net.cn/order/3683.html) 管理 redis 的慢日志 |
| 31   | [SYNC](https://www.redis.net.cn/order/3684.html) 用于复制功能(replication)的内部命令 |

## 9. 脚本

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [EVAL script numkeys key [key ...\] arg [arg ...]](https://www.redis.net.cn/order/3643.html) 执行 Lua 脚本。 |
| 2    | [EVALSHA sha1 numkeys key [key ...\] arg [arg ...]](https://www.redis.net.cn/order/3644.html) 执行 Lua 脚本。 |
| 3    | [SCRIPT EXISTS script [script ...\]](https://www.redis.net.cn/order/3645.html) 查看指定的脚本是否已经被保存在缓存当中。 |
| 4    | [SCRIPT FLUSH](https://www.redis.net.cn/order/3646.html) 从脚本缓存中移除所有脚本。 |
| 5    | [SCRIPT KILL](https://www.redis.net.cn/order/3647.html) 杀死当前正在运行的 Lua 脚本。 |
| 6    | [SCRIPT LOAD script](https://www.redis.net.cn/order/3648.html) 将脚本 script 添加到脚本缓存中，但并不立即执行这个脚本。 |

## 10. 事务

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [DISCARD](https://www.redis.net.cn/order/3638.html) 取消事务，放弃执行事务块内的所有命令。 |
| 2    | [EXEC](https://www.redis.net.cn/order/3639.html) 执行所有事务块内的命令。 |
| 3    | [MULTI](https://www.redis.net.cn/order/3640.html) 标记一个事务块的开始。 |
| 4    | [UNWATCH](https://www.redis.net.cn/order/3641.html) 取消 WATCH 命令对所有 key 的监视。 |
| 5    | [WATCH key [key ...\]](https://www.redis.net.cn/order/3642.html) 监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。 |

## 11. HelperLogLog

> 计算近似值

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [PFADD key element [element ...\]](https://www.redis.net.cn/order/3629.html) 添加指定元素到 HyperLogLog 中。 |
| 2    | [PFCOUNT key [key ...\]](https://www.redis.net.cn/order/3630.html) 返回给定 HyperLogLog 的基数估算值。 |
| 3    | [PFMERGE destkey sourcekey [sourcekey ...\]](https://www.redis.net.cn/order/3631.html) 将多个 HyperLogLog 合并为一个 HyperLogLog |

## 12. 发布订阅

| 号   | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [PSUBSCRIBE pattern [pattern ...\]](https://www.redis.net.cn/order/3632.html) 订阅一个或多个符合给定模式的频道。 |
| 2    | [PUBSUB subcommand [argument [argument ...\]]](https://www.redis.net.cn/order/3633.html) 查看订阅与发布系统状态。 |
| 3    | [PUBLISH channel message](https://www.redis.net.cn/order/3634.html) 将信息发送到指定的频道。 |
| 4    | [PUNSUBSCRIBE [pattern [pattern ...\]]](https://www.redis.net.cn/order/3635.html) 退订所有给定模式的频道。 |
| 5    | [SUBSCRIBE channel [channel ...\]](https://www.redis.net.cn/order/3636.html) 订阅给定的一个或多个频道的信息。 |
| 6    | [UNSUBSCRIBE [channel [channel ...\]]](https://www.redis.net.cn/order/3637.html) 指退订给定的频道。 |

## 13. 地理位置

> 地理位置

| [Redis GEOHASH 命令](https://www.redis.net.cn/order/3687.html) | 返回一个或多个位置元素的 Geohash 表示                     |
| ------------------------------------------------------------ | --------------------------------------------------------- |
| [Redis GEOPOS 命令](https://www.redis.net.cn/order/3688.html) | 从key里返回所有给定位置元素的位置（经度和纬度）           |
| [Redis GEODIST 命令](https://www.redis.net.cn/order/3686.html) | 返回两个给定位置之间的距离                                |
| [Redis GEORADIUS 命令](https://www.redis.net.cn/order/3689.html) | 以给定的经纬度为中心， 找出某一半径内的元素               |
| [Redis GEOADD 命令](https://www.redis.net.cn/order/3685.html) | 将指定的地理空间位置（纬度、经度、名称）添加到指定的key中 |
| [Redis GEORADIUSBYMEMBER 命令](https://www.redis.net.cn/order/3690.html) | 找出位于指定范围内的元素，中心点是由给定的位置元素决定    |

```
http://api.map.baidu.com/location/ip?ak=BwANis3l4wAbKhCsoC5LOpEzTlbQGhVi
http://api.map.baidu.com/location/ip?ak=BwANis3l4wAbKhCsoC5LOpEzTlbQGhVi
```

数据准备

```
geoadd city 116.403847 39.915526 beijing 121.506572 31.245168 shanghai
geoadd city 113.331084 23.112223 guangzhou 113.821705 22.638172 shenzhen

```

# 三、Redis 持久化(Persistence)机制

## 1. RDB（Redis DateBase）方式

> 

## 2. AOF(Append only File )  方式

> 

# 四、Redis的删除策略

> Redis 不会立即删除过期的Key

## 1. 定期删除

> 1. Redis每隔一段时间就会去查看设置了过期时间的Key，默认在100ms的间隔时间内查看3个Key

## 2. 惰性删除

> 1. 如果你去查询一个过了生存时间的Key时，Redis会先查看当前Key的生存时间，如果已经到了，直接删除当前的Key,并且返回用户一个空值

## 3. 淘汰机制

> 在Redis内存已经满了的时候，添加一个新的数据，执行淘汰机制                                              
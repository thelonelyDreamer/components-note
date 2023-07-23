### 1. 过期 key 删除策略
1. 定时删除：创建一个定时器，当key过期时，定时器立即删除过期数据。这样做节约内存，但是cpu负荷高，由于redis时单线程的，在访问量大时，甚至会引起线程阻塞。
2. 惰性删除： 数据到达过期时间，不做处理。等下次访问该数据时，如果未过期，返回数据。如果已过期，删除，返回不存在。这样CPU压力会降低，但是内存压力很大，长期有过期数据占用内存。
3. 定期删除：这种策略平衡了前两种极端的方案，Redis启动服务器初始化时，读取配置server.hz的值，默认为10.每秒执行server.hz次的serverCron()中的方法，周期性的轮询redis库中的时效型数据，采用随机抽取的策略，利用过期数据占比的方式控制删除频度（过期数据的比例不超过25%）。

### 2. 过期 key 淘汰算法
- volatile-lru -> Evict using approximated LRU, only keys with an expire set.
- allkeys-lru -> Evict any key using approximated LRU.
- volatile-lfu -> Evict using approximated LFU, only keys with an expire set.
- allkeys-lfu -> Evict any key using approximated LFU.
- volatile-random -> Remove a random key having an expire set.
- allkeys-random -> Remove a random key, any key.
- volatile-ttl -> Remove the key with the nearest expire time (minor TTL)
- noeviction -> Don't evict anything, just return an error on write operations.
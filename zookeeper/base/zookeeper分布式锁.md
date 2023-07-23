## 一、相关概念

### 1. 分布式锁原理

> 当客户要获取锁时，在lock节点创建临时有序节点
> 
> 客户端创建的子节点，如果子节点最小，则获取锁，否则监听比自己小中最大的节点
> 
> **临时有序节点**

### 2. curator的内置锁

- InterProcessMultiLock 可以同时对几个路径加锁，释放也是同时的

- InterProcessMutex 可重入排他锁

- InterProcessReadWriteLock 读写锁

- InterProcessSemaphoreMutex 不可重入排他锁

- InterProcessSemaphoreV2 共享信号量

## 二、代码示例

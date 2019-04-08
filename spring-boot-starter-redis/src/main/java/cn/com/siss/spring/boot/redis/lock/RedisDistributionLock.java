package cn.com.siss.spring.boot.redis.lock;

import org.springframework.data.redis.core.RedisTemplate;


public interface RedisDistributionLock {
    /**
     * 加锁成功，返回加锁时间
     * @param lockKey
     * @param threadName
     * @return
     */
    long lock(RedisTemplate redisTemplate, String lockKey, String threadName);

    /**
     * 解锁， 需要更新加锁时间，判断是否有权限
     * @param lockKey
     * @param lockValue
     * @param threadName
     */
    void unlock(RedisTemplate redisTemplate, String lockKey, long lockValue, String threadName);

    /**
     * 多服务器集群，使用下面的方法，代替System.currentTimeMillis()，获取redis时间，避免多服务的时间不一致问题！！！
     * @return
     */
    long currtTimeForRedis(RedisTemplate redisTemplate);
}
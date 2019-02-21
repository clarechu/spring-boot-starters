package cn.com.siss.spring.boot.redis.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisTemplateUtil {
    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public static boolean set(RedisTemplate redisTemplate, final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    public static boolean set(RedisTemplate redisTemplate, final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 批量删除对应的value
     * @param keys
     */
    public static void remove(RedisTemplate redisTemplate, final String... keys) {
        for (String key : keys) {
            remove(redisTemplate,key);
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    public static void removePattern(RedisTemplate redisTemplate, final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    /**
     * 删除对应的value
     * @param key
     */
    public static Boolean remove(RedisTemplate redisTemplate, final String key) {
        Boolean flag = false;
        if (exists(redisTemplate,key)) {
            flag = redisTemplate.delete(key);
        }
        return flag;
    }
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public static boolean exists(RedisTemplate redisTemplate, final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     * @param key
     * @return
     */
    public static Object get(RedisTemplate redisTemplate, final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }
    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public static void hmSet(RedisTemplate redisTemplate, String key, Object hashKey, Object value){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public static Object hmGet(RedisTemplate redisTemplate, String key, Object hashKey){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public static void lPush(RedisTemplate redisTemplate, String k, Object v){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public static List<Object> lRange(RedisTemplate redisTemplate, String k, long l, long l1){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }

    /**
     * 集合添加
     * @param key
     * @param value
     */
    public static Long add(RedisTemplate redisTemplate, String key, Object value){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        Long flag = set.add(key,value);
        return flag;
    }

    /**
     * 集合获取
     * @param key
     * @return
     */
    public static Set<Object> setMembers(RedisTemplate redisTemplate, String key){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public static Boolean zAdd(RedisTemplate redisTemplate, String key, Object value, double scoure){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        Boolean flag = zset.add(key,value,scoure);
        return flag;
    }

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public static Set<Object> rangeByScore(RedisTemplate redisTemplate, String key, double scoure, double scoure1){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    /**
     *
     * @param key
     * @param liveTime
     * @return
     */
    public Long incr(RedisTemplate redisTemplate, String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        entityIdCounter.getAndDecrement();
        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
        log.info("自增加一:{}",increment);
        return increment;
    }

    /**
     *
     * @param key
     * @param liveTime
     * @return
     */
    public Long decr(RedisTemplate redisTemplate, String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndDecrement();
        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
        log.info("减法:{}",increment);
        return increment;
    }


}
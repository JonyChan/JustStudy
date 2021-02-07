package com.test.service;

import com.google.common.base.Joiner;
import com.test.beans.CacheKeyConstants;
import com.test.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;

/**
 * @Created by:chenxu
 * @Created date:2021/2/7 11:35
 */
@Service
@Slf4j
public class SysCacheService {

    @Resource(name = "redisPool")
    private RedisPool redisPool;

    public void saveCache(String toSavedValue, int timeoutSeconds, CacheKeyConstants prefix){
        saveCache(toSavedValue,timeoutSeconds,prefix,null);
    }

    public void saveCache(String toSavedValue, int timeoutSeconds, CacheKeyConstants prefix, String ...keys){
        if (toSavedValue == null){
            return;
        }
        ShardedJedis shardedJedis = null;
        try{
            String cacheKey = generateCacheKey(prefix, keys);
            shardedJedis = redisPool.instance();
            shardedJedis.setex(cacheKey,timeoutSeconds,toSavedValue);
        }catch (Exception e){
            log.error("save cache exception,keys:{},prefix:{}", JsonMapper.obj2String(keys),prefix.name(),e);
        }finally {
            redisPool.safeClose(shardedJedis);
        }
    }

    public String getFromCache(CacheKeyConstants prefix,String...keys){
        String cacheKey = generateCacheKey(prefix, keys);
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = redisPool.instance();
            String value = shardedJedis.get(cacheKey);
            return value;
        }catch (Exception e){
            log.error("get from catch error,prefix:{},keys:{}",prefix.name(),JsonMapper.obj2String(keys),e);
            return null;
        }finally {
            redisPool.safeClose(shardedJedis);
        }
    }

    private String generateCacheKey(CacheKeyConstants prefix,String... keys){
        String key = prefix.name();
        if (keys != null && keys.length>0){
            key += "_" + Joiner.on("_").join(keys);
        }
        return key;
    }

}

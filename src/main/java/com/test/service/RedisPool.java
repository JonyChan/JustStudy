package com.test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/**
 * @Created by:chenxu
 * @Created date:2021/2/7 11:29
 * 通过代码连接Redis，要做安全关闭
 */
@Service("redisPool")
@Slf4j
public class RedisPool {

    @Resource(name = "ShardedJedisPool")
    private ShardedJedisPool shardedJedisPool;


    public ShardedJedis instance(){
        return shardedJedisPool.getResource();
    }

    public void safeClose(ShardedJedis shardedJedis){
        try{
            if (shardedJedis != null){
                shardedJedis.close();
            }
        }catch (Exception e){
            log.error("return redis resource exception",e);
        }
    }

}

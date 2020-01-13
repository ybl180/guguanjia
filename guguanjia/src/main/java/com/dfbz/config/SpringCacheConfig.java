package com.dfbz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/2 13:29
 * @desciption
 */

/**
 * spring缓存配置类
 * 1.引入spring-data-redis和jedis依赖
 * 2.配置redis数据库连接工厂
 * 3.配置RedisTemplate
 */
@Configuration
@PropertySource(value = "classpath:redis.properties", encoding = "utf-8")
@EnableCaching      //开启缓存注解支持
public class SpringCacheConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.maxIdle}")
    private int maxIdle;
    @Value("${redis.minIdle}")
    private int minIdle;

    /**
     * 获取redis的连接对象的工厂类接口，有JedisConnectionFactory和LettuceConnectionFactory两个子类型
     */
    @Bean
    public RedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        //配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        return jedisConnectionFactory;
    }

    /**
     * spring data 提供的封装后的redis数据操作对象
     * 1.设置连接工厂
     * 2.设置key的序列化策略
     * 3.设置value的序列化策略
     */
    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        /*
         * redis的序列化器：RedisSerializer是一个父接口，具体的多个子类型分别实现了不同的序列化方式
         * 1.key的序列化器使用 StringRedisSerializer
         * 2.value的序列化器使用 GenericJackson2JsonRedisSerializer
         */
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        //设置hash序列化器
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        return redisTemplate;
    }

    /**
     * spring cache   整合  redis
     * 1.配置redis的连接工厂
     * 2.配置RedisTemplate
     * 3.配置缓存管理器CacheManager
     * 4.配置开启缓存注解支持
     * 5.缓存管理的实体类需要添加实现序列化接口
     */
    @Bean
    public CacheManager getCacheManager(RedisTemplate<String, Object> redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        HashSet<String> cacheNames = new HashSet<>();
        cacheNames.add("sysOfficeCache");
        cacheNames.add("statuteCache");
        cacheNames.add("resourceCache");
        redisCacheManager.setCacheNames(cacheNames);
        return redisCacheManager;
    }

}

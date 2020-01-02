package com.dfbz.service.Impl;

import com.dfbz.entity.SysOffice;
import com.dfbz.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/2 17:31
 * @desciption
 */
@Service
@Transactional
@CacheConfig(cacheNames = "sysOfficeCache")
public class SysOfficeServiceImpl extends BaseServiceImpl<SysOffice> implements SysOfficeService {
//
//    @Autowired
//    RedisTemplate<String, Object> redisTemplate;
//
//    /*
//     * 需求：改造office的查询功能，实现通过redis缓存来存放查询列表信息
//     * 实现技术： spring-data-redis     jedis连接
//     *
//     * 1.配置spring data redis的设置
//     * 2.如果redis中没有存在对应的key的数据，则从数据库中查询，并放入redis管理
//     *   如果有则直接从缓存数据库中获取
//     * */
//    @Override
//    public List<SysOffice> selectAll() {
//        String key = "com.dfbz.service.Impl.SysOfficeServiceImpl:selectAll";
//        if (redisTemplate.hasKey(key)) {
//            return (List<SysOffice>) redisTemplate.opsForValue().get(key);
//        }
//        List<SysOffice> sysOffices = super.selectAll();
//        redisTemplate.opsForValue().set(key, sysOffices);
//        return sysOffices;
//    }


    /**
     * @Cacheable: 使用缓存，不存在key则自动查询数据库，存在则直接返回key对应的缓存数据
     * cacheNames:设置当前缓存数据存放的缓存对象
     * key：缓存的key值，值是springEl表达式语法
     * 1.字符串必须用''扩住   'cn:nyse:service:impl:SysOfficeServiceImpl:selectAll'
     * 2.获取方法的参数的值则可以通过#参数名.属性名     比如方法参数User user     key动态添加user进来    #user
     * 3.获取方法的参数是map类型可以通过#参数名['key']
     */
    @Cacheable(/*cacheNames = "sysOfficeCache",*/ key = "'com.dfbz.service.Impl.SysOfficeServiceImpl:selectAll'")
    @Override
    public List<SysOffice> selectAll() {
        return super.selectAll();
    }


    /**
     * 当发生增删改需要清空所有管理的缓存信息
     * CacheEvict:清除缓存信息
     * allEntries：表示清空所有当前缓存管理对象的key
     *
     * @param record
     * @return
     */
    @CacheEvict(/*cacheNames = "sysOfficeCache",*/ allEntries = true)
    @Override
    public int updateByPrimaryKeySelective(SysOffice record) {
        return super.updateByPrimaryKeySelective(record);
    }
}

import com.alibaba.druid.pool.DruidDataSource;
import com.dfbz.config.SpringCacheConfig;
import com.dfbz.config.SpringMybatisConfig;
import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Date;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/23 13:52
 * @desciption
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class TestConfig {
    @Autowired
    DruidDataSource dataSource;

    @Autowired
    AppVersionService appVersionService;

    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testService() {
//        System.out.println(appVersionService.selectAll());
        System.out.println(appVersionService.selectPage(1, 3));
    }

//    @Test
//    public void TestInsert() {
//        AppVersion appVersion = new AppVersion();
//        appVersion.setDelFlag("0");
//        appVersion.setUpdateDate(new Date());
//        appVersion.setCreateDate(new Date());
//        appVersion.setCreateBy("admin");
//        appVersion.setDownPath("http://127.0.0.1:8080/guguanjia/manager/#/ajax/manager/app/index");
//        appVersion.setVersionNo("1.5.10");
//        appVersion.setPlatform(0);
//        appVersion.setForceUpdate(0);
//        int i = appVersionService.insert(appVersion);//动态更新
//        int a = i / 0;
//        System.out.println(appVersion);
//    }

    @Test
    public void testSpringCache() {
        System.out.println(jedisConnectionFactory.getConnection().getNativeConnection());
    }


    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("key1", "value1");
        valueOperations.set("键2", "值2");
        System.out.println(valueOperations.get("key1"));
        System.out.println(redisTemplate.keys("*"));

        System.out.println(redisTemplate.hasKey("key1"));
        System.out.println(valueOperations.get("num"));
    }

}

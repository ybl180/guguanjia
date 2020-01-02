package com.dfbz.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/23 13:46
 * @desciption
 */

/**
 * spring与mybatis整合配置：
 * 1.数据源配置
 * 2.SqlSessionFactoryBean配置
 * 3.开启扫描mapper接口
 * <p>
 * spring整合日志：
 * 1.导入log4j2\slf4j依赖
 * 2.在resources下放入log4j2.xml
 * 3.在mybtis的configuration设置使用log4j2的日志
 */
@Configuration
@MapperScan(basePackages = "com.dfbz.mapper")
@Import({SpringTransactionConfig.class, SpringCacheConfig.class})
@PropertySource(value = "classpath:system.properties", encoding = "utf-8")
public class SpringMybatisConfig {

    @Bean
    public DruidDataSource getDataSource() {
        Properties properties = new Properties();
        try {
            properties.load(SpringMybatisConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromPropety(properties);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean getSqlSessionFactory(DruidDataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(Log4j2Impl.class);
        factoryBean.setConfiguration(configuration);

        PageInterceptor pageInterceptor = new PageInterceptor();//分页拦截对象
        //开启分页对象默认设置，解决自动适配方言问题
        pageInterceptor.setProperties(new Properties());
        factoryBean.setPlugins(new Interceptor[]{pageInterceptor});

        return factoryBean;
    }


}

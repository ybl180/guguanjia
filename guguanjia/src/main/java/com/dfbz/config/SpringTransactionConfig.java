package com.dfbz.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/23 17:13
 * @desciption
 */
@Configuration
@ComponentScan(basePackages = "com.dfbz.service")
@EnableTransactionManagement
public class SpringTransactionConfig {
    @Bean
    public DataSourceTransactionManager getDataSourceTransaction(DruidDataSource druidDataSource) {
        return new DataSourceTransactionManager(druidDataSource);
    }
}

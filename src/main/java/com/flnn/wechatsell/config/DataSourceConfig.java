package com.flnn.wechatsell.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    /**
     * 因为mybatis存在自动配置的类，这里只要自定义一个DataSource就可以
     * @return DataSource
     */
    @Bean
    public DataSource createDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("druid-driver-classname"));
        dataSource.setUrl(env.getProperty("druid-jdbc-url"));
        dataSource.setUsername(env.getProperty("druid-datasource-username"));
        dataSource.setPassword(env.getProperty("druid-datasource-password"));
        dataSource.setInitialSize(Integer.valueOf(env.getProperty("druid-min-connection")));
        dataSource.setMinIdle(Integer.valueOf(env.getProperty("druid-min-connection")));
        dataSource.setMaxActive(Integer.valueOf(env.getProperty("druid-max-connection")));

        List<Filter> filterList = new ArrayList<>();
        StatFilter statFilter = new StatFilter();
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(50L);
        statFilter.setLogSlowSql(true);
        filterList.add(statFilter);
        dataSource.setProxyFilters(filterList);
        return dataSource;
    }


}

package tl.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.io.Resources;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("tl")
@MapperScan("tl.mapper")
@EnableTransactionManagement //允许使用事务注解
public class AppConfig {
    @Bean
    public DataSource dataSource(){
        Properties properties=new Properties();
        try {
            properties.load(Resources.getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ComboPooledDataSource c3P0PooledDataSource=new ComboPooledDataSource();
        try {
            c3P0PooledDataSource.setDriverClass(properties.getProperty("driver"));
            c3P0PooledDataSource.setJdbcUrl(properties.getProperty("url"));
            c3P0PooledDataSource.setUser(properties.getProperty("username"));
            c3P0PooledDataSource.setPassword(properties.getProperty("password"));

        }  catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return c3P0PooledDataSource;
    }
    @Bean
    @Autowired
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("tl.entity");
        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
        configuration.setLazyLoadingEnabled(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }

    @Bean
    @Autowired
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        /**
         * 开启声明式事务
         */
        return new DataSourceTransactionManager(dataSource);
    }
}

package com.libarymanagement.core.conf.mybatis;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Lee on 2018/2/28.
 */
@Configuration
@MapperScan(basePackages = "com.libarymanagement.core.mapper")
public class MybatisConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource sqlDbMasterDataSource() throws Exception {
        Properties prop = new Properties();
        prop.put("driverClassName", env.getProperty("spring.datasource.driverClassName"));
        prop.put("url",env.getProperty("spring.datasource.url"));
        prop.put("username",env.getProperty("spring.datasource.username"));
        prop.put("password",env.getProperty("spring.datasource.password"));
        return DruidDataSourceFactory.createDataSource(prop);
    }

//    @Bean
//    public Interceptor[] mybatisInterceptor(){
//        Interceptor interceptor = new MapperInterceptor();
//        Properties properties = new Properties();
//        properties.put("IDENTITY","MYSQL");
//        properties.put("mappers","com.github.abel533.mapper.Mapper");
//        interceptor.setProperties(properties);
//        Interceptor[] c2 = {interceptor};
//        return c2;
//    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置
        return fb.getObject();
    }
}

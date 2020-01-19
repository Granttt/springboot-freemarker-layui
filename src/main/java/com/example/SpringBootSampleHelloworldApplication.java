package com.example;

import com.example.domain.repository.DynamicDataSourceRegister;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

//@EnableScheduling // 启用定时任务
@SpringBootApplication
@Import({DynamicDataSourceRegister.class})//注册动态多数据源
@MapperScan({"com.example.mapper"}) //@MapperScan({"com.kfit.demo","com.kfit.user"})
//@ComponentScan(value="com.thread")
@ComponentScan(value="com") //扫描整个父类包
@EnableCaching
public class SpringBootSampleHelloworldApplication {
	
	protected static Logger logger=LoggerFactory.getLogger(SpringBootSampleHelloworldApplication.class);
	/*
	如果不启用多数据源，则无需以下配置，仍然在application.properties中配置mybatis.mapper-locations即可生效
	如果配置了多数据源，但sql采用注解的方式，也无需以下配置。sql注解的方式包括：@Select， @Insert等。只要在类上添加@Mapper注解即可，容器加载会识别为mapper对象
	如果配置多数据源且采用xml的sql书写方式，则在application.properties中配置mybatis.mapper-locations不再生效，可以在定义SqlSessionFactory的时候
	，加上如下配置：
	 */
	//直接在application.properties文件里添加 mybatis.mapperLocations=classpath:mapper/*.xml 改变等号后面的路径就能指定xml位置的
	@Bean
//    @ConfigurationProperties(prefix="spring.datasource")//读取配置文件中前缀为spring.datasource的所有属性信息
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

	@Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }
//
//	@Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }

	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleHelloworldApplication.class, args);
		logger.info("SpringBoot Start Success");

	}
}

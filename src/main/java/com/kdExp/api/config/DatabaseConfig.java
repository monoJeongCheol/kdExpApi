package com.kdExp.api.config;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.Alias;
import org.modelmapper.ModelMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {

	private Logger	log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ApplicationContext context;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = "kdExpDataSource")
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        
//        factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*.xml"));
//        
//        Resource[] res = resolveMapperLocations();
//        factoryBean.setMapperLocations(res);
//        
//        return factoryBean.getObject();
//    }

//    @Bean
//    public SqlSessionTemplate sqlSession() throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory());
//    }
	
//    private final Environment environment;
//
//    @Bean(name = "yechongDataSource")
//    public DataSource datasource(){
//
//        @SuppressWarnings("rawtypes")
//		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url(environment.getProperty("yechong.datasource.url"));
//        dataSourceBuilder.username(environment.getProperty("yechong.datasource.username"));
//        dataSourceBuilder.password(environment.getProperty("yechong.datasource.password"));
//        dataSourceBuilder.driverClassName(environment.getProperty("yechong.datasource.driver-class-name"));
//
//        return dataSourceBuilder.build();
//    }
//
    @Bean
    public SqlSessionFactory sqlSessionFatory(@Qualifier("kdExpDataSource") DataSource datasource) throws Exception{

        SqlSessionFactoryBean sqlSessFactory = new SqlSessionFactoryBean();
        sqlSessFactory.setDataSource(datasource);

        Resource[] res = resolveMapperLocations();

        sqlSessFactory.setMapperLocations(res);
        sqlSessFactory.setVfs(SpringBootVFS.class);
        sqlSessFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis-config.xml"));

        ClassLoader runClassLoader = Thread.currentThread().getContextClassLoader();
        Reflections reflections = new Reflections(ClasspathHelper.forPackage("com.kdExp.api"), new SubTypesScanner(false));
        List<Class<?>> typeAliasRegistryList = new ArrayList<>();
        reflections.getAllTypes().forEach(type -> {
            Class<?> typeClass;
            try {
                typeClass = Class.forName(type, false, runClassLoader);
                if (typeClass.isAnnotationPresent(Alias.class)) {
                    typeAliasRegistryList.add(typeClass);
                }
            } catch (NoClassDefFoundError ignore) {
            } catch (ClassNotFoundException ignore) {
            }
        });
        Class<?>[] typeAliasRegistryArray = typeAliasRegistryList.toArray(new Class<?>[typeAliasRegistryList.size()]);
        sqlSessFactory.setTypeAliases(typeAliasRegistryArray);

        return sqlSessFactory.getObject();
    }

    // First DB 용 Mapper xml 리스트를 리턴해주는 메소드
    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath:/mappers/**/*.xml");
        mapperLocations.add("classpath:/mappers/**/**/*.xml");
        List<Resource> resources = new ArrayList<>();
        if (!mapperLocations.isEmpty()) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("kdExpDataSource") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

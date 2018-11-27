package com.tan.springbootmysql.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//表明此配置类的优先级最高
/*@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
//开启事务，使用基于类的动态代理，即CGLib
@EnableTransactionManagement(proxyTargetClass = true)
//开启对Spring Data JPA Repository的支持
@EnableJpaRepositories(basePackages = "com.tan.springbootmysql.repository")
@EntityScan(basePackages = "com.tan.springbootmysql.entity")
public class JpaConfiguration {
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}*/

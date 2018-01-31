package com.dummies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * @author taumal
 * @since 11/13/17 7:09 PM
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.dummies.dao", "com.dummies.service"})
@PropertySource({"classpath:db.properties"})
public class RootConfig {

    @Autowired
    private Environment env;

    Properties hibernateProperties() {
        return new Properties() {
            {
//                setProperty("hibernate.hbm2ddl.auto", "create-drop"); //hibernate.hbm2ddl.auto
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//                setProperty("hibernate.globally_quoted_identifiers", "true");
                setProperty("hibernate.show.sql", "false");
                setProperty("hibernate.query.substitutions", "true=1 false=0");
                setProperty("hibernate.jdbc.use_scrollable_resultset", "true");
                setProperty("hibernate.jdbc.use_get_generated_keys", "false");
                setProperty("hibernate.cache.use_second_level_cache", "true");
                setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
                setProperty("hibernate.cache.provider_configuration_file_resource_path", "ehcache.xml");
                setProperty("hibernate.generate_statistics", "true");
            }
        };
    }
}

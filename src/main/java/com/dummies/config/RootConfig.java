package com.dummies.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
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

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan("org.quantum.qmail.model","");
//        sessionFactory.setPhysicalNamingStrategy(PhysicalNamingStrategyStandardImpl.INSTANCE);
        sessionFactory.setImplicitNamingStrategy(ImplicitNamingStrategyLegacyJpaImpl.INSTANCE);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource getDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(env.getProperty("app.jdbc.driverClassName"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(env.getProperty("app.jdbc.url"));
        dataSource.setUser(env.getProperty("app.jdbc.username"));
        dataSource.setPassword(env.getProperty("app.jdbc.password"));
        dataSource.setAcquireIncrement(5);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setMaxPoolSize(30);
        dataSource.setMaxStatements(50);
        dataSource.setMinPoolSize(5);

        return dataSource;
    }
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());

        return txManager;
    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(sessionFactory().getObject());

        return hibernateTemplate;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        jdbcTemplate.setFetchSize(50);

        return jdbcTemplate;
    }

    @Bean
    BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator apc = new BeanNameAutoProxyCreator();
        apc.setBeanNames(new String[]{"*Dao","*Service"});
        apc.setInterceptorNames(new String[]{"transactionInterceptor"});
        apc.setProxyTargetClass(true); //Force CGLib to create bean without interface

        return apc;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


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

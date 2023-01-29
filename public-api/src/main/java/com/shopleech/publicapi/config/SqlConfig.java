package com.shopleech.publicapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Ahto Jalak
 * @since 25.01.2023
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.shopleech.publicapi.*"})
@PropertySource("classpath:application.properties")
public class SqlConfig {

   @Autowired
   private DataSource dataSource;
   @Autowired
   private LocalContainerEntityManagerFactoryBean entityManagerFactory;
   private Environment env;

   public SqlConfig(Environment env) {
      this.env = env;
   }

   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("org.postgresql.Driver");
      dataSource.setUrl(env.getProperty("spring.datasource.url"));
      dataSource.setSchema(env.getProperty("spring.datasource.schema"));
      dataSource.setUsername(env.getProperty("spring.datasource.username"));
      dataSource.setPassword(env.getProperty("spring.datasource.password"));
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em =
              new LocalContainerEntityManagerFactoryBean();

      em.setDataSource(dataSource);

      // Classpath scanning of @Component, @Service, etc annotated class
      em.setPackagesToScan(new String[]{"com.shopleech.publicapi"});

      // Vendor adapter
      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);

      // Hibernate properties
      Properties additionalProperties = new Properties();
      additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
      additionalProperties.put("hibernate.show_sql", "true");
      // additionalProperties.put("hibernate.hbm2ddl.auto", "");
      em.setJpaProperties(additionalProperties);

      return em;
   }

   @Bean
   public JpaTransactionManager transactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
      return transactionManager;
   }

   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   }
}

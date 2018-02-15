/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.persist.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;

import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.support.ConnectionUsernameProvider;
import org.springframework.data.jdbc.support.oracle.ProxyDataSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Siva
 *
 */
/*@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
            "co.org.profamilia.transversal.persist.repository"
        }, 
        transactionManagerRef = "transversalTransactionManager",
        entityManagerFactoryRef = "transversalEntityManagerFactory")
*/
public class TransversalPersistenceConfig {

    private static final Logger logger = LoggerFactory.getLogger(TransversalPersistenceConfig.class);

    private OracleDataSource oracleDs = null;

    @Autowired
    private ConnectionUsernameProvider contextProvider;

    @Value("${dataSource.driver_class}")
    private String driverClass;
    @Value("${dataSource.url}")
    private String url;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    @Value("${hibernate.show_sql}")
    private String showSql;
    @Value("${hibernate.format_sql}")
    private String formatSql;
    @Value("${hibernate.use_sql_comments}")
    private String useSqlComments;
    @Value("${hibernate.default_schema:}")
    private String defaultSchema;
    
    @Primary
    @Bean(name = "transversalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean transversalEntityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(transversalDataSource());
        em.setPackagesToScan(new String[]{"co.org.profamilia.transversal.persist"});

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Primary
    @Bean(name = "transversalTransactionManager")
    @Autowired    
    public PlatformTransactionManager transversalTransactionManager(@Qualifier("transversalEntityManagerFactory") EntityManagerFactory transversalEntityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(transversalEntityManagerFactory);
        return transactionManager;
    }

    @Primary
    @Bean(name = "transversalDataSource")
    public DataSource transversalDataSource() {
        logger.error(contextProvider.toString());
        logger.error(transversalOracleDataSource().toString());
        ProxyDataSource dataSource = new ProxyDataSource(oracleDs, contextProvider);
        return dataSource;
    }

    @Primary
    @Bean(name = "transversalOracleDataSource")
    public OracleDataSource transversalOracleDataSource() {
        try {
            oracleDs = new OracleDataSource();
            oracleDs.setUser(username);
            oracleDs.setPassword(password);
            oracleDs.setURL(url);
        } catch (Exception ex) {
            logger.error(null, ex);
        }
        return oracleDs;
    }

    final Properties additionalProperties() {

        logger.error("hibernate.dialect: [" + this.dialect + "]");
        logger.error("hibernate.show_sql: [" + this.showSql + "]");

        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", this.dialect);
        hibernateProperties.setProperty("hibernate.show_sql", this.showSql);
        hibernateProperties.setProperty("hibernate.format_sql", this.formatSql);
        if (!this.defaultSchema.isEmpty()) {
            logger.error("hibernate.default_schema: [" + this.defaultSchema + "]");
            hibernateProperties.setProperty("hibernate.default_schema", this.defaultSchema);
        }
        //hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
        //hibernateProperties.setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
        //jpaProperties.put("hibernate.hbm2ddl.auto", this.hbm2ddlAuto);

        return hibernateProperties;
    }

}

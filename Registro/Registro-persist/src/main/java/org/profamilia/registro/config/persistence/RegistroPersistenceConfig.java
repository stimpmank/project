package org.profamilia.registro.config.persistence;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.support.ConnectionUsernameProvider;
import org.springframework.data.jdbc.support.oracle.ProxyDataSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

/*@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
            "org.profamilia.registro.model.repository"
        }, 
        transactionManagerRef = "registroHibernateTransactionManager",
        entityManagerFactoryRef = "registroSessionFactory")
*/
public class RegistroPersistenceConfig {

    private static final Logger logger = LoggerFactory.getLogger(RegistroPersistenceConfig.class);

    private OracleDataSource oracleDs = null;

    @Autowired
    private ConnectionUsernameProvider contextProvider;

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

    
    @Bean(name = "registroSessionFactory")
    public SessionFactory registroSessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(registroDataSource());
        builder.scanPackages(new String[]{"org.profamilia.registro.model.entities"})
                .addProperties(getHibernateProperties());
        return builder.buildSessionFactory();
    }
    
    @Bean(name = "registroHibernateTransactionManager")
    @Autowired
    @Qualifier("registroSessionFactory")
    public HibernateTransactionManager registroHibernateTransactionManager(SessionFactory sessionFactory) throws SQLException {
        return new HibernateTransactionManager(sessionFactory);
    }

    final Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.dialect", dialect);
        prop.put("hibernate.show_sql", showSql);
        prop.put("hibernate.format_sql", formatSql);
        //prop.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);        
        return prop;
    }
    
    @Bean(name = "registroDataSource")
    public DataSource registroDataSource() {
        logger.error(contextProvider.toString());
        logger.error(oracleRegistroDataSource().toString());        
        ProxyDataSource dataSource = new ProxyDataSource(oracleDs, contextProvider);
        return dataSource;
    }

    @Bean(name="oracleRegistroDataSource")
    public OracleDataSource oracleRegistroDataSource() {
        try {
            logger.error(url);
            oracleDs = new OracleDataSource();
            oracleDs.setUser(username);
            oracleDs.setPassword(password);
            oracleDs.setURL(url);
        } catch (Exception ex) {
            logger.error(null, ex);
        } 
        return oracleDs;
    }
    
}

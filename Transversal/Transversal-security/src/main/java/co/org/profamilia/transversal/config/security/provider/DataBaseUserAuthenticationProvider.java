    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.config.security.provider;

import co.org.profamilia.transversal.persist.entities.Sausuario;
import co.org.profamilia.transversal.service.CustomUserDetailsService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.jdbc.support.ConnectionUsernameProvider;
import org.springframework.data.jdbc.support.oracle.ProxyDataSource;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Order(value = 100)
public class DataBaseUserAuthenticationProvider extends DaoAuthenticationProvider {
    
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
    
    @Override
    protected void doAfterPropertiesSet() throws Exception {
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        Connection conn = null;
        String loginUsername = null, loginPasswd = null;

        if (authentication.getName() == null) {
            logger.debug("Authentication failed: no username provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        try {
            loginUsername = authentication.getName();
            loginPasswd = authentication.getCredentials().toString();
        } catch (Exception ex) {
            logger.warn("Authentication failed: error getting username/password credentials", ex);
        }

        try {
            conn = createOracleDataSource(loginUsername, loginPasswd).getConnection();
            conn.close();
        } catch (SQLException ex) {
            logger.error(authentication, ex);
            handleLoginException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        
        try {
            
            SimpleJpaRepository sr = new SimpleJpaRepository(Sausuario.class, this.sessionFactory(loginUsername).createEntityManager());
            this.setUserDetailsService(new CustomUserDetailsService(sr));            
        } catch (SQLException ex) {
            handleLoginException(ex);
        }
        return super.authenticate(authentication);
    }

    private void handleLoginException(SQLException ex) {

        if (ex.getMessage().contains("ORA-28001")) {
            throw new CredentialsExpiredException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.expired",
                    "Credentials was expired"));
        }

        if (ex.getMessage().contains("ORA-01017")) {
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        if (ex.getMessage().contains("The Network Adapter could not establish the connection")) {
            throw new AuthenticationServiceException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.connError",
                    "Error al conectar con la base de datos de aplicacion"), ex);
        }

        throw new AuthenticationServiceException("Error al validar el usuario: No posee los permisos necesarios.");
    }
    
    private SessionFactory sessionFactory(String username) throws SQLException {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource(username));
        builder.scanPackages(new String[]{"co.org.profamilia.transversal.persist"}).addProperties(getHibernateProperties());
        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.dialect", dialect);
        prop.put("hibernate.show_sql", showSql);
        prop.put("hibernate.format_sql", formatSql);
        //prop.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        return prop;
    }
    
    final Properties additionalProperties() {
        
        logger.error("hibernate.dialect: ["+this.dialect+"]");
        logger.error("hibernate.show_sql: ["+this.showSql+"]");
        
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", this.dialect);
        hibernateProperties.setProperty("hibernate.show_sql", this.showSql);
        hibernateProperties.setProperty("hibernate.format_sql", this.formatSql);
        //hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
        //hibernateProperties.setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
        //jpaProperties.put("hibernate.hbm2ddl.auto", this.hbm2ddlAuto);
        
        return hibernateProperties;
    }

    public DataSource dataSource(String username) throws SQLException {
        logger.error("this.username: ["+this.username+"], this.password["+this.password+"]");
        logger.error("user from login page: [" + username + "]");
        ProxyDataSource dataSource = new ProxyDataSource(
                createOracleDataSource(this.username, this.password), (ConnectionUsernameProvider) () -> {
                    logger.debug("user from login page: [" + username + "]");
                    return username;
                });
        return dataSource;
    }
    
    private OracleDataSource createOracleDataSource(final String username, final String password) throws SQLException {
        OracleDataSource oracleDs = new OracleDataSource();
        oracleDs.setUser(username);
        oracleDs.setPassword(password);
        oracleDs.setURL(url);
        return oracleDs;
    }


}

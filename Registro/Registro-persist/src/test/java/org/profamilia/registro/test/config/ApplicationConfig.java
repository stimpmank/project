package org.profamilia.registro.test.config;


import co.org.profamilia.transversal.config.security.provider.CurrentUsernameProvider;
import org.profamilia.registro.config.persistence.HibernatePersistenceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jdbc.support.ConnectionUsernameProvider;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySources({
    @PropertySource("classpath:application.properties")
})
@ComponentScan(
        basePackages = {
            "org.profamilia.registro"
        })
//@Import({RegistroPersistenceConfig.class})
@Import({HibernatePersistenceConfig.class})
public class ApplicationConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        logger.error("---");
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public ConnectionUsernameProvider connectionUsernameProvider() {
            return new CurrentUsernameProvider();
        }
    
}

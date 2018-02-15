package co.org.profamilia.transversal.test.config;

import co.org.profamilia.transversal.config.security.provider.CurrentUsernameProvider;
import co.org.profamilia.transversal.persist.config.JPAPersistenceConfig;
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
            "co.org.profamilia.tranversal"
        })
//@Import({TransversalPersistenceConfig.class})
@Import({JPAPersistenceConfig.class})
public class ApplicationConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public ConnectionUsernameProvider connectionUsernameProvider() {
            return new CurrentUsernameProvider();
        }
    
}

package org.profamilia.registro.config;

import co.org.profamilia.transversal.persist.config.TransversalPersistenceConfig;
import org.profamilia.registro.config.persistence.RegistroPersistenceConfig;
import org.springframework.context.annotation.Bean;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySources({
    @PropertySource("classpath:persistence.properties"),
    @PropertySource("classpath:application.properties")
})
@ComponentScan(
        basePackages = {
            "co.org.profamilia.transversal",
            "co.org.profamilia.clinico",
            "org.profamilia.registro"
        }, excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = RegistroPersistenceConfig.class),
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = TransversalPersistenceConfig.class)})
public class ApplicationConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}

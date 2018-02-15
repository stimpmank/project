package co.org.profamilia.transversal.config.security;

import co.org.profamilia.transversal.config.security.encoder.FakePasswordEncoder;
import co.org.profamilia.transversal.config.security.provider.DataBaseUserAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration
@PropertySources({
    @PropertySource("classpath:persistence.properties")
})
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = {"co.org.profamilia.transversal"})

@Order(value = 100)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";

    public SecurityConfig() {
    }
    
    public MessageSource messageSource() {
        logger.error("---");
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE);
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding(CHARACTER_ENCODING);
        return messageSource;
    }
        
    @Bean
    public DataBaseUserAuthenticationProvider authenticationProvider() throws Exception {
        logger.error("---");
        DataBaseUserAuthenticationProvider authenticationProvider = new DataBaseUserAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setMessageSource(messageSource());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.error("---");
        return new FakePasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.error("---");
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        logger.error("---");
        web.ignoring().antMatchers("/resources/**", "/javax.faces.resource/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.error("---");
        http
                .authorizeRequests()
                .antMatchers("/", "/index.pf", "/templates/**").permitAll()
                .antMatchers("/content/**", "/prin*").access("hasRole('ROLE_ALLACCESS')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/appLogin")
                .usernameParameter("app_username")
                .passwordParameter("app_password")
                .defaultSuccessUrl("/principal", true)
                .and()
                .logout()
                .logoutUrl("/appLogout").permitAll()
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                //.rememberMeServices(rememberMeServices())
                .key("remember-me-key")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accesoden")
                .and()
                .csrf().disable();

    }

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        logger.error("---");
        return super.authenticationManagerBean();
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    

}

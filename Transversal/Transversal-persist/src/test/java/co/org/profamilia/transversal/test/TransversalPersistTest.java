/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.test;

import co.org.profamilia.transversal.test.config.ApplicationConfig;
import co.org.profamilia.transversal.persist.entities.Sapermrol;
import co.org.profamilia.transversal.persist.repository.SausuarioRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 *
 * @author czambrano
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ApplicationConfig.class})
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        DbUnitTestExecutionListener.class})
//@PropertySources({
//    @PropertySource("classpath:application.properties")
//})
//@DbUnitConfiguration(databaseConnection={"transversalDataSource"})
//@DatabaseSetup(type = DatabaseOperation.REFRESH, value = "/programacion-entries.xml")
public class TransversalPersistTest {
    
    private static final Logger logger = LoggerFactory.getLogger(TransversalPersistTest.class);
        
    @Autowired
    private SausuarioRepository sausuarioRepository;
    
    //@Test
    public void getPermisosUsuarioRol() {
        Date today = new Date();
        List<Sapermrol> searchResults = sausuarioRepository.getPermisosUsuarioRol("sysadb", today, today, "sysadb", today, today);
        assertThat(searchResults).hasAtLeastOneElementOfType(Sapermrol.class);
    }
    
    
}

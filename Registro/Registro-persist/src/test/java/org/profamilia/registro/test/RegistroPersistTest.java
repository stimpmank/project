/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profamilia.registro.test;

import org.profamilia.registro.test.config.ApplicationConfig;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import java.util.List;
import java.util.logging.Level;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.profamilia.registro.model.entities.Sapermrol;
import org.profamilia.registro.model.exception.ModelException;
import org.profamilia.registro.model.repository.ActivosDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@PropertySources({
@PropertySource("classpath:application.properties")
})
@DbUnitConfiguration(databaseConnection={"registroDataSource"})
//@DatabaseSetup(type = DatabaseOperation.REFRESH, value = "/programacion-entries.xml")
public class RegistroPersistTest {
    
    private static final Logger logger = LoggerFactory.getLogger(RegistroPersistTest.class);
        
    @Autowired
    @Qualifier("ActivosDaoImpl")
    private ActivosDao activosDaoImpl;
    
    @Test
    public void getPermisosUsuarioRol() {
        try {
            List<Sapermrol> searchResults = activosDaoImpl.getPermisosUsuarioRol("sysadb");
            assertThat(searchResults).hasAtLeastOneElementOfType(Sapermrol.class);
        } catch (ModelException ex) {
            java.util.logging.Logger.getLogger(RegistroPersistTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

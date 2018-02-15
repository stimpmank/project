/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.profamilia.clinico.test;


import co.com.profamilia.clinico.test.config.ApplicationConfig;
import co.org.profamilia.clinico.persist.repository.CpclinicaRepository;
import co.org.profamilia.clinico.persist.repository.CpprofesioRepository;
import co.org.profamilia.clinico.persistence.entity.Cpclinica;
import co.org.profamilia.clinico.persistence.entity.Cpprofesio;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class})
@PropertySources({
	@PropertySource("classpath:application.properties")
})
@DbUnitConfiguration(databaseConnection={"transversalDataSource"})
//@DatabaseSetup(type = DatabaseOperation.REFRESH, value = "/programacion-entries.xml")

public class ClinicoPersistTest {
    
    private static final Logger logger = LoggerFactory.getLogger(ClinicoPersistTest.class);
    
    
    @Autowired
    private CpprofesioRepository cpprofesioRepository;
    
    @Autowired
    CpclinicaRepository cpclinicaRepository;
    
    
    @Test
    public void findActiveCpprofesioByCpclinica() {
        Iterable<Cpprofesio> searchResults = cpprofesioRepository.findActiveCpprofesio(Long.valueOf("70"), null, null);
         
        assertThat(searchResults).hasAtLeastOneElementOfType(Cpprofesio.class);
    }
    
    //@Test
    public void findActiveCpclinica() {
        Iterable<Cpclinica> searchResults = cpclinicaRepository.findActiveCpclinica();
         
        assertThat(searchResults).hasAtLeastOneElementOfType(Cpclinica.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.profamilia.agendamiento.test;

import co.com.profamilia.agendamiento.test.config.ApplicationConfig;
import co.org.profamilia.agendamiento.persist.repository.NovedadDetalleRepository;
import co.org.profamilia.agendamiento.persist.repository.NovedadRepository;
import co.org.profamilia.agendamiento.persist.repository.ProgramacionRepository;
import co.org.profamilia.agendamiento.persistence.entity.Novedad;
import co.org.profamilia.agendamiento.persistence.entity.NovedadDetalle;
import co.org.profamilia.agendamiento.persistence.entity.Programacion;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import java.sql.Date;
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
//TransactionalTestExecutionListener.class,
//DbUnitTestExecutionListener.class})
//@PropertySources({
//@PropertySource("classpath:application.properties")
//})
//@DbUnitConfiguration(databaseConnection={"transversalDataSource"})
//@DatabaseSetup(type = DatabaseOperation.REFRESH, value = "/programacion-entries.xml")

public class AgendamientoPersistTest {
    
    private static final Logger logger = LoggerFactory.getLogger(AgendamientoPersistTest.class);
    
    @Autowired
    private NovedadRepository novedadRepository;
    
    @Autowired
    private ProgramacionRepository programacionRepository;
    
    @Autowired
    private NovedadDetalleRepository novedadDetalleRepository;
    
    
    //@Test
    public void findProgramacion() {
        Iterable<Programacion> searchResults = programacionRepository.findProgramacion(Long.valueOf(1), Long.valueOf(1));
        assertThat(searchResults).hasAtLeastOneElementOfType(Programacion.class);
    }
    
    //@Test
    public void findNovedad() {
        Iterable<Novedad> searchResults = novedadRepository.findNovedad(Long.valueOf(1), Long.valueOf(1), Date.valueOf("2018-01-01"), Date.valueOf("2018-01-30"));
         
        for (Novedad n: searchResults){
            logger.error(""+n.getFechaFin());
        }
        assertThat(searchResults).hasAtLeastOneElementOfType(Novedad.class);
    }
    /*
    @Test
    public void findNovedad2() {
        Novedad searchResults = novedadRepository.findNovedadById(Long.valueOf(10));
         
        assertThat(searchResults).isInstanceOf(Novedad.class);
    }
    
    @Test
    public void findNovedadDetalle() {
        Iterable<NovedadDetalle> searchResults = novedadDetalleRepository.findByDiaNovedad(Long.valueOf(1), Long.valueOf(1), Date.valueOf("2018-01-01"), Date.valueOf("2018-01-30"));
         
        assertThat(searchResults).hasAtLeastOneElementOfType(NovedadDetalle.class);
    }
    */
        
}

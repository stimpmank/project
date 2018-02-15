package co.org.profamilia.agendamiento.persist.repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.springframework.data.repository.CrudRepository;

import co.org.profamilia.agendamiento.persistence.entity.DiaNovedad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author czambrano
 */

public interface DiaNovedadRepository extends CrudRepository<DiaNovedad, Long> {
    
    static final String BASE_QUERY = ""
            + " SELECT DISTINCT n FROM DiaNovedad n "
            + "   LEFT JOIN FETCH n.novedad no "                  
            + "   LEFT JOIN FETCH no.programacion p "
            + " WHERE "
            + "   p.id = :idNovedad ";
    
    @Query(value = "SELECT p FROM DiaNovedad p LEFT JOIN FETCH p.novedadDetalles where p.id = :id")
    DiaNovedad findById(@Param("id") Long id);
    
    @Query(value = BASE_QUERY)
    Iterable<DiaNovedad> findByNovedad(@Param("idNovedad") Long idNovedad);
    
}

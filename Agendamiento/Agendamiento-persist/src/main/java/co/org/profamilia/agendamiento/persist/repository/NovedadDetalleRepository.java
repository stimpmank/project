package co.org.profamilia.agendamiento.persist.repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.org.profamilia.agendamiento.persistence.entity.NovedadDetalle;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author czambrano
 */
public interface NovedadDetalleRepository extends CrudRepository<NovedadDetalle, Long>, NovedadDetalleRepositoryCustom {
    
     static final String BASE_QUERY = ""
            + " SELECT DISTINCT n FROM NovedadDetalle n "
            + "   LEFT JOIN FETCH n.diaNovedad d "             
            + "   LEFT JOIN FETCH d.novedad no "            
            + "   LEFT JOIN FETCH no.programacion p "
            + " WHERE "
            + "   1 = 1 ";
    
    @Query(value = BASE_QUERY
            + "   and n.fecha IS NOT NULL "
            + "   and n.fecha BETWEEN :fechaInicio and :fechaFin"
            + "   and d.novedad.programacion.idProfesional = :idProfesional "
            + "   and d.novedad.programacion.idSede = :idSede "
            + " ORDER BY "
            + "   n.fecha asc")
    Iterable<NovedadDetalle> findByDiaNovedad(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin);  
    
    List<NovedadDetalle> findByDiaNovedad(Long IdNovedad);
    
}

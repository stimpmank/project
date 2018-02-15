package co.org.profamilia.agendamiento.persist.repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.org.profamilia.agendamiento.persistence.entity.DiaNovedad;

import org.springframework.data.repository.CrudRepository;

import co.org.profamilia.agendamiento.persistence.entity.Novedad;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author czambrano
 */
public interface NovedadRepository extends CrudRepository<Novedad, Long> {
    
    static final String BASE_QUERY = ""
            + " SELECT DISTINCT n FROM Novedad n "
            + "   LEFT JOIN FETCH n.programacion p "
            + " WHERE "
            + "   p.idProfesional IS NOT NULL "
            + "   and p.idSede IS NOT NULL "
            + "   and p.idProfesional = :idProfesional "
            + "   and p.idSede = :idSede";

    static final String BASE_QUERY_FETCH_DIA_NOVEDADES = ""
            + " SELECT DISTINCT n FROM Novedad n "
            + "   LEFT JOIN FETCH n.programacion p "
            + "   LEFT JOIN FETCH n.diaNovedads d "
            + " WHERE "
            + "   p.idProfesional IS NOT NULL "
            + "   and p.idSede IS NOT NULL "
            + "   and p.idProfesional = :idProfesional "
            + "   and p.idSede = :idSede";
    
    @Query(value = ""
            + " SELECT n FROM Novedad n "            
            + "   JOIN FETCH n.programacion p "
            + "   JOIN FETCH n.diaNovedads d "
            + " WHERE "
            + "  n.id = :id")
    Novedad findNovedadById(
            @Param("id") Long id);
    
    @Query(value = BASE_QUERY)
    Iterable<Novedad> findNovedad(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede);
    
    @Query(value = BASE_QUERY
            + "   and n.fechaInicio IS NOT NULL "
            + "   and n.fechaFin IS NOT NULL "
            + "   and n.fechaInicio BETWEEN :fechaInicio and :fechaFin"
            + "   and n.fechaFin BETWEEN :fechaInicio and :fechaFin")
    Iterable<Novedad> findNovedad(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin);
    
    @Query(value = BASE_QUERY
            + "   and n.fechaInicio IS NOT NULL "
            + "   and n.fechaFin IS NOT NULL "
            + "   and n.fechaInicio BETWEEN :fechaInicio and :fechaFin"
            + "   and n.fechaFin BETWEEN :fechaInicio and :fechaFin"
            + "   and n.estado = :estado ")
    Iterable<Novedad> findNovedad(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin,
            @Param("estado") Long estado);

    @Query(value = BASE_QUERY_FETCH_DIA_NOVEDADES
            + "   and n.fechaInicio IS NOT NULL "
            + "   and n.fechaFin IS NOT NULL "
            + "   and n.fechaInicio BETWEEN :fechaInicio and :fechaFin"
            + "   and n.fechaFin BETWEEN :fechaInicio and :fechaFin")
    Iterable<Novedad> findNovedadFetchDias(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin);

    @Query(value = BASE_QUERY_FETCH_DIA_NOVEDADES            
            + "   and n.fechaInicio IS NOT NULL "
            + "   and n.fechaFin IS NOT NULL "
            + "   and n.fechaInicio BETWEEN :fechaInicio and :fechaFin"
            + "   and n.fechaFin BETWEEN :fechaInicio and :fechaFin"
            + "   and n.estado <= :estado ")
    Iterable<Novedad> findNovedadFetchDias(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin,
            @Param("estado") Long estado);

}

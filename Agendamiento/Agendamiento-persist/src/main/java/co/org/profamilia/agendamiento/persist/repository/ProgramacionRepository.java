package co.org.profamilia.agendamiento.persist.repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.springframework.data.repository.CrudRepository;

import co.org.profamilia.agendamiento.persistence.entity.Programacion;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author czambrano
 */
public interface ProgramacionRepository extends CrudRepository<Programacion, Long>, JpaSpecificationExecutor<Programacion> {

    static final String BASE_QUERY = " SELECT DISTINCT p FROM Programacion p "
            + "   LEFT JOIN FETCH p.diaProgramacions d"
            + " where "
            + "   p.idProfesional IS NOT NULL "
            + "   and p.idSede IS NOT NULL "
            + "   and p.idProfesional = :idProfesional "
            + "   and p.idSede = :idSede";
    
    static final String BASE_QUERY_FETCH_NOVEDADES = " SELECT DISTINCT p FROM Programacion p "
            + "   JOIN FETCH p.novedads n"
            + "   JOIN FETCH p.diaProgramacions d"
            + " where "
            + "   p.idProfesional IS NOT NULL "
            + "   and p.idSede IS NOT NULL "
            + "   and p.idProfesional = :idProfesional "
            + "   and p.idSede = :idSede";

    @Query(value = BASE_QUERY_FETCH_NOVEDADES)
    Iterable<Programacion> findProgramacionFetchNovedades(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede);

    @Query(value = BASE_QUERY_FETCH_NOVEDADES
            + "   and p.estado IS NOT NULL "
            + "   and p.estado = :estado ")
    Iterable<Programacion> findProgramacionFetchNovedades(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("estado") Long estado);

    @Query(value = BASE_QUERY_FETCH_NOVEDADES
            + "   and p.fechaInicio IS NOT NULL "
            + "   and p.fechaFin IS NOT NULL "
            + "   and p.fechaInicio = :fechaInicio"
            + "   and p.fechaFin = :fechaFin")
    Iterable<Programacion> findProgramacionFetchNovedades(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin);

    @Query(value = BASE_QUERY_FETCH_NOVEDADES
            + "   and p.fechaInicio IS NOT NULL "
            + "   and p.fechaFin IS NOT NULL "
            + "   and p.estado IS NOT NULL "
            + "   and p.fechaInicio = :fechaInicio"
            + "   and p.fechaFin = :fechaFin"
            + "   and p.estado = :estado ")
    Iterable<Programacion> findProgramacionFetchNovedades(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin,
            @Param("estado") Long estado);
    
    @Query(value = BASE_QUERY)
    Iterable<Programacion> findProgramacion(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede);

    @Query(value = BASE_QUERY
            + "   and p.estado IS NOT NULL "
            + "   and p.estado = :estado ")
    Iterable<Programacion> findProgramacion(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("estado") Long estado);

    @Query(value = BASE_QUERY
            + "   and p.fechaInicio IS NOT NULL "
            + "   and p.fechaFin IS NOT NULL "
            + "   and p.fechaInicio = :fechaInicio"
            + "   and p.fechaFin = :fechaFin")
    Iterable<Programacion> findProgramacion(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin);

    @Query(value = BASE_QUERY
            + "   and p.fechaInicio IS NOT NULL "
            + "   and p.fechaFin IS NOT NULL "
            + "   and p.estado IS NOT NULL "
            + "   and p.fechaInicio = :fechaInicio"
            + "   and p.fechaFin = :fechaFin"
            + "   and p.estado = :estado ")
    Iterable<Programacion> findProgramacion(
            @Param("idProfesional") Long idProfesional,
            @Param("idSede") Long idSede,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin,
            @Param("estado") Long estado);

    // solo para ejemplo de uso criteria api (si se requiere un query dinamico)
    default Programacion findProgramacionCriteria(Long idProfesional, Long idSede, Date fechaInicio, Date fechaFin) {
        return findOne(new Specification<Programacion>() {
            @Override
            public Predicate toPredicate(Root<Programacion> root,
                    CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                if (idProfesional != null && idSede != null && fechaInicio != null && fechaFin != null) {
                    return criteriaQuery.where(
                            criteriaBuilder.and(
                                    criteriaBuilder.and(
                                            root.get("idProfesional").isNotNull(),
                                            criteriaBuilder.equal(
                                                    root.get("idProfesional"),
                                                    idProfesional
                                            )
                                    ),
                                    criteriaBuilder.and(
                                            root.get("idSede").isNotNull(),
                                            criteriaBuilder.equal(
                                                    root.get("idSede"),
                                                    idSede
                                            )
                                    ),
                                    criteriaBuilder.and(
                                            root.get("fechaInicio").isNotNull(),
                                            criteriaBuilder.equal(
                                                    root.get("fechaInicio"),
                                                    fechaInicio
                                            )
                                    ),
                                    criteriaBuilder.and(
                                            root.get("fechaFin").isNotNull(),
                                            criteriaBuilder.equal(
                                                    root.get("fechaFin"),
                                                    fechaInicio
                                            )
                                    )
                            )
                    ).getRestriction();
                } else {
                    return criteriaBuilder.conjunction();
                }
            }
        });
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.clinico.persist.repository;

import co.org.profamilia.clinico.persistence.entity.Cpprofesio;
import co.org.profamilia.clinico.persistence.entity.CpprofesioId;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author czambrano
 */
public interface CpprofesioRepository extends CrudRepository<Cpprofesio, CpprofesioId>, JpaSpecificationExecutor<Cpprofesio> {

    static final String CPPROFESIO_AND_CPPLINICA_BASE = " SELECT DISTINCT p FROM Cpprofesio p "
            + "   LEFT JOIN FETCH p.cpclinica c"
            + " WHERE "
            + "   p.id.cpfncodigo IS NOT NULL"
            ;    
    
    @Query(value = CPPROFESIO_AND_CPPLINICA_BASE
            + "   AND p.cpclinica IS NOT NULL "
            + "   AND p.cpclinica.cclncodigo = :cpclinica "
            + "   AND p.cpfcestado IS NOT NULL "
            + "   AND p.cpfcestado = :cpfcestado")
    Iterable<Cpprofesio> findCpprofesio(
            @Param("cpclinica") Long cpclinica,
            @Param("cpfcestado") String cpfcestado);
        
    @Query(value = CPPROFESIO_AND_CPPLINICA_BASE
            + "   AND p.cpclinica IS NOT NULL "
            + "   AND p.cpclinica.cclncodigo = :cpclinica "
            + "   AND p.cpfcestado IS NOT NULL "
            + "   AND p.cpfcestado = 'VG'")
    Iterable<Cpprofesio> findActiveCpprofesio(
            @Param("cpclinica") Long cpclinica);
    
    @Query(value = CPPROFESIO_AND_CPPLINICA_BASE
            + "   AND p.cpclinica IS NOT NULL "
            + "   AND CAST(p.cpclinica.cclncodigo as string) LIKE CONCAT('%',CAST(:cpclinica as string),'%') " 
            + "   AND p.cpfcestado IS NOT NULL "
            + "   AND p.cpfcestado = 'VG'"
            + "   AND p.cpfcnombre IS NOT NULL "
            + "   AND UPPER(p.cpfcnombre) LIKE CONCAT('%',UPPER(:cpfcnombre),'%')"
            + "   AND p.id.cpfncodigo IS NOT NULL "
            + "   AND CAST(p.id.cpfncodigo as string) LIKE CONCAT('%',CAST(:cpfncodigo as string),'%')")
    Iterable<Cpprofesio> findActiveCpprofesio(
            @Param("cpclinica") Long cpclinica,
            @Param("cpfncodigo") Long cpfncodigo,
            @Param("cpfcnombre") String cpfcnombre);
    
}

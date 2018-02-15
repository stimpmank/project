/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.clinico.persist.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author czambrano
 */
public interface CpclinicaRepository extends CrudRepository<co.org.profamilia.clinico.persistence.entity.Cpclinica, Long>, JpaSpecificationExecutor<co.org.profamilia.clinico.persistence.entity.Cpclinica> {

    static final String CPPROFESIO_AND_CPPLINICA_BASE = " SELECT DISTINCT c FROM Cpclinica c "            
            + "   LEFT JOIN FETCH c.cpprofesios p "
            + " WHERE "
            + "   c.cclncodigo IS NOT NULL ";    
    
    @Query(value = CPPROFESIO_AND_CPPLINICA_BASE
            + " AND c.cclcestado IS NOT NULL "
            + " AND c.cclcestado = :cclcestado")
    Iterable<co.org.profamilia.clinico.persistence.entity.Cpclinica> findCpclinicaByEstado(
            @Param("cclcestado") String cpfcestado);
    
    @Query(value = CPPROFESIO_AND_CPPLINICA_BASE
            + " AND c.cclcestado = 'VG'")
    Iterable<co.org.profamilia.clinico.persistence.entity.Cpclinica> findActiveCpclinica();
    
    @Query(value = CPPROFESIO_AND_CPPLINICA_BASE
            + " AND c.cclcestado = 'VG' "
            + " AND CAST(c.cclncodigo as string) LIKE CONCAT('%',CAST(:cclncodigo as string),'%')"
            + " AND c.cclcnombre IS NOT NULL "
            + " AND UPPER(c.cclcnombre) LIKE CONCAT('%',UPPER(:cclcnombre),'%')")
    Iterable<co.org.profamilia.clinico.persistence.entity.Cpclinica> findActiveCpclinica(
            @Param("cclncodigo") Long cclncodigo,
            @Param("cclcnombre") String cclcnombre);
}

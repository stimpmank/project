/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.persist.repository;

import co.org.profamilia.transversal.persist.entities.Sapermrol;
import co.org.profamilia.transversal.persist.entities.Sausuario;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author czambrano
 */
public interface SausuarioRepository extends CrudRepository<Sausuario, String>, JpaSpecificationExecutor<Sausuario>, SausuarioRepositoryCustom {
    
    @Query(value="ALTER USER ?0 IDENTIFIED BY ?1", nativeQuery = true)
    void alterUser(String username, String password);
        
    @Query(value="" +
            " SELECT sapr " +
            " FROM Sarolsusua saru, Sarol saro, Sapermrol sapr " +
            " WHERE saru.sarolsusuaPK.srucusuari = :srucusuari "+
            " AND saru.srucetapa = 'AC' " +
            " AND saru.srudfecini <= :srudfecini "  +
            " AND (saru.srudfecfin IS NULL or saru.srudfecfin > :srudfecfin ) "   +
            " AND saru.sarolsusuaPK.srucaplica = saro.id.srocaplica "  +
            " AND saru.sarolsusuaPK.srucrol = saro.id.srocnombre " +
            " AND saro.srocetapa = 'AC' "   +
            " AND sapr.id.sprcrol = saro.id.srocnombre "  +
            " AND sapr.id.sprcaplica = saro.id.srocaplica "  +
            " AND  sapr.sprcetapa = 'AC' "   +
            " AND  sapr.saprograma.spgcetapa = 'AC' "   +
            " AND  sapr.saprograma.spgcnombre NOT IN ("   +
            "      SELECT sapu.id.spucprogra FROM Sapermusua sapu "  +
            "      WHERE sapu.id.spucusuari = :srucusuari2 " + 
            "      AND sapu.id.spucprogra = sapr.saprograma.spgcnombre "  +
            "      AND sapu.spucetapa = 'AC' " +
            "      AND sapu.spuchabili = 'N' "  +
            "      AND sapu.spudfecini <= :srudfecini2 "  +
            "      AND (sapu.spudfecfin IS NULL OR sapu.spudfecfin > :srudfecfin2) "  +
            "   ) "
            + "ORDER BY sapr.saprograma.spgcdescri asc ")
    public List<Sapermrol> getPermisosUsuarioRol(
            @Param("srucusuari")String srucusuari, 
            @Param("srudfecini") Date srudfecini, 
            @Param("srudfecfin") Date srudfecfin, 
            @Param("srucusuari2") String srucusuari2,
            @Param("srudfecini2") Date srudfecini2, 
            @Param("srudfecfin2") Date srudfecfin2);
}

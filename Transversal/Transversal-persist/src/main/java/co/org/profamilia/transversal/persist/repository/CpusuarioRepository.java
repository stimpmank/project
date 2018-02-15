/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.persist.repository;

import co.org.profamilia.transversal.persist.entities.Cpusuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author czambrano
 */
public interface CpusuarioRepository extends CrudRepository<Cpusuario, String> {
    
}

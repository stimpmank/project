package co.org.profamilia.agendamiento.persist.repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.org.profamilia.agendamiento.persistence.entity.DiaProgramacion;

/**
 *
 * @author czambrano
 */
public interface DiaProgramacionRepository extends CrudRepository<DiaProgramacion, Long> {
    
    List<DiaProgramacion> findByProgramacion(Long IdProgramacion);
    
}

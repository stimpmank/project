/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.clinico.persist.mapper;

import java.util.Collection;

/**
 *
 * @author czambrano
 * @param <Model>
 * @param <Entity>
 */
public interface GenericJPAEntityMapper<Model, Entity>{
    
    GenericJPAEntityMapper<Model,Entity> getMapper();
    
    //Model toSingleDTO(Entity entity);
    //Entity toSingleEntity(Model dto);
    
    Model toDTO(Entity entity);
    Entity toEntity(Model dto);
    
    Collection<Model> toDTOCollection(Iterable<Entity> entity);
    Collection<Model> toDTOCollection(Collection<Entity> entity);
    Collection<Entity> toEntityCollection(Collection<Model> dto);
    
    
}

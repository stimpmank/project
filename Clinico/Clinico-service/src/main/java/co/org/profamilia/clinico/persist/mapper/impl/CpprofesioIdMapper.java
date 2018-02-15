/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.clinico.persist.mapper.impl;

import co.org.profamilia.clinico.dto.CpprofesioIdDTO;
import co.org.profamilia.clinico.persistence.entity.CpprofesioId;

import java.util.Collection;
import org.mapstruct.Mapper;

/**
 *
 * @author czambrano
 */
@Mapper
public abstract class CpprofesioIdMapper extends AbstractGenericJPAEntityMapper<CpprofesioIdDTO, CpprofesioId>{
    
    
    public CpprofesioIdMapper(){
        this.mapperClass = CpprofesioIdMapper.class;
    }
    
    @Override    
    public abstract CpprofesioIdDTO toDTO(CpprofesioId entity);
    @Override    
    public abstract CpprofesioId toEntity(CpprofesioIdDTO dto);
    @Override
    public abstract Collection<CpprofesioIdDTO> toDTOCollection(Collection<CpprofesioId> entity);
    @Override
    public abstract Collection<CpprofesioId> toEntityCollection(Collection<CpprofesioIdDTO> dto);
}

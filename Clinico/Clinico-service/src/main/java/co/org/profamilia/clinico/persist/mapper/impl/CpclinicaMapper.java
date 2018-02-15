/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.clinico.persist.mapper.impl;

import co.org.profamilia.clinico.dto.CpclinicaDTO;
import co.org.profamilia.clinico.persistence.entity.Cpclinica;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author czambrano
 */
@Mapper(uses={CpprofesioMapper.class})
public abstract class CpclinicaMapper extends AbstractGenericJPAEntityMapper<CpclinicaDTO, Cpclinica>{
    
    public CpclinicaMapper(){
        this.mapperClass = CpclinicaMapper.class;
    }
    
    @Override
    @Mappings({
        @Mapping(source = "cpprofesios", target = "cpprofesioDTOs")
    })
    public abstract CpclinicaDTO toDTO(Cpclinica entity);    
    
    @Override
    @Mappings({
        @Mapping(target = "cpprofesios", source = "cpprofesioDTOs")
    })
    public abstract Cpclinica toEntity(CpclinicaDTO dto);    
    @Override
    public abstract Collection<CpclinicaDTO> toDTOCollection(Collection<Cpclinica> entity);
    @Override
    public abstract Collection<Cpclinica> toEntityCollection(Collection<CpclinicaDTO> dto);
    
    
    
    
}

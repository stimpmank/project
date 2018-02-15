/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.clinico.persist.mapper.single.impl;

import co.org.profamilia.clinico.persist.mapper.impl.AbstractGenericJPAEntityMapper;
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
@Mapper
public abstract class SingleCpclinicaMapper extends AbstractGenericJPAEntityMapper<CpclinicaDTO, Cpclinica>{
    
    public SingleCpclinicaMapper(){
        this.mapperClass = SingleCpclinicaMapper.class;
    }
    
    @Override
    @Mappings({
        @Mapping(target = "cpprofesioDTOs", ignore = true)
    })
    public abstract CpclinicaDTO toDTO(Cpclinica entity);
    
    @Override
    @Mappings({
        @Mapping(target = "cpprofesios", ignore = true)
    })
    public abstract Cpclinica toEntity(CpclinicaDTO dto);
    
    @Override    
    public abstract Collection<CpclinicaDTO> toDTOCollection(Collection<Cpclinica> entity);
    @Override    
    public abstract Collection<Cpclinica> toEntityCollection(Collection<CpclinicaDTO> dto);
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.persist.mapper.single.impl;

import co.org.profamilia.agendamiento.dto.ProgramacionDTO;
import co.org.profamilia.agendamiento.persist.mapper.impl.AbstractGenericJPAEntityMapper;
import co.org.profamilia.agendamiento.persistence.entity.Programacion;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author czambrano
 */
@Mapper
public abstract class SingleProgramacionMapper extends AbstractGenericJPAEntityMapper<ProgramacionDTO, Programacion>{
    
    public SingleProgramacionMapper(){
        this.mapperClass = SingleProgramacionMapper.class;
    }
    
    @Override
    @Mappings({
        @Mapping(target = "novedadDTOs", ignore = true),
        @Mapping(target = "diaProgramacionDTOs", ignore = true)
    })
    public abstract ProgramacionDTO toDTO(Programacion entity);
    
    @Override
    @Mappings({
        @Mapping(target = "novedads", ignore = true),
        @Mapping(target = "diaProgramacions", ignore = true)
    })
    public abstract Programacion toEntity(ProgramacionDTO dto);
    
    @Override    
    public abstract Collection<ProgramacionDTO> toDTOCollection(Collection<Programacion> entity);
    @Override    
    public abstract Collection<Programacion> toEntityCollection(Collection<ProgramacionDTO> dto);
    
    
    
    
}

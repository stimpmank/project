/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.persist.mapper.impl;

import co.org.profamilia.agendamiento.dto.ProgramacionDTO;
import co.org.profamilia.agendamiento.persistence.entity.Programacion;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author czambrano
 */
@Mapper(uses={NovedadMapper.class, DiaProgramacionMapper.class})
public abstract class ProgramacionMapper extends AbstractGenericJPAEntityMapper<ProgramacionDTO, Programacion>{
    
    public ProgramacionMapper(){
        this.mapperClass = ProgramacionMapper.class;
    }
    
    @Override
    @Mappings({
        @Mapping(source = "diaProgramacions", target = "diaProgramacionDTOs")
    })
    public abstract ProgramacionDTO toDTO(Programacion entity);    
    
    @Override
    @Mappings({
        @Mapping(target = "diaProgramacions", source = "diaProgramacionDTOs")
    })
    public abstract Programacion toEntity(ProgramacionDTO dto);    
    @Override
    public abstract Collection<ProgramacionDTO> toDTOCollection(Collection<Programacion> entity);
    @Override
    public abstract Collection<Programacion> toEntityCollection(Collection<ProgramacionDTO> dto);
    
    
    
    
}

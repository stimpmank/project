/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.persist.mapper.impl;

import co.org.profamilia.agendamiento.persist.mapper.single.impl.SingleProgramacionMapper;
import co.org.profamilia.agendamiento.dto.DiaProgramacionDTO;
import co.org.profamilia.agendamiento.persistence.entity.DiaProgramacion;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author czambrano
 */
@Mapper(uses={SingleProgramacionMapper.class})
public abstract class DiaProgramacionMapper extends AbstractGenericJPAEntityMapper<DiaProgramacionDTO, DiaProgramacion>{
    
    protected final SingleProgramacionMapper singleProgramacionMapper = Mappers.getMapper(SingleProgramacionMapper.class);
    
    public DiaProgramacionMapper(){
        this.mapperClass = DiaProgramacionMapper.class;
        DiaProgramacionDTO a = new DiaProgramacionDTO();
    }
    
    @Override
    @Mappings({
        @Mapping(expression = "java(singleProgramacionMapper.toDTO(entity.getProgramacion()))", target = "programacionDTO")
    })
    public abstract DiaProgramacionDTO toDTO(DiaProgramacion entity);
    @Override
    @Mappings({
        @Mapping(expression = "java(singleProgramacionMapper.toEntity(dto.getProgramacionDTO()))", target = "programacion")
    })
    public abstract DiaProgramacion toEntity(DiaProgramacionDTO dto);
    @Override
    public abstract Collection<DiaProgramacionDTO> toDTOCollection(Collection<DiaProgramacion> entity);
    @Override
    public abstract Collection<DiaProgramacion> toEntityCollection(Collection<DiaProgramacionDTO> dto);
}

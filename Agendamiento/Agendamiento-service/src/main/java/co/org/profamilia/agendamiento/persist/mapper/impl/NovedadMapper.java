/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.persist.mapper.impl;

import co.org.profamilia.agendamiento.persist.mapper.single.impl.SingleProgramacionMapper;
import co.org.profamilia.agendamiento.dto.NovedadDTO;
import co.org.profamilia.agendamiento.persistence.entity.Novedad;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


/**
 *
 * @author czambrano
 */
@Mapper(uses={SingleProgramacionMapper.class,DiaNovedadMapper.class})
public abstract class NovedadMapper extends AbstractGenericJPAEntityMapper<NovedadDTO, Novedad>{
    
    protected final SingleProgramacionMapper singleProgramacionMapper = Mappers.getMapper(SingleProgramacionMapper.class);
    
    public NovedadMapper(){
        this.mapperClass = NovedadMapper.class;
    }
    
    @Override
    @Mappings({
        @Mapping(expression = "java(singleProgramacionMapper.toDTO(entity.getProgramacion()))", target = "programacionDTO"),
        @Mapping(source = "diaNovedads", target = "diaNovedadDTOs")
    })
    public abstract NovedadDTO toDTO(Novedad entity);
    @Override
    @Mappings({
        @Mapping(expression = "java(singleProgramacionMapper.toEntity(dto.getProgramacionDTO()))", target = "programacion"),
        @Mapping(target = "diaNovedads", source = "diaNovedadDTOs")
    })
    public abstract Novedad toEntity(NovedadDTO dto);
    @Override
    public abstract Collection<NovedadDTO> toDTOCollection(Collection<Novedad> entity);
    @Override
    public abstract Collection<Novedad> toEntityCollection(Collection<NovedadDTO> dto);
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.persist.mapper.impl;

import co.org.profamilia.agendamiento.dto.NovedadDetalleDTO;
import co.org.profamilia.agendamiento.persist.mapper.single.impl.SingleDiaNovedadMapper;
import co.org.profamilia.agendamiento.persistence.entity.NovedadDetalle;
import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author czambrano
 */
@Mapper(uses = {SingleDiaNovedadMapper.class})
public abstract class NovedadDetalleMapper extends AbstractGenericJPAEntityMapper<NovedadDetalleDTO, NovedadDetalle> {

    protected final SingleDiaNovedadMapper singleDiaNovedadMapper = Mappers.getMapper(SingleDiaNovedadMapper.class);

    public NovedadDetalleMapper() {
        this.mapperClass = NovedadDetalleMapper.class;
    }

    @Override
    @Mappings({
        @Mapping(expression = "java(singleDiaNovedadMapper.toDTO(entity.getDiaNovedad()))", target = "diaNovedadDTO"),})
    public abstract NovedadDetalleDTO toDTO(NovedadDetalle entity);

    @Override
    @Mappings({
        @Mapping(expression = "java(singleDiaNovedadMapper.toEntity(dto.getDiaNovedadDTO()))", target = "diaNovedad"),    
    })
    public abstract NovedadDetalle toEntity(NovedadDetalleDTO dto);

    @Override
    public abstract Collection<NovedadDetalleDTO> toDTOCollection(Collection<NovedadDetalle> entity);

    @Override
    public abstract Collection<NovedadDetalle> toEntityCollection(Collection<NovedadDetalleDTO> dto);
}

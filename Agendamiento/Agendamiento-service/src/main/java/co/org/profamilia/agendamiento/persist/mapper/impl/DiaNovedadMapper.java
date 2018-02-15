/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.persist.mapper.impl;

import co.org.profamilia.agendamiento.persist.mapper.single.impl.SingleNovedadMapper;
import co.org.profamilia.agendamiento.dto.DiaNovedadDTO;
import co.org.profamilia.agendamiento.persistence.entity.DiaNovedad;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author czambrano
 */
@Mapper(uses={SingleNovedadMapper.class,NovedadDetalleMapper.class})
public abstract class DiaNovedadMapper extends AbstractGenericJPAEntityMapper<DiaNovedadDTO, DiaNovedad>{
    
    protected final SingleNovedadMapper singleNovedadMapper = Mappers.getMapper(SingleNovedadMapper.class);
    
    public DiaNovedadMapper(){
        this.mapperClass = DiaNovedadMapper.class;
    }
    
    @Override
    @Mappings({
        @Mapping(expression = "java(singleNovedadMapper.toDTO(entity.getNovedad()))", target = "novedadDTO"),
        @Mapping(ignore=true, target = "novedadDetalleDTOs")
    })
    //@Mapping(target="subzonesDTO", ignore=true)
    public abstract DiaNovedadDTO toDTO(DiaNovedad entity);
    @Override
    @Mappings({
        @Mapping(expression = "java(singleNovedadMapper.toEntity(dto.getNovedadDTO()))", target = "novedad"),
        @Mapping(target = "novedadDetalles", ignore=true)
    })
    //@Mapping(target="subzones", ignore=true)
    public abstract DiaNovedad toEntity(DiaNovedadDTO dto);
    @Override
    public abstract Collection<DiaNovedadDTO> toDTOCollection(Collection<DiaNovedad> entity);
    @Override
    public abstract Collection<DiaNovedad> toEntityCollection(Collection<DiaNovedadDTO> dto);
    
}

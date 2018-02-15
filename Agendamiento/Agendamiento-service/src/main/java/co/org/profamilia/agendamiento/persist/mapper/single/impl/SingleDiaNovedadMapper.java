/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.persist.mapper.single.impl;

import co.org.profamilia.agendamiento.dto.DiaNovedadDTO;
import co.org.profamilia.agendamiento.persist.mapper.impl.AbstractGenericJPAEntityMapper;
import co.org.profamilia.agendamiento.persist.mapper.impl.NovedadDetalleMapper;
import co.org.profamilia.agendamiento.persistence.entity.DiaNovedad;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author czambrano
 */
@Mapper(uses = {SingleNovedadMapper.class})
public abstract class SingleDiaNovedadMapper extends AbstractGenericJPAEntityMapper<DiaNovedadDTO, DiaNovedad> {

    public SingleDiaNovedadMapper() {
        this.mapperClass = SingleDiaNovedadMapper.class;
    }

    @Override
    @Mappings({
        @Mapping(source = "novedad", target = "novedadDTO"),
        @Mapping(ignore = true, target = "novedadDetalleDTOs")
    })
    public abstract DiaNovedadDTO toDTO(DiaNovedad entity);

    @Override
    @Mappings({
        @Mapping(target = "novedad", source = "novedadDTO"),
        @Mapping(target = "novedadDetalles", ignore=true)
    })
    public abstract DiaNovedad toEntity(DiaNovedadDTO dto);

    @Override
    public abstract Collection<DiaNovedadDTO> toDTOCollection(Collection<DiaNovedad> entity);

    @Override
    public abstract Collection<DiaNovedad> toEntityCollection(Collection<DiaNovedadDTO> dto);

}

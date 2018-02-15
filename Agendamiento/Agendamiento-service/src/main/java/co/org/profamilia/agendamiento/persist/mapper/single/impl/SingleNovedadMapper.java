/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.persist.mapper.single.impl;

import co.org.profamilia.agendamiento.dto.NovedadDTO;
import co.org.profamilia.agendamiento.persist.mapper.impl.AbstractGenericJPAEntityMapper;
import co.org.profamilia.agendamiento.persistence.entity.Novedad;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


/**
 *
 * @author czambrano
 */
@Mapper(uses={SingleProgramacionMapper.class})
public abstract class SingleNovedadMapper extends AbstractGenericJPAEntityMapper<NovedadDTO, Novedad>{
    
    public SingleNovedadMapper(){
        this.mapperClass = SingleNovedadMapper.class;
    }
    
    @Override
    @Mappings({
        @Mapping(source = "programacion", target = "programacionDTO"),
        @Mapping(ignore = true, target = "diaNovedadDTOs")
    })
    public abstract NovedadDTO toDTO(Novedad entity);
    @Override
    @Mappings({
        @Mapping(target = "programacion", source = "programacionDTO"),
        @Mapping(target = "diaNovedads", ignore = true)
    })
    public abstract Novedad toEntity(NovedadDTO dto);
    @Override
    public abstract Collection<NovedadDTO> toDTOCollection(Collection<Novedad> entity);
    @Override
    public abstract Collection<Novedad> toEntityCollection(Collection<NovedadDTO> dto);
    
}

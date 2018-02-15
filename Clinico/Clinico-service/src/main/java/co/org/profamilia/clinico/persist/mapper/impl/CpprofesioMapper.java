/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.clinico.persist.mapper.impl;

import co.org.profamilia.clinico.persist.mapper.single.impl.SingleCpclinicaMapper;
import co.org.profamilia.clinico.dto.CpprofesioDTO;
import co.org.profamilia.clinico.persistence.entity.Cpprofesio;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author czambrano
 */
@Mapper(uses={SingleCpclinicaMapper.class, CpprofesioIdMapper.class})
public abstract class CpprofesioMapper extends AbstractGenericJPAEntityMapper<CpprofesioDTO, Cpprofesio>{
    
    protected final SingleCpclinicaMapper singleCpclinicaMapper = Mappers.getMapper(SingleCpclinicaMapper.class);
    
    public CpprofesioMapper(){
        this.mapperClass = CpprofesioMapper.class;
        CpprofesioDTO a = new CpprofesioDTO();
    }
    
    @Override
    @Mappings({
        @Mapping(expression = "java(singleCpclinicaMapper.toDTO(entity.getCpclinica()))", target = "cpclinicaDTO")
    })
    public abstract CpprofesioDTO toDTO(Cpprofesio entity);
    @Override
    @Mappings({
        @Mapping(expression = "java(singleCpclinicaMapper.toEntity(dto.getCpclinicaDTO()))", target = "cpclinica")
    })
    public abstract Cpprofesio toEntity(CpprofesioDTO dto);
    @Override
    public abstract Collection<CpprofesioDTO> toDTOCollection(Collection<Cpprofesio> entity);
    @Override
    public abstract Collection<Cpprofesio> toEntityCollection(Collection<CpprofesioDTO> dto);
}

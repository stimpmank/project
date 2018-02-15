/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.clinico.service;

import co.org.profamilia.clinico.dto.CpclinicaDTO;
import co.org.profamilia.clinico.dto.CpprofesioDTO;
import co.org.profamilia.clinico.persist.mapper.GenericJPAEntityMapper;
import co.org.profamilia.clinico.persist.mapper.impl.CpclinicaMapper;
import co.org.profamilia.clinico.persist.mapper.impl.CpprofesioMapper;
import co.org.profamilia.clinico.persist.repository.CpclinicaRepository;
import co.org.profamilia.clinico.persist.repository.CpprofesioRepository;
import co.org.profamilia.clinico.persistence.entity.Cpclinica;
import co.org.profamilia.clinico.persistence.entity.Cpprofesio;
import java.util.Collection;
import org.mapstruct.factory.Mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author czambrano
 */
@Service("clinicoService")
public class ClinicoService {

    private static final Logger logger = LoggerFactory.getLogger(ClinicoService.class);

    @Autowired
    CpprofesioRepository cpprofesioRepository;
    
    @Autowired
    CpclinicaRepository cpclinicaRepository;
    
    private final GenericJPAEntityMapper<CpprofesioDTO, Cpprofesio> cpprofesioMapper = (CpprofesioMapper) Mappers.getMapper(CpprofesioMapper.class);
    private final GenericJPAEntityMapper<CpclinicaDTO, Cpclinica> cpclinicaMapper = (CpclinicaMapper) Mappers.getMapper(CpclinicaMapper.class);
    
    public Collection<CpprofesioDTO> findActiveCpprofesioByCpclinica(Long idClinica) {
        return cpprofesioMapper.toDTOCollection(cpprofesioRepository.findActiveCpprofesio(idClinica));
    }
    
    public Collection<CpprofesioDTO> findActiveCpprofesioByCpclinica(Long idClinica, Long cpfncodigo, String cpfcnombre) {
        return cpprofesioMapper.toDTOCollection(cpprofesioRepository.findActiveCpprofesio(idClinica, cpfncodigo, cpfcnombre));
    }
    
    public CpclinicaDTO getSingleCpclinica(Long cclncodigo) {
        Cpclinica entity = cpclinicaRepository.findOne(cclncodigo);
        entity.setCpprofesios(null);
        return cpclinicaMapper.toDTO(entity);
    }
    
    public Collection<CpclinicaDTO> findActiveCpclinica() {
        return cpclinicaMapper.toDTOCollection(cpclinicaRepository.findActiveCpclinica());
    }
    
    public Collection<CpclinicaDTO> findActiveCpclinica(Long cclncodigo, String cclcnombre) {
        return cpclinicaMapper.toDTOCollection(cpclinicaRepository.findActiveCpclinica(cclncodigo, cclcnombre));
    }

}

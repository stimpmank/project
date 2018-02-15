/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.service;

import co.org.profamilia.agendamiento.dto.DiaNovedadDTO;
import co.org.profamilia.agendamiento.dto.DiaProgramacionDTO;
import co.org.profamilia.agendamiento.dto.NovedadDTO;
import co.org.profamilia.agendamiento.dto.NovedadDetalleDTO;
import co.org.profamilia.agendamiento.dto.ProgramacionDTO;
import co.org.profamilia.agendamiento.persist.mapper.GenericJPAEntityMapper;
import co.org.profamilia.agendamiento.persist.mapper.impl.DiaNovedadMapper;
import co.org.profamilia.agendamiento.persist.mapper.impl.DiaProgramacionMapper;
import co.org.profamilia.agendamiento.persist.mapper.impl.NovedadDetalleMapper;
import co.org.profamilia.agendamiento.persist.mapper.impl.NovedadMapper;
import co.org.profamilia.agendamiento.persist.mapper.impl.ProgramacionMapper;
import co.org.profamilia.agendamiento.persist.repository.DiaNovedadRepository;
import co.org.profamilia.agendamiento.persist.repository.DiaProgramacionRepository;
import co.org.profamilia.agendamiento.persist.repository.NovedadDetalleRepository;
import co.org.profamilia.agendamiento.persist.repository.NovedadRepository;
import co.org.profamilia.agendamiento.persist.repository.ProgramacionRepository;
import co.org.profamilia.agendamiento.persistence.entity.DiaNovedad;
import co.org.profamilia.agendamiento.persistence.entity.DiaProgramacion;
import co.org.profamilia.agendamiento.persistence.entity.Novedad;
import co.org.profamilia.agendamiento.persistence.entity.NovedadDetalle;
import co.org.profamilia.agendamiento.persistence.entity.Programacion;
import java.util.ArrayList;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author czambrano
 */
@Service("agendamientoService")
public class AgendamientoService {

    private static final Logger logger = LoggerFactory.getLogger(AgendamientoService.class);

    @Autowired
    ProgramacionRepository programacionReposiory;

    @Autowired
    DiaProgramacionRepository diaProgramacionReposiory;

    @Autowired
    NovedadRepository novedadReposiory;

    @Autowired
    DiaNovedadRepository diaNovedadReposiory;

    @Autowired
    NovedadDetalleRepository novedadDetalleReposiory;

    private final GenericJPAEntityMapper<ProgramacionDTO, Programacion> programacionMapper = (ProgramacionMapper) Mappers.getMapper(ProgramacionMapper.class);
    private final GenericJPAEntityMapper<DiaProgramacionDTO, DiaProgramacion> diaProgramacionMapper = (DiaProgramacionMapper) Mappers.getMapper(DiaProgramacionMapper.class);

    private final GenericJPAEntityMapper<NovedadDTO, Novedad> novedadMapper = (NovedadMapper) Mappers.getMapper(NovedadMapper.class);
    private final GenericJPAEntityMapper<DiaNovedadDTO, DiaNovedad> diaNovedadMapper = (DiaNovedadMapper) Mappers.getMapper(DiaNovedadMapper.class);

    private final GenericJPAEntityMapper<NovedadDetalleDTO, NovedadDetalle> novedadDetalleMapper = (NovedadDetalleMapper) Mappers.getMapper(NovedadDetalleMapper.class);

    public ProgramacionDTO getProgramacionById(Long id) {
        return programacionMapper.toDTO(programacionReposiory.findOne(id));
    }

    public Collection<ProgramacionDTO> getProgramacion(Long idProfesional, Long idSede) {
        return programacionMapper.toDTOCollection(programacionReposiory.findProgramacion(idProfesional, idSede));
    }

    public Collection<ProgramacionDTO> getProgramacion(Long idProfesional, Long idSede, Long estado) {
        return programacionMapper.toDTOCollection(programacionReposiory.findProgramacion(idProfesional, idSede, estado));
    }

    public Collection<ProgramacionDTO> getProgramacion(Long idProfesional, Long idSede, Date fechaInicio, Date fechaFin) {
        return programacionMapper.toDTOCollection(programacionReposiory.findProgramacion(idProfesional, idSede, fechaInicio, fechaFin));
    }

    public Collection<ProgramacionDTO> getProgramacion(Long idProfesional, Long idSede, Date fechaInicio, Date fechaFin, Long estado) {
        return programacionMapper.toDTOCollection(programacionReposiory.findProgramacion(idProfesional, idSede, fechaInicio, fechaFin, estado));
    }

    public Collection<ProgramacionDTO> getAllProgramacion() {
        return programacionMapper.toDTOCollection(programacionReposiory.findAll());
    }

    public Collection<DiaProgramacionDTO> getAllDiaProgramacionByProgramacion(Long IdProgramacion) {
        return diaProgramacionMapper.toDTOCollection(diaProgramacionReposiory.findByProgramacion(IdProgramacion));
    }

    public ProgramacionDTO save(ProgramacionDTO dto) {

        Programacion entity = programacionMapper.toEntity(dto);

        for (DiaProgramacion dia : entity.getDiaProgramacions()) {
            if (dia.getHabil().intValue() == 0) {
                dia.setHoraInicio(null);
                dia.setHoraFin(null);
                dia.setHoraInicioAlmuerzo(null);
                dia.setHoraFinAlmuerzo(null);
            }
        }

        logger.error("Primera transaccion, persistir maestro Programacion");

        // Se extraen los dias de novedad.
        Set<DiaProgramacion> _tmp = new HashSet(entity.getDiaProgramacions());

        // Se nulifica la lista de dias de novedad
        entity.setDiaProgramacions(null);

        // Se persiste solo el entity maestro
        entity = programacionReposiory.save(entity);

        logger.error("Segunda transaccion [" + entity.getId() + "], persistir detalle lista DiaProgramacion");
        // Se actualiza el entity maestro en la lista de dias novedad
        for (DiaProgramacion dia : _tmp) {
            dia.setProgramacion(entity);
        }
        // Se almacenan los dias de programacion
        diaProgramacionReposiory.save(_tmp);

        return programacionMapper.toDTO(entity);

    }

    public NovedadDTO getNovedadById(Long id) {
        return novedadMapper.toDTO(novedadReposiory.findOne(id));
    }

    public Collection<NovedadDTO> getNovedad(Long idProfesional, Long idSede, Date fechaInicio, Date fechaFin) {
        return novedadMapper.toDTOCollection(novedadReposiory.findNovedadFetchDias(idProfesional, idSede, fechaInicio, fechaFin));
    }

    public Collection<NovedadDTO> getNovedad(Long idProfesional, Long idSede, Date fechaInicio, Date fechaFin, Long estado) {
        return novedadMapper.toDTOCollection(novedadReposiory.findNovedad(idProfesional, idSede, fechaInicio, fechaFin, estado));
    }

    public Collection<NovedadDTO> getAllNovedad() {
        return novedadMapper.toDTOCollection(novedadReposiory.findAll());
    }

    /*public Collection<DiaNovedadDTO> getAllDiaNovedadByProgramacion(Long idProfesional, Long idSede, Date fechaInicio, Date fechaFin) {
        return diaNovedadMapper.toDTOCollection(novedadReposiory.findNovedadFetchDias(idProfesional, idSede, fechaInicio, fechaFin));
    }

    public Collection<DiaNovedadDTO> getAllDiaNovedadByProgramacion(Long idProfesional, Long idSede, Date fechaInicio, Date fechaFin, Long estado) {
        return diaNovedadMapper.toDTOCollection(novedadReposiory.findNovedadFetchDias(idProfesional, idSede, fechaInicio, fechaFin, estado));
    }*/

    public NovedadDTO save(NovedadDTO dto) {

        Novedad entity = novedadMapper.toEntity(dto);

        for (DiaNovedad dia : entity.getDiaNovedads()) {
            if (dia.getHabil().intValue() == 0) {
                dia.setHoraInicio(null);
                dia.setHoraFin(null);
            }
        }

        logger.error("Primera transaccion");

        // Se extraen los dias de novedad.
        Set<DiaNovedad> _tmp = new HashSet(entity.getDiaNovedads());

        // Se limpia la lista de dias de novedad
        entity.setDiaNovedads(null);

        // Se persiste solo el entity maestro
        entity = novedadReposiory.save(entity);

        logger.error("Segunda transaccion [" + entity.getId() + "]");
        // Se actualiza el entity maestro en la lista de dias novedad
        for (DiaNovedad dia : _tmp) {
            dia.setNovedad(entity);
        }

        // Se almacenan los dias de novedad
        diaNovedadReposiory.save(_tmp);
        

        return novedadMapper.toDTO(entity);

    }

    public Collection<DiaNovedadDTO> getAllDiaNovedadByNovedad(Long idNovedad) {
        return diaNovedadMapper.toDTOCollection(diaNovedadReposiory.findByNovedad(idNovedad));
    }

    public Collection<NovedadDetalleDTO> getAllDetalleNovedad(Long idProfesional, Long idSede, Date fechaInicio, Date fechaFin) {
        return novedadDetalleMapper.toDTOCollection(novedadDetalleReposiory.findByDiaNovedad(idProfesional, idSede, fechaInicio, fechaFin));
    }

    public void saveNovedadDetalles(NovedadDTO dto) {
        novedadDetalleReposiory.saveNovedadDetalles(novedadMapper.toEntity(dto));
    }
    
    public void deleteNovedadDetalles(Collection<NovedadDetalleDTO> list) {  
        //ArrayList<NovedadDetalle> arrayList = new ArrayList<NovedadDetalleDTO>(list);
        novedadDetalleReposiory.delete(novedadDetalleMapper.toEntityCollection(list));
    }

}

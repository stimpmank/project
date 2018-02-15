/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.web;

import co.org.profamilia.agendamiento.dto.DiaProgramacionDTO;
import co.org.profamilia.agendamiento.dto.ProgramacionDTO;
import co.org.profamilia.agendamiento.dto.wrapper.impl.WrapperDiaProgramacionDTO;
import co.org.profamilia.agendamiento.service.AgendamientoService;
import co.org.profamilia.agendamiento.web.dummy.DummyProfesional;
import co.org.profamilia.agendamiento.web.dummy.DummySede;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author czambrano
 */
@ManagedBean(name = "programacion")
@ViewScoped
public class ProgramacionBean extends AbstractAuthenticatedBean {

    private static final Logger logger = LoggerFactory.getLogger(ProgramacionBean.class);

    @ManagedProperty(value = "#{agendamientoService}")
    AgendamientoService agendamientoService;

    private Collection<DummyProfesional> profesionalesDTOList;
    private DummyProfesional profesionalSelect;
    private DummyProfesional profesionalQuery;

    private Collection<DummySede> sedesDTOList;
    private DummySede sedeSelect;
    private DummySede sedeQuery;

    private Collection<ProgramacionDTO> programacionDTOList;
    private ProgramacionDTO programacionSelect;
    private Set<WrapperDiaProgramacionDTO> sortedWrapperDiaProgramacionDTO;

    public ProgramacionBean() {

    }

    @PostConstruct
    public void init() {
        programacionSelect = new ProgramacionDTO();

    }

    private ProgramacionDTO buildEmptyProgramacionDTO() {
        ProgramacionDTO dto = new ProgramacionDTO();
        dto.setId(Long.valueOf(-1));
        dto.setDiaProgramacionDTOs(buildEmptySetDiaProgramacionDTO());
        return dto;
    }

    private Set<DiaProgramacionDTO> buildEmptySetDiaProgramacionDTO() {
        Set<DiaProgramacionDTO> dias = new HashSet<>();

        for (int i = 1; i <= 7; i++) {
            dias.add(buildDefaultDiaProgramacionDTO(i));
        }
        return dias;
    }

    private DiaProgramacionDTO buildDefaultDiaProgramacionDTO(int num_dia) {
        DiaProgramacionDTO dia = new DiaProgramacionDTO();
        dia.setId(Long.valueOf(num_dia*-1));
        dia.setHabil(0l);
        dia.setDia(Long.valueOf(num_dia));
        dia.setProgramacionDTO(programacionSelect);
        return dia;
    }

    private void consultarProgramaciones(Long idProfesional, Long idSede) {
        try {

            programacionDTOList
                    = agendamientoService.getProgramacion(idProfesional, idSede);

            if (this.programacionDTOList.size() == 1) {
                this.setProgramacionSelect(this.programacionDTOList.stream().findFirst().get());
                if (this.programacionSelect.getDiaProgramacionDTOs() == null || this.programacionSelect.getDiaProgramacionDTOs().isEmpty()) {
                    this.programacionSelect.setDiaProgramacionDTOs(buildEmptySetDiaProgramacionDTO());
                } else {
                    for (int i = 0; i <= 7; i++) {
                        boolean exist = false;
                        for (DiaProgramacionDTO dia : this.programacionSelect.getDiaProgramacionDTOs()) {
                            if (dia.getDia().intValue() == i) {
                                exist = true;
                            }
                            if (!exist) {
                                this.programacionSelect.getDiaProgramacionDTOs().add(buildDefaultDiaProgramacionDTO(i));
                            }
                        }
                    }
                }
            } else {
                programacionSelect = this.buildEmptyProgramacionDTO();
                if (this.profesionalSelect != null) {
                    this.addInfoMessage("SCHEDULE_DEFAULT_CREATED", "No se encontro registro de programacion, se crea una por defecto.");
                }
            }

        } catch (Exception e) {
            logger.error("Error al consultar programaciones:", e);
            addErrorMessage("SCHEDULE_GET_ERROR", "Error al consultar contratos");
        }
    }

    public void consultarProgramaciones() {
        this.consultarProgramaciones(profesionalSelect.getId(), sedeSelect.getId());
    }

    private void consultarProfesionales() {
        try {

            this.profesionalesDTOList = new ArrayList();
            this.profesionalesDTOList.add(new DummyProfesional(Long.valueOf(1), "Pepe Perez"));
            this.profesionalesDTOList.add(new DummyProfesional(Long.valueOf(2), "Marcos Rodriguez"));
            this.profesionalesDTOList.add(new DummyProfesional(Long.valueOf(3), "Simon Trinidad"));

        } catch (Exception e) {
            logger.error("Error al consultar profesionales:", e);
            addErrorMessage("DOCTOR_GET_ERROR", "Error al consultar profesionales");
        }
    }

    public List<DummyProfesional> autoComplProfesionales(String query) throws Exception {
        List<DummyProfesional> tmpList = new ArrayList();

        for (DummyProfesional dto : getProfesionalesDTOList()) {
            if (dto.getName().toUpperCase().contains(query.toUpperCase())
                    || String.valueOf(dto.getId()).toUpperCase().contains(query)) {
                tmpList.add(dto);
            }
        }

        return tmpList;
    }

    public void consultarProfesionalesMock() {
        try {
            // se debe paras el profesionalQuery, e implementar en persistencia 
            // una consulta dinamica por codigo o por nombre o por ambas 
            // segun sea el caso
            profesionalesDTOList
                    = this.getProfesionalesDTOList();

        } catch (Exception e) {
            logger.error("Error al consultar profesionales:", e);
            addErrorMessage("DOCTOR_GET_ERROR", "Error al consultar profesionales");
        }

    }

    public Collection<DummyProfesional> getProfesionalesDTOList() {

        if (profesionalesDTOList == null || profesionalesDTOList.isEmpty()) {
            this.consultarProfesionales();
        }
        return profesionalesDTOList;
    }

    public void setProfesionalesDTOList(Collection<DummyProfesional> profesionalesDTOList) {
        this.profesionalesDTOList = profesionalesDTOList;
    }

    public void clearModalProfesional() {
        profesionalQuery = new DummyProfesional(null, null);
    }

    private void consultarSedes() {
        try {

            sedesDTOList = new ArrayList();
            this.sedesDTOList.add(new DummySede(Long.valueOf(1), "Sede Principal"));
            this.sedesDTOList.add(new DummySede(Long.valueOf(2), "Sede Rionegro"));
            this.sedesDTOList.add(new DummySede(Long.valueOf(3), "Otra de otra parte"));

        } catch (Exception e) {
            logger.error("Error al consultar sedes:", e);
            addErrorMessage("CLINIC_GET_ERROR", "Error al consultar sedes");
        }
    }

    public List<DummySede> autoComplSedes(String query) throws Exception {
        List<DummySede> tmpList = new ArrayList();

        for (DummySede dto : getSedesDTOList()) {
            if (dto.getName().toUpperCase().contains(query.toUpperCase())
                    || String.valueOf(dto.getId()).toUpperCase().contains(query)) {
                tmpList.add(dto);
            }
        }

        return tmpList;
    }

    public void consultarSedesMock() {
        try {
            // se debe paras el profesionalQuery, e implementar en persistencia 
            // una consulta dinamica por codigo o por nombre o por ambas 
            // segun sea el caso
            sedesDTOList
                    = this.getSedesDTOList();

        } catch (Exception e) {
            logger.error("Error al consultar sedes:", e);
            addErrorMessage("CLINIC_GET_ERROR", "Error al consultar sedes");
        }

    }

    public void clearModalSede() {
        sedeQuery = new DummySede(null, null);
    }

    public Collection<DummySede> getSedesDTOList() {
        if (sedesDTOList == null || sedesDTOList.isEmpty()) {
            this.consultarSedes();
        }
        return sedesDTOList;
    }

    public void setSedesDTOList(Collection<DummySede> sedesDTOList) {
        this.sedesDTOList = sedesDTOList;
    }

    /////////////////////7
    public Object getProfesionalSelect() {
        return profesionalSelect;
    }

    public void setProfesionalSelect(DummyProfesional profesionalSelect) {
        this.profesionalSelect = profesionalSelect;
    }

    public DummyProfesional getProfesionalQuery() {
        return profesionalQuery;
    }

    public void setProfesionalQuery(DummyProfesional profesionalQuery) {
        this.profesionalQuery = profesionalQuery;
    }

    public Object getSedeSelect() {
        return sedeSelect;
    }

    public void setSedeSelect(DummySede sedeSelect) {
        this.sedeSelect = sedeSelect;
    }

    public DummySede getSedeQuery() {
        return sedeQuery;
    }

    public void setSedeQuery(DummySede sedeQuery) {
        this.sedeQuery = sedeQuery;
    }

    public Collection<ProgramacionDTO> getProgramacionDTOList() {
        return programacionDTOList;
    }

    private void sortDiaProgramacionDTOs() {
        if (this.programacionSelect.getDiaProgramacionDTOs() != null
                && !this.programacionSelect.getDiaProgramacionDTOs().isEmpty()) {

            this.sortedWrapperDiaProgramacionDTO = new TreeSet<>();

            for (DiaProgramacionDTO dia : this.programacionSelect.getDiaProgramacionDTOs()) {
                //logger.error("Original Dia : " + dia.getDia());
                WrapperDiaProgramacionDTO wrapper = new WrapperDiaProgramacionDTO(dia);
                this.sortedWrapperDiaProgramacionDTO.add(wrapper);
            }

            for (WrapperDiaProgramacionDTO dia : this.sortedWrapperDiaProgramacionDTO) {
                //logger.error("Ordenado dia : " + dia.getDia());
            }
        }
    }

    public void setProgramacionDTOList(Collection<ProgramacionDTO> programacionDTOList) {
        logger.error("setProgramacionDTOList: " + programacionSelect);
        this.programacionDTOList = programacionDTOList;
    }

    public ProgramacionDTO getProgramacionSelect() {
        logger.error("getProgramacionSelect: " + programacionSelect);
        sortDiaProgramacionDTOs();
        return programacionSelect;
    }

    public void setProgramacionSelect(ProgramacionDTO programacionSelect) {
        logger.error("setProgramacionSelect: " + programacionSelect.toString());
        this.programacionSelect = programacionSelect;
    }

    public AgendamientoService getAgendamientoService() {
        return agendamientoService;
    }

    public void setAgendamientoService(AgendamientoService agendamientoService) {
        this.agendamientoService = agendamientoService;
    }

    public Set<WrapperDiaProgramacionDTO> getSortedWrapperDiaProgramacionDTO() {
        return sortedWrapperDiaProgramacionDTO;
    }

    public void setSortedWrapperDiaProgramacionDTO(SortedSet<WrapperDiaProgramacionDTO> sortedWrapperDiaProgramacionDTO) {
        this.sortedWrapperDiaProgramacionDTO = sortedWrapperDiaProgramacionDTO;
    }

    public void save() throws Exception {
        logger.error("SAVED!!!!");

        for (DiaProgramacionDTO dia : this.programacionSelect.getDiaProgramacionDTOs()) {
            dia.setProgramacionDTO(this.programacionSelect);
            //logger.error("Original Dia : " + dia + "[HI]:["+dia.getHoraInicio()+"] [HF]:["+dia.getHoraFin()+"]");
        }
        
        if (this.programacionSelect.getFechaInicio()==null){
            //TODO: [CZ] Pasar a constante
            this.programacionSelect.setFechaInicio(new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2018"));
        }
        
        if (this.programacionSelect.getFechaFin()==null){
            //Pasar a constante
            this.programacionSelect.setFechaFin(new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2018"));
        }
        
        if (this.programacionSelect.getIdProfesional()==null){
            this.programacionSelect.setIdProfesional(this.profesionalSelect.getId());
        }
        
        if (this.programacionSelect.getIdSede()==null){
            this.programacionSelect.setIdSede(this.sedeSelect.getId());
        }
        
        this.programacionSelect = this.agendamientoService.save(this.programacionSelect);
        
        this.addInfoMessage("SCHEDULE_SAVED", "Programacion guardada correctamente");
    }

}

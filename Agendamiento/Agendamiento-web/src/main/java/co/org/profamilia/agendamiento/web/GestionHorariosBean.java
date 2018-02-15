/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.web;

import co.org.profamilia.agendamiento.dto.ProgramacionDTO;
import co.org.profamilia.agendamiento.dto.NovedadDTO;
import co.org.profamilia.agendamiento.dto.DiaProgramacionDTO;
import co.org.profamilia.agendamiento.dto.DiaNovedadDTO;
import co.org.profamilia.agendamiento.dto.wrapper.impl.WrapperDiaNovedadDTO;
import co.org.profamilia.agendamiento.dto.wrapper.impl.WrapperDiaProgramacionDTO;
import co.org.profamilia.clinico.service.ClinicoService;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author czambrano
 */
@ManagedBean(name = "gestionHorarios")
@ViewScoped
public class GestionHorariosBean extends AbstractCpprofesioCpclinicaBean {

    private static final Logger logger = LoggerFactory.getLogger(GestionHorariosBean.class);

    private Collection<ProgramacionDTO> programacionDTOList;
    private Collection<NovedadDTO> novedadDTOList;
    //private Collection<DiaNovedadDTO> diaNovedadDTOList;
    private Collection<DiaProgramacionDTO> diaProgramacionDTOList;
    private Set<WrapperDiaProgramacionDTO> sortedWrapperDiaProgramacionDTO;
    private Set<WrapperDiaNovedadDTO> sortedWrapperDiaNovedadDTO;

    private ProgramacionDTO programacionSelect;
    private NovedadDTO novedadSelect;
    private NovedadDTO novedadDTO;
    private DiaNovedadDTO diaNovedadDTO;
    private DiaProgramacionDTO diaProgramacionDTO;

    private Boolean habilitarEditarNovedad;

    private Boolean habilitarCrearNovedad;
    private Boolean habilitarVerAgendaNovedad;

    private Boolean aux_novedad_estado;
    
    
    
    public GestionHorariosBean() {

    }

    @PostConstruct
    public void init() {
        
        //programacionSelect = new ProgramacionDTO();
        novedadSelect = new NovedadDTO();
        
        //diaNovedadDTOList = new ArrayList<DiaNovedadDTO>();
        this.setHabilitarVerAgendaNovedad(false);
    }

    private ProgramacionDTO buildEmptyProgramacionDTO() {
        ProgramacionDTO dto = new ProgramacionDTO();
        //dto.setId(Long.valueOf(-1));
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
        //dia.setId(Long.valueOf(num_dia * -1));
        dia.setHabil(0l);
        dia.setDia(Long.valueOf(num_dia));
        dia.setProgramacionDTO(programacionSelect);
        return dia;
    }

    private DiaNovedadDTO buildDefaultDiaNovedadDTO(int num_dia) {
        DiaNovedadDTO dia = new DiaNovedadDTO();
        //dia.setId(Long.valueOf(num_dia * -1));
        dia.setHabil(0l);
        dia.setTipoNovedad(0l);
        dia.setDia(Long.valueOf(num_dia));
        dia.setNovedadDTO(novedadSelect);
        return dia;
    }

    private Set<DiaNovedadDTO> buildEmptySetDiaNovedadDTO() {
        Set<DiaNovedadDTO> dias = new HashSet<>();

        for (int i = 1; i <= 7; i++) {
            dias.add(buildDefaultDiaNovedadDTO(i));
        }
        return dias;
    }

    private NovedadDTO buildEmptyNovedadDTO() {
        NovedadDTO dto = new NovedadDTO();
        //dto.setId(Long.valueOf(-1));
        dto.setEstado(1l);
        dto.setDiaNovedadDTOs(buildEmptySetDiaNovedadDTO());
        return dto;
    }

    private void consultarProgramaciones(Long idProfesional, Long idSede) {
        try {

            programacionDTOList
                    = agendamientoService.getProgramacion(idProfesional, idSede);

            if (this.programacionDTOList.size() >= 1) {
                this.setProgramacionSelect(this.programacionDTOList.stream().findFirst().get());
                if (this.programacionSelect.getDiaProgramacionDTOs() == null || this.programacionSelect.getDiaProgramacionDTOs().isEmpty()) {
                    this.programacionSelect.setDiaProgramacionDTOs(buildEmptySetDiaProgramacionDTO());
                } else {
                    for (int i = 1; i <= 7; i++) {
                        boolean exist = false;
                        for (DiaProgramacionDTO dia : this.programacionSelect.getDiaProgramacionDTOs()) {
                            if (dia.getDia().intValue() == i) {
                                exist = true;
                            }
                        }
                        if (!exist) {
                            logger.error("Creando dia [" + i + "]");
                            this.programacionSelect.getDiaProgramacionDTOs().add(buildDefaultDiaProgramacionDTO(i));
                        }
                    }
                }
            } else {
                programacionSelect = this.buildEmptyProgramacionDTO();
                if (this.programacionSelect != null) {
                    this.sortDiaProgramacionDTOs();
                    this.addInfoMessage("SCHEDULE_DEFAULT_CREATED", "No se encontro registro de programacion, se crea una por defecto.");
                    //this.programacionDTOList.add(this.programacionSelect);
                }
            }
            sortDiaProgramacionDTOs();

        } catch (Exception e) {
            logger.error("Error al consultar programaciones:", e);
            addErrorMessage("SCHEDULE_GET_ERROR", "Error al consultar contratos");
        }
    }

    private void consultarNovedades(Long idProfesional, Long idSede, Date fechaInicio, Date fechaFin) {
        try {

            this.programacionSelect = null;

            logger.error("consultarNovedades idProfesional " + idProfesional);
            logger.error("consultarNovedades idSede " + idSede);
            logger.error("consultarNovedades fechaInicio: " + fechaInicio);
            logger.error("consultarNovedades fechaFin:" + fechaFin);

            programacionDTOList = agendamientoService.getProgramacion(idProfesional, idSede);
            if (this.programacionDTOList.size() >= 1) {
                this.setProgramacionSelect(this.programacionDTOList.stream().findFirst().get());
                novedadDTOList = agendamientoService.getNovedad(idProfesional, idSede, fechaInicio, fechaFin);

                logger.error("consultarNovedades this.novedadDTOList.size():" + this.novedadDTOList.size());
                if (this.novedadDTOList == null || this.novedadDTOList.isEmpty()) {
                    novedadSelect = this.buildEmptyNovedadDTO();
                } else if (this.novedadDTOList.size() == 1) {
                    this.setNovedadSelect(this.novedadDTOList.stream().findFirst().get());
                }
            } else {
                this.novedadSelect = null;
                this.addInfoMessage("SCHEDULE_UPDATE_NOT_DATA", "No existen Programacion para el profesional medico y la sede indicada, por favor dirijase a la pestaña de General y Cree la Programación");
            }
            sortDiaNovedadDTOs();

        } catch (Exception e) {
            logger.error("Error al consultar novedades:", e);
            addErrorMessage("SCHEDULE_UPDATE_GET_ERROR", "Error al consultar novedades");
        }
    }

    public void consultarProgramaciones() {
        this.consultarProgramaciones(cpprofesioSelect.getId().getCpfncodigo(), cpprofesioSelect.getId().getCpfnclinic());
    }

    public void consultarNovedades() {
        this.consultarNovedades(cpprofesioSelect.getId().getCpfncodigo(), cpprofesioSelect.getId().getCpfnclinic(), this.fechaInicioNovedad, this.fechaFinNovedad);
    }

    public void consultarDiasNovedad() {
        this.consultarDiasNovedad(this.novedadSelect.getId());
    }

    public void consultarDiasNovedad(Long idNovedad) {
        try {

            Collection<DiaNovedadDTO> diaNovedadDTOList
                    = agendamientoService.getAllDiaNovedadByNovedad(idNovedad);

            this.setHabilitarEditarNovedad(true);//validar
            this.setHabilitarCrearNovedad(false);//validar

            this.novedadSelect.setDiaNovedadDTOs(new HashSet<>(diaNovedadDTOList));

            if (this.novedadSelect.getDiaNovedadDTOs() == null || this.novedadSelect.getDiaNovedadDTOs().isEmpty()) {
                this.novedadSelect.setDiaNovedadDTOs(buildEmptySetDiaNovedadDTO());
            } else {
                for (int i = 1; i <= 7; i++) {
                    boolean exist = false;
                    for (DiaNovedadDTO dia : this.novedadSelect.getDiaNovedadDTOs()) {
                        if (dia.getDia().intValue() == i) {
                            exist = true;
                        }
                    }
                    if (!exist) {
                        logger.error("Creando dia [" + i + "]");
                        this.novedadSelect.getDiaNovedadDTOs().add(buildDefaultDiaNovedadDTO(i));
                    }
                }
            }
            this.setHabilitarVerAgendaNovedad(true);
            this.sortDiaNovedadDTOs();

        } catch (Exception e) {
            logger.error("Error al consultar programaciones:", e);
            addErrorMessage("SCHEDULE_GET_ERROR", "Error al consultar programaciones");
        }
    }

    public void crearNovedad() {

        this.novedadSelect = this.buildEmptyNovedadDTO();

        this.sortDiaNovedadDTOs();
        this.setHabilitarEditarNovedad(true);
        this.setHabilitarCrearNovedad(true);
        this.setHabilitarVerAgendaNovedad(true);

    }

    private void sortDiaNovedadDTOs() {
        if (this.novedadSelect != null 
                && this.novedadSelect.getDiaNovedadDTOs() != null
                && !this.novedadSelect.getDiaNovedadDTOs().isEmpty()) {

            this.sortedWrapperDiaNovedadDTO = new TreeSet<>();

            for (DiaNovedadDTO dia : this.novedadSelect.getDiaNovedadDTOs()) {
                //logger.error("Original Dia : " + dia.getDia());
                WrapperDiaNovedadDTO wrapper = new WrapperDiaNovedadDTO(dia);
                this.sortedWrapperDiaNovedadDTO.add(wrapper);
            }

            //for (WrapperDiaNovedadDTO dia : this.sortedWrapperDiaNovedadDTO) {
            //    logger.error("Ordenado dia : " + dia.getDia());
            //}
        }
    }

    public void guardarNovedad() {
        try {
            logger.error("ENTRO");
            
            if (this.novedadSelect.getProgramacionDTO() == null) {
                logger.error("Novedad sin programacion");
                if (this.programacionSelect != null && this.programacionSelect.getId().intValue() != -1) {
                    logger.error("programacionSelect: ["+programacionSelect.getId()+"]");
                    this.novedadSelect.setProgramacionDTO(this.programacionSelect);
                } else {
                    logger.error("Consultando nuevamente programacion . . . ["+cpprofesioSelect.getId().getCpfncodigo()+"]["+cpprofesioSelect.getId().getCpfnclinic()+"]");
                    programacionDTOList = agendamientoService.getProgramacion(cpprofesioSelect.getId().getCpfncodigo(), cpprofesioSelect.getId().getCpfnclinic());
                    if (this.programacionDTOList.size() >= 1) {
                        this.novedadSelect.setProgramacionDTO(this.programacionDTOList.stream().findFirst().get());
                    }
                }
            }

            novedadSelect = agendamientoService.save(novedadSelect);
            if (novedadSelect != null) {
                agendamientoService.saveNovedadDetalles(novedadSelect);
                logger.error("EXITO" + novedadSelect.toString());
                this.consultarNovedades();
                this.addInfoMessage("SCHEDULE_SAVED", "Novedad guardada correctamente");
            }
        } catch (Exception e) {
            logger.error("Error al guardar programaciones:", e);
            addErrorMessage("NO_KEY", "Error al guardar programaciones");
        }
    }

    public void guardarProgramaciones() {
        try {
            

            if (this.programacionSelect.getFechaInicio() == null) {
                //TODO: [CZ] Pasar a constante
                this.programacionSelect.setFechaInicio(new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2018"));
            }

            if (this.programacionSelect.getFechaFin() == null) {
                //Pasar a constante
                this.programacionSelect.setFechaFin(new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2018"));
            }

            if (this.programacionSelect.getIdProfesional() == null) {
                this.programacionSelect.setIdProfesional(this.cpprofesioSelect.getId().getCpfncodigo());
            }

            if (this.programacionSelect.getIdSede() == null) {
                this.programacionSelect.setIdSede(this.cpprofesioSelect.getId().getCpfnclinic());
            }

            if (this.programacionSelect.getEstado() == null) {
                //Pasar a constante
                this.programacionSelect.setEstado(1l);
            }

            this.programacionSelect = this.agendamientoService.save(this.programacionSelect);
            if (this.programacionSelect != null) {
                logger.error("EXITO");
                this.consultarProgramaciones();
                this.addInfoMessage("SCHEDULE_SAVED", "Programacion guardada correctamente");

            }
        } catch (Exception e) {
            logger.error("Error al guardar programaciones:", e);
            addErrorMessage("NO_KEY", "Error al guardar programaciones");
        }
    }

    public Collection<ProgramacionDTO> getProgramacionDTOList() {
        return programacionDTOList;
    }

    public void setProgramacionDTOList(Collection<ProgramacionDTO> programacionDTOList) {
        this.programacionDTOList = programacionDTOList;
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

        }
    }

    public ProgramacionDTO getProgramacionSelect() {
        if (this.programacionSelect != null) {
            sortDiaProgramacionDTOs();
        }
        return programacionSelect;
    }

    public void setProgramacionSelect(ProgramacionDTO programacionSelect) {
        this.programacionSelect = programacionSelect;
    }
    
    public Collection<NovedadDTO> getNovedadDTOList() {
        return novedadDTOList;
    }

    public void setNovedadDTOList(Collection<NovedadDTO> novedadDTOList) {
        this.novedadDTOList = novedadDTOList;
    }

    public Collection<DiaProgramacionDTO> getDiaProgramacionDTOList() {
        return diaProgramacionDTOList;
    }

    public void setDiaProgramacionDTOList(Collection<DiaProgramacionDTO> diaProgramacionDTOList) {
        this.diaProgramacionDTOList = diaProgramacionDTOList;
    }

    public NovedadDTO getNovedadDTO() {
        return novedadDTO;
    }

    public void setNovedadDTO(NovedadDTO novedadDTO) {
        this.novedadDTO = novedadDTO;
    }

    public DiaNovedadDTO getDiaNovedadDTO() {
        return diaNovedadDTO;
    }

    public void setDiaNovedadDTO(DiaNovedadDTO diaNovedadDTO) {
        this.diaNovedadDTO = diaNovedadDTO;
    }

    public DiaProgramacionDTO getDiaProgramacionDTO() {
        return diaProgramacionDTO;
    }

    public void setDiaProgramacionDTO(DiaProgramacionDTO diaProgramacionDTO) {
        this.diaProgramacionDTO = diaProgramacionDTO;
    }
    
    public Set<WrapperDiaProgramacionDTO> getSortedWrapperDiaProgramacionDTO() {
        return sortedWrapperDiaProgramacionDTO;
    }

    public void setSortedWrapperDiaProgramacionDTO(Set<WrapperDiaProgramacionDTO> sortedWrapperDiaProgramacionDTO) {
        this.sortedWrapperDiaProgramacionDTO = sortedWrapperDiaProgramacionDTO;
    }

    public void loadFechaInicio() {
        logger.error("loadFechaInicio: " + this.fechaInicioNovedad);
        logger.error("novedadSelect: " + novedadSelect);
    }

    public void loadFechaFin() {
        logger.error("loadFechaFin: " + this.fechaFinNovedad);
        logger.error("novedadSelect: " + novedadSelect);
    }

    public NovedadDTO getNovedadSelect() {
        logger.error("getNovedadSelect: " + novedadSelect);
        return novedadSelect;
    }

    public void setNovedadSelect(NovedadDTO novedadSelect) {
        logger.error("setNovedadSelect: " + novedadSelect);
        this.novedadSelect = novedadSelect;
    }

    public Set<WrapperDiaNovedadDTO> getSortedWrapperDiaNovedadDTO() {
        return sortedWrapperDiaNovedadDTO;
    }

    public void setSortedWrapperDiaNovedadDTO(Set<WrapperDiaNovedadDTO> sortedWrapperDiaNovedadDTO) {
        this.sortedWrapperDiaNovedadDTO = sortedWrapperDiaNovedadDTO;
    }

    public Boolean getHabilitarEditarNovedad() {
        return habilitarEditarNovedad;
    }

    public void setHabilitarEditarNovedad(Boolean habilitarEditarNovedad) {
        this.habilitarEditarNovedad = habilitarEditarNovedad;
    }

    public Boolean getHabilitarCrearNovedad() {
        return habilitarCrearNovedad;
    }

    public void setHabilitarCrearNovedad(Boolean habilitarCrearNovedad) {
        this.habilitarCrearNovedad = habilitarCrearNovedad;
    }

    public Boolean getHabilitarVerAgendaNovedad() {
        return habilitarVerAgendaNovedad;
    }

    public void setHabilitarVerAgendaNovedad(Boolean habilitarVerAgendaNovedad) {
        this.habilitarVerAgendaNovedad = habilitarVerAgendaNovedad;
    }

    public Boolean getAux_novedad_estado() {
        this.aux_novedad_estado = this.novedadSelect.getEstado().intValue() > 0;
        return this.aux_novedad_estado;
    }

    public void setAux_novedad_estado(Boolean aux_novedad_estado) {
        if (this.novedadSelect != null) {
            this.aux_novedad_estado = aux_novedad_estado;
            if (this.aux_novedad_estado) {
                this.novedadSelect.setEstado(1l);
            } else {
                this.novedadSelect.setEstado(0l);
            }
        }
    }
    
    public void validHour(WrapperDiaNovedadDTO dia){
        logger.error("" + dia);
    }
    
}

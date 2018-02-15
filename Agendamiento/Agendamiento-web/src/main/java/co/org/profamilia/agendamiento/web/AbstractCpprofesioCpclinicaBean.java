/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.web;

import co.org.profamilia.agendamiento.service.AgendamientoService;
import co.org.profamilia.clinico.dto.CpclinicaDTO;
import co.org.profamilia.clinico.dto.CpprofesioDTO;
import co.org.profamilia.clinico.dto.CpprofesioIdDTO;
import co.org.profamilia.clinico.service.ClinicoService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author czambrano
 */
public abstract class AbstractCpprofesioCpclinicaBean extends AbstractAuthenticatedBean {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCpprofesioCpclinicaBean.class);

    @ManagedProperty(value = "#{agendamientoService}")
    AgendamientoService agendamientoService;

    @ManagedProperty(value = "#{clinicoService}")
    ClinicoService clinicoService;

    protected Map<Long, CpclinicaDTO> cacheCpclinicaDTOs;
    protected CpprofesioDTO cpprofesioSelect;
    protected CpprofesioDTO cpprofesioQuery;

    protected Map<Long, CpprofesioDTO> cacheCpprofesioDTOs;
    protected CpclinicaDTO cpclinicaSelect;
    protected CpclinicaDTO cpclinicaQuery;

    protected Date fechaInicioNovedad;
    protected Date fechaFinNovedad;

    public AbstractCpprofesioCpclinicaBean() {

        this.cpclinicaQuery = new CpclinicaDTO();
        this.cpprofesioQuery = new CpprofesioDTO();
        this.cpprofesioQuery.setId(new CpprofesioIdDTO());

        this.fechaInicioNovedad = new Date();
        this.fechaFinNovedad = new Date();

    }

    private void loadCpclinicaDTOListByQuery(Long cclncodigo, String cclcnombre) {
        try {

            Collection<CpclinicaDTO> _tmpList = clinicoService.findActiveCpclinica(cclncodigo, cclcnombre);
            this.cacheCpclinicaDTOs = _tmpList.stream().collect(Collectors.toMap(cpclinicaDTO -> cpclinicaDTO.getCclncodigo(), cpclinicaDTO -> cpclinicaDTO));

        } catch (Exception e) {
            logger.error("Error al consultar clinicas:", e);
            addErrorMessage("CLINIC_GET_ERROR", "Error al consultar clinicas");
        }
    }

    public void loadCpclinicaDTOListQuery() {
        this.loadCpclinicaDTOListByQuery(this.cpclinicaQuery.getCclncodigo(), this.cpclinicaQuery.getCclcnombre());
    }

    public void resetCpclinicaDTOList() {
        this.loadCpclinicaDTOListByQuery(null, null);
    }

    public List<CpclinicaDTO> autoComplCpclinica(String query) throws Exception {
        List<CpclinicaDTO> tmpList = new ArrayList();

        for (CpclinicaDTO dto : getCpclinicaDTOList()) {
            if (dto.getCclcnombre().toUpperCase().contains(query.toUpperCase())
                    || dto.getCclcciudad().toUpperCase().contains(query.toUpperCase())
                    || String.valueOf(dto.getCclncodigo()).toUpperCase().contains(query)) {
                tmpList.add(dto);
            }
        }

        return tmpList;
    }

    public Collection<CpclinicaDTO> getCpclinicaDTOList() {
        if (this.cacheCpclinicaDTOs == null || this.cacheCpclinicaDTOs.isEmpty()) {
            this.resetCpclinicaDTOList();
        }

        return cacheCpclinicaDTOs.values().stream().collect(Collectors.toList());
    }

    public void setCpclinicaDTOList(Collection<CpclinicaDTO> cpclinicaDTOList) {
        this.cacheCpclinicaDTOs = cpclinicaDTOList.stream().collect(Collectors.toMap(cpclinicaDTO -> cpclinicaDTO.getCclncodigo(), cpclinicaDTO -> cpclinicaDTO));
    }

    public void clearModalCpclinica() {
        cpclinicaQuery = new CpclinicaDTO();
    }
    
    public CpclinicaDTO getCpclinicaById(Long cclncodigo){
        if (this.cacheCpclinicaDTOs == null || this.cacheCpclinicaDTOs.isEmpty()) {
            this.resetCpclinicaDTOList();
        }
        return this.cacheCpclinicaDTOs.get(cclncodigo);
    }
    
    private void loadCpprofesioByCpclinica(Long idClinica, Long cpfncodigo, String cpfcnombre) {
        try {            
            Collection<CpprofesioDTO> _tmpList = clinicoService.findActiveCpprofesioByCpclinica(idClinica, cpfncodigo, cpfcnombre);
            this.cacheCpprofesioDTOs = _tmpList.stream().collect(Collectors.toMap(cpprofesioDTO -> cpprofesioDTO.getId().getCpfncodigo(), cpprofesioDTO -> cpprofesioDTO));

        } catch (Exception e) {
            logger.error("Error al consultar profesionales:", e);
            addErrorMessage("DOCTOR_GET_ERROR", "Error al consultar profesionales");
        }
    }

    public void loadCpprofesioDTOListByCpclinicaQuery() {
        this.cpprofesioSelect = null;
        this.loadCpprofesioByCpclinica(this.cpclinicaSelect.getCclncodigo(), this.cpprofesioQuery.getCpfacedula(), this.cpprofesioQuery.getCpfcnombre());
    }

    public void resetCpprofesioDTOListByCpclinica() {
        if (this.cpclinicaSelect == null) {
            this.loadCpprofesioByCpclinica(null, null, null);
        } else {
            this.loadCpprofesioByCpclinica(this.cpclinicaSelect.getCclncodigo(), null, null);
        }
    }

    public List<CpprofesioDTO> autoComplCpprofesio(String query) throws Exception {
        List<CpprofesioDTO> tmpList = new ArrayList();

        for (CpprofesioDTO dto : getCpprofesioDTOList()) {
            if (dto.getCpfcnombre().toUpperCase().contains(query.toUpperCase())
                    || String.valueOf(dto.getId().getCpfncodigo()).toUpperCase().contains(query)
                    || String.valueOf(dto.getCpfacedula()).toUpperCase().contains(query)) {
                tmpList.add(dto);
            }
        }

        return tmpList;
    }

    public Collection<CpprofesioDTO> getCpprofesioDTOList() {
        if (this.cacheCpprofesioDTOs == null || this.cacheCpprofesioDTOs.isEmpty()) {
            this.resetCpprofesioDTOListByCpclinica();
        }

        return cacheCpprofesioDTOs.values().stream().collect(Collectors.toList());
    }

    public void setCpprofesioDTOList(Collection<CpprofesioDTO> cpprofesioDTOList) {
        this.cacheCpprofesioDTOs = cpprofesioDTOList.stream().collect(Collectors.toMap(cpprofesioDTO -> cpprofesioDTO.getId().getCpfncodigo(), cpprofesioDTO -> cpprofesioDTO));
    }

    public void clearModalCpprofesio() {
        cpprofesioQuery = new CpprofesioDTO();
        cpprofesioQuery.setId(new CpprofesioIdDTO());
    }
    
    public CpprofesioDTO getCpprofesioById(Long cpfncodigo){
        if (this.cacheCpprofesioDTOs == null || this.cacheCpprofesioDTOs.isEmpty()) {
            this.resetCpprofesioDTOListByCpclinica();
        }
        return this.cacheCpprofesioDTOs.get(cpfncodigo);
    }
    
    public String getNombreDia(Long dia) {
        String nombreDia = null;
        switch (dia.intValue()) {
            case 1:
                //nombreDia = "lunes";
                nombreDia = messageSourceAccessor.getMessage("DAY_1", "Lunes");
                break;
            case 2:
                nombreDia = "martes";
                break;
            case 3:
                nombreDia = "miercoles";
                break;
            case 4:
                nombreDia = "jueves";
                break;
            case 5:
                nombreDia = "viernes";
                break;
            case 6:
                nombreDia = "sabado";
                break;
            case 7:
                nombreDia = "domingo";
                break;
            default:
                nombreDia = "no es un numero de dia valido";
        }
        return nombreDia;
    }

    /* Setter y getter*/
    public AgendamientoService getAgendamientoService() {
        return agendamientoService;
    }

    public void setAgendamientoService(AgendamientoService agendamientoService) {
        this.agendamientoService = agendamientoService;
    }

    public ClinicoService getClinicoService() {
        return clinicoService;
    }

    public void setClinicoService(ClinicoService clinicoService) {
        this.clinicoService = clinicoService;
    }

    public CpprofesioDTO getCpprofesioSelect() {
        return cpprofesioSelect;
    }

    public void setCpprofesioSelect(CpprofesioDTO cpprofesioSelect) {
        this.cpprofesioSelect = cpprofesioSelect;
    }

    public CpprofesioDTO getCpprofesioQuery() {
        return cpprofesioQuery;
    }

    public void setCpprofesioQuery(CpprofesioDTO cpprofesioQuery) {
        this.cpprofesioQuery = cpprofesioQuery;
    }

    public CpclinicaDTO getCpclinicaSelect() {
        return cpclinicaSelect;
    }

    public void setCpclinicaSelect(CpclinicaDTO cpclinicaSelect) {
        this.cpclinicaSelect = cpclinicaSelect;
    }

    public CpclinicaDTO getCpclinicaQuery() {
        return cpclinicaQuery;
    }

    public void setCpclinicaQuery(CpclinicaDTO cpclinicaQuery) {
        this.cpclinicaQuery = cpclinicaQuery;
    }

    public Date getFechaInicioNovedad() {
        return fechaInicioNovedad;
    }

    public void setFechaInicioNovedad(Date fechaInicioNovedad) {
        this.fechaInicioNovedad = fechaInicioNovedad;
    }

    public Date getFechaFinNovedad() {
        return fechaFinNovedad;
    }

    public void setFechaFinNovedad(Date fechaFinNovedad) {
        this.fechaFinNovedad = fechaFinNovedad;
    }

}

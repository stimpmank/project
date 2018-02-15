/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.web;

import co.org.profamilia.agendamiento.dto.NovedadDetalleDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author czambrano
 */
@ManagedBean(name = "detalleNovedades")
@ViewScoped
public class DetalleNovedadesBean extends AbstractCpprofesioCpclinicaBean {

    private static final Logger logger = LoggerFactory.getLogger(DetalleNovedadesBean.class);

    private Collection<NovedadDetalleDTO> novedadDetalleDTOList;
    private List<NovedadDetalleDTO> novedadDetalleDTOSelectedList;
    private NovedadDetalleDTO novedadDetalleSelect;

    public DetalleNovedadesBean() {

    }

    @PostConstruct
    public void DetalleNovedadesBeanInit() {
        novedadDetalleSelect = new NovedadDetalleDTO();
        this.novedadDetalleDTOSelectedList = new ArrayList<>();

    }

    public void consultarDetalleNovedades() {
        this.consultarDetalleNovedades(this.cpprofesioSelect.getId().getCpfncodigo(), this.cpprofesioSelect.getId().getCpfnclinic(), this.fechaInicioNovedad, this.fechaFinNovedad);
    }

    private void consultarDetalleNovedades(Long idProfesional, Long idSede, Date fechaInicio, Date fechaFin) {
        try {

            logger.error("consultarNovedades idProfesional " + idProfesional);
            logger.error("consultarNovedades idSede " + idSede);
            logger.error("consultarNovedades fechaInicio: " + fechaInicio);
            logger.error("consultarNovedades fechaFin:" + fechaFin);

            novedadDetalleDTOList = agendamientoService.getAllDetalleNovedad(idProfesional, idSede, fechaInicio, fechaFin);

        } catch (Exception e) {
            logger.error("Error al consultar detalle de novedades:", e);
            addErrorMessage("DETAILED_GET_ERROR", "Error al consultar detalle de novedades");
        }
    }

    public void eliminarDetalleNovedades() {
        try {
            this.agendamientoService.deleteNovedadDetalles(novedadDetalleDTOSelectedList);
            this.consultarDetalleNovedades();
        } catch (Exception e) {
            logger.error("Error al eliminar detalle de novedades:", e);
            addErrorMessage("DETAILED_DELETE_ERROR", "Error al eliminar detalle de novedades");
        }
    }

    public Collection<NovedadDetalleDTO> getNovedadDetalleDTOList() {
        return novedadDetalleDTOList;
    }

    public void setNovedadDetalleDTOList(Collection<NovedadDetalleDTO> novedadDetalleDTOList) {
        this.novedadDetalleDTOList = novedadDetalleDTOList;
    }

    public NovedadDetalleDTO getNovedadDetalleSelect() {
        return novedadDetalleSelect;
    }

    public void setNovedadDetalleSelect(NovedadDetalleDTO novedadDetalleSelect) {
        this.novedadDetalleSelect = novedadDetalleSelect;
    }

    public List<NovedadDetalleDTO> getNovedadDetalleDTOSelectedList() {
        return novedadDetalleDTOSelectedList;
    }

    public void setNovedadDetalleDTOSelectedList(List<NovedadDetalleDTO> novedadDetalleDTOSelectedList) {
        this.novedadDetalleDTOSelectedList = novedadDetalleDTOSelectedList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.web.application;

import co.org.profamilia.agendamiento.service.AgendamientoService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.profamilia.registro.model.entities.Chsexo;
import org.profamilia.registro.model.entities.Cptipoiden;
import org.profamilia.registro.service.ClinicoProService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author czambrano
 */
@ManagedBean(name = "agendamientoLoaderBean")
@ApplicationScoped
public class ApplicationPropertiesLoaderBean {

    protected static final Logger logger = LoggerFactory.getLogger(ApplicationPropertiesLoaderBean.class);

    @ManagedProperty(value = "#{agendamientoService}")
    AgendamientoService agendamientoService;
    
    @ManagedProperty(value = "#{clinicoProService}")
    ClinicoProService clinicoProService;

    List<Cptipoiden> entityListTipoIdentificacion;
    List<Chsexo> entityListSexo;
    
    @PostConstruct
    public void init() {

        try {
            this.getEntityListTipoIdentificacion();
        } catch (Exception ex) {
            logger.error("Error al inicializar lista estado civil", ex);
        }

        try {
            this.getEntityListSexo();
        } catch (Exception ex) {
            logger.error("Error al inicializar lista estado civil", ex);
        }
       
    }

    public List<Cptipoiden> getEntityListTipoIdentificacion() throws Exception {
        if (entityListTipoIdentificacion == null || entityListTipoIdentificacion.isEmpty()) {
            try {
                entityListTipoIdentificacion = (ArrayList<Cptipoiden>) this.clinicoProService.getTipoIdentificacion();
            } catch (Exception e) {
                logger.error("Error consultando la lista de tipos de identificacion");
                throw new Exception("Error consultando la lista de tipos de identificacion:" + e.getMessage(), e);
            }
        }
        return entityListTipoIdentificacion;
    }

    public void setEntityListTipoIdentificacion(List<Cptipoiden> entityListTipoIdentificacion) {
        this.entityListTipoIdentificacion = entityListTipoIdentificacion;
    }

    public List<Chsexo> getEntityListSexo() throws Exception {
        if (entityListSexo == null || entityListSexo.isEmpty()) {
            try {
                entityListSexo = (ArrayList<Chsexo>) this.clinicoProService.getSexoSap();
            } catch (Exception e) {
                logger.error("Error consultando la lista de Generos");
                throw new Exception("Error consultando la lista de Generos:" + e.getMessage(), e);
            }
        }
        return entityListSexo;
    }

    public void setEntityListSexo(List<Chsexo> entityListSexo) {
        this.entityListSexo = entityListSexo;
    }


    public AgendamientoService getAgendamientoService() {
        return agendamientoService;
    }

    public void setAgendamientoService(AgendamientoService agendamientoService) {
        this.agendamientoService = agendamientoService;
    }

    public ClinicoProService getClinicoProService() {
        return clinicoProService;
    }

    public void setClinicoProService(ClinicoProService clinicoProService) {
        this.clinicoProService = clinicoProService;
    }
    
    
  
    
}

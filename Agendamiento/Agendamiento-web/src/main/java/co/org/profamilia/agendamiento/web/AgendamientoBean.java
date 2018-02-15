/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.web;

import co.org.profamilia.agendamiento.config.web.dto.AgendaUsuarioDTO;
import co.org.profamilia.agendamiento.service.AgendamientoService;
import co.org.profamilia.agendamiento.web.application.ApplicationPropertiesLoaderBean;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.profamilia.registro.constants.IConstantes;
import org.profamilia.registro.model.dtos.Chconsutarif;
import org.profamilia.registro.model.entities.Chusuario;
import org.profamilia.registro.model.entities.Cpentidadadm;
import org.profamilia.registro.model.entities.Cpmunidane;
import org.profamilia.registro.model.entities.Cpocupacio;
import org.profamilia.registro.model.entities.Cptipoiden;
import org.profamilia.registro.model.exception.ModelException;
import org.profamilia.registro.service.ClinicoProService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author czambrano
 */
@ManagedBean(name = "agendamiento")
@ViewScoped
public class AgendamientoBean extends AbstractAuthenticatedBean {

    private static final Logger logger = LoggerFactory.getLogger(AgendamientoBean.class);

    @ManagedProperty(value = "#{agendamientoService}")
    AgendamientoService agendamientoService;

    @ManagedProperty(value = "#{clinicoProService}")
    ClinicoProService clinicoProService;

    private List<SelectItem> listTipoIdentificacion;

    Chusuario usuarioSAP;
    AgendaUsuarioDTO agendaSelected;
    Cpentidadadm entidad;
    private List<Chusuario> lstUsuariosActivos;
    private List<Chusuario> lstUsuariosInactivos;
    private List<Chusuario> lstUsuarios;
    private List<AgendaUsuarioDTO> lstAgendasUserDto;
    List<Chconsutarif> ltsTarifaServicio;

    private Boolean enableResult;

    public AgendamientoBean() {

    }

    @PostConstruct
    public void init() {
        this.usuarioSAP = new Chusuario();
        this.setEnableResult(false);
    }

    public void consultarUsuario() {
        if (usuarioSAP.getHusetipoiden() == null
                || usuarioSAP.getHusetipoiden().equals("")) {
            this.addErrorMessage("MSG_CAMPO_OBLIGATORIO", "El campo Tipo Identificación es requerido.");
        }

        if (usuarioSAP.getHusanumeiden() == null) {
            this.addErrorMessage("MSG_CAMPO_OBLIGATORIO", "El campo Número Identificación es requerido.");
        }
        if (usuarioSAP != null) {
            logger.error("consultarUsuario: this.usuarioSAP.getHusetipoiden() " + this.usuarioSAP.getHusetipoiden());
            logger.error("consultarUsuario: this.usuarioSAP.getHusanumeiden().longValue() " + this.usuarioSAP.getHusanumeiden().longValue());
            this.resetDatos(false);
            consultarTipoIdent(this.usuarioSAP.getHusetipoiden(), this.usuarioSAP.getHusanumeiden().longValue());
        }

    }

    public void consultarTipoIdent(String tipo, Long numIdent) {

        logger.error("1------ ");
        try {
            try {
                lstUsuariosActivos = this.clinicoProService.getUsuariosClienteActivo(usuarioSAP.getHusetipoiden(), usuarioSAP.getHusanumeiden());

                logger.error("2------ " + lstUsuariosActivos.size());

            } catch (ModelException e) {
                throw new Exception("Error consultando usuario:" + usuarioSAP.getHusanumeiden() + "" + e.getMessage(), e);
            }

            try {
                lstUsuariosInactivos = this.clinicoProService.getUsuariosClienteInactivo(usuarioSAP.getHusetipoiden(),
                        usuarioSAP.getHusanumeiden());

                logger.error("3------ " + lstUsuariosInactivos.size());

            } catch (ModelException e) {
                throw new Exception("Error consultando usuario:" + usuarioSAP.getHusanumeiden() + "" + e.getMessage(), e);
            }

            if (lstUsuariosInactivos == null) {
                lstUsuariosInactivos = new ArrayList<Chusuario>();
            }

            if (lstUsuariosActivos == null) {
                lstUsuariosActivos = new ArrayList<Chusuario>();
            }

            logger.error("4------ ");

            if (lstUsuariosInactivos.isEmpty() && lstUsuariosActivos.isEmpty()) {
                logger.error("5------ " + lstUsuariosInactivos.size());
                resetDatos();
                this.usuarioSAP.setHusanumeiden(new BigDecimal(numIdent));
                this.usuarioSAP.setHusetipoiden(tipo);
                this.addInfoMessage("MSG_DATA_NOT_FOUND", "Usuario no encontrado.");
            }

            if (lstUsuariosActivos != null && !lstUsuariosActivos.isEmpty()) {
                if (lstUsuariosActivos.size() == 1 && lstUsuariosInactivos.isEmpty()) {
                    logger.error("6------ " + lstUsuariosInactivos.size());
                    usuarioSAP = lstUsuariosActivos.get(0);

                    postLoadUsuarioSap();

                } else if ((lstUsuariosActivos.size() + lstUsuariosInactivos.size()) > 1) {
                    if (lstUsuarios == null) {
                        logger.error("7------ " + lstUsuariosInactivos.size());
                        lstUsuarios = new ArrayList<Chusuario>();
                        lstUsuarios.addAll(lstUsuariosActivos);
                        lstUsuarios.addAll(lstUsuariosInactivos);
                        RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('modalUsuarios').show()");
                    }
                    //renderSelecionarUsuario = true;
                    //selectedIndex = ACTIVAR_SELECCIONAR_USUARIO;

                }

            } else {
                usuarioSAP.setHuslnumero(0);
                usuarioSAP.setHuscprimernomb("");
                usuarioSAP.setHuscsegundnomb("");
                usuarioSAP.setHuscprimerapel("");
                usuarioSAP.setHuscsegundapel("");
                //renderRegistrar = true;
            }
            //this.showPanels = true;
        } catch (Exception e) {
            logger.error("Error al consultar usuario", e);
        }
    }

    public void postLoadUsuarioSap() throws Exception {

        this.setEnableResult(true);
        consultaAgendaUsuario(usuarioSAP.getHusanumeiden().longValue());
        
        if (usuarioSAP.getHuscsegundapel() == null || usuarioSAP.getHuscsegundapel().isEmpty()) {
            usuarioSAP.setHuscsegundapel("-");
        }

        //renderUsuario = true;
        //this.onchageDepatamentos();
        //this.loadFechaNaci();
    }

    private void consultaAgendaUsuario(Long numIdent) {
        logger.error("consultaAgendaUsuario numIdent " + numIdent);

        lstAgendasUserDto = new ArrayList<AgendaUsuarioDTO>();

        AgendaUsuarioDTO dto = new AgendaUsuarioDTO();
        dto.setId(BigDecimal.ONE);
        dto.setProfesional("Dr Juan Ramirez");
        dto.setSede("Calle 34 Av caracas");
        dto.setFecha(new Date());
        dto.setHora("08: 00");
        dto.setEspecialidad("General");
        dto.setEstado(BigDecimal.ONE);

        AgendaUsuarioDTO dto2 = new AgendaUsuarioDTO();
        dto2.setId(BigDecimal.TEN);
        dto2.setProfesional("Dr Diana Chacon");
        dto2.setSede("Calle 34 Av caracas");
        dto2.setFecha(new Date());
        dto2.setHora("10: 00");
        dto2.setEspecialidad("Fertilidad");
        dto2.setEstado(BigDecimal.ONE);

        lstAgendasUserDto.add(dto);
        lstAgendasUserDto.add(dto2);
        logger.error("consultaAgendaUsuario lstAgendasUserDto  " + lstAgendasUserDto.size());

    }

    public void invokeModificaCLienteWS() throws Exception {

        String tipoVenta = "ZPOS";
        
        logger.error("getHusccelular ------- " + usuarioSAP.getHusccelular());
        logger.error("getHusccorreoelec ------- " + usuarioSAP.getHusccorreoelec());

        usuarioSAP.setHusnocupacion(BigDecimal.ZERO);
        usuarioSAP.setHuscentidadadm("13000");
        usuarioSAP.setHusetipoafilia("X");
        usuarioSAP.setHusetipcliente("0");
        usuarioSAP.setHusesexo("I");
        usuarioSAP.setHusdfechanacim(new Date());
        
        
        try {    
            if (usuarioSAP != null) {
                // Guardamos el usuarioSAP 
                try {
                    this.clinicoProService.saveUsuarioSap(usuarioSAP,
                            userBean.getUser().getSausuario().getSusclogin(),
                            ltsTarifaServicio,
                            Short.valueOf(String.valueOf(userBean.getClinica().getCclncodigo())),
                            tipoVenta);
                    this.addInfoMessage("MSG_MODIFICACION", "Se modifico el usuario correctamente");

                } catch (Exception e) {
                    this.addInfoMessage("ERROR_TEC", "Error almacenando usuario");
                    throw new Exception("Error almacenando usuario - invokeModificaCLienteWS:" + usuarioSAP.getHusanumeiden() + "" + e.getMessage(), e);
                }

            }

        } catch (RemoteException e) {
            this.addInfoMessage("ERROR_TEC", "Error almacenando usuario");
            throw new Exception("Error almacenando usuario ServicioWeb - invokeCreaCLienteWS:" + usuarioSAP.getHusanumeiden() + "" + e.getMessage(), e);
        } catch (ModelException e) {
            this.addInfoMessage("ERROR_TEC", "Error almacenando usuario");
            throw new Exception("Error almacenando usuario ServicioWeb - invokeCreaCLienteWS:" + usuarioSAP.getHusanumeiden() + "" + e.getMessage(), e);
        }
        
    }

    public void resetDatos() {
        this.resetDatos(true);
    }

    public void resetDatos(boolean restUsuarioSAP) {
        if (restUsuarioSAP) {
            this.usuarioSAP = new Chusuario();
        }
        this.listTipoIdentificacion = null;
        this.lstUsuarios = null;
        this.lstUsuariosActivos = null;
        this.lstUsuariosInactivos = null;
    }

    public AgendamientoService getAgendamientoService() {
        return agendamientoService;
    }

    public void setAgendamientoService(AgendamientoService agendamientoService) {
        this.agendamientoService = agendamientoService;
    }

    public List<SelectItem> getListTipoIdentificacion() throws Exception {
        if (listTipoIdentificacion == null || listTipoIdentificacion.isEmpty()) {
            listTipoIdentificacion = new ArrayList<>();
            for (Cptipoiden item : loaderBean.getEntityListTipoIdentificacion()) {
                listTipoIdentificacion.add(new SelectItem(item.getCticcodigo(), item.getCticdescri()));
            }

        }
        return listTipoIdentificacion;
    }

    public void setListTipoIdentificacion(List<SelectItem> listTipoIdentificacion) {
        this.listTipoIdentificacion = listTipoIdentificacion;
    }

    public Chusuario getUsuarioSAP() {
        return usuarioSAP;
    }

    public void setUsuarioSAP(Chusuario usuarioSAP) {
        this.usuarioSAP = usuarioSAP;
    }

    public ClinicoProService getClinicoProService() {
        return clinicoProService;
    }

    public void setClinicoProService(ClinicoProService clinicoProService) {
        this.clinicoProService = clinicoProService;
    }

    public List<Chusuario> getLstUsuariosActivos() {
        return lstUsuariosActivos;
    }

    public void setLstUsuariosActivos(List<Chusuario> lstUsuariosActivos) {
        this.lstUsuariosActivos = lstUsuariosActivos;
    }

    public List<Chusuario> getLstUsuariosInactivos() {
        return lstUsuariosInactivos;
    }

    public void setLstUsuariosInactivos(List<Chusuario> lstUsuariosInactivos) {
        this.lstUsuariosInactivos = lstUsuariosInactivos;
    }

    public List<Chusuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<Chusuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public List<AgendaUsuarioDTO> getLstAgendasUserDto() {
        return lstAgendasUserDto;
    }

    public void setLstAgendasUserDto(List<AgendaUsuarioDTO> lstAgendasUserDto) {
        this.lstAgendasUserDto = lstAgendasUserDto;
    }

    public Boolean getEnableResult() {
        return enableResult;
    }

    public void setEnableResult(Boolean enableResult) {
        this.enableResult = enableResult;
    }

    public AgendaUsuarioDTO getAgendaSelected() {
        return agendaSelected;
    }

    public void setAgendaSelected(AgendaUsuarioDTO agendaSelected) {
        this.agendaSelected = agendaSelected;
    }

    public Cpentidadadm getEntidad() {
        return entidad;
    }

    public void setEntidad(Cpentidadadm entidad) {
        this.entidad = entidad;
    }

    public List<Chconsutarif> getLtsTarifaServicio() {
        return ltsTarifaServicio;
    }

    public void setLtsTarifaServicio(List<Chconsutarif> ltsTarifaServicio) {
        this.ltsTarifaServicio = ltsTarifaServicio;
    }
    
    

}

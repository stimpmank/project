package org.profamilia.registro.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.component.export.*;
import org.profamilia.registro.model.dtos.Chestadociv;
import org.profamilia.registro.model.entities.Chdetactivid;
import org.profamilia.registro.model.entities.ChdetactividPK;
import org.profamilia.registro.model.entities.Chetnia;
import org.profamilia.registro.model.entities.Chniveleduca;
import org.profamilia.registro.model.entities.Chregistactiv;
import org.profamilia.registro.model.entities.Chusuario;
import org.profamilia.registro.model.entities.Chusuarioreg;
import org.profamilia.registro.model.entities.Cpclinica;
import org.profamilia.registro.model.entities.Cpdepadane;
import org.profamilia.registro.model.entities.Cpmunidane;
import org.profamilia.registro.model.entities.Cptipoiden;
import org.profamilia.registro.model.exception.ModelException;
import org.profamilia.registro.service.ClinicoProService;
import org.profamilia.registro.web.application.ApplicationPropertiesLoaderBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.org.profamilia.transversal.web.session.UserBean;

/**
 *
 * @author andres.vargas
 */

@ManagedBean(name = "registroActividad")
@ViewScoped
public class RegistroActividadBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5543031808582867943L;

	/**
	 * Lista que almacena la lista de Departamentos
	 */
	private List<SelectItem> listDepartamentos;

	/**
	 * Lista que almacena la lista de Ciudades
	 */
	private List<SelectItem> listMunicipios;

	/**
	 * Lista que almacena la lista de Ciudades
	 */
	private List<SelectItem> listMunicipio;

	/**
	 * 
	 */
	private List<Chregistactiv> lstChregistro;

	/**
	 * 
	 */
	private List<Chregistactiv> lstChregistroActividades;

	/**
	 * 
	 */
	private List<Chusuarioreg> lstChusuarioreg;

	/**
	 * 
	 * 
	 */
	private List<Chusuario> lstChusuarioregistrados;

	/**
	 * 
	 */
	private List<Chregistactiv> filteredRegistro;

	/**
	 * Lista que almacena la lista de tipos de documentos
	 */
	private List<SelectItem> listTipoIdentificacion;

	/**
	 * Lista que Nivel Educativo
	 */
	private List<SelectItem> lstNivelEducativo;

	/**
	 * Almacena la lista de los posibles estados civil
	 */
	private List<SelectItem> listEstadoCivil;

	/**
	 * Almacena la lista de los posibles estados civil
	 */
	private List<SelectItem> listClinica;

	/**
	 * Lista Etnia
	 */
	private List<SelectItem> lstEtnia;

	/**
	 * Objeto usuario registro
	 */
	Chregistactiv chregistro;

	Chusuarioreg chusuarioreg;

	Chdetactivid chdetactivid;

	ChdetactividPK chdetactividPK;

	private String tematico;

	private String actividad;

	private String modalidad;

	private BigDecimal intensidad;

	private String lineaaccion;

	private String poblacion;

	private String[] vulnerabilidad;

	private String[] discapacidad;

	private String actividadNombre;

	private String sitio;

	private String zona;

	private String proClinica;

	private BigDecimal departamento;

	private BigDecimal ciudad;

	private String registroActividadBean;

	private Date fechaActividad;

	Boolean showPanels = false;

	Boolean showPanelUsuario = false;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ManagedProperty(value = "#{clinicoProService}")
	ClinicoProService clinicoProService;

	@ManagedProperty(value = "#{userBean}")
	UserBean userBean;

	@ManagedProperty(value = "#{loaderBean}")
	ApplicationPropertiesLoaderBean loaderBean;

	public RegistroActividadBean() {
		lstChregistro = new ArrayList<Chregistactiv>();
		chusuarioreg = new Chusuarioreg();
		lstChusuarioreg = new ArrayList<Chusuarioreg>();
		lstChregistroActividades = new ArrayList<Chregistactiv>();
		lstChusuarioregistrados = new ArrayList<Chusuario>();

	}

	@PostConstruct
	public void init() {

	}

	// getter and setter
	public Chregistactiv getChregistro() {
		return chregistro;
	}

	public void setChregistro(Chregistactiv chregistro) {
		this.chregistro = chregistro;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public BigDecimal getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(BigDecimal intensidad) {
		this.intensidad = intensidad;
	}

	public String getLineaaccion() {
		return lineaaccion;
	}

	public void setLineaaccion(String lineaaccion) {
		this.lineaaccion = lineaaccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String[] getVulnerabilidad() {
		return vulnerabilidad;
	}

	public void setVulnerabilidad(String[] vulnerabilidad) {
		this.vulnerabilidad = vulnerabilidad;
	}

	public String getSitio() {
		return sitio;
	}

	public void setSitio(String sitio) {
		this.sitio = sitio;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getProClinica() {
		return proClinica;
	}

	public void setProClinica(String proClinica) {
		this.proClinica = proClinica;
	}

	public BigDecimal getDepartamento() {
		return departamento;
	}

	public void setDepartamento(BigDecimal departamento) {
		this.departamento = departamento;
	}

	public BigDecimal getCiudad() {
		return ciudad;
	}

	public void setCiudad(BigDecimal ciudad) {
		this.ciudad = ciudad;
	}

	public String getRegistroActividadBean() {
		return registroActividadBean;
	}

	public void setRegistroActividadBean(String registroActividadBean) {
		this.registroActividadBean = registroActividadBean;
	}

	public List<Chregistactiv> getLstChregistro() {
		return lstChregistro;
	}

	public void setLstChregistro(List<Chregistactiv> lstChregistro) {
		this.lstChregistro = lstChregistro;
	}

	public List<Chregistactiv> getFilteredRegistro() {
		return filteredRegistro;
	}

	public void setFilteredRegistro(List<Chregistactiv> filteredRegistro) {
		this.filteredRegistro = filteredRegistro;
	}

	public Boolean getShowPanels() {
		return showPanels;
	}

	public void setShowPanels(Boolean showPanels) {
		this.showPanels = showPanels;
	}

	public Chusuarioreg getChusuarioreg() {
		return chusuarioreg;
	}

	public void setChusuarioreg(Chusuarioreg chusuarioreg) {
		this.chusuarioreg = chusuarioreg;
	}

	public Chdetactivid getChdetactivid() {
		return chdetactivid;
	}

	public void setChdetactivid(Chdetactivid chdetactivid) {
		this.chdetactivid = chdetactivid;
	}

	public ChdetactividPK getChdetactividPK() {
		return chdetactividPK;
	}

	public void setChdetactividPK(ChdetactividPK chdetactividPK) {
		this.chdetactividPK = chdetactividPK;
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

	public List<SelectItem> getLstNivelEducativo() throws Exception {
		if (lstNivelEducativo == null || lstNivelEducativo.isEmpty()) {
			lstNivelEducativo = new ArrayList<>();

			for (Chniveleduca item : loaderBean.getEntityListNivelEducativo()) {
				lstNivelEducativo.add(new SelectItem(new BigDecimal(item.getCneecodigo()), item.getCnecdescripcio()));
			}

		}
		return lstNivelEducativo;
	}

	public void setLstNivelEducativo(List<SelectItem> lstNivelEducativo) {
		this.lstNivelEducativo = lstNivelEducativo;
	}

	public List<SelectItem> getListEstadoCivil() throws Exception {
		if (listEstadoCivil == null || listEstadoCivil.isEmpty()) {
			listEstadoCivil = new ArrayList<>();

			for (Chestadociv item : loaderBean.getEntityListEstadoCivil()) {
				listEstadoCivil.add(new SelectItem(item.getCececodigo(), item.getCeccdescripcio()));
			}

		}
		return listEstadoCivil;
	}

	public void setListEstadoCivil(List<SelectItem> listEstadoCivil) {
		this.listEstadoCivil = listEstadoCivil;
	}

	public List<SelectItem> getLstEtnia() throws Exception {
		if (lstEtnia == null || lstEtnia.isEmpty()) {
			lstEtnia = new ArrayList<>();
			for (Chetnia item : loaderBean.getEntityListEtnia()) {
				lstEtnia.add(new SelectItem(item.getCetecodigo(), item.getCetcdescripcio()));
			}
		}
		return lstEtnia;
	}

	public void setLstEtnia(List<SelectItem> lstEtnia) {
		this.lstEtnia = lstEtnia;
	}

	public List<Chusuarioreg> getLstChusuarioreg() {
		return lstChusuarioreg;
	}

	public void setLstChusuarioreg(List<Chusuarioreg> lstChusuarioreg) {
		this.lstChusuarioreg = lstChusuarioreg;
	}

	public Boolean getShowPanelUsuario() {
		return showPanelUsuario;
	}

	public void setShowPanelUsuario(Boolean showPanelUsuario) {
		this.showPanelUsuario = showPanelUsuario;
	}

	public Date getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(Date fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public List<Chregistactiv> getLstChregistroActividades() {
		return lstChregistroActividades;
	}

	public void setLstChregistroActividades(List<Chregistactiv> lstChregistroActividades) {
		this.lstChregistroActividades = lstChregistroActividades;
	}

	public String getTematico() {
		return tematico;
	}

	public void setTematico(String tematico) {
		this.tematico = tematico;
	}

	public String getActividadNombre() {
		return actividadNombre;
	}

	public void setActividadNombre(String actividadNombre) {
		this.actividadNombre = actividadNombre;
	}

	public String[] getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(String[] discapacidad) {
		this.discapacidad = discapacidad;
	}

	//////////////////////////////////////
	public ClinicoProService getClinicoProService() {
		return clinicoProService;
	}

	public void setClinicoProService(ClinicoProService clinicoProService) {
		this.clinicoProService = clinicoProService;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public ApplicationPropertiesLoaderBean getLoaderBean() {
		return loaderBean;
	}

	public void setLoaderBean(ApplicationPropertiesLoaderBean loaderBean) {
		this.loaderBean = loaderBean;
	}

	public void guardarActividad() throws Exception {

		String vulnera = "";

		chregistro = new Chregistactiv();
		chregistro.setHcrctipoactivi(actividad);
		chregistro.setHcrdfechregist(new Date());
		chregistro.setHcrcmodalidad(modalidad);
		chregistro.setHcrnintenhoras(intensidad);
		chregistro.setHcrclineaccion(lineaaccion);
		chregistro.setHcrcpoblacion(poblacion);
		for (String item : vulnerabilidad) {
			vulnera = vulnera + item + ",";
		}
		vulnera = vulnera.substring(0, vulnera.length() - 1);
		chregistro.setHcrcvulnerabil(vulnera);
		chregistro.setHcrcsitio(sitio);
		chregistro.setHcrczona(zona);
		chregistro.setHcrcproclinica(proClinica);
		chregistro.setHcrcdepartame(departamento);
		chregistro.setHcrdfechactiv(fechaActividad);
		chregistro.setHcrcejeteamti(tematico);
		chregistro.setHcrcnombacti(actividadNombre);
		chregistro.setHcrcciudad(ciudad);
		lstChregistro.add(chregistro);

		if (chregistro != null) {
			// Guardamos la actividad
			try {
				this.clinicoProService.saveActividad(chregistro, userBean.getUser().getSausuario().getSusclogin(),
						Short.valueOf(String.valueOf(userBean.getClinica().getCclncodigo())));
				this.addInfoMessage("MSG_ADICION", "Se registro la actividad correctamente");
				resetDatos();
				this.showPanels = true;

			} catch (Exception e) {
				this.addErrorMessage("ERROR_TEC", "Error almacenando actividad");
				throw new Exception("Error almacenando actividad - actividad:" + chregistro.getHcrctipoactivi() + ""
						+ e.getMessage(), e);
			}

		}

	}

	public void guardarUsuarioxActividad() throws Exception {

		if (chusuarioreg != null && !chusuarioreg.equals("")) {
			String discapa = "";

			for (String item : discapacidad) {
				discapa = discapa + item + ",";
			}
			discapa = discapa.substring(0, discapa.length() - 1);
			chusuarioreg.setHcuediscapadad(discapa);

			lstChusuarioreg.add(chusuarioreg);

			if (lstChregistro != null && !lstChregistro.isEmpty()) {

				// Guardamos el usuario y la referencia de la actividad
				try {
					this.clinicoProService.saveActividadxUsuario(chusuarioreg,
							userBean.getUser().getSausuario().getSusclogin(),
							Short.valueOf(String.valueOf(userBean.getClinica().getCclncodigo())), lstChregistro);
					this.addInfoMessage("MSG_ADICION", "Se registro el usuario correctamente");
					this.chusuarioreg = new Chusuarioreg();
					this.showPanelUsuario = true;

				} catch (Exception e) {
					this.addErrorMessage("ERROR_TEC", "Error almacenando usuario y actividad");
					throw new Exception("Error almacenando usuario - actividad:" + chusuarioreg.getHculnumero() + ""
							+ chusuarioreg.getHcuanumeiden() + "" + e.getMessage(), e);
				}
			}
		}

	}

	public void guardarUsuarioxActividad2() throws Exception {

		if (chusuarioreg != null && !chusuarioreg.equals("")) {
			String discapa = "";

			for (String item : discapacidad) {
				discapa = discapa + item + ",";
			}
			discapa = discapa.substring(0, discapa.length() - 1);
			chusuarioreg.setHcuediscapadad(discapa);

			if (lstChregistro != null && !lstChregistro.isEmpty()) {

				// Guardamos el usuario y la referencia de la actividad
				try {
					this.clinicoProService.saveActividadxUsuario(chusuarioreg,
							userBean.getUser().getSausuario().getSusclogin(),
							Short.valueOf(String.valueOf(userBean.getClinica().getCclncodigo())), lstChregistro);

					this.addInfoMessage("MSG_ADICION", "Se registro el usuario correctamente");
					resetDatosUsuario();
					RequestContext context = RequestContext.getCurrentInstance();
					context.execute("PF('modalRegistroUsuario').hide();");
				} catch (Exception e) {
					this.addErrorMessage("ERROR_TEC", "Error almacenando usuario y actividad");
					throw new Exception("Error almacenando usuario - actividad:" + chusuarioreg.getHculnumero() + ""
							+ chusuarioreg.getHcuanumeiden() + "" + e.getMessage(), e);
				}
			}
		}

	}

	public void onRowSelect(SelectEvent event) {
		if (chregistro != null) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('modalRegistroUsuario').show();");

		}
	}

	public String reload() {

		this.resetDatosUsuario();
		return "pretty:registrarActividades";
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Actividad Seleccionada",
				((Chregistactiv) event.getObject()).getHcrctipoactivi());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<SelectItem> getListDepartamentos() throws Exception {
		if (listDepartamentos == null || listDepartamentos.isEmpty()) {
			this.listDepartamentos = new ArrayList<>();
			for (Cpdepadane item : loaderBean.getEntityListDepto()) {
				listDepartamentos.add(new SelectItem(new BigDecimal(item.getCddncodigo()), item.getCddcdescri()));
			}
		}
		return listDepartamentos;
	}

	public void loadListMunicipios(Short CodDepart) throws Exception {
		if (CodDepart != null || CodDepart > 0) {
			listMunicipios = new ArrayList<SelectItem>();
			ArrayList<Cpmunidane> listMunicipiosAux = null;
			try {
				listMunicipiosAux = (ArrayList<Cpmunidane>) this.clinicoProService.getMunicipios(CodDepart);
			} catch (Exception e) {
				throw new Exception("Error consultando la lista de Municipios:" + e.getMessage(), e);
			}

			if (!listMunicipiosAux.isEmpty()) {
				for (Cpmunidane item : listMunicipiosAux) {
					listMunicipios.add(new SelectItem(new BigDecimal(item.getCpmunidanePK().getCmdncodmun()),
							item.getCmdcnommun()));
				}
			}

		}
	}

	public List<SelectItem> getListMunicipios() throws Exception {
		if ((listMunicipios == null || listMunicipios.isEmpty()) && (this.chusuarioreg.getHcundepartame() != null)) {
			try {
				Cpmunidane a;
				loadListMunicipios(this.chusuarioreg.getHcundepartame().shortValue());
			} catch (Exception e) {
				throw new Exception("Error consultando la lista de Municipios - getListMunicipios:" + e.getMessage(),
						e);
			}
		}
		return listMunicipios;

	}

	public List<SelectItem> getListMunicipio() throws Exception {
		if ((listMunicipios == null || listMunicipios.isEmpty()) && (this.departamento != null)) {
			try {
				Cpmunidane a;
				loadListMunicipios(this.departamento.shortValue());
			} catch (Exception e) {
				throw new Exception("Error consultando la lista de Municipios - getListMunicipios:" + e.getMessage(),
						e);
			}
		}
		return listMunicipios;

	}

	public void onchageDepatamentos() throws Exception {
		loadListMunicipios(this.chusuarioreg.getHcundepartame().shortValue());

	}

	public void loadListMunicipio(Short CodDepart) throws Exception {
		if (CodDepart != null || CodDepart > 0) {
			listMunicipios = new ArrayList<SelectItem>();
			ArrayList<Cpmunidane> listMunicipiosAux = null;
			try {
				listMunicipiosAux = (ArrayList<Cpmunidane>) this.clinicoProService.getMunicipios(CodDepart);
			} catch (Exception e) {
				throw new Exception("Error consultando la lista de Municipios:" + e.getMessage(), e);
			}

			if (!listMunicipiosAux.isEmpty()) {
				for (Cpmunidane item : listMunicipiosAux) {
					listMunicipios.add(new SelectItem(new BigDecimal(item.getCpmunidanePK().getCmdncodmun()),
							item.getCmdcnommun()));
				}
			}

		}
	}

	public void onchageDepatamento() throws Exception {
		loadListMunicipios(this.departamento.shortValue());

	}

	public void resetDatos() {
		this.resetDatos(true);
	}

	public void resetDatos(boolean restActividad) {

		this.chregistro = new Chregistactiv();
		this.actividad = "";
		this.lineaaccion = "";
		this.modalidad = "";
		this.poblacion = "";
		this.sitio = "";
		this.tematico = "";
		this.actividadNombre = "";
		this.vulnerabilidad = null;
		this.zona = "";
		this.intensidad = new BigDecimal(0);
		this.proClinica = null;
		this.departamento = new BigDecimal(0);
		this.fechaActividad = null;
		this.ciudad = new BigDecimal(0);

	}

	public void resetDatosUsuario() {
		this.resetDatosUsuario(true);
	}

	public void resetDatosUsuario(boolean restActividadusuario) {

		this.chusuarioreg = new Chusuarioreg();
		this.discapacidad = null;
		this.chdetactivid = new Chdetactivid();
		this.lstChregistro.clear();
		this.showPanels = false;

	}

	public void consultaUsuario() {
		if (chusuarioreg.getHcuetipoiden() == null || chusuarioreg.getHcuetipoiden().equals("")) {
			this.addErrorMessage("MSG_CAMPO_OBLIGATORIO", "El campo Tipo Identificación es requerido.");
		}

		if (chusuarioreg.getHcuanumeiden() == null) {
			this.addErrorMessage("MSG_CAMPO_OBLIGATORIO", "El campo Número Identificación es requerido.");
		}
		if (chusuarioreg != null) {
			this.resetDatos(false);
			consultaTipoIdent(this.chusuarioreg.getHcuetipoiden(), this.chusuarioreg.getHcuanumeiden().longValue());
		}

	}

	public void consultaTipoIdent(String tipo, Long numIdent) {
		try {
			try {
				lstChusuarioregistrados = this.clinicoProService.getUsuariosActividades(chusuarioreg.getHcuetipoiden(),
						chusuarioreg.getHcuanumeiden());

				if (lstChusuarioregistrados != null) {
					for (Chusuario chusu : lstChusuarioregistrados) {
						chusuarioreg.setHcuanumeiden(chusu.getHusanumeiden());
						chusuarioreg.setHcuccelular(chusu.getHusccelular());
						chusuarioreg.setHcuccorreoelec(chusu.getHusccorreoelec());
						chusuarioreg.setHcucetnia(chusu.getHuscetnia());
						chusuarioreg.setHcucprimerapel(chusu.getHuscprimerapel());
						chusuarioreg.setHcucsegundapel(chusu.getHuscsegundapel());
						chusuarioreg.setHcucprimernomb(chusu.getHuscprimernomb());
						chusuarioreg.setHcucsegundnomb(chusu.getHuscsegundnomb());
						chusuarioreg.setHcunniveledu(chusu.getHusnniveledu());
						chusuarioreg.setHcundepartame(chusu.getHusndepartamen());
						chusuarioreg.setHcunciudad(chusu.getHusnciudad());
						chusuarioreg.setHcueestadcivil(chusu.getHuseestadcivil());
						chusuarioreg.setHcueindetigener(chusu.getHusesexo());
						chusuarioreg.setHcunedad(new BigDecimal(calcularEdad(chusu.getHusdfechanacim())));

					}

				}
			} catch (ModelException e) {
				throw new Exception("Error consultando usuario:" + usuarioSAP.getHusanumeiden() + "" + e.getMessage(),
						e);
			}

		} catch (Exception e) {
			logger.error("Error al consultar usuario", e);
		}
	}

	public List<SelectItem> getListClinica() {
		if (listClinica == null || listClinica.isEmpty()) {
			listClinica = new ArrayList<>();

			try {
				listClinica.add(new SelectItem("", "Seleccione una opción..."));
				for (Cpclinica item : loaderBean.getEntityListClinica()) {

					listClinica.add(new SelectItem(item.getCclncodigo(), item.getCclcnombre()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return listClinica;
	}

	public void setListClinica(List<SelectItem> listClinica) {
		this.listClinica = listClinica;
	}

}

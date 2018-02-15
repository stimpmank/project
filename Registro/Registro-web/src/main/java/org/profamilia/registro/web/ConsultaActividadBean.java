package org.profamilia.registro.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.component.export.ExcelOptions;
import org.profamilia.registro.model.dtos.Chusuariosxactividad;
import org.profamilia.registro.model.entities.Chdetactivid;
import org.profamilia.registro.model.entities.Chregistactiv;
import org.profamilia.registro.model.entities.Chusuarioreg;
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

@ManagedBean(name = "consultaActividad")
@ViewScoped
public class ConsultaActividadBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5543031808582867943L;

	private String actividadBusqueda;

	private List<Chregistactiv> lstActividad;

	private Chregistactiv chregistactiv;

	private List<Chdetactivid> lstDetactivid;

	private List<Chusuarioreg> lstUsuarioActividad;

	private List<Chusuariosxactividad> lstActividadxusuarios;

	private Chusuarioreg usuarioReg;

	private Chusuarioreg chusuarioreg;

	private Chusuariosxactividad chusuarioact;

	Boolean showRegistradas = false;

	private ExcelOptions excelOpt;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ManagedProperty(value = "#{clinicoProService}")
	ClinicoProService clinicoProService;

	@ManagedProperty(value = "#{userBean}")
	UserBean userBean;

	@ManagedProperty(value = "#{loaderBean}")
	ApplicationPropertiesLoaderBean loaderBean;

	public ConsultaActividadBean() {
		lstDetactivid = new ArrayList<Chdetactivid>();
		chregistactiv = new Chregistactiv();
	}

	@PostConstruct
	public void init() {

	}

	// getter and setter

	public String getActividadBusqueda() {
		return actividadBusqueda;
	}

	public void setActividadBusqueda(String actividadBusqueda) {
		this.actividadBusqueda = actividadBusqueda;
	}

	public List<Chregistactiv> getLstActividad() {
		return lstActividad;
	}

	public void setLstActividad(List<Chregistactiv> lstActividad) {
		this.lstActividad = lstActividad;
	}

	public Boolean getShowRegistradas() {
		return showRegistradas;
	}

	public void setShowRegistradas(Boolean showRegistradas) {
		this.showRegistradas = showRegistradas;
	}

	public List<Chdetactivid> getLstDetactivid() {
		return lstDetactivid;
	}

	public void setLstDetactivid(List<Chdetactivid> lstDetactivid) {
		this.lstDetactivid = lstDetactivid;
	}

	public Chregistactiv getChregistactiv() {
		return chregistactiv;
	}

	public void setChregistactiv(Chregistactiv chregistactiv) {
		this.chregistactiv = chregistactiv;
	}

	public List<Chusuarioreg> getLstUsuarioActividad() {
		return lstUsuarioActividad;
	}

	public void setLstUsuarioActividad(List<Chusuarioreg> lstUsuarioActividad) {
		this.lstUsuarioActividad = lstUsuarioActividad;
	}

	public Chusuarioreg getChusuarioreg() {
		return chusuarioreg;
	}

	public void setChusuarioreg(Chusuarioreg chusuarioreg) {
		this.chusuarioreg = chusuarioreg;
	}

	public Chusuarioreg getUsuario() {
		return usuarioReg;
	}

	public void setUsuario(Chusuarioreg usuarioReg) {
		this.usuarioReg = usuarioReg;
	}

	public ExcelOptions getExcelOpt() {
		return excelOpt;
	}

	public void setExcelOpt(ExcelOptions excelOpt) {
		this.excelOpt = excelOpt;
	}

	public List<Chusuariosxactividad> getLstActividadxusuarios() {
		return lstActividadxusuarios;
	}

	public void setLstActividadxusuarios(List<Chusuariosxactividad> lstActividadxusuarios) {
		this.lstActividadxusuarios = lstActividadxusuarios;
	}

	public Chusuariosxactividad getChusuarioact() {
		return chusuarioact;
	}

	public void setChusuarioact(Chusuariosxactividad chusuarioact) {
		this.chusuarioact = chusuarioact;
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

	public void consultarActividad() throws Exception {

		if (actividadBusqueda != null) {
			// Consultar actividades registradas
			try {
				lstActividad = this.clinicoProService.getBuscarActividad(actividadBusqueda);
				this.showRegistradas = true;

			} catch (Exception e) {
				this.addErrorMessage("ERROR_TEC", "Error consultando actividad");
				throw new Exception("Error consultando actividad:" + actividadBusqueda + "" + e.getMessage(), e);
			}

		}

	}

	public void customizationOptions() {
		excelOpt = new ExcelOptions();
		excelOpt.setFacetBgColor("#F88017");
		excelOpt.setFacetFontSize("10");
		excelOpt.setFacetFontColor("#0000ff");
		excelOpt.setFacetFontStyle("BOLD");
		excelOpt.setCellFontColor("#00ff00");
		excelOpt.setCellFontSize("8");

	}

	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);

			cell.setCellStyle(cellStyle);
		}
	}

	public void consultaUsuariosxActividad() throws Exception {
		if (chregistactiv != null) {
			lstUsuarioActividad = new ArrayList<Chusuarioreg>();
			try {
				lstDetactivid = this.clinicoProService.getBuscarUsuarioxActividad(chregistactiv.getHcrlnumero());
				if (lstDetactivid != null) {
					lstActividadxusuarios = new ArrayList<Chusuariosxactividad>();

					for (Chdetactivid deta : lstDetactivid) {
						chusuarioact = new Chusuariosxactividad();

						Long usuario = null;

						usuario = deta.getChdetactividPK().getHcdnusuario();
						usuarioReg = this.clinicoProService.getUsuarioPorId(usuario);
						lstUsuarioActividad.add(usuarioReg);

						chusuarioact.setActividad(chregistactiv);
						chusuarioact.setUsuarioreg(usuarioReg);
						lstActividadxusuarios.add(chusuarioact);

					}

				}

			} catch (ModelException e) {
				logger.error("No pudo consultar los usuarios x actividad" + chregistactiv.getHcrlnumero());
			}
		}

	}

}

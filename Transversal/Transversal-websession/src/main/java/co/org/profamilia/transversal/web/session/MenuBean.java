/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.web.session;

import co.org.profamilia.transversal.persist.entities.Sapermrol;
import co.org.profamilia.transversal.persist.exception.ModelException;
import co.org.profamilia.transversal.service.ProfamiliaUserService;
import co.org.profamilia.transversal.web.BaseBean;
import co.org.profamilia.transversal.web.util.FacesUtils;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author czambrano
 */
@ManagedBean(name = "sessionMenuBean")
@SessionScoped
public class MenuBean extends BaseBean implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(MenuBean.class);

	private static final String MENU_INFO_SAP = "Informacion SAP";
	private static final String MENU_INFO_CONTRATO = "Información contrato";
	private static final String ITEM_ADD_USER_SAP = "Registrar usuario";
	private static final String ITEM_FIND_AGR = "Buscador Contrato";

	private static final String ID_MENU_INFO_SAP = "modificarusuariosap";// modificarUsuarioSap";
																			// //
																			// aqui
																			// debe
																			// ir
																			// el
																			// codigo
																			// del
																			// menu
																			// primer
																			// nivel
	private static final String ID_MENU_INFO_CONTRATO = "rsopdconsu";// modificarUsuarioSap";
																		// //
																		// aqui
																		// debe
																		// ir el
																		// codigo
																		// del
																		// menu
																		// primer
																		// nivel

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	@ManagedProperty(value = "#{profamiliaUserService}")
	ProfamiliaUserService profamiliaUserService;

	List<Sapermrol> listPermisos = null;
	private DefaultMenuModel model;

	@PostConstruct
	public void init() {

		try {

			loadPermiso();
			initMenu();

			/*
			 * // Inicializa el primer nivel del menu DefaultSubMenu
			 * menu_info_sap = new DefaultSubMenu(MENU_INFO_SAP.toUpperCase());
			 * DefaultSubMenu menu_info_contr = new
			 * DefaultSubMenu(MENU_INFO_CONTRATO.toUpperCase());
			 * 
			 * // Consulta las opciones de menu disponibles para el usuario
			 * logeado model = new DefaultMenuModel();
			 * 
			 * DefaultMenuItem item_x = new
			 * DefaultMenuItem(ITEM_ADD_USER_SAP.toUpperCase());
			 * item_x.setUrl("/registrarUsuarioSap");
			 * menu_info_sap.addElement(item_x);
			 * 
			 * item_x = new DefaultMenuItem(ITEM_FIND_AGR.toUpperCase());
			 * item_x.setUrl("/buscadorContrato");
			 * menu_info_contr.addElement(item_x);
			 * 
			 * //agrega las opciones de los menu de primer nivel
			 * this.loadPermiso(); if (this.listPermisos != null &&
			 * !this.listPermisos.isEmpty()) { for (Sapermrol s : listPermisos)
			 * {
			 * 
			 * //logger.warn("Permiso: " + s.getSaprograma().getSpgcnombre() +
			 * "Aplica: " + s.getSaprograma().getSpgcaplica()); DefaultMenuItem
			 * item = new DefaultMenuItem();
			 * 
			 * //TODO: Esta implementacion se puede hacer dinamica // Si el item
			 * consultado corresponde a info sap if
			 * (s.getSaprograma().getSpgcaplica().equalsIgnoreCase(
			 * ID_MENU_INFO_SAP)) {
			 * item.setValue(s.getSaprograma().getSpgcdescri());
			 * item.setUrl(s.getSaprograma().getSpgcnombre());
			 * menu_info_sap.addElement(item); }
			 * 
			 * // Si el item consultado corresponde a info contrato if
			 * (s.getSaprograma().getSpgcaplica().equalsIgnoreCase(
			 * ID_MENU_INFO_CONTRATO)) {
			 * item.setValue(s.getSaprograma().getSpgcdescri());
			 * item.setUrl(s.getSaprograma().getSpgcnombre());
			 * menu_info_contr.addElement(item); } } }
			 * model.model.addElement(menu_info_sap);
			 * model.addElement(menu_info_contr);
			 */
			model.generateUniqueIds();

		} catch (ModelException ex) {
			FacesUtils.addErrorMessage("Error al inicializar el menu [" + ex.getMessage() + "]");
			logger.error("Error al inicializar el menu", ex);
		}

	}

	private void initMenu() {

		if (this.model == null || this.model.getElements().isEmpty()) {

			// Se utiliza para recolectar aplicaciones sin duplicados
			Set<String> aplicaciones = new HashSet<>();

			model = new DefaultMenuModel();

			String base_menu_elements = this.messageSourceAccessor.getMessage("session.menu.base_menu_elements", "");

			for (Sapermrol s : listPermisos) {

				if (s != null && s.getSaprograma() != null && s.getSaprograma().getSpgcaplica() != null) {

					if (base_menu_elements.isEmpty() || base_menu_elements.toUpperCase()
							.contains(s.getSaprograma().getSpgcaplica().toUpperCase())) {
						aplicaciones.add(s.getSaprograma().getSpgcaplica().toUpperCase());
					}
				}

			}

			for (String nombre : aplicaciones) {

				DefaultSubMenu submenu = new DefaultSubMenu(this.messageSourceAccessor.getMessage(nombre, nombre));
				submenu.setId(nombre);
				model.addElement(submenu);

			}
		logger.error("Total submenu's [" + this.model.getElements().size() + "]");

		for (MenuElement menue : model.getElements()) {
			for (Sapermrol s : listPermisos) {

				if (s != null && s.getSaprograma() != null && s.getSaprograma().getSpgcaplica() != null
						&& s.getSaprograma().getSpgcaplica().equalsIgnoreCase(((DefaultSubMenu) menue).getId())) {

					DefaultMenuItem item = new DefaultMenuItem();

					item.setValue(this.messageSourceAccessor.getMessage(
							"URL.".concat(s.getSaprograma().getSpgcdescri().toUpperCase()),
							s.getSaprograma().getSpgcdescri().toUpperCase()));
					item.setUrl(s.getSaprograma().getSpgcnombre());
					((DefaultSubMenu) menue).addElement(item);

				}

			}

		}
	}

	}

	private void loadPermiso() throws ModelException {
		if (this.listPermisos == null || this.listPermisos.isEmpty()) {
			this.listPermisos = profamiliaUserService
					.getPermisosUsuarioRol(userBean.getUser().getSausuario().getSusclogin());
		}
	}

	public DefaultMenuModel getModel() {
		return model;
	}

	public void setModel(DefaultMenuModel model) {
		this.model = model;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public ProfamiliaUserService getProfamiliaUserService() {
		return profamiliaUserService;
	}

	public void setProfamiliaUserService(ProfamiliaUserService profamiliaUserService) {
		this.profamiliaUserService = profamiliaUserService;
	}

	public List<Sapermrol> getListPermisos() {
		return listPermisos;
	}

	public void setListPermisos(List<Sapermrol> listPermisos) {
		this.listPermisos = listPermisos;
	}

	public DefaultSubMenu parseDefaultSubMenu(MenuElement menue) {
		return ((DefaultSubMenu) menue);
	}

	public DefaultMenuItem parseDefaultMenuItem(MenuElement menue) {
		return ((DefaultMenuItem) menue);
	}

}

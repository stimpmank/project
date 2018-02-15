package org.profamilia.registro.model.dtos;

import org.profamilia.registro.model.entities.Chregistactiv;
import org.profamilia.registro.model.entities.Chusuarioreg;
import org.profamilia.registro.model.entities.Cpmunidane;

public class Chusuariosxactividad {

	Chregistactiv actividad;

	Chusuarioreg usuarioreg;

	String nombreActividad;
	
	Cpmunidane departamento;
	
	String nombreDepartamento;
	
	String asisteActividad;

	public Chusuariosxactividad() {
	}

	public Chregistactiv getActividad() {
		return actividad;
	}

	public void setActividad(Chregistactiv actividad) {
		this.actividad = actividad;
	}

	public Chusuarioreg getUsuarioreg() {
		return usuarioreg;
	}

	public void setUsuarioreg(Chusuarioreg usuarioreg) {
		this.usuarioreg = usuarioreg;
	}

	public String getNombreActividad() {
		if(actividad != null && !actividad.equals("")){
			if(actividad.getHcrctipoactivi().equals("MS")){
				nombreActividad = "Movilizacion Social";
			}else if(actividad.getHcrctipoactivi().equals("PF")){
				nombreActividad = "Proceso Formativo";
			}else if(actividad.getHcrctipoactivi().equals("AS")){
				nombreActividad = "Asistencia Técnica";
			}else if(actividad.getHcrctipoactivi().equals("MT")){
				nombreActividad = "Mesa de Trabajo";
			}else if(actividad.getHcrctipoactivi().equals("FS")){
				nombreActividad = "Foro/Seminario";
			}else if(actividad.getHcrctipoactivi().equals("SF")){
				nombreActividad = "Feria de Sexualidad";
			}else if(actividad.getHcrctipoactivi().equals("FT")){
				nombreActividad = "Formacion para el Trabajo";
			}else if(actividad.getHcrctipoactivi().equals("DI")){
				nombreActividad = "Diplomados";
			}else if(actividad.getHcrctipoactivi().equals("EQ")){
				nombreActividad = "Entrenamiento en técnica médicas y quirúrgicas";
			}
		}
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public Cpmunidane getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Cpmunidane departamento) {
		this.departamento = departamento;
	}

	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	public String getAsisteActividad() {
		if(usuarioreg.getHcueasistenesco().equals("A")){
			asisteActividad = "Asiste";
			
		}else if(usuarioreg.getHcueasistenesco().equals("N")){
			asisteActividad = "No Asiste";
		}
		
		return asisteActividad;
	}

	public void setAsisteActividad(String asisteActividad) {
		this.asisteActividad = asisteActividad;
	}

	
	
	

}

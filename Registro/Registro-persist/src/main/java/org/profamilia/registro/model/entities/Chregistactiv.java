package org.profamilia.registro.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CHREGISTACTIV database table.
 * 
 */
@Entity
@Table(catalog = "", schema = "HISTORIA")
@NamedQuery(name="Chregistactiv.findAll", query="SELECT c FROM Chregistactiv c")
public class Chregistactiv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long hcrlnumero;

	private BigDecimal hcrcciudad;

	private BigDecimal hcrcdepartame;

	private String hcrcejeteamti;
	
	private String hcrcnombacti;

	private String hcrclineaccion;

	private String hcrcmodalidad;

	private String hcrcoperador;

	private String hcrcpoblacion;

	private String hcrcproclinica;

	private String hcrcsitio;

	private String hcrctipoactivi;

	private String hcrcvulnerabil;

	private String hcrczona;

	@Temporal(TemporalType.DATE)
	private Date hcrdfechactiv;

	@Temporal(TemporalType.DATE)
	private Date hcrdfechregist;

	private BigDecimal hcrnintenhoras;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	public Chregistactiv() {
	}

	public long getHcrlnumero() {
		return this.hcrlnumero;
	}

	public void setHcrlnumero(long hcrlnumero) {
		this.hcrlnumero = hcrlnumero;
	}

	public BigDecimal getHcrcciudad() {
		return this.hcrcciudad;
	}

	public void setHcrcciudad(BigDecimal hcrcciudad) {
		this.hcrcciudad = hcrcciudad;
	}

	public BigDecimal getHcrcdepartame() {
		return this.hcrcdepartame;
	}

	public void setHcrcdepartame(BigDecimal hcrcdepartame) {
		this.hcrcdepartame = hcrcdepartame;
	}

	public String getHcrcejeteamti() {
		return this.hcrcejeteamti;
	}

	public void setHcrcejeteamti(String hcrcejeteamti) {
		this.hcrcejeteamti = hcrcejeteamti;
	}

	public String getHcrcnombacti() {
		return hcrcnombacti;
	}

	public void setHcrcnombacti(String hcrcnombacti) {
		this.hcrcnombacti = hcrcnombacti;
	}

	public String getHcrclineaccion() {
		return this.hcrclineaccion;
	}

	public void setHcrclineaccion(String hcrclineaccion) {
		this.hcrclineaccion = hcrclineaccion;
	}

	public String getHcrcmodalidad() {
		return this.hcrcmodalidad;
	}

	public void setHcrcmodalidad(String hcrcmodalidad) {
		this.hcrcmodalidad = hcrcmodalidad;
	}

	public String getHcrcoperador() {
		return this.hcrcoperador;
	}

	public void setHcrcoperador(String hcrcoperador) {
		this.hcrcoperador = hcrcoperador;
	}

	public String getHcrcpoblacion() {
		return this.hcrcpoblacion;
	}

	public void setHcrcpoblacion(String hcrcpoblacion) {
		this.hcrcpoblacion = hcrcpoblacion;
	}

	public String getHcrcproclinica() {
		return this.hcrcproclinica;
	}

	public void setHcrcproclinica(String hcrcproclinica) {
		this.hcrcproclinica = hcrcproclinica;
	}

	public String getHcrcsitio() {
		return this.hcrcsitio;
	}

	public void setHcrcsitio(String hcrcsitio) {
		this.hcrcsitio = hcrcsitio;
	}

	public String getHcrctipoactivi() {
		return this.hcrctipoactivi;
	}

	public void setHcrctipoactivi(String hcrctipoactivi) {
		this.hcrctipoactivi = hcrctipoactivi;
	}

	public String getHcrcvulnerabil() {
		return this.hcrcvulnerabil;
	}

	public void setHcrcvulnerabil(String hcrcvulnerabil) {
		this.hcrcvulnerabil = hcrcvulnerabil;
	}

	public String getHcrczona() {
		return this.hcrczona;
	}

	public void setHcrczona(String hcrczona) {
		this.hcrczona = hcrczona;
	}

	public Date getHcrdfechactiv() {
		return this.hcrdfechactiv;
	}

	public void setHcrdfechactiv(Date hcrdfechactiv) {
		this.hcrdfechactiv = hcrdfechactiv;
	}

	public Date getHcrdfechregist() {
		return this.hcrdfechregist;
	}

	public void setHcrdfechregist(Date hcrdfechregist) {
		this.hcrdfechregist = hcrdfechregist;
	}

	public BigDecimal getHcrnintenhoras() {
		return this.hcrnintenhoras;
	}

	public void setHcrnintenhoras(BigDecimal hcrnintenhoras) {
		this.hcrnintenhoras = hcrnintenhoras;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

}
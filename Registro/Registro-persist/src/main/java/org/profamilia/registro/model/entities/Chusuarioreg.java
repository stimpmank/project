package org.profamilia.registro.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the CHUSUARIOREG database table.
 * 
 */
@Entity
@Table(catalog = "", schema = "HISTORIA")
@NamedQuery(name="Chusuarioreg.findAll", query="SELECT c FROM Chusuarioreg c")
public class Chusuarioreg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long hculnumero;

	private BigDecimal hcuanumeiden;

	private String hcuccelular;

	private String hcuccorreoelec;

	private String hcucetnia;

	private String hcucoperador;

	private String hcucprimerapel;

	private String hcucprimernomb;

	private String hcucsegundapel;

	private String hcucsegundnomb;

	private String hcucvulnerabili;

	@Temporal(TemporalType.DATE)
	private Date hcudfecregistr;

	private String hcueasistenesco;

	private String hcuediscapadad;

	private String hcueestadcivil;

	private String hcueindetigener;

	private String hcueorientsexu;

	private String hcuesexo;

	private String hcuetipoiden;

	private BigDecimal hcunciudad;

	private BigDecimal hcundepartame;

	private BigDecimal hcunedad;

	private BigDecimal hcunniveledu;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	public Chusuarioreg() {
	}

	public long getHculnumero() {
		return this.hculnumero;
	}

	public void setHculnumero(long hculnumero) {
		this.hculnumero = hculnumero;
	}

	public BigDecimal getHcuanumeiden() {
		return this.hcuanumeiden;
	}

	public void setHcuanumeiden(BigDecimal hcuanumeiden) {
		this.hcuanumeiden = hcuanumeiden;
	}

	public String getHcuccelular() {
		return this.hcuccelular;
	}

	public void setHcuccelular(String hcuccelular) {
		this.hcuccelular = hcuccelular;
	}

	public String getHcuccorreoelec() {
		return this.hcuccorreoelec;
	}

	public void setHcuccorreoelec(String hcuccorreoelec) {
		this.hcuccorreoelec = hcuccorreoelec;
	}

	public String getHcucetnia() {
		return this.hcucetnia;
	}

	public void setHcucetnia(String hcucetnia) {
		this.hcucetnia = hcucetnia;
	}

	public String getHcucoperador() {
		return this.hcucoperador;
	}

	public void setHcucoperador(String hcucoperador) {
		this.hcucoperador = hcucoperador;
	}

	public String getHcucprimerapel() {
		return this.hcucprimerapel;
	}

	public void setHcucprimerapel(String hcucprimerapel) {
		this.hcucprimerapel = hcucprimerapel;
	}

	public String getHcucprimernomb() {
		return this.hcucprimernomb;
	}

	public void setHcucprimernomb(String hcucprimernomb) {
		this.hcucprimernomb = hcucprimernomb;
	}

	public String getHcucsegundapel() {
		return this.hcucsegundapel;
	}

	public void setHcucsegundapel(String hcucsegundapel) {
		this.hcucsegundapel = hcucsegundapel;
	}

	public String getHcucsegundnomb() {
		return this.hcucsegundnomb;
	}

	public void setHcucsegundnomb(String hcucsegundnomb) {
		this.hcucsegundnomb = hcucsegundnomb;
	}

	public String getHcucvulnerabili() {
		return this.hcucvulnerabili;
	}

	public void setHcucvulnerabili(String hcucvulnerabili) {
		this.hcucvulnerabili = hcucvulnerabili;
	}

	public Date getHcudfecregistr() {
		return this.hcudfecregistr;
	}

	public void setHcudfecregistr(Date hcudfecregistr) {
		this.hcudfecregistr = hcudfecregistr;
	}

	public String getHcueasistenesco() {
		return this.hcueasistenesco;
	}

	public void setHcueasistenesco(String hcueasistenesco) {
		this.hcueasistenesco = hcueasistenesco;
	}

	public String getHcuediscapadad() {
		return this.hcuediscapadad;
	}

	public void setHcuediscapadad(String hcuediscapadad) {
		this.hcuediscapadad = hcuediscapadad;
	}

	public String getHcueestadcivil() {
		return this.hcueestadcivil;
	}

	public void setHcueestadcivil(String hcueestadcivil) {
		this.hcueestadcivil = hcueestadcivil;
	}

	public String getHcueindetigener() {
		return this.hcueindetigener;
	}

	public void setHcueindetigener(String hcueindetigener) {
		this.hcueindetigener = hcueindetigener;
	}

	public String getHcueorientsexu() {
		return this.hcueorientsexu;
	}

	public void setHcueorientsexu(String hcueorientsexu) {
		this.hcueorientsexu = hcueorientsexu;
	}

	public String getHcuesexo() {
		return this.hcuesexo;
	}

	public void setHcuesexo(String hcuesexo) {
		this.hcuesexo = hcuesexo;
	}

	public String getHcuetipoiden() {
		return this.hcuetipoiden;
	}

	public void setHcuetipoiden(String hcuetipoiden) {
		this.hcuetipoiden = hcuetipoiden;
	}

	public BigDecimal getHcunciudad() {
		return this.hcunciudad;
	}

	public void setHcunciudad(BigDecimal hcunciudad) {
		this.hcunciudad = hcunciudad;
	}

	public BigDecimal getHcundepartame() {
		return this.hcundepartame;
	}

	public void setHcundepartame(BigDecimal hcundepartame) {
		this.hcundepartame = hcundepartame;
	}

	public BigDecimal getHcunedad() {
		return this.hcunedad;
	}

	public void setHcunedad(BigDecimal hcunedad) {
		this.hcunedad = hcunedad;
	}

	public BigDecimal getHcunniveledu() {
		return this.hcunniveledu;
	}

	public void setHcunniveledu(BigDecimal hcunniveledu) {
		this.hcunniveledu = hcunniveledu;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

}
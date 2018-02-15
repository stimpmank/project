package org.profamilia.registro.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The primary key class for the CHDETACTIVID database table.
 * 
 */
@Embeddable
public class ChdetactividPK implements Serializable {
	
	private long hcdlnumero;
	
	private long hcdnusuario;
	
	public ChdetactividPK() {

	
	}

	public ChdetactividPK(long hcdlnumero, long hcdnusuario) {

		this.hcdlnumero = hcdlnumero;
		this.hcdnusuario = hcdnusuario;
		
	}
	

	

	public long getHcdlnumero() {
		return this.hcdlnumero;
	}

	public void setHcdlnumero(long hcdlnumero) {
		this.hcdlnumero = hcdlnumero;
	}

	public long getHcdnusuario() {
		return this.hcdnusuario;
	}

	public void setHcdnusuario(long hcdnusuario) {
		this.hcdnusuario = hcdnusuario;
	}

	

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (long) hcdlnumero;
		hash += (long) hcdnusuario;
	
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ChdetactividPK)) {
			return false;
		}
		ChdetactividPK other = (ChdetactividPK) object;
		if (this.hcdlnumero != other.hcdlnumero) {
			return false;
		}
		if (this.hcdnusuario != other.hcdnusuario) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "org.profamilia.registro.model.entities.ChdetactividPK[ hcdlnumero=" + hcdlnumero + ", hcdnusuario="
				+ hcdnusuario + "]";
	}

}
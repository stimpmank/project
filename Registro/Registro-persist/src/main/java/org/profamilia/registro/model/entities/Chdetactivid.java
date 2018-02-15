package org.profamilia.registro.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
/**
*
* @author andres.vargas
*/
@Entity
@Table(catalog = "", schema = "HISTORIA")
@NamedQueries({
    @NamedQuery(name="Chdetactivid.findAll", query="SELECT c FROM Chdetactivid c")})
public class Chdetactivid implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ChdetactividPK chdetactividPK;

	private String hcdcoperador;

	@Temporal(TemporalType.DATE)
	private Date hcddfecharegis;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	public Chdetactivid() {
		chdetactividPK = new ChdetactividPK();
	}
	
	public Chdetactivid(ChdetactividPK chdetactividPK) {
        this.chdetactividPK = chdetactividPK;
    }

    public Chdetactivid(ChdetactividPK chdetactividPK, String hcdcoperador, Date hcddfecharegis, BigDecimal version) {
        this.chdetactividPK = chdetactividPK;
        this.hcdcoperador = hcdcoperador;
        this.hcddfecharegis = hcddfecharegis;
        this.version = version;
      
    }

    public Chdetactivid(long hcdlnumero, long hcdnusuario) {
        this.chdetactividPK = new ChdetactividPK(hcdlnumero, hcdnusuario);
    }

    public ChdetactividPK getChdetactividPK() {
        return chdetactividPK;
    }

    public void setChdetactividPK(ChdetactividPK chdetactividPK) {
        this.chdetactividPK = chdetactividPK;
    }

	public String getHcdcoperador() {
		return this.hcdcoperador;
	}

	public void setHcdcoperador(String hcdcoperador) {
		this.hcdcoperador = hcdcoperador;
	}

	public Date getHcddfecharegis() {
		return this.hcddfecharegis;
	}

	public void setHcddfecharegis(Date hcddfecharegis) {
		this.hcddfecharegis = hcddfecharegis;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}
	
	  @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (chdetactividPK != null ? chdetactividPK.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof Chdetactivid)) {
	            return false;
	        }
	        Chdetactivid other = (Chdetactivid) object;
	        if ((this.chdetactividPK == null && other.chdetactividPK != null) || (this.chdetactividPK != null && !this.chdetactividPK.equals(other.chdetactividPK))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "org.profamilia.registro.model.entities.Chdetactivid[ chdetactividPK=" + chdetactividPK + " ]";
	    }

}
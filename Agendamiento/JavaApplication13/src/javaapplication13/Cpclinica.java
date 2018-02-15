/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author czambrano
 */
@Entity
@Table(name = "CPCLINICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cpclinica.findAll", query = "SELECT c FROM Cpclinica c"),
    @NamedQuery(name = "Cpclinica.findByCclncodigo", query = "SELECT c FROM Cpclinica c WHERE c.cclncodigo = :cclncodigo"),
    @NamedQuery(name = "Cpclinica.findByCclcnombre", query = "SELECT c FROM Cpclinica c WHERE c.cclcnombre = :cclcnombre"),
    @NamedQuery(name = "Cpclinica.findByCclcdirect", query = "SELECT c FROM Cpclinica c WHERE c.cclcdirect = :cclcdirect"),
    @NamedQuery(name = "Cpclinica.findByCclcdirecc", query = "SELECT c FROM Cpclinica c WHERE c.cclcdirecc = :cclcdirecc"),
    @NamedQuery(name = "Cpclinica.findByCclctelefo", query = "SELECT c FROM Cpclinica c WHERE c.cclctelefo = :cclctelefo"),
    @NamedQuery(name = "Cpclinica.findByCcldfecreg", query = "SELECT c FROM Cpclinica c WHERE c.ccldfecreg = :ccldfecreg"),
    @NamedQuery(name = "Cpclinica.findByCclcusuar", query = "SELECT c FROM Cpclinica c WHERE c.cclcusuar = :cclcusuar"),
    @NamedQuery(name = "Cpclinica.findByCclcciudad", query = "SELECT c FROM Cpclinica c WHERE c.cclcciudad = :cclcciudad"),
    @NamedQuery(name = "Cpclinica.findByCclccodsgs", query = "SELECT c FROM Cpclinica c WHERE c.cclccodsgs = :cclccodsgs"),
    @NamedQuery(name = "Cpclinica.findByCclcestado", query = "SELECT c FROM Cpclinica c WHERE c.cclcestado = :cclcestado"),
    @NamedQuery(name = "Cpclinica.findByCclcimputa", query = "SELECT c FROM Cpclinica c WHERE c.cclcimputa = :cclcimputa"),
    @NamedQuery(name = "Cpclinica.findByCclcfarmac", query = "SELECT c FROM Cpclinica c WHERE c.cclcfarmac = :cclcfarmac"),
    @NamedQuery(name = "Cpclinica.findByVersion", query = "SELECT c FROM Cpclinica c WHERE c.version = :version"),
    @NamedQuery(name = "Cpclinica.findByCclcfax", query = "SELECT c FROM Cpclinica c WHERE c.cclcfax = :cclcfax"),
    @NamedQuery(name = "Cpclinica.findByCclchorario", query = "SELECT c FROM Cpclinica c WHERE c.cclchorario = :cclchorario"),
    @NamedQuery(name = "Cpclinica.findByCclccorreo", query = "SELECT c FROM Cpclinica c WHERE c.cclccorreo = :cclccorreo"),
    @NamedQuery(name = "Cpclinica.findByCclcrouter", query = "SELECT c FROM Cpclinica c WHERE c.cclcrouter = :cclcrouter"),
    @NamedQuery(name = "Cpclinica.findByCclccoradm", query = "SELECT c FROM Cpclinica c WHERE c.cclccoradm = :cclccoradm"),
    @NamedQuery(name = "Cpclinica.findByCclnregion", query = "SELECT c FROM Cpclinica c WHERE c.cclnregion = :cclnregion"),
    @NamedQuery(name = "Cpclinica.findByCclccorreg", query = "SELECT c FROM Cpclinica c WHERE c.cclccorreg = :cclccorreg"),
    @NamedQuery(name = "Cpclinica.findByCclccodippf", query = "SELECT c FROM Cpclinica c WHERE c.cclccodippf = :cclccodippf")})
public class Cpclinica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CCLNCODIGO")
    private Short cclncodigo;
    @Basic(optional = false)
    @Column(name = "CCLCNOMBRE")
    private String cclcnombre;
    @Column(name = "CCLCDIRECT")
    private String cclcdirect;
    @Column(name = "CCLCDIRECC")
    private String cclcdirecc;
    @Column(name = "CCLCTELEFO")
    private String cclctelefo;
    @Basic(optional = false)
    @Column(name = "CCLDFECREG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ccldfecreg;
    @Basic(optional = false)
    @Column(name = "CCLCUSUAR")
    private String cclcusuar;
    @Column(name = "CCLCCIUDAD")
    private String cclcciudad;
    @Column(name = "CCLCCODSGS")
    private String cclccodsgs;
    @Basic(optional = false)
    @Column(name = "CCLCESTADO")
    private String cclcestado;
    @Basic(optional = false)
    @Column(name = "CCLCIMPUTA")
    private String cclcimputa;
    @Basic(optional = false)
    @Column(name = "CCLCFARMAC")
    private String cclcfarmac;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "CCLCFAX")
    private String cclcfax;
    @Column(name = "CCLCHORARIO")
    private String cclchorario;
    @Column(name = "CCLCCORREO")
    private String cclccorreo;
    @Basic(optional = false)
    @Column(name = "CCLCROUTER")
    private String cclcrouter;
    @Column(name = "CCLCCORADM")
    private String cclccoradm;
    @Basic(optional = false)
    @Column(name = "CCLNREGION")
    private short cclnregion;
    @Column(name = "CCLCCORREG")
    private String cclccorreg;
    @Column(name = "CCLCCODIPPF")
    private String cclccodippf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cpclinica", fetch = FetchType.LAZY)
    private Set<Cpprofesio> cpprofesioSet;

    public Cpclinica() {
    }

    public Cpclinica(Short cclncodigo) {
        this.cclncodigo = cclncodigo;
    }

    public Cpclinica(Short cclncodigo, String cclcnombre, Date ccldfecreg, String cclcusuar, String cclcestado, String cclcimputa, String cclcfarmac, String cclcrouter, short cclnregion) {
        this.cclncodigo = cclncodigo;
        this.cclcnombre = cclcnombre;
        this.ccldfecreg = ccldfecreg;
        this.cclcusuar = cclcusuar;
        this.cclcestado = cclcestado;
        this.cclcimputa = cclcimputa;
        this.cclcfarmac = cclcfarmac;
        this.cclcrouter = cclcrouter;
        this.cclnregion = cclnregion;
    }

    public Short getCclncodigo() {
        return cclncodigo;
    }

    public void setCclncodigo(Short cclncodigo) {
        this.cclncodigo = cclncodigo;
    }

    public String getCclcnombre() {
        return cclcnombre;
    }

    public void setCclcnombre(String cclcnombre) {
        this.cclcnombre = cclcnombre;
    }

    public String getCclcdirect() {
        return cclcdirect;
    }

    public void setCclcdirect(String cclcdirect) {
        this.cclcdirect = cclcdirect;
    }

    public String getCclcdirecc() {
        return cclcdirecc;
    }

    public void setCclcdirecc(String cclcdirecc) {
        this.cclcdirecc = cclcdirecc;
    }

    public String getCclctelefo() {
        return cclctelefo;
    }

    public void setCclctelefo(String cclctelefo) {
        this.cclctelefo = cclctelefo;
    }

    public Date getCcldfecreg() {
        return ccldfecreg;
    }

    public void setCcldfecreg(Date ccldfecreg) {
        this.ccldfecreg = ccldfecreg;
    }

    public String getCclcusuar() {
        return cclcusuar;
    }

    public void setCclcusuar(String cclcusuar) {
        this.cclcusuar = cclcusuar;
    }

    public String getCclcciudad() {
        return cclcciudad;
    }

    public void setCclcciudad(String cclcciudad) {
        this.cclcciudad = cclcciudad;
    }

    public String getCclccodsgs() {
        return cclccodsgs;
    }

    public void setCclccodsgs(String cclccodsgs) {
        this.cclccodsgs = cclccodsgs;
    }

    public String getCclcestado() {
        return cclcestado;
    }

    public void setCclcestado(String cclcestado) {
        this.cclcestado = cclcestado;
    }

    public String getCclcimputa() {
        return cclcimputa;
    }

    public void setCclcimputa(String cclcimputa) {
        this.cclcimputa = cclcimputa;
    }

    public String getCclcfarmac() {
        return cclcfarmac;
    }

    public void setCclcfarmac(String cclcfarmac) {
        this.cclcfarmac = cclcfarmac;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCclcfax() {
        return cclcfax;
    }

    public void setCclcfax(String cclcfax) {
        this.cclcfax = cclcfax;
    }

    public String getCclchorario() {
        return cclchorario;
    }

    public void setCclchorario(String cclchorario) {
        this.cclchorario = cclchorario;
    }

    public String getCclccorreo() {
        return cclccorreo;
    }

    public void setCclccorreo(String cclccorreo) {
        this.cclccorreo = cclccorreo;
    }

    public String getCclcrouter() {
        return cclcrouter;
    }

    public void setCclcrouter(String cclcrouter) {
        this.cclcrouter = cclcrouter;
    }

    public String getCclccoradm() {
        return cclccoradm;
    }

    public void setCclccoradm(String cclccoradm) {
        this.cclccoradm = cclccoradm;
    }

    public short getCclnregion() {
        return cclnregion;
    }

    public void setCclnregion(short cclnregion) {
        this.cclnregion = cclnregion;
    }

    public String getCclccorreg() {
        return cclccorreg;
    }

    public void setCclccorreg(String cclccorreg) {
        this.cclccorreg = cclccorreg;
    }

    public String getCclccodippf() {
        return cclccodippf;
    }

    public void setCclccodippf(String cclccodippf) {
        this.cclccodippf = cclccodippf;
    }

    @XmlTransient
    public Set<Cpprofesio> getCpprofesioSet() {
        return cpprofesioSet;
    }

    public void setCpprofesioSet(Set<Cpprofesio> cpprofesioSet) {
        this.cpprofesioSet = cpprofesioSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cclncodigo != null ? cclncodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cpclinica)) {
            return false;
        }
        Cpclinica other = (Cpclinica) object;
        if ((this.cclncodigo == null && other.cclncodigo != null) || (this.cclncodigo != null && !this.cclncodigo.equals(other.cclncodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication13.Cpclinica[ cclncodigo=" + cclncodigo + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author czambrano
 */
@Entity
@Table(name = "CPPROFESIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cpprofesio.findAll", query = "SELECT c FROM Cpprofesio c"),
    @NamedQuery(name = "Cpprofesio.findByCpfncodigo", query = "SELECT c FROM Cpprofesio c WHERE c.cpprofesioPK.cpfncodigo = :cpfncodigo"),
    @NamedQuery(name = "Cpprofesio.findByCpfcnombre", query = "SELECT c FROM Cpprofesio c WHERE c.cpfcnombre = :cpfcnombre"),
    @NamedQuery(name = "Cpprofesio.findByCpfnclinic", query = "SELECT c FROM Cpprofesio c WHERE c.cpprofesioPK.cpfnclinic = :cpfnclinic"),
    @NamedQuery(name = "Cpprofesio.findByCpfacedula", query = "SELECT c FROM Cpprofesio c WHERE c.cpfacedula = :cpfacedula"),
    @NamedQuery(name = "Cpprofesio.findByCpfcdirecc", query = "SELECT c FROM Cpprofesio c WHERE c.cpfcdirecc = :cpfcdirecc"),
    @NamedQuery(name = "Cpprofesio.findByCpfctelefo", query = "SELECT c FROM Cpprofesio c WHERE c.cpfctelefo = :cpfctelefo"),
    @NamedQuery(name = "Cpprofesio.findByCpfntipcon", query = "SELECT c FROM Cpprofesio c WHERE c.cpfntipcon = :cpfntipcon"),
    @NamedQuery(name = "Cpprofesio.findByCpfncuomes", query = "SELECT c FROM Cpprofesio c WHERE c.cpfncuomes = :cpfncuomes"),
    @NamedQuery(name = "Cpprofesio.findByCpfncuosem", query = "SELECT c FROM Cpprofesio c WHERE c.cpfncuosem = :cpfncuosem"),
    @NamedQuery(name = "Cpprofesio.findByCpfncuodia", query = "SELECT c FROM Cpprofesio c WHERE c.cpfncuodia = :cpfncuodia"),
    @NamedQuery(name = "Cpprofesio.findByCpfncuolun", query = "SELECT c FROM Cpprofesio c WHERE c.cpfncuolun = :cpfncuolun"),
    @NamedQuery(name = "Cpprofesio.findByCpfncuomar", query = "SELECT c FROM Cpprofesio c WHERE c.cpfncuomar = :cpfncuomar"),
    @NamedQuery(name = "Cpprofesio.findByCpfncuomie", query = "SELECT c FROM Cpprofesio c WHERE c.cpfncuomie = :cpfncuomie"),
    @NamedQuery(name = "Cpprofesio.findByCpfncuojue", query = "SELECT c FROM Cpprofesio c WHERE c.cpfncuojue = :cpfncuojue"),
    @NamedQuery(name = "Cpprofesio.findByCpfncuovie", query = "SELECT c FROM Cpprofesio c WHERE c.cpfncuovie = :cpfncuovie"),
    @NamedQuery(name = "Cpprofesio.findByCpfncuosab", query = "SELECT c FROM Cpprofesio c WHERE c.cpfncuosab = :cpfncuosab"),
    @NamedQuery(name = "Cpprofesio.findByCpfcobliga", query = "SELECT c FROM Cpprofesio c WHERE c.cpfcobliga = :cpfcobliga"),
    @NamedQuery(name = "Cpprofesio.findByCpfntippro", query = "SELECT c FROM Cpprofesio c WHERE c.cpfntippro = :cpfntippro"),
    @NamedQuery(name = "Cpprofesio.findByCpfdfecreg", query = "SELECT c FROM Cpprofesio c WHERE c.cpfdfecreg = :cpfdfecreg"),
    @NamedQuery(name = "Cpprofesio.findByCpfthorreg", query = "SELECT c FROM Cpprofesio c WHERE c.cpfthorreg = :cpfthorreg"),
    @NamedQuery(name = "Cpprofesio.findByCpfcusuar", query = "SELECT c FROM Cpprofesio c WHERE c.cpfcusuar = :cpfcusuar"),
    @NamedQuery(name = "Cpprofesio.findByCpfcestado", query = "SELECT c FROM Cpprofesio c WHERE c.cpfcestado = :cpfcestado"),
    @NamedQuery(name = "Cpprofesio.findByCpfctipper", query = "SELECT c FROM Cpprofesio c WHERE c.cpfctipper = :cpfctipper"),
    @NamedQuery(name = "Cpprofesio.findByCpfaporfte", query = "SELECT c FROM Cpprofesio c WHERE c.cpfaporfte = :cpfaporfte"),
    @NamedQuery(name = "Cpprofesio.findByCpfaporica", query = "SELECT c FROM Cpprofesio c WHERE c.cpfaporica = :cpfaporica"),
    @NamedQuery(name = "Cpprofesio.findByCpfaportim", query = "SELECT c FROM Cpprofesio c WHERE c.cpfaportim = :cpfaportim"),
    @NamedQuery(name = "Cpprofesio.findByCpfnauxcnt", query = "SELECT c FROM Cpprofesio c WHERE c.cpfnauxcnt = :cpfnauxcnt"),
    @NamedQuery(name = "Cpprofesio.findByCpfncodimp", query = "SELECT c FROM Cpprofesio c WHERE c.cpfncodimp = :cpfncodimp"),
    @NamedQuery(name = "Cpprofesio.findByCpfacotarp", query = "SELECT c FROM Cpprofesio c WHERE c.cpfacotarp = :cpfacotarp"),
    @NamedQuery(name = "Cpprofesio.findByVersion", query = "SELECT c FROM Cpprofesio c WHERE c.version = :version"),
    @NamedQuery(name = "Cpprofesio.findByCpfapagtim", query = "SELECT c FROM Cpprofesio c WHERE c.cpfapagtim = :cpfapagtim"),
    @NamedQuery(name = "Cpprofesio.findByCpffporarp", query = "SELECT c FROM Cpprofesio c WHERE c.cpffporarp = :cpffporarp"),
    @NamedQuery(name = "Cpprofesio.findByCpfcregmedic", query = "SELECT c FROM Cpprofesio c WHERE c.cpfcregmedic = :cpfcregmedic"),
    @NamedQuery(name = "Cpprofesio.findByCpfctipide", query = "SELECT c FROM Cpprofesio c WHERE c.cpfctipide = :cpfctipide"),
    @NamedQuery(name = "Cpprofesio.findByCpfcregiva", query = "SELECT c FROM Cpprofesio c WHERE c.cpfcregiva = :cpfcregiva"),
    @NamedQuery(name = "Cpprofesio.findByCpfntipoespe", query = "SELECT c FROM Cpprofesio c WHERE c.cpfntipoespe = :cpfntipoespe"),
    @NamedQuery(name = "Cpprofesio.findByCpfcotrosi", query = "SELECT c FROM Cpprofesio c WHERE c.cpfcotrosi = :cpfcotrosi"),
    @NamedQuery(name = "Cpprofesio.findByCpfnprofent", query = "SELECT c FROM Cpprofesio c WHERE c.cpfnprofent = :cpfnprofent")})
public class Cpprofesio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CpprofesioPK cpprofesioPK;
    @Basic(optional = false)
    @Column(name = "CPFCNOMBRE")
    private String cpfcnombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CPFACEDULA")
    private BigDecimal cpfacedula;
    @Basic(optional = false)
    @Column(name = "CPFCDIRECC")
    private String cpfcdirecc;
    @Basic(optional = false)
    @Column(name = "CPFCTELEFO")
    private String cpfctelefo;
    @Basic(optional = false)
    @Column(name = "CPFNTIPCON")
    private short cpfntipcon;
    @Basic(optional = false)
    @Column(name = "CPFNCUOMES")
    private short cpfncuomes;
    @Basic(optional = false)
    @Column(name = "CPFNCUOSEM")
    private short cpfncuosem;
    @Basic(optional = false)
    @Column(name = "CPFNCUODIA")
    private short cpfncuodia;
    @Basic(optional = false)
    @Column(name = "CPFNCUOLUN")
    private short cpfncuolun;
    @Basic(optional = false)
    @Column(name = "CPFNCUOMAR")
    private short cpfncuomar;
    @Basic(optional = false)
    @Column(name = "CPFNCUOMIE")
    private short cpfncuomie;
    @Basic(optional = false)
    @Column(name = "CPFNCUOJUE")
    private short cpfncuojue;
    @Basic(optional = false)
    @Column(name = "CPFNCUOVIE")
    private short cpfncuovie;
    @Basic(optional = false)
    @Column(name = "CPFNCUOSAB")
    private short cpfncuosab;
    @Basic(optional = false)
    @Column(name = "CPFCOBLIGA")
    private String cpfcobliga;
    @Basic(optional = false)
    @Column(name = "CPFNTIPPRO")
    private short cpfntippro;
    @Basic(optional = false)
    @Column(name = "CPFDFECREG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cpfdfecreg;
    @Basic(optional = false)
    @Column(name = "CPFTHORREG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cpfthorreg;
    @Basic(optional = false)
    @Column(name = "CPFCUSUAR")
    private String cpfcusuar;
    @Basic(optional = false)
    @Column(name = "CPFCESTADO")
    private String cpfcestado;
    @Basic(optional = false)
    @Column(name = "CPFCTIPPER")
    private String cpfctipper;
    @Basic(optional = false)
    @Column(name = "CPFAPORFTE")
    private BigDecimal cpfaporfte;
    @Basic(optional = false)
    @Column(name = "CPFAPORICA")
    private BigDecimal cpfaporica;
    @Basic(optional = false)
    @Column(name = "CPFAPORTIM")
    private BigDecimal cpfaportim;
    @Basic(optional = false)
    @Column(name = "CPFNAUXCNT")
    private short cpfnauxcnt;
    @Basic(optional = false)
    @Column(name = "CPFNCODIMP")
    private short cpfncodimp;
    @Basic(optional = false)
    @Column(name = "CPFACOTARP")
    private BigDecimal cpfacotarp;
    @Column(name = "VERSION")
    private BigInteger version;
    @Basic(optional = false)
    @Column(name = "CPFAPAGTIM")
    private BigDecimal cpfapagtim;
    @Basic(optional = false)
    @Column(name = "CPFFPORARP")
    private double cpffporarp;
    @Column(name = "CPFCREGMEDIC")
    private String cpfcregmedic;
    @Column(name = "CPFCTIPIDE")
    private String cpfctipide;
    @Column(name = "CPFCREGIVA")
    private String cpfcregiva;
    @Column(name = "CPFNTIPOESPE")
    private Short cpfntipoespe;
    @Column(name = "CPFCOTROSI")
    private String cpfcotrosi;
    @Column(name = "CPFNPROFENT")
    private Long cpfnprofent;
    @JoinColumn(name = "CPFNCLINIC", referencedColumnName = "CCLNCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cpclinica cpclinica;

    public Cpprofesio() {
    }

    public Cpprofesio(CpprofesioPK cpprofesioPK) {
        this.cpprofesioPK = cpprofesioPK;
    }

    public Cpprofesio(CpprofesioPK cpprofesioPK, String cpfcnombre, BigDecimal cpfacedula, String cpfcdirecc, String cpfctelefo, short cpfntipcon, short cpfncuomes, short cpfncuosem, short cpfncuodia, short cpfncuolun, short cpfncuomar, short cpfncuomie, short cpfncuojue, short cpfncuovie, short cpfncuosab, String cpfcobliga, short cpfntippro, Date cpfdfecreg, Date cpfthorreg, String cpfcusuar, String cpfcestado, String cpfctipper, BigDecimal cpfaporfte, BigDecimal cpfaporica, BigDecimal cpfaportim, short cpfnauxcnt, short cpfncodimp, BigDecimal cpfacotarp, BigDecimal cpfapagtim, double cpffporarp) {
        this.cpprofesioPK = cpprofesioPK;
        this.cpfcnombre = cpfcnombre;
        this.cpfacedula = cpfacedula;
        this.cpfcdirecc = cpfcdirecc;
        this.cpfctelefo = cpfctelefo;
        this.cpfntipcon = cpfntipcon;
        this.cpfncuomes = cpfncuomes;
        this.cpfncuosem = cpfncuosem;
        this.cpfncuodia = cpfncuodia;
        this.cpfncuolun = cpfncuolun;
        this.cpfncuomar = cpfncuomar;
        this.cpfncuomie = cpfncuomie;
        this.cpfncuojue = cpfncuojue;
        this.cpfncuovie = cpfncuovie;
        this.cpfncuosab = cpfncuosab;
        this.cpfcobliga = cpfcobliga;
        this.cpfntippro = cpfntippro;
        this.cpfdfecreg = cpfdfecreg;
        this.cpfthorreg = cpfthorreg;
        this.cpfcusuar = cpfcusuar;
        this.cpfcestado = cpfcestado;
        this.cpfctipper = cpfctipper;
        this.cpfaporfte = cpfaporfte;
        this.cpfaporica = cpfaporica;
        this.cpfaportim = cpfaportim;
        this.cpfnauxcnt = cpfnauxcnt;
        this.cpfncodimp = cpfncodimp;
        this.cpfacotarp = cpfacotarp;
        this.cpfapagtim = cpfapagtim;
        this.cpffporarp = cpffporarp;
    }

    public Cpprofesio(long cpfncodigo, short cpfnclinic) {
        this.cpprofesioPK = new CpprofesioPK(cpfncodigo, cpfnclinic);
    }

    public CpprofesioPK getCpprofesioPK() {
        return cpprofesioPK;
    }

    public void setCpprofesioPK(CpprofesioPK cpprofesioPK) {
        this.cpprofesioPK = cpprofesioPK;
    }

    public String getCpfcnombre() {
        return cpfcnombre;
    }

    public void setCpfcnombre(String cpfcnombre) {
        this.cpfcnombre = cpfcnombre;
    }

    public BigDecimal getCpfacedula() {
        return cpfacedula;
    }

    public void setCpfacedula(BigDecimal cpfacedula) {
        this.cpfacedula = cpfacedula;
    }

    public String getCpfcdirecc() {
        return cpfcdirecc;
    }

    public void setCpfcdirecc(String cpfcdirecc) {
        this.cpfcdirecc = cpfcdirecc;
    }

    public String getCpfctelefo() {
        return cpfctelefo;
    }

    public void setCpfctelefo(String cpfctelefo) {
        this.cpfctelefo = cpfctelefo;
    }

    public short getCpfntipcon() {
        return cpfntipcon;
    }

    public void setCpfntipcon(short cpfntipcon) {
        this.cpfntipcon = cpfntipcon;
    }

    public short getCpfncuomes() {
        return cpfncuomes;
    }

    public void setCpfncuomes(short cpfncuomes) {
        this.cpfncuomes = cpfncuomes;
    }

    public short getCpfncuosem() {
        return cpfncuosem;
    }

    public void setCpfncuosem(short cpfncuosem) {
        this.cpfncuosem = cpfncuosem;
    }

    public short getCpfncuodia() {
        return cpfncuodia;
    }

    public void setCpfncuodia(short cpfncuodia) {
        this.cpfncuodia = cpfncuodia;
    }

    public short getCpfncuolun() {
        return cpfncuolun;
    }

    public void setCpfncuolun(short cpfncuolun) {
        this.cpfncuolun = cpfncuolun;
    }

    public short getCpfncuomar() {
        return cpfncuomar;
    }

    public void setCpfncuomar(short cpfncuomar) {
        this.cpfncuomar = cpfncuomar;
    }

    public short getCpfncuomie() {
        return cpfncuomie;
    }

    public void setCpfncuomie(short cpfncuomie) {
        this.cpfncuomie = cpfncuomie;
    }

    public short getCpfncuojue() {
        return cpfncuojue;
    }

    public void setCpfncuojue(short cpfncuojue) {
        this.cpfncuojue = cpfncuojue;
    }

    public short getCpfncuovie() {
        return cpfncuovie;
    }

    public void setCpfncuovie(short cpfncuovie) {
        this.cpfncuovie = cpfncuovie;
    }

    public short getCpfncuosab() {
        return cpfncuosab;
    }

    public void setCpfncuosab(short cpfncuosab) {
        this.cpfncuosab = cpfncuosab;
    }

    public String getCpfcobliga() {
        return cpfcobliga;
    }

    public void setCpfcobliga(String cpfcobliga) {
        this.cpfcobliga = cpfcobliga;
    }

    public short getCpfntippro() {
        return cpfntippro;
    }

    public void setCpfntippro(short cpfntippro) {
        this.cpfntippro = cpfntippro;
    }

    public Date getCpfdfecreg() {
        return cpfdfecreg;
    }

    public void setCpfdfecreg(Date cpfdfecreg) {
        this.cpfdfecreg = cpfdfecreg;
    }

    public Date getCpfthorreg() {
        return cpfthorreg;
    }

    public void setCpfthorreg(Date cpfthorreg) {
        this.cpfthorreg = cpfthorreg;
    }

    public String getCpfcusuar() {
        return cpfcusuar;
    }

    public void setCpfcusuar(String cpfcusuar) {
        this.cpfcusuar = cpfcusuar;
    }

    public String getCpfcestado() {
        return cpfcestado;
    }

    public void setCpfcestado(String cpfcestado) {
        this.cpfcestado = cpfcestado;
    }

    public String getCpfctipper() {
        return cpfctipper;
    }

    public void setCpfctipper(String cpfctipper) {
        this.cpfctipper = cpfctipper;
    }

    public BigDecimal getCpfaporfte() {
        return cpfaporfte;
    }

    public void setCpfaporfte(BigDecimal cpfaporfte) {
        this.cpfaporfte = cpfaporfte;
    }

    public BigDecimal getCpfaporica() {
        return cpfaporica;
    }

    public void setCpfaporica(BigDecimal cpfaporica) {
        this.cpfaporica = cpfaporica;
    }

    public BigDecimal getCpfaportim() {
        return cpfaportim;
    }

    public void setCpfaportim(BigDecimal cpfaportim) {
        this.cpfaportim = cpfaportim;
    }

    public short getCpfnauxcnt() {
        return cpfnauxcnt;
    }

    public void setCpfnauxcnt(short cpfnauxcnt) {
        this.cpfnauxcnt = cpfnauxcnt;
    }

    public short getCpfncodimp() {
        return cpfncodimp;
    }

    public void setCpfncodimp(short cpfncodimp) {
        this.cpfncodimp = cpfncodimp;
    }

    public BigDecimal getCpfacotarp() {
        return cpfacotarp;
    }

    public void setCpfacotarp(BigDecimal cpfacotarp) {
        this.cpfacotarp = cpfacotarp;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public BigDecimal getCpfapagtim() {
        return cpfapagtim;
    }

    public void setCpfapagtim(BigDecimal cpfapagtim) {
        this.cpfapagtim = cpfapagtim;
    }

    public double getCpffporarp() {
        return cpffporarp;
    }

    public void setCpffporarp(double cpffporarp) {
        this.cpffporarp = cpffporarp;
    }

    public String getCpfcregmedic() {
        return cpfcregmedic;
    }

    public void setCpfcregmedic(String cpfcregmedic) {
        this.cpfcregmedic = cpfcregmedic;
    }

    public String getCpfctipide() {
        return cpfctipide;
    }

    public void setCpfctipide(String cpfctipide) {
        this.cpfctipide = cpfctipide;
    }

    public String getCpfcregiva() {
        return cpfcregiva;
    }

    public void setCpfcregiva(String cpfcregiva) {
        this.cpfcregiva = cpfcregiva;
    }

    public Short getCpfntipoespe() {
        return cpfntipoespe;
    }

    public void setCpfntipoespe(Short cpfntipoespe) {
        this.cpfntipoespe = cpfntipoespe;
    }

    public String getCpfcotrosi() {
        return cpfcotrosi;
    }

    public void setCpfcotrosi(String cpfcotrosi) {
        this.cpfcotrosi = cpfcotrosi;
    }

    public Long getCpfnprofent() {
        return cpfnprofent;
    }

    public void setCpfnprofent(Long cpfnprofent) {
        this.cpfnprofent = cpfnprofent;
    }

    public Cpclinica getCpclinica() {
        return cpclinica;
    }

    public void setCpclinica(Cpclinica cpclinica) {
        this.cpclinica = cpclinica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpprofesioPK != null ? cpprofesioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cpprofesio)) {
            return false;
        }
        Cpprofesio other = (Cpprofesio) object;
        if ((this.cpprofesioPK == null && other.cpprofesioPK != null) || (this.cpprofesioPK != null && !this.cpprofesioPK.equals(other.cpprofesioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication13.Cpprofesio[ cpprofesioPK=" + cpprofesioPK + " ]";
    }
    
}

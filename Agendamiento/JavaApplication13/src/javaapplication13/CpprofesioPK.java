/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author czambrano
 */
@Embeddable
public class CpprofesioPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CPFNCODIGO")
    private long cpfncodigo;
    @Basic(optional = false)
    @Column(name = "CPFNCLINIC")
    private short cpfnclinic;

    public CpprofesioPK() {
    }

    public CpprofesioPK(long cpfncodigo, short cpfnclinic) {
        this.cpfncodigo = cpfncodigo;
        this.cpfnclinic = cpfnclinic;
    }

    public long getCpfncodigo() {
        return cpfncodigo;
    }

    public void setCpfncodigo(long cpfncodigo) {
        this.cpfncodigo = cpfncodigo;
    }

    public short getCpfnclinic() {
        return cpfnclinic;
    }

    public void setCpfnclinic(short cpfnclinic) {
        this.cpfnclinic = cpfnclinic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cpfncodigo;
        hash += (int) cpfnclinic;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpprofesioPK)) {
            return false;
        }
        CpprofesioPK other = (CpprofesioPK) object;
        if (this.cpfncodigo != other.cpfncodigo) {
            return false;
        }
        if (this.cpfnclinic != other.cpfnclinic) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication13.CpprofesioPK[ cpfncodigo=" + cpfncodigo + ", cpfnclinic=" + cpfnclinic + " ]";
    }
    
}

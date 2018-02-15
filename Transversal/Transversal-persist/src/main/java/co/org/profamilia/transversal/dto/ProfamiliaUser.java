package co.org.profamilia.transversal.dto;

import co.org.profamilia.transversal.persist.entities.Cpusuario;
import co.org.profamilia.transversal.persist.entities.Sausuario;

/**
 *
 * @author czambrano
 */
public class ProfamiliaUser {
    
    private Sausuario sausuario;
    private Cpusuario cpusuario;

    public Sausuario getSausuario() {
        return sausuario;
    }

    public void setSausuario(Sausuario sausuario) {
        this.sausuario = sausuario;
    }

    public Cpusuario getCpusuario() {
        return cpusuario;
    }

    public void setCpusuario(Cpusuario cpusuario) {
        this.cpusuario = cpusuario;
    }
    
    
    
}

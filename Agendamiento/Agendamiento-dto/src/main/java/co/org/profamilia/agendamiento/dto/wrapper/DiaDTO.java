/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.dto.wrapper;

import java.math.BigDecimal;

/**
 *
 * @author czambrano
 */
public interface DiaDTO {
    
    public BigDecimal getId();

    public void setId(BigDecimal id);
    
    public BigDecimal getDia();
    
    public void setDia(BigDecimal dia);
    
    public BigDecimal getHabil();
    
    public void setHabil(BigDecimal habil);
    
    public String getHoraInicio();
    
    public void setHoraInicio(String horaInicio);
    
    public String getHoraFin() ;
    
    public void setHoraFin(String horaFin) ;
}

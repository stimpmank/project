/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.web.constants;

/**
 *
 * @author czambrano
 */
public interface Constants {
    
    
    public enum Estado {
        
        ACTIVO(1),
        INACTIVO(0),
        ELIMINADO(-1);
        
        private final int estado;
        
        private Estado(int estado){
            this.estado = estado;
        }
        
        public int getValue(){
            return this.estado;
        }
        
        
    }
    
    String ESTADO_SIN_MOVIMIENTO = "SM";
    String ESTADO_CON_MOVIMIENTO = "CM";
    int CCONUSUA = 15;
    
}

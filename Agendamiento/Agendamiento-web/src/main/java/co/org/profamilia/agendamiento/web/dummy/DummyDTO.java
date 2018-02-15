/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.web.dummy;

import java.math.BigDecimal;

/**
 *
 * @author czambrano
 */
public abstract class DummyDTO {
    
    private Long id;
    private String name;

    public DummyDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DummyDTO{" + "id=" + id + ", name=" + name + '}';
    }
    
}

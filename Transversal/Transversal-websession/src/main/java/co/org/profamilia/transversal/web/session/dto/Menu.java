/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.web.session.dto;

import co.org.profamilia.transversal.persist.entities.Sapermrol;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author czambrano
 */
public class Menu {

    private String name;
    private Collection<Sapermrol> childs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Sapermrol> getChilds() {
        if (this.childs == null) {
            this.childs = new ArrayList<>();
        }
        return childs;
    }

    public void setChilds(Collection<Sapermrol> childs) {
        this.childs = childs;
    }

}

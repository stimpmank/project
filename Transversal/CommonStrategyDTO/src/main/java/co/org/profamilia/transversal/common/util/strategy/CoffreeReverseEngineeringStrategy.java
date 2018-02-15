/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.common.util.strategy;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;

/**
 *
 * @author czambrano
 */
public class CoffreeReverseEngineeringStrategy extends DelegatingReverseEngineeringStrategy {

    public CoffreeReverseEngineeringStrategy(ReverseEngineeringStrategy delegate) {
        super(delegate);
    }

    @Override
    public String tableToClassName(TableIdentifier tableIdentifier) {
        String className = super.tableToClassName(tableIdentifier);
        return className + "DTO";
    }

    @Override
    public String classNameToCompositeIdName(String className){
        return className.substring(0, className.length()-3) + "IdDTO";
    }

}

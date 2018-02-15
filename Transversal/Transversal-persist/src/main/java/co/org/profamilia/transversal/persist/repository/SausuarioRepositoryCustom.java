/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.persist.repository;

/**
 *
 * @author czambrano
 */

public interface SausuarioRepositoryCustom {
    
    public void changePassword(String username, String oldPassword,
            String newPassword);
    
}

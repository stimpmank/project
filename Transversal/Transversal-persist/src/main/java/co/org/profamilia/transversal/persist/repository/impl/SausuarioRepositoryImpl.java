/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.persist.repository.impl;

import co.org.profamilia.transversal.persist.repository.SausuarioRepositoryCustom;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author czambrano
 */
@Repository
@Transactional
public class SausuarioRepositoryImpl implements SausuarioRepositoryCustom {

    @Autowired
    @Qualifier("transversalEntityManagerFactory")
    EntityManager entityManager;
        

    @Override
    public void changePassword(String username, String oldPassword,
            String newPassword) {

        Query q = entityManager.createNativeQuery("ALTER USER " + username + " IDENTIFIED BY " + newPassword);
        q.executeUpdate();

    }

}

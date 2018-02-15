package org.profamilia.registro.model.entities.legacy.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.profamilia.registro.model.exception.ModelException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDao extends HibernateDaoSupport {

    public BaseDao(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

	

	

}

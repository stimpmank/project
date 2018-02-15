/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.persist.mapper.impl;

import co.org.profamilia.agendamiento.persist.mapper.GenericJPAEntityMapper;
import java.util.ArrayList;
import java.util.Collection;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author czambrano
 * @param <Model>
 * @param <Entity>
 */
public abstract class AbstractGenericJPAEntityMapper<Model, Entity> implements GenericJPAEntityMapper<Model, Entity> {

    protected GenericJPAEntityMapper<Model, Entity> INSTANCE = null;
    protected Class<? extends AbstractGenericJPAEntityMapper> mapperClass;

    @Override
    public GenericJPAEntityMapper<Model, Entity> getMapper() {
        if (this.INSTANCE == null) {
            this.INSTANCE = Mappers.getMapper(mapperClass);
        }
        return INSTANCE;
    }

}

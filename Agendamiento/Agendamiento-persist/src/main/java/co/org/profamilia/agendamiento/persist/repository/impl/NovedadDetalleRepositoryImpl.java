package co.org.profamilia.agendamiento.persist.repository.impl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.org.profamilia.agendamiento.persist.repository.*;
import co.org.profamilia.agendamiento.persistence.entity.DiaNovedad;
import co.org.profamilia.agendamiento.persistence.entity.Novedad;
import co.org.profamilia.agendamiento.persistence.entity.NovedadDetalle;
import java.util.List;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import oracle.net.aso.i;
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
public class NovedadDetalleRepositoryImpl implements NovedadDetalleRepositoryCustom {

    @Autowired
    @Qualifier("transversalEntityManagerFactory")
    EntityManager entityManager;

    private final int BATCH_SIZE = 50;

    @Override
    public void saveNovedadDetalles(Novedad novedad) {

        Map<String, DiaNovedad> dias = new HashMap<>();

        for (DiaNovedad d : novedad.getDiaNovedads()) {
            dias.put(String.valueOf(d.getDia()), d);
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(novedad.getFechaInicio());

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(novedad.getFechaFin());

        int i = 0;
        while (calendar.before(endCalendar)) {

            DiaNovedad diaNovedad = dias.get(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)));

            if (diaNovedad.getHabil() > 0) {

                i++;
                
                NovedadDetalle nd = new NovedadDetalle();
                nd.setFecha(calendar.getTime());
                nd.setDiaNovedad(diaNovedad);

                persistOrMenge(nd);

                if (i % BATCH_SIZE == 0) {
                    // Flush a batch of inserts and release memory.
                    entityManager.flush();
                    entityManager.clear();
                }
            }

            calendar.add(Calendar.DATE, 1);

        }
        entityManager.flush();
        entityManager.clear();

    }

    private NovedadDetalle persistOrMenge(NovedadDetalle nd) {
        if (nd.getId() == null) {
            entityManager.persist(nd);
        } else {
            entityManager.merge(nd);
        }
        return nd;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.dto.wrapper;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author czambrano
 */
public abstract class AbstractWrapperDiaDTO<T,K> implements java.io.Serializable, Comparable<K> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractWrapperDiaDTO.class);

    protected T dto;

    public AbstractWrapperDiaDTO(T dto) {
        this.dto = dto;
    }

    public T getDto() {
        return dto;
    }

    public void setDto(T dto) {
        this.dto = dto;
    }
    
    abstract public Long getId();

    abstract public void setId(Long id);

    abstract public Boolean getHabil() ;

    abstract public void setHabil(Boolean habil) ;

    abstract public Long getDia() ;

    abstract public void setDia(Long dia);

    abstract public Date getHoraInicio() throws ParseException ;

    abstract public void setHoraInicio(Date horaInicio) ;

    abstract public Date getHoraFin() throws ParseException;

    abstract public void setHoraFin(Date horaFin) ;

    protected String convertDateToStringHour(Date time) {
        String s = null;
        if (time != null) {
            Format format = new SimpleDateFormat("HH:mm");
            s = format.format(time);
            logger.error("String Time:" + s);
        }
        return s;
    }

    protected Date convertStringHourToDate(String time) throws ParseException {
        Date d = null;
        if (time != null) {
            DateFormat format = new SimpleDateFormat("HH:mm");
            d = format.parse(time);
        }
        return d;
    }

    
}

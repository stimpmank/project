package co.org.profamilia.agendamiento.dto.wrapper.impl;
// Generated 15-ene-2018 21:43:54 by Hibernate Tools 3.2.2.GA

import co.org.profamilia.agendamiento.dto.DiaNovedadDTO;
import co.org.profamilia.agendamiento.dto.NovedadDetalleDTO;
import co.org.profamilia.agendamiento.dto.wrapper.AbstractWrapperDiaDTO;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DiaProgramacionDTO generated by hbm2java
 */
public class WrapperDiaNovedadDTO  extends AbstractWrapperDiaDTO<DiaNovedadDTO,WrapperDiaNovedadDTO> {

    private static final Logger logger = LoggerFactory.getLogger(WrapperDiaNovedadDTO.class);

    public WrapperDiaNovedadDTO(DiaNovedadDTO dto) {
        super(dto);
    }
    
        
    @Override
    public Long getId() {
        return (this.dto != null) ? this.dto.getId() : null;
    }

    @Override
    public void setId(Long id) {
        this.dto.setId(id);
    }

    @Override
    public Boolean getHabil() {
        return this.dto.getHabil().intValue() > 0;
    }

    @Override
    public void setHabil(Boolean habil) {
        if (this.dto != null) {
            if (habil) {
                this.dto.setHabil(1l);
            } else {
                this.dto.setHabil(0l);
            }
        }
    }

    @Override
    public Long getDia() {
        return (this.dto != null) ? this.dto.getDia() : null;
    }

    @Override
    public void setDia(Long dia) {
        if (this.dto != null) {
            this.dto.setDia(dia);
        }
    }

    @Override
    public Date getHoraInicio() throws ParseException {
        return (this.dto != null && this.dto.getHoraInicio() != null) ? convertStringHourToDate(dto.getHoraInicio()) : null;
    }

    @Override
    public void setHoraInicio(Date horaInicio) {
        if (this.dto != null) {
            this.dto.setHoraInicio(convertDateToStringHour(horaInicio));
        }
    }

    @Override
    public Date getHoraFin() throws ParseException {
        return (this.dto != null && this.dto.getHoraFin() != null) ? convertStringHourToDate(dto.getHoraFin()) : null;
    }

    @Override
    public void setHoraFin(Date horaFin) {
        if (this.dto != null) {
            this.dto.setHoraFin(convertDateToStringHour(horaFin));
        }
    }

    public Set<NovedadDetalleDTO> getNovedadDetalleDTOs(){
        return ((DiaNovedadDTO)this.dto).getNovedadDetalleDTOs();
    }
    
    public void setNovedadDetalleDTOs(Set<NovedadDetalleDTO> novedadDetalleDTOs) {
        if (this.dto != null) {
            ((DiaNovedadDTO)this.dto).setNovedadDetalleDTOs(novedadDetalleDTOs);
        }
    }
    
    public Boolean getTipoNovedad() {
        return ((DiaNovedadDTO)this.dto).getTipoNovedad().intValue() > 0;
    }

    public void setTipoNovedad(Boolean tipoNovedad) {
        if (this.dto != null) {
            if (tipoNovedad) {
                ((DiaNovedadDTO)this.dto).setTipoNovedad(1l);
            } else {
                ((DiaNovedadDTO)this.dto).setTipoNovedad(0l);
            }
        }
    }

    @Override
    public int compareTo(WrapperDiaNovedadDTO o) {
        
        if (this == null) {
            return o == null ? 0 : 1;
        }

        if (o == null) {
            return 1;
        }

        if (this.getDia() == null) {
            return o.getDia() == null ? 0 : 1;
        }

        if (o.getDia() == null) {
            return 1;
        }

        return this.getDia().compareTo(o.getDia());
    }


}

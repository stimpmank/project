/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.agendamiento.web;

import co.org.profamilia.agendamiento.web.application.ApplicationPropertiesLoaderBean;
import co.org.profamilia.transversal.web.BaseBean;
import co.org.profamilia.transversal.web.session.UserBean;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author varonmarcos
 */
public class AbstractAuthenticatedBean extends BaseBean {

    @ManagedProperty(value = "#{userBean}")
    UserBean userBean;

    @ManagedProperty(value = "#{agendamientoLoaderBean}")
    ApplicationPropertiesLoaderBean loaderBean;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public ApplicationPropertiesLoaderBean getLoaderBean() {
        return loaderBean;
    }

    public void setLoaderBean(ApplicationPropertiesLoaderBean loaderBean) {
        this.loaderBean = loaderBean;
    }
    
    public int calcularEdad(Date fecha) {
        Calendar birth = new GregorianCalendar();
        Calendar today = new GregorianCalendar();
        int age = 0;
        int factor = 0;
        Date birthDate = fecha;
        Date currentDate = new Date(); //current date
        birth.setTime(birthDate);
        today.setTime(currentDate);
        if (today.get(Calendar.MONTH) <= birth.get(Calendar.MONTH)) {
            if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) {
                if (today.get(Calendar.DATE) < birth.get(Calendar.DATE)) {
                    factor = -1; //Aun no celebra su cumpleaños
                }
            } else {
                factor = -1; //Aun no celebra su cumpleaños
            }
        }
        age = (today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)) + factor;
        return age;
    }
   
}

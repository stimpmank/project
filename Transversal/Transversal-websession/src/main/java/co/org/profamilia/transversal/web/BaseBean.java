/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.org.profamilia.transversal.web;

import co.org.profamilia.transversal.web.util.FacesUtils;
import javax.faces.bean.ManagedProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;

public class BaseBean {

    private static final Logger logger = LoggerFactory.getLogger(BaseBean.class);

    @ManagedProperty(value = "#{messageSourceAccessor}")
    protected MessageSourceAccessor messageSourceAccessor;

    public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    public MessageSourceAccessor getMessageSourceAccessor() {
        return messageSourceAccessor;
    }

    protected void addErrorMessage(String key, String defaultMsg) {
        FacesUtils.addErrorMessage(messageSourceAccessor.getMessage(
                key, defaultMsg)
        );
    }
    
    protected void addInfoMessage(String key, String defaultMsg) {
        FacesUtils.addInfoMessage(messageSourceAccessor.getMessage(
                key, defaultMsg)
        );
    }
    
}

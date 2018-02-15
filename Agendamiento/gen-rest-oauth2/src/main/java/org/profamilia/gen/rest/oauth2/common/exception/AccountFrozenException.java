package org.profamilia.gen.rest.oauth2.common.exception;

import org.profamilia.gen.rest.oauth2.util.AppUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class AccountFrozenException extends UsernameNotFoundException {

    public AccountFrozenException() {
        super(AppUtil.getMessage("accountFrozenException"));
    }

    public AccountFrozenException(String msg) {
        super(msg);
    }
}

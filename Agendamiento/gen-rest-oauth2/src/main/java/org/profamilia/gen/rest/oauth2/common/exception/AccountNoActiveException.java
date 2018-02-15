package org.profamilia.gen.rest.oauth2.common.exception;

import org.profamilia.gen.rest.oauth2.util.AppUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AccountNoActiveException extends UsernameNotFoundException {

    public AccountNoActiveException() {
        super(AppUtil.getMessage("accountNoActiveException"));
    }
    public AccountNoActiveException(String msg) {
        super(msg);
    }
}

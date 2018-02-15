package co.org.profamilia.transversal.config.security.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.support.ConnectionUsernameProvider;
import org.springframework.stereotype.Component;

@Component
@Qualifier("org.springframework.data.jdbc.support.ConnectionUsernameProvider")
public class CurrentUsernameProvider implements ConnectionUsernameProvider {

    private static final Logger logger = LoggerFactory.getLogger(CurrentUsernameProvider.class);

    @Override
    public String getUserName() {        
        return "realuser";
    }

}

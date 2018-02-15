package co.org.profamilia.agendamiento.config.web;



import co.org.profamilia.agendamiento.config.ApplicationConfig;
import co.org.profamilia.transversal.config.security.SecurityConfig;
import co.org.profamilia.transversal.config.web.ProfamiliaWebAppInitializer;
import co.org.profamilia.transversal.config.web.ProfamiliaWebConfig;
import co.org.profamilia.transversal.persist.config.JPAPersistenceConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.profamilia.registro.config.persistence.HibernatePersistenceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebAppInitializer extends ProfamiliaWebAppInitializer {
    
    private static final Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);

    @Override
    protected List<Class> getConfigClasses() {
        List<Class> configClasses = new ArrayList();
        
        configClasses.add(SecurityConfig.class);
        
        //configClasses.add(RegistroPersistenceConfig.class);
        //configClasses.add(AgendamientoPersistenceConfig.class);
        //configClasses.add(TransversalPersistenceConfig.class);
        configClasses.add(JPAPersistenceConfig.class);
        configClasses.add(HibernatePersistenceConfig.class);
        
        configClasses.add(ProfamiliaWebConfig.class);
        configClasses.add(ApplicationConfig.class);
        
        return configClasses;
    }

    @Override
    protected Map<String, String> getInitialParameters() {
        Map<String,String> initialParameters = new HashMap();
        
        return initialParameters;
    }

    
}
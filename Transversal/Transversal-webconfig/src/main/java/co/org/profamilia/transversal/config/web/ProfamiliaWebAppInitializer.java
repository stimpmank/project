package co.org.profamilia.transversal.config.web;

import java.util.List;
import java.util.Map;
import javax.servlet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityConfig;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public abstract class ProfamiliaWebAppInitializer implements WebApplicationInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ProfamiliaWebAppInitializer.class);
   
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        logger.debug("Inicializando aplicacion . . .");

        getInitialParameters().entrySet().stream().forEach((entry) -> {
            servletContext.setInitParameter(entry.getKey(), entry.getValue());
        });

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
                
        getConfigClasses().stream().forEach((_class) -> {
            ctx.register(_class);
        });
        
        ctx.setServletContext(servletContext);

        servletContext.addListener(new ContextLoaderListener(ctx));
        servletContext.addListener(new RequestContextListener());
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }

    protected abstract List<Class> getConfigClasses();

    protected abstract Map<String, String> getInitialParameters();
    
    

    
}

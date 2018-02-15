package co.org.profamilia.transversal.service;

import co.org.profamilia.transversal.persist.entities.Cpusuario;
import co.org.profamilia.transversal.persist.entities.Sapermrol;
import co.org.profamilia.transversal.persist.entities.Sausuario;
import co.org.profamilia.transversal.persist.repository.CpusuarioRepository;
import co.org.profamilia.transversal.persist.repository.SausuarioRepository;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("profamiliaUserService")
public class ProfamiliaUserService {

    protected final static Logger logger = LoggerFactory.getLogger(ProfamiliaUserService.class);
    
    @Autowired
    SausuarioRepository sausuarioReposiory;
    
    @Autowired
    CpusuarioRepository cpusuarioRepository;

    public Cpusuario getUsuarioPorId(String curcusuari) {
        return cpusuarioRepository.findOne(curcusuari);  
    }
    
    public Sausuario getSausuario(String username) {
        return sausuarioReposiory.findOne(username);   
    }

    
    public void changePassword(String username, String oldPassword,
            String newPassword)  {
        
        Sausuario sausuario = sausuarioReposiory.findOne(username);           
        sausuarioReposiory.changePassword(username, newPassword, newPassword);
        sausuario.setSusndiexp(30);
        sausuarioReposiory.save(sausuario);
    }
    
    public List<Sapermrol> getPermisosUsuarioRol(String login)  {
        Date wtoday = new Date();
        logger.error("SE DEJA QUEMADO EL USUARIO sysadb PARA PRUEBAS DE MENU");
        return sausuarioReposiory.getPermisosUsuarioRol(login,wtoday,wtoday,login,wtoday,wtoday);
    }
    

}

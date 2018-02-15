package co.org.profamilia.transversal.service;

import co.org.profamilia.transversal.persist.entities.Sarolsusua;
import co.org.profamilia.transversal.persist.entities.Sausuario;
import co.org.profamilia.transversal.persist.repository.SausuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetailsService implements UserDetailsService {

    protected final static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    
    CrudRepository<Sausuario, String> sausuarioReposiory;

    public CustomUserDetailsService(CrudRepository<Sausuario, String> sausuarioReposiory) {
        this.sausuarioReposiory = sausuarioReposiory;
    }
    
    public CrudRepository<Sausuario, String> getSausuarioReposiory() {
        return sausuarioReposiory;
    }

    public void setSausuarioReposiory(CrudRepository<Sausuario, String> sausuarioReposiory) {
        this.sausuarioReposiory = sausuarioReposiory;
    }
    
    private User createUser(final Sausuario usuario) {
        return new User(
                usuario.getSusclogin(),
                "---",
                true,// estado
                true,
                true,
                true,
                getGrantedAuthorities(usuario));
    }

    private List<GrantedAuthority> getGrantedAuthorities(final Sausuario usuario) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_ALLACCESS"));

        if (usuario.getSarolsusuaCollection() != null) {
            for (Sarolsusua rol : usuario.getSarolsusuaCollection()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getSarolsusuaPK().getSrucrol().toUpperCase()));

            }
        }
        return authorities;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        Sausuario usuario = null;
        try {
            usuario = sausuarioReposiory.findOne(username);            
        } catch (Exception ex) {
            logger.error("Error consultando usuario en login", ex);
        }
        if (usuario == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return createUser(usuario);

    }


}

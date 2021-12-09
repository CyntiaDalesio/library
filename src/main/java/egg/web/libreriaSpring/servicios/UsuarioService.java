
package egg.web.libreriaSpring.servicios;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Usuario;
import egg.web.libreriaSpring.enums.Role;
import egg.web.libreriaSpring.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService{

    
    @Autowired
    private UsuarioRepositorio userRepo;
    
    
    @Transactional
    public Usuario save(String username, String password, String password2) throws ErrorServicio{
    
    Usuario usuario= new Usuario();
        
        
    
        if (username==null || username.isEmpty()) {
            throw new ErrorServicio("El username no puede ser nulo");
        }
    
      if (password==null || password.isEmpty() || password2==null || password2.isEmpty() ) {
            throw new ErrorServicio("El password no puede ser nulo");
        }
      
      
        if (!password.equals(password2)) {
                        throw new ErrorServicio("Los password no coinciden");

        }
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(password)); // encripta la contraseña
        usuario.setUsername(username);
     usuario.setRole(Role.USER);
        
        
        
    return userRepo.save(usuario);
     
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        try {
                    Usuario usuario= userRepo.findByUsername(username);
                    User user;
                    
                   List<GrantedAuthority> authorities= new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+usuario.getRole()));
                    
                    return  new User(username, usuario.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
        
    }
    

}
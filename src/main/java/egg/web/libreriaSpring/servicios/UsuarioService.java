
package egg.web.libreriaSpring.servicios;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Usuario;
import egg.web.libreriaSpring.repositorios.UsuarioRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    
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
      
        usuario.setPassword(password);
        usuario.setUsername(username);
        
        
        
    return userRepo.save(usuario);
     
}

}
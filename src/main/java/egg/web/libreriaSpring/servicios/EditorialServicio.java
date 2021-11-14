package egg.web.libreriaSpring.servicios;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Editorial;
import egg.web.libreriaSpring.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio edRepo;

    @Transactional

    public void GuardarEditorial(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {

            throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
        }

        Editorial editorial = new Editorial(nombre);
        edRepo.save(editorial);
    }

    @Transactional

    public void modificarEditorial(String nombre, String id) throws ErrorServicio {
        Optional<Editorial> respuesta = edRepo.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();

            if (nombre == null || nombre.isEmpty()) {

                throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
            }
            editorial.setNombre(nombre);
            edRepo.save(editorial);

        } else {

            throw new ErrorServicio("No se encontró la editorial ingresada");
        }

    }

    @Transactional

    public void eliminarEditorial(String id) throws ErrorServicio {
        Optional<Editorial> respuesta = edRepo.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();

            editorial.setAlta(false);
            edRepo.save(editorial);

        } else {

            throw new ErrorServicio("No se encontró la editorial ingresada");
        }

    }
        
    public List<Editorial> listarEditorial(){
    
    
    return edRepo.findByAltaTrue();
    
    }
    
        public Editorial buscarEditorialID(String id) {
        Optional<Editorial> respuesta = edRepo.findById(id);
        Editorial editorial = respuesta.get();

        return editorial;

    }
    
    
}

package egg.web.libreriaSpring.servicios;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Autor;
import egg.web.libreriaSpring.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepo;

    @Transactional
    public void GuardarAutor(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {

            throw new ErrorServicio("El nombre del autor no puede ser nulo");
        }

        Autor autor = new Autor(nombre);
        autorRepo.save(autor);
    }

    public List<Autor> listarAutor() {

        //  return autorRepo.findAll();
        return autorRepo.findByAltaTrue();
    }

    public Autor buscarAutorID(String id) {
        Optional<Autor> respuesta = autorRepo.findById(id);
        Autor autor = respuesta.get();

        return autor;

    }

    @Transactional

    public void modificarAutor(String id, String nombre) throws ErrorServicio {
        if (id == null || id.isEmpty()) {

            throw new ErrorServicio("El id del autor no puede ser nulo");
        }
        if (nombre == null || nombre.isEmpty()) {

            throw new ErrorServicio("El nombre del autor no puede ser nulo");
        }

        Optional<Autor> respuesta = autorRepo.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            autor.setNombre(nombre);
            autorRepo.save(autor);

        } else {

            throw new ErrorServicio("No se encontró el autor ingresado");
        }

    }

    @Transactional
    public void EliminarAutor(String id) throws ErrorServicio {
        Optional<Autor> respuesta = autorRepo.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            if (id == null || id.isEmpty()) {

                throw new ErrorServicio("El nombre del Autor no puede ser nulo");
            }
            autor.setAlta(false);
            autorRepo.save(autor);

        } else {

            throw new ErrorServicio("No se encontró el Autor ingresado");
        }

    }

}

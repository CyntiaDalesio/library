package egg.web.libreriaSpring.servicios;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Autor;
import egg.web.libreriaSpring.entidades.Editorial;
import egg.web.libreriaSpring.entidades.Libro;
import egg.web.libreriaSpring.repositorios.AutorRepositorio;
import egg.web.libreriaSpring.repositorios.EditorialRepositorio;
import egg.web.libreriaSpring.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepo;
    @Autowired
    private EditorialRepositorio edRepo;
    @Autowired
    private AutorRepositorio autorRepo;

    @Transactional

    public void guardarLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, String autorId, String editorialId) throws ErrorServicio {

        if (autorId.equals(null)) {

            throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
        }
        if (editorialId.equals(null)) {

            throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
        }

        Optional<Editorial> respuesta = edRepo.findById(editorialId);
        if (!respuesta.isPresent()) {

            throw new ErrorServicio("No se encontró la editorial");
        } else {
            Optional<Autor> resp = autorRepo.findById(autorId);
            if (!resp.isPresent()) {

                throw new ErrorServicio("No se encontró el autor");
            } else {

                Autor autor = resp.get();
                Editorial editorial = respuesta.get();
                Libro libro = new Libro();

                if (titulo == null || titulo.isEmpty()) {

                    throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
                }
                if (isbn == null || isbn.equals(null)) {

                    throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
                }

                if (anio == null) {

                    throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
                }

                if (ejemplares == null) {

                    throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
                }

                libro.setAnio(anio);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEjemplares(ejemplares);
                libro.setEjemplaresPrestados(0);
                libro.setEjemplaresRestantes(ejemplares);
                libro.setTitulo(titulo);
                libro.setIsbn(isbn);
                libroRepo.save(libro);

            }
        }

    }

    @Transactional

    public void modificarLibro(String id,Long isbn, String titulo, Integer anio, Integer ejemplares, String autorId, String editorialId) throws ErrorServicio {
    if (id == null || id.isEmpty()) {

            throw new ErrorServicio("El id del autor no puede ser nulo");}
        if (titulo == null || titulo.isEmpty()) {

            throw new ErrorServicio("El nombre del libro no puede ser nulo");
        }
        if (isbn == null || isbn.equals(null)) {

            throw new ErrorServicio("El isbn no puede ser nulo");
        }

        if (anio == null) {

            throw new ErrorServicio("El año  no puede ser nulo");
        }

        if (ejemplares == null) {

            throw new ErrorServicio("El numero de ejemplares  no puede ser nulo");
        }
        if (autorId.equals(null)) {

            throw new ErrorServicio("El nombre del autor no puede ser nulo");
        }
        if (editorialId.equals(null)) {

            throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
        }

        Optional<Editorial> respuesta = edRepo.findById(editorialId);
        if (!respuesta.isPresent()) {

            throw new ErrorServicio("No se encontró la editorial");
        } else {
            Optional<Autor> resp = autorRepo.findById(autorId);
            if (!resp.isPresent()) {

                throw new ErrorServicio("No se encontró la autor");
            } else {
                Autor autor=resp.get();
                Editorial editorial= respuesta.get();
                
                
                   Optional<Libro> res = libroRepo.findById(id);
        if (res.isPresent()) {
            Libro libro = res.get();

    


                    libro.setAnio(anio);
                    libro.setAutor(autor);
                    libro.setEditorial(editorial);
                    libro.setEjemplares(ejemplares);
                    libro.setEjemplaresPrestados(0);
                    libro.setEjemplaresRestantes(ejemplares);
                    libro.setTitulo(titulo);
                    libro.setIsbn(isbn);
                    libroRepo.save(libro);
                }

            }
        }

    }

    @Transactional

    public void eliminarLibro(String id) throws ErrorServicio {

        if (id == null || id.isEmpty()) {

            throw new ErrorServicio("El nombre del libro no puede ser nulo");
        }

        Optional<Libro> respuesta = libroRepo.findById(id);
        if (!respuesta.isPresent()) {

            throw new ErrorServicio("no se encontró libro alguno");
        } else {

            Libro libro = respuesta.get();

            libro.setAlta(false);
            libroRepo.save(libro);
        }

    }
    
      public List<Libro> listarLibro(){
    
    
    return libroRepo.findByAltaTrue();
    }
      
      
          public Libro buscarLibroID(String id) {
        Optional<Libro> respuesta = libroRepo.findById(id);
        Libro libro = respuesta.get();

        return libro;

    }
}

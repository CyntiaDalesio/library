package egg.web.libreriaSpring.servicios;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Cliente;
import egg.web.libreriaSpring.entidades.Libro;
import egg.web.libreriaSpring.entidades.Prestamo;
import egg.web.libreriaSpring.repositorios.ClienteRepositorio;
import egg.web.libreriaSpring.repositorios.LibroRepositorio;
import egg.web.libreriaSpring.repositorios.PrestamoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoServicio {

    @Autowired
    PrestamoRepositorio prestaRepo;
    @Autowired
    LibroRepositorio libroRepo;

    @Autowired
    ClienteRepositorio cliRepo;

    @Transactional
    public void crearPrestamo(String id, Date fechaDevolucion, Date fechaPrestamo, String idCliente) throws ErrorServicio {

        System.out.println("id cliente: " + idCliente);
        if (id == null || id.isEmpty()) {

            throw new ErrorServicio("El id del libro no puede ser nulo");
        }

        if (fechaPrestamo == null) {

            throw new ErrorServicio("El fechaPrestamo no puede ser nulo");
        }

        if (idCliente == null) {

            throw new ErrorServicio("El cliente  no puede ser nulo");
        }

        Optional<Libro> respuesta = libroRepo.findById(id);

        Optional<Cliente> resp = cliRepo.findById(idCliente);

        if (!(respuesta.isPresent())) {
            throw new ErrorServicio("No se encontró el libro");
        } else if (!resp.isPresent()) {
            System.out.println("imprimo resp: " + resp.get().getApellido());
            throw new ErrorServicio("No se encontró el cliente");
        } else {

            Libro libro = respuesta.get();
            Cliente cliente = resp.get();
            Prestamo prestamo = new Prestamo();

            prestamo.setCliente(cliente);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechadevolucion(fechaDevolucion);
            prestamo.setLibro(libro);
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
            prestaRepo.save(prestamo);
        }

    }

    public List<Prestamo> listarPrestamo() {

        List<Prestamo> prestamos = prestaRepo.findByAltaTrue();
        return prestamos;

    }

    @Transactional
    public void eliminarPrestamo(String id) throws ErrorServicio {

        Optional<Prestamo> respuesta = prestaRepo.findById(id);

        if (!respuesta.isPresent()) {

            throw new ErrorServicio("No se encontró prestamo");

        } else {

            Prestamo prestamo = respuesta.get();

            prestamo.setAlta(false);
            prestaRepo.save(prestamo);
        }

    }

    public Prestamo buscarPrestamoId(String id) throws ErrorServicio {

        Optional<Prestamo> respuesta = prestaRepo.findById(id);

        if (!respuesta.isPresent()) {

            throw new ErrorServicio("No se encontró prestamo");

        } else {

            Prestamo prestamo = respuesta.get();
            return prestamo;
        }
    }
@Transactional
    public void devolverLibro(String id, Date fechaDevolucion) throws ErrorServicio {

        Optional<Prestamo> respuesta = prestaRepo.findById(id);

        if (!respuesta.isPresent()) {

            throw new ErrorServicio("No se encontró prestamo");

        } else {

            Prestamo prestamo = respuesta.get();

            prestamo.setFechadevolucion(fechaDevolucion);
            prestaRepo.save(prestamo);
        }

    }
}

package egg.web.libreriaSpring.controladores;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Cliente;
import egg.web.libreriaSpring.entidades.Libro;
import egg.web.libreriaSpring.entidades.Prestamo;
import egg.web.libreriaSpring.servicios.ClienteService;
import egg.web.libreriaSpring.servicios.LibroServicio;
import egg.web.libreriaSpring.servicios.PrestamoServicio;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrestamoController {

    @Autowired
    private ClienteService cliSer;
    @Autowired
    private LibroServicio libroserv;
    @Autowired
    private PrestamoServicio prestamoSer;

    @GetMapping("/prestamos")
    public String index(ModelMap model) {

        List<Libro> libros = libroserv.listarLibro();
        model.put("libros", libros);

        return "prestamos/index.html";
    }

    @GetMapping("/prestamoDevolucion/prestamo/{id}")
    public String prestar(@PathVariable String id, ModelMap modelo) {
        Libro libro = libroserv.buscarLibroID(id);
        List<Cliente> clientes = cliSer.listarCliente();
        modelo.put("libro", libro);
        modelo.put("clientes", clientes);
        return "prestamos/nuevo.html";
    }

//    @RequestMapping(value = "/prestamos/{id}", method = RequestMethod.POST)
    @PostMapping("/prestamos/{id}")
    public String crear(@PathVariable String id, @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDevolucion,@DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaPrestamo, @RequestParam String idCliente) throws ErrorServicio {
        try {

            System.out.println("imprimo de prueba");
            prestamoSer.crearPrestamo(id, fechaDevolucion, fechaPrestamo, idCliente);

        } catch (ErrorServicio ex) {

            System.out.println(ex.getLocalizedMessage());
            System.out.println("Error en el crear del controller");
        }

        return "redirect:/prestamos";
    }

    @GetMapping("prestamoDevolucion/devolucion/{id}")
    public String devolverPrestamo(ModelMap modelo,@PathVariable String id ) throws ErrorServicio{
    
    Prestamo prestamo= prestamoSer.buscarPrestamoId(id);
    modelo.put("prestamo", prestamo);
    return "prestamos/devolver.html";
    }
    
    
    @PostMapping("/prestamoDevolucion/devolucion/{id}")
    public String devolver(@PathVariable String id,@DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDevolucion ) throws ErrorServicio {
        
        prestamoSer.devolverLibro(id, fechaDevolucion);
        
        return "redirect:/prestamos/listar";
    }
    
    
    
    

    @GetMapping("/prestamos/listar")
    public String listarPrestamo(ModelMap model) {

        List<Prestamo> prestamos = prestamoSer.listarPrestamo();
        model.put("prestamos", prestamos);

        return "prestamos/listar.html";
    }
    
    
        @GetMapping("/prestamoDevolucion/eliminar/{id}")
        public String eliminar(@PathVariable String id) throws ErrorServicio{
        
            prestamoSer.eliminarPrestamo(id);
            
            
        return "redirect:/prestamos/listar";
        
        }
    

}

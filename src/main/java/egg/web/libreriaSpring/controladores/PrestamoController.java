package egg.web.libreriaSpring.controladores;

import egg.web.libreriaSpring.entidades.Libro;
import egg.web.libreriaSpring.servicios.LibroServicio;
import egg.web.libreriaSpring.servicios.PrestamoServicio;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PrestamoController {

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
    public String prestar(@PathVariable String id, ModelMap modelo){
    
    
    return "redirect:/prestamos";
    }
    
     @GetMapping("/prestamoDevolucion/devolucion/{id}")
    public String devolver(@PathVariable String id, ModelMap modelo){
    
    
    return "redirect:/prestamos";
    }
    
    
    
    
    
    
    
    

    
    
    
    
    
    
}

package egg.web.libreriaSpring.controladores;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Autor;
import egg.web.libreriaSpring.entidades.Editorial;
import egg.web.libreriaSpring.entidades.Libro;
import egg.web.libreriaSpring.servicios.AutorServicio;
import egg.web.libreriaSpring.servicios.EditorialServicio;
import egg.web.libreriaSpring.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibroController {

    @Autowired
    private LibroServicio libroserv;
    @Autowired
    private AutorServicio autorserv;
    @Autowired
    private EditorialServicio edservi;

    @GetMapping("/libros")
    public String index(ModelMap model) {

        List<Libro> libros = libroserv.listarLibro();
        model.put("libros", libros);

        return "libros/index.html";
    }

    @GetMapping("/libros/nuevo")
    public String nuevo(ModelMap modelo, ModelMap model) {
// con un solo modelmap me bastaria.
        List<Autor> autores = autorserv.listarAutor();
        modelo.put("autores", autores);

        List<Editorial> editoriales = edservi.listarEditorial();
        model.put("editoriales", editoriales);

        return "libros/nuevo.html";

    }

    // tengo que listar autores 
    
//completar con los campos que me van a enviar 
    @PostMapping("/libros")
    public String crear(@RequestParam Long isbn,@RequestParam String titulo,@RequestParam Integer anio,@RequestParam Integer ejemplares, @RequestParam String idAutor,@RequestParam String idEditorial  ) {
        try {
            libroserv.guardarLibro(isbn, titulo, anio, ejemplares, idAutor, idEditorial);
        } catch (ErrorServicio ex) {
            
        }
        return "redirect:/libros";
    }
    
    
    @GetMapping("/libros/eliminar/{id}")
    public String eliminar(@PathVariable String id) throws ErrorServicio {

        libroserv.eliminarLibro(id);

        return "redirect:/libros";
    }
    
    //---------------------------------------------
    
    
    
     @GetMapping("/libros/editar/{id}")
    public String editar(@PathVariable String id, ModelMap model) throws ErrorServicio {

        Libro libro = libroserv.buscarLibroID(id);
         List<Autor> autores = autorserv.listarAutor();
        model.put("autores", autores);

        List<Editorial> editoriales = edservi.listarEditorial();
        model.put("editoriales", editoriales);

        model.put("libro", libro);

        return "libros/editar.html";
    }

    @PostMapping("/libros/actualizar/{id}")
    public String actualizar(@PathVariable String id,@RequestParam Long isbn,@RequestParam String titulo,@RequestParam Integer anio,@RequestParam Integer ejemplares, @RequestParam String idAutor,@RequestParam String idEditorial) throws ErrorServicio {
        
        libroserv.modificarLibro(id,isbn, titulo, anio, ejemplares, idAutor, idEditorial);

        return "redirect:/libros";
    }
    //-------------------------------------------
    
}

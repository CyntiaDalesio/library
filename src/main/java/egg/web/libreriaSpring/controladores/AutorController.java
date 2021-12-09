package egg.web.libreriaSpring.controladores;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Autor;
import egg.web.libreriaSpring.servicios.AutorServicio;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutorController {

    @Autowired
    private AutorServicio servi;

    @GetMapping("/autores")
    public String index(ModelMap model) {

        List<Autor> autores = servi.listarAutor();
        model.put("autores", autores);

        return "autores/index";
    }

    @GetMapping("/autores/nuevo")
    public String nuevo() {
        return "autores/nuevo.html";
    }

    @PostMapping("/autores")
    public String crear(@RequestParam String name) {
        try {
            servi.GuardarAutor(name);
        } catch (ErrorServicio ex) {
            Logger.getLogger(AutorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/autores";
    }
//////----------------------------------------------------------------------------------------
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/autores/editar/{id}")
    public String editar(@PathVariable String id, ModelMap model) throws ErrorServicio {

        Autor autor = servi.buscarAutorID(id);

        model.put("autor", autor);

        return "autores/editar.html";
    }

    @PostMapping("autores/actualizar/{id}")
    public String actualizar(@PathVariable String id, @RequestParam String name) throws ErrorServicio {
        servi.modificarAutor(id, name);

        return "redirect:/autores";
    }

///////-------------------------------------------------------------------------------------
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/autores/eliminar/{id}")
    public String eliminar(@PathVariable String id) throws ErrorServicio {

        servi.EliminarAutor(id);

        return "redirect:/autores";
    }

}

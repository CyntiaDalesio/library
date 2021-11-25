package egg.web.libreriaSpring.controladores;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Editorial;
import egg.web.libreriaSpring.servicios.EditorialServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditorialController {

    @Autowired
    private EditorialServicio edservi;

    @GetMapping("/editoriales")
    public String index(ModelMap model) {

        List<Editorial> editoriales = edservi.listarEditorial();
        model.put("editoriales", editoriales);

        return "editoriales/index.html";
    }

    @GetMapping("/editoriales/nuevo")
    public String nuevo() {
        return "editoriales/nuevo.html";
    }

    @PostMapping("/editoriales")
    public String crear(@RequestParam String name) {
        try {
            edservi.GuardarEditorial(name);
        } catch (ErrorServicio ex) {
            Logger.getLogger(EditorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/editoriales";
    }

    @GetMapping("/editoriales/eliminar/{id}")
    public String eliminar(@PathVariable String id) throws ErrorServicio {

        edservi.eliminarEditorial(id);

        return "redirect:/editoriales";
    }

    //---------------------------------------------
    @GetMapping("/editoriales/editar/{id}")
    public String editar(@PathVariable String id, ModelMap model) throws ErrorServicio {

        Editorial editorial = edservi.buscarEditorialID(id);

        model.put("editorial", editorial);

        return "editoriales/editar.html";
    }

    @PostMapping("editoriales/actualizar/{id}")
    public String actualizar(@PathVariable String id, @RequestParam String name) throws ErrorServicio {

        edservi.modificarEditorial(name, id);

        return "redirect:/editoriales";
    }

    //-------------------------------------------------
}

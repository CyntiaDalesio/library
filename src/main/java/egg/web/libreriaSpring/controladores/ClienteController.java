
package egg.web.libreriaSpring.controladores;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Cliente;
import egg.web.libreriaSpring.servicios.ClienteService;
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
public class ClienteController {

    
    @Autowired
    private ClienteService cliservi;
    
    @GetMapping("/clientes")
    public String index(ModelMap model) {

        List<Cliente> clientes = cliservi.listarCliente();
        model.put("clientes", clientes);

        return "clientes/index";
    }
    
     @GetMapping("/clientes/nuevo")
    public String nuevo() {
        return "clientes/nuevo.html";
    }
    
      @PostMapping("/clientes")
    public String crear(@RequestParam String name, @RequestParam String telefono, @RequestParam String apellido, @RequestParam Long documento) {
        try {
            cliservi.guardarCliente(name,documento, apellido, telefono);
        } catch (ErrorServicio ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/clientes";
    }
    
    
    @GetMapping("/clientes/editar/{id}")
    public String editar(@PathVariable String id, ModelMap model) throws ErrorServicio {

        Cliente cliente = cliservi.buscarClienteID(id);

        model.put("cliente", cliente);

        return "clientes/editar.html";
    }
     @PostMapping("clientes/actualizar/{id}")
    public String actualizar(@PathVariable String id, @RequestParam String name, @RequestParam String telefono, @RequestParam String apellido, @RequestParam Long documento) throws ErrorServicio {
        cliservi.modificarCliente(id,name,documento, apellido, telefono);

        return "redirect:/clientes";
    }
      @GetMapping("/clientes/eliminar/{id}")
    public String eliminar(@PathVariable String id) throws ErrorServicio {

        cliservi.EliminarCliente(id);

        return "redirect:/clientes";
    }
}

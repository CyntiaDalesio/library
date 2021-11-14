package egg.web.libreriaSpring.servicios;

import egg.web.libreriaSpring.Errores.ErrorServicio;
import egg.web.libreriaSpring.entidades.Cliente;
import egg.web.libreriaSpring.repositorios.ClienteRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepositorio cliRepo;
    
    @Transactional
    public void guardarCliente(String nombre, Long documento, String apellido, String telefono) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            
            throw new ErrorServicio("El nombre del cliente no puede ser nulo");
        }
        if (documento == null || nombre.isEmpty()) {
            
            throw new ErrorServicio("El documento del cliente no puede ser nulo");
        }
        
        if (apellido == null || nombre.isEmpty()) {
            
            throw new ErrorServicio("El apellido del cliente no puede ser nulo");
        }
        
        if (telefono == null || nombre.isEmpty()) {
            
            throw new ErrorServicio("El telefono del cliente no puede ser nulo");
        }
        
        Cliente cliente = new Cliente(documento, nombre, apellido, telefono);
        cliRepo.save(cliente);
    }
    
    public List<Cliente> listarCliente() {

        //  return autorRepo.findAll();
        return cliRepo.findByAltaTrue();
    }
    
    public Cliente buscarClienteID(String id) {
        Optional<Cliente> respuesta = cliRepo.findById(id);
        Cliente cliente = respuesta.get();
        
        return cliente;
        
    }
    
    @Transactional
    
    public void modificarCliente(String id, String nombre, Long documento, String apellido, String telefono) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            
            throw new ErrorServicio("El nombre del cliente no puede ser nulo");
        }
        if (documento == null || nombre.isEmpty()) {
            
            throw new ErrorServicio("El documento del cliente no puede ser nulo");
        }
        
        if (apellido == null || nombre.isEmpty()) {
            
            throw new ErrorServicio("El apellido del cliente no puede ser nulo");
        }
        
        if (telefono == null || nombre.isEmpty()) {
            
            throw new ErrorServicio("El telefono del cliente no puede ser nulo");
        }
        
        Optional<Cliente> respuesta = cliRepo.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            cliente.setDocumento(documento);
            cliRepo.save(cliente);
            
        } else {
            
            throw new ErrorServicio("No se encontró el autor ingresado");
        }
        
    }
    
    @Transactional
    public void EliminarCliente(String id) throws ErrorServicio {
        Optional<Cliente> respuesta = cliRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            
            if (id == null || id.isEmpty()) {
                
                throw new ErrorServicio("El nombre del cliente no puede ser nulo");
            }
            cliente.setAlta(false);
            cliRepo.save(cliente);
            
        } else {
            
            throw new ErrorServicio("No se encontró el cliente ingresado");
        }
        
    }
    
}

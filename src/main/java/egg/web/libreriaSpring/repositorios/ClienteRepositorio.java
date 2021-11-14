package egg.web.libreriaSpring.repositorios;

import egg.web.libreriaSpring.entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String> {

    public List<Cliente> findByAltaTrue();

    public List<Cliente> findByNombre(String nombre);

}

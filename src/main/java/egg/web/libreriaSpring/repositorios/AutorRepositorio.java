
package egg.web.libreriaSpring.repositorios;

import egg.web.libreriaSpring.entidades.Autor;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface AutorRepositorio extends JpaRepository<Autor,String>{

//    @Query("SELECT c FROM Autor c WHERE c.nombre = :nombre")
// List<Autor> buscarNombreAutor(@Param("nombre") String nombre);
    public List<Autor> findByNombre(String nombre);
    public List<Autor> findByAltaTrue();
    
}

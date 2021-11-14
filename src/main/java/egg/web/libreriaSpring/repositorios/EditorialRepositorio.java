
package egg.web.libreriaSpring.repositorios;

import egg.web.libreriaSpring.entidades.Editorial;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial,String>{
    
//    @Query("SELECT c FROM Editorial c WHERE c.nombre=:nombre")
//    public List<Editorial> buscarNombreEditorial0(@Param ("nombre") String nombre);
//    
 public List<Editorial> findByAltaTrue();
 
   public List<Editorial> findByNombre(String nombre);
 
 
}

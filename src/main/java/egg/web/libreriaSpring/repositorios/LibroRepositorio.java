
package egg.web.libreriaSpring.repositorios;

import egg.web.libreriaSpring.entidades.Libro;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,String>{

//    @Query("SELECT c FROM Libro c WHERE c.ISBN LIKE :ISBN")
//    public List<Libro> buscarPorISBN(@Param ("ISBN")Long ISBN);
//    
//     @Query("SELECT c FROM Autor c WHERE c.nombre LIKE :nombre")
//    public List<Libro> buscarPorAutor(@Param ("nombre")String nombre);
//    
//    
//     @Query("SELECT c FROM Editorial c WHERE c.nombre LIKE :nombre")
//    public List<Libro> buscarPorEditorial(@Param ("nombre")String nombre);
//    
//    
    
  
    public List<Libro> findByAltaTrue();
//    
}

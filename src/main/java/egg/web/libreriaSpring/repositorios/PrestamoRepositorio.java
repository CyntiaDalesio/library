
package egg.web.libreriaSpring.repositorios;


import egg.web.libreriaSpring.entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo,String>{

}

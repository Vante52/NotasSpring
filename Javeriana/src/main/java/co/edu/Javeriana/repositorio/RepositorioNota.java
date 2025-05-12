package co.edu.Javeriana.repositorio;

import co.edu.Javeriana.modelo.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepositorioNota extends JpaRepository<Nota, Long> {
    List<Nota> findByEstudianteId(Long estudianteId);

    List<Nota> findByEstudianteIdAndMateria(Long estudianteId, String materia);
}
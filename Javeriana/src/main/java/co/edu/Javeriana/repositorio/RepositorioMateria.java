package co.edu.Javeriana.repositorio;

import co.edu.Javeriana.modelo.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositorioMateria extends JpaRepository<Materia, Long> {
    Optional<Materia> findByNombre(String nombre);
    List<Materia> findDistinctByNotas_Estudiante_Id(Long estudianteId);
}
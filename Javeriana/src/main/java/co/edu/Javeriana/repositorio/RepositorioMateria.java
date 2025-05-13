package co.edu.Javeriana.repositorio;

import co.edu.Javeriana.modelo.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioMateria extends JpaRepository<Materia, Long> {
    Optional<Materia> findByNombre(String nombre);
}
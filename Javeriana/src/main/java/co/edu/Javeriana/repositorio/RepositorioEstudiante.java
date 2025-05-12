package co.edu.Javeriana.repositorio;

import co.edu.Javeriana.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEstudiante extends JpaRepository<Estudiante, Long> {}
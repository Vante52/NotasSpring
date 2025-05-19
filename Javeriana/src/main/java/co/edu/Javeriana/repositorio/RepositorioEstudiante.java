package co.edu.Javeriana.repositorio;


import co.edu.Javeriana.modelo.Estudiante;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEstudiante extends ReactiveCrudRepository<Estudiante, Long> {}

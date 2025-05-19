package co.edu.Javeriana.repositorio;

import co.edu.Javeriana.modelo.Materia;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.util.Optional;

@Repository
public interface RepositorioMateria extends ReactiveCrudRepository<Materia, Long> {
    Flux<Materia> findByNombre(String nombre);
}

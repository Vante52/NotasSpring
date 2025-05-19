package co.edu.Javeriana.repositorio;

import co.edu.Javeriana.modelo.Nota;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RepositorioNota extends ReactiveCrudRepository<Nota, Long> {
    Flux<Nota> findByEstudianteId(Long estudianteId);
    Flux<Nota> findByEstudianteIdAndMateriaId(Long estudianteId, Long materiaId);
}

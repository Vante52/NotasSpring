package co.edu.Javeriana.controlador;

import co.edu.Javeriana.modelo.Estudiante;
import co.edu.Javeriana.repositorio.RepositorioEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private RepositorioEstudiante repo;

    @GetMapping
    public Flux<Estudiante> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Estudiante> obtener(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping
    public Mono<Estudiante> guardar(@RequestBody Estudiante estudiante) {
        return repo.save(estudiante);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminar(@PathVariable Long id) {
        return repo.deleteById(id);
    }
}

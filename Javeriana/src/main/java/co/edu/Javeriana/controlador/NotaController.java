package co.edu.Javeriana.controlador;

import co.edu.Javeriana.modelo.Materia;
import co.edu.Javeriana.modelo.Nota;
import co.edu.Javeriana.repositorio.RepositorioEstudiante;
import co.edu.Javeriana.repositorio.RepositorioMateria;
import co.edu.Javeriana.repositorio.RepositorioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private RepositorioNota notaRepo;

    @Autowired
    private RepositorioEstudiante estudianteRepo;

    @Autowired
    private RepositorioMateria materiaRepo;

    @GetMapping("/estudiante/{estudianteId}")
    public Flux<Nota> listarPorEstudiante(@PathVariable Long estudianteId) {
        return notaRepo.findByEstudianteId(estudianteId);
    }

    @PostMapping
    public Mono<Nota> guardarNota(@RequestBody Nota nota) {
        return materiaRepo.findById(nota.getMateriaId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Materia no encontrada")))
                .flatMap(materia -> notaRepo.save(nota));
    }

    @GetMapping("/{id}")
    public Mono<Nota> obtenerNota(@PathVariable Long id) {
        return notaRepo.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarNota(@PathVariable Long id) {
        return notaRepo.deleteById(id);
    }
}

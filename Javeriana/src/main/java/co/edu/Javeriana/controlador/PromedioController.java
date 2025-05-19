package co.edu.Javeriana.controlador;

import co.edu.Javeriana.modelo.Materia;
import co.edu.Javeriana.modelo.Nota;
import co.edu.Javeriana.repositorio.RepositorioEstudiante;
import co.edu.Javeriana.repositorio.RepositorioMateria;
import co.edu.Javeriana.repositorio.RepositorioNota;
import co.edu.Javeriana.servicio.ServicioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/promedios")
public class PromedioController {

    @Autowired
    private RepositorioNota notaRepo;

    @Autowired
    private RepositorioEstudiante estudianteRepo;

    @Autowired
    private RepositorioMateria materiaRepo;

    @Autowired
    private ServicioNota servicioNota;

    @GetMapping("/estudiante/{estudianteId}/materia/{materiaId}")
    public Mono<Double> promedioPorMateria(
            @PathVariable Long estudianteId,
            @PathVariable Long materiaId) {

        return servicioNota.calcularPromedioPorMateria(estudianteId, materiaId);
    }

    @GetMapping("/estudiante/{estudianteId}/materias")
    public Flux<Materia> listarMateriasEstudiante(@PathVariable Long estudianteId) {
        return notaRepo.findByEstudianteId(estudianteId)
                .map(Nota::getMateriaId)
                .distinct()
                .flatMap(materiaRepo::findById);
    }

    @GetMapping("/estudiante/{estudianteId}/materia/{materiaId}/notas")
    public Flux<Nota> listarNotasDeMateria(
            @PathVariable Long estudianteId,
            @PathVariable Long materiaId) {

        return notaRepo.findByEstudianteIdAndMateriaId(estudianteId, materiaId);
    }
}

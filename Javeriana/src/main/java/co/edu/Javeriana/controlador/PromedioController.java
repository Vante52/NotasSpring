package co.edu.Javeriana.controlador;

import co.edu.Javeriana.modelo.Estudiante;
import co.edu.Javeriana.modelo.Materia;
import co.edu.Javeriana.modelo.Nota;
import co.edu.Javeriana.repositorio.RepositorioEstudiante;
import co.edu.Javeriana.repositorio.RepositorioMateria;
import co.edu.Javeriana.repositorio.RepositorioNota;
import co.edu.Javeriana.servicio.ServicioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notas")
public class PromedioController {

    @Autowired
    private RepositorioNota notaRepo;

    @Autowired
    private RepositorioEstudiante estudianteRepo;

    @Autowired
    private RepositorioMateria materiaRepo;

    @Autowired
    private ServicioNota servicioNota;

    @GetMapping("/{estudianteId}/materias")
    public String listarMateriasPorEstudiante(@PathVariable Long estudianteId, Model model) {
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + estudianteId));

        List<Materia> materias = materiaRepo.findDistinctByNotas_Estudiante_Id(estudianteId);

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("materias", materias);
        return "materias-estudiante";
    }

    @GetMapping("/{estudianteId}/materia/{materiaId}")
    public String listarNotasPorMateria(@PathVariable Long estudianteId,
                                        @PathVariable Long materiaId,
                                        Model model) {
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + estudianteId));

        Materia materia = materiaRepo.findById(materiaId)
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada con ID: " + materiaId));

        List<Nota> notas = notaRepo.findByEstudianteIdAndMateriaId(estudianteId, materiaId);

        double promedio = servicioNota.calcularPromedioPorMateria(estudianteId, materia.getNombre());

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("materia", materia);
        model.addAttribute("notas", notas);
        model.addAttribute("promedio", promedio);
        return "notas";
    }

    @GetMapping("/{estudianteId}/materia/{materiaId}/nueva")
    public String nuevaNotaPorMateria(@PathVariable Long estudianteId,
                                      @PathVariable Long materiaId,
                                      Model model) {
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

        Materia materia = materiaRepo.findById(materiaId)
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));

        Nota nota = new Nota();
        nota.setEstudiante(estudiante);
        nota.setMateria(materia);

        model.addAttribute("nota", nota);
        model.addAttribute("materiaFija", true); // puedes usar esto en el formulario para bloquear el campo materia
        return "editar-nota";
    }
}

package co.edu.Javeriana.controlador;

import co.edu.Javeriana.modelo.Estudiante;
import co.edu.Javeriana.modelo.Materia;
import co.edu.Javeriana.modelo.Nota;
import co.edu.Javeriana.repositorio.RepositorioEstudiante;
import co.edu.Javeriana.repositorio.RepositorioMateria;
import co.edu.Javeriana.repositorio.RepositorioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("notas//promedios")
public class PromedioController {

    @Autowired
    private RepositorioNota notaRepo;

    @Autowired
    private RepositorioEstudiante estudianteRepo;

    @Autowired
    private RepositorioMateria materiaRepo;

    // Mostrar el formulario con el combo de materias
    @GetMapping("/{estudianteId}")
    public String mostrarFormulario(@PathVariable Long estudianteId, Model model) {
        Estudiante estudiante = estudianteRepo.findById(estudianteId).orElseThrow();
        List<Materia> materias = materiaRepo.findAll();

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("materias", materias);
        return "nota-promedio"; // corresponde al archivo HTML que me diste
    }

    // Calcular el promedio ponderado
    @PostMapping("/{estudianteId}/promedio")
    public String calcularPromedio(@PathVariable Long estudianteId,
                                   @RequestParam Long materiaId,
                                   Model model) {

        Estudiante estudiante = estudianteRepo.findById(estudianteId).orElseThrow();
        Materia materia = materiaRepo.findById(materiaId).orElseThrow();
        List<Materia> materias = materiaRepo.findAll();

        List<Nota> notas = notaRepo.findByEstudianteIdAndMateriaId(estudianteId, materiaId);

        double promedio = 0.0;
        for (Nota nota : notas) {
            promedio += (nota.getValor() * nota.getPorcentaje() / 100.0);
        }

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("materias", materias);
        model.addAttribute("materiaSeleccionada", materia);
        model.addAttribute("promedio", promedio);

        return "nota-promedio";
    }
}

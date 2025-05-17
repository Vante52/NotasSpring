package co.edu.Javeriana.controlador;

import co.edu.Javeriana.modelo.Estudiante;
import co.edu.Javeriana.modelo.Materia;
import co.edu.Javeriana.repositorio.RepositorioMateria;
import co.edu.Javeriana.repositorio.RepositorioNota;
import co.edu.Javeriana.servicio.ServicioNota;
import co.edu.Javeriana.modelo.Nota;
import co.edu.Javeriana.repositorio.RepositorioEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private RepositorioNota notaRepo;

    @Autowired
    private RepositorioEstudiante estudianteRepo;

    @Autowired
    private RepositorioMateria materiaRepo;

    @Autowired
    private ServicioNota servicioNota;

    @GetMapping("/{estudianteId}")
    public String listar(@PathVariable Long estudianteId, Model model) {
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + estudianteId));

        model.addAttribute("notas", notaRepo.findByEstudianteId(estudianteId));
        model.addAttribute("estudiante", estudiante);
        return "notas";
    }

    @GetMapping("/{estudianteId}/nueva")
    public String nueva(@PathVariable Long estudianteId, Model model) {
        Nota nota = new Nota();
        nota.setEstudiante(estudianteRepo.findById(estudianteId).orElseThrow());
        model.addAttribute("nota", nota);
        return "editar-nota";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Nota nota, @RequestParam String nombreMateria) {
        Materia materia = materiaRepo.findByNombre(nombreMateria)
                .orElseGet(() -> {
                    Materia nueva = new Materia();
                    nueva.setNombre(nombreMateria);
                    nueva.setCreditos(1);
                    return materiaRepo.save(nueva);
                });

        nota.setMateria(materia);
        notaRepo.save(nota);
        return "redirect:/notas/" + nota.getEstudiante().getId();
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("nota", notaRepo.findById(id).orElseThrow());
        return "editar-nota";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        Nota nota = notaRepo.findById(id).orElseThrow();
        Long estudianteId = nota.getEstudiante().getId();
        notaRepo.delete(nota);
        return "redirect:/notas/" + estudianteId;
    }

    @GetMapping("/{estudianteId}/promedio")
    public String mostrarFormularioPromedio(@PathVariable Long estudianteId, Model model) {
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + estudianteId));

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("materias", materiaRepo.findAll());
        model.addAttribute("promedio", null);
        return "nota-promedio";
    }

    @PostMapping("/promedio")
    public String calcularPromedio(@RequestParam Long estudianteId,
                                   @RequestParam Long materiaId,
                                   Model model) {
        Materia materia = materiaRepo.findById(materiaId)
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));

        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

        double promedio = servicioNota.calcularPromedioPorMateria(estudianteId, materia.getNombre());

        model.addAttribute("promedio", promedio);
        model.addAttribute("estudiante", estudiante);
        model.addAttribute("materiaSeleccionada", materia);
        model.addAttribute("materias", materiaRepo.findAll());
        return "nota-promedio";
    }


    public RepositorioNota getNotaRepo() {
        return notaRepo;
    }

    public void setNotaRepo(RepositorioNota notaRepo) {
        this.notaRepo = notaRepo;
    }

    public RepositorioEstudiante getEstudianteRepo() {
        return estudianteRepo;
    }

    public void setEstudianteRepo(RepositorioEstudiante estudianteRepo) {
        this.estudianteRepo = estudianteRepo;
    }

    public RepositorioMateria getMateriaRepo() {
        return materiaRepo;
    }

    public void setMateriaRepo(RepositorioMateria materiaRepo) {
        this.materiaRepo = materiaRepo;
    }

    public ServicioNota getServicioNota() {
        return servicioNota;
    }

    public void setServicioNota(ServicioNota servicioNota) {
        this.servicioNota = servicioNota;
    }
}
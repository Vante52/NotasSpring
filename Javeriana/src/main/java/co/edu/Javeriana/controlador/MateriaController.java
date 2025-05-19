package co.edu.Javeriana.controlador;

import co.edu.Javeriana.modelo.Materia;
import co.edu.Javeriana.repositorio.RepositorioMateria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private RepositorioMateria materiaRepo;

    @GetMapping("/nueva")
    public String nuevaMateria(@RequestParam Long estudianteId, Model model) {
        model.addAttribute("materia", new Materia());
        model.addAttribute("estudianteId", estudianteId);
        return "formulario-materia";
    }

    @PostMapping("/guardar")
    public String guardarMateria(@ModelAttribute Materia materia, @RequestParam("estudianteId") Long estudianteId) {
        materiaRepo.save(materia);
        return "redirect:/notas/" + estudianteId + "/materias";
    }
}
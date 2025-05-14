package co.edu.Javeriana.servicio;


import co.edu.Javeriana.repositorio.RepositorioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioNota {

    @Autowired
    private RepositorioNota repositorioNota;

    public List<co.edu.Javeriana.modelo.Nota> obtenerNotasPorEstudianteYMateria(Long estudianteId, String materia) {
        return repositorioNota.findByEstudianteIdAndMateriaNombre(estudianteId, materia);
    }
    public double calcularPromedioPorMateria(Long estudianteId, String materia) {
        List<co.edu.Javeriana.modelo.Nota> notas = obtenerNotasPorEstudianteYMateria(estudianteId, materia);
        double sumaPonderada = notas.stream()
                .mapToDouble(n -> n.getValor() * n.getPorcentaje())
                .sum();
        double totalPorcentaje = notas.stream()
                .mapToDouble(co.edu.Javeriana.modelo.Nota::getPorcentaje)
                .sum();
        return totalPorcentaje > 0 ? sumaPonderada / totalPorcentaje : null;
        //return notas.stream().mapToDouble((Nota::getValor)).average().orElse(0.0);
    }

}
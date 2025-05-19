package co.edu.Javeriana.servicio;

import co.edu.Javeriana.modelo.Nota;
import co.edu.Javeriana.repositorio.RepositorioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicioNota {

    @Autowired
    private RepositorioNota repositorioNota;

    public Mono<Double> calcularPromedioPorMateria(Long estudianteId, Long materiaId) {
        return repositorioNota.findByEstudianteIdAndMateriaId(estudianteId, materiaId)
                .collectList()
                .map(notas -> {
                    double sumaPonderada = notas.stream()
                            .mapToDouble(n -> n.getValor() * n.getPorcentaje())
                            .sum();
                    double totalPorcentaje = notas.stream()
                            .mapToDouble(Nota::getPorcentaje)
                            .sum();
                    return totalPorcentaje > 0 ? sumaPonderada / totalPorcentaje : null;
                });
    }
}


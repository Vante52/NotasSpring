// src/main/java/co/edu/javeriana/modelo/Nota.java
package co.edu.Javeriana.modelo;

import co.edu.Javeriana.modelo.Materia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Estudiante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private co.edu.Javeriana.modelo.Estudiante estudiante;

    // Relación con Materia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    @Column(length = 500)
    private String observacion;

    @Column
    private Double valor;

    @Column
    private Double porcentaje;
}

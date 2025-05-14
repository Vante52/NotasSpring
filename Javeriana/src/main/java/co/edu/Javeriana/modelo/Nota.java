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

    public Long getId() {
        return id;
    }


    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
}

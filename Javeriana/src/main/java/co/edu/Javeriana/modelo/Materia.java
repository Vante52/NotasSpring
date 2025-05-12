// src/main/java/co/edu/javeriana/modelo/Materia.java
package co.edu.Javeriana.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer creditos;

    @OneToMany(mappedBy = "materia",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<co.edu.Javeriana.modelo.Nota> notas = new ArrayList<>();
}

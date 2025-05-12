// src/main/java/co/edu/javeriana/modelo/Estudiante.java
package co.edu.Javeriana.modelo;

import co.edu.Javeriana.modelo.Nota;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String correo;

    @OneToMany(mappedBy = "estudiante",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Nota> notas = new ArrayList<>();
}

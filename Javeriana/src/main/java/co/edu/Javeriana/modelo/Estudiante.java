package co.edu.Javeriana.modelo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("estudiante")
public class Estudiante {
    @Id
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
}

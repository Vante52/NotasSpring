package co.edu.Javeriana.modelo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("materia")
public class Materia {
    @Id
    private Long id;
    private String nombre;
    private Integer creditos;
}

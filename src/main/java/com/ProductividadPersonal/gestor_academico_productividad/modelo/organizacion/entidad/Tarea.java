package com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data // Genera Getters y Setters
@NoArgsConstructor // Constructor vacío para JPA
@AllArgsConstructor // Constructor con todos los campos
@Entity // Marca esta clase como una tabla de la BD
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria

    private String titulo;
    private String descripcion;

    // Campo usado para el Tablero Kanban (Estado: PENDIENTE, EN_PROCESO, COMPLETADA)
    @Column(nullable = false)
    private String estado = "PENDIENTE";

    private Integer prioridad; // 1 (Alta) a 3 (Baja)

    private LocalDate fechaVencimiento;

    // ====================================================================
    // NUEVO CAMBIO: RELACIÓN CON LA ENTIDAD TABLERO
    // ====================================================================

    // Relación Muchos a Uno: Varias tareas pueden pertenecer a un solo tablero.
    // Esto crea la llave foránea (Foreign Key) en la tabla 'tareas'.
    @ManyToOne(fetch = FetchType.LAZY) // Carga perezosa: no carga el tablero a menos que se necesite
    @JoinColumn(name = "tablero_id", nullable = false) // Define la columna de la llave foránea
    private Tablero tablero;
}
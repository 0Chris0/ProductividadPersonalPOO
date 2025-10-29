package com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;

    @Column(nullable = false)
    private String estado = "PENDIENTE"; // PENDIENTE, EN_PROCESO, COMPLETADA

    private Integer prioridad; // 1 (Alta) a 3 (Baja)
    private LocalDate fechaVencimiento;

    // ====================================================================
    // RELACIÓN CON TABLERO
    // ====================================================================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tablero_id", nullable = false)
    private Tablero tablero;
}

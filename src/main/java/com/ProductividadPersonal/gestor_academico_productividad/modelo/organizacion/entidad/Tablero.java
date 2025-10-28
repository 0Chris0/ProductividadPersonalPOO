package com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tableros")
public class Tablero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Ej: "Tareas de Ingeniería", "Proyecto Final"
    private String descripcion;

    // Si quieres un tablero por usuario, necesitarías una relación a una entidad Usuario.

    // Relación Uno a Muchos con Tarea (opcional, pero buena práctica):
    // @OneToMany(mappedBy = "tablero", cascade = CascadeType.ALL)
    // private List<Tarea> tareas;
}
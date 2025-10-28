package com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Provee Getters, Setters, etc.
@NoArgsConstructor // Constructor vacío para JPA
@Entity
@Table(name = "habitos")
public class Habito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Usamos Long para ID de JPA

    private String nombre;
    private String descripcion;

    // Relación Muchos a Uno: JPA se encarga de la llave foránea
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaHabito categoria; // Referencia a la entidad, no al ID primitivo

    // El constructor con parámetros puede ser generado con @AllArgsConstructor si es necesario,
    // pero se omite para simplicidad con Lombok y JPA.

    @Override
    public String toString() {
        return this.nombre;
    }
}
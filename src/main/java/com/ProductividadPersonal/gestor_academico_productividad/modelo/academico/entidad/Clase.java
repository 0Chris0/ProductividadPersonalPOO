package com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad;

import jakarta.persistence.*;
import lombok.Data;      // Incluye Getter, Setter, toString, etc.
import lombok.NoArgsConstructor; // Constructor vacío (Obligatorio para JPA)
import lombok.AllArgsConstructor; // Constructor con todos los campos (Opcional, pero útil)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // ¡CRUCIAL! Mapea esta clase a una tabla de la BD
@Table(name = "clases")
public class Clase {

    @Id // Marca como Clave Primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generado por la BD
    private Long id;

    @Column(nullable = false)
    private String nombre; // Ej: Programación Orientada a Objetos

    private String hora; // Ej: 14:00 - 16:00
    private String dias; // Ej: Lunes, Miércoles, Viernes

    // NOTA: Con @Data y @NoArgsConstructor/AllArgsConstructor,
    // ya no necesitas escribir los Getters, Setters y el constructor manual que tenías antes.
}
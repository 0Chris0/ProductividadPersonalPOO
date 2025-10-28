package com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Genera Getters y Setters
@NoArgsConstructor // Constructor vacío para JPA
@AllArgsConstructor // Constructor con todos los campos (opcional)
@Entity // ¡CRUCIAL! Indica que es una tabla de la base de datos
@Table(name = "categorias_habitos")
public class CategoriaHabito {

    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Le dice a la BD que genere el valor automáticamente
    private Long id; // Usamos Long para ID de JPA (en lugar de int)

    @Column(nullable = false, unique = true) // No puede ser nulo y debe ser único
    private String nombre; // Ej: "Estudio", "Salud"

    // Puedes añadir otros atributos, como una descripción
    private String descripcion;
}
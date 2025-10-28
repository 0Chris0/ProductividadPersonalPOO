package com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad;

import jakarta.persistence.*;
import lombok.Data;      // Genera Getters, Setters, etc.
import lombok.NoArgsConstructor; // Constructor vacío
import lombok.AllArgsConstructor; // Constructor con todos los campos

@Data // Provee la funcionalidad de Getters y Setters
@NoArgsConstructor // Necesario para JPA (constructor vacío)
@AllArgsConstructor // ¡CRÍTICO! Genera el constructor que usa EvaluacionController
@Entity // Indica que esta clase es una tabla en la base de datos
@Table(name = "evaluaciones")
public class Evaluacion {

    @Id // Marca como Clave Primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generado por la BD
    private Long id; // Clave primaria, obligatoria para JPA

    private String nombre;
    private double valor;        // Porcentaje o peso de la evaluación
    private double calificacion; // Nota obtenida

    private String modulo;       // El módulo/clase al que pertenece

    // Nota: El constructor manual que tenías ya no es necesario,
    // ya que @AllArgsConstructor lo maneja automáticamente.
}
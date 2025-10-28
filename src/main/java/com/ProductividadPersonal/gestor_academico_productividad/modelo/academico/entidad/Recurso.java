package com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad;

import jakarta.persistence.*;
import lombok.Data; // Incluye Getter y Setter
import lombok.NoArgsConstructor; // Constructor vacío
import lombok.AllArgsConstructor; // Constructor con todos los campos

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Indica que esta clase es una tabla en la base de datos
@Table(name = "recursos") // Se añade el nombre de la tabla para claridad
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria, obligatoria para JPA

    private String nombre;
    private String tipo;
    private String enlace;

    // CRÍTICO: Si Recurso está ligado a una Clase, debe tener una relación @ManyToOne
    // private Clase claseAsociada;
}
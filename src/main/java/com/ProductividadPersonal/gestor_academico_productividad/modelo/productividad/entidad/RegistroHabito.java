package com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate; // ¡Usamos la clase moderna de Java!

@Data
@NoArgsConstructor // Constructor vacío para JPA
@Entity
@Table(name = "registro_habitos")
public class RegistroHabito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Usamos Long para ID de JPA

    @Column(nullable = false)
    private LocalDate fecha; // Fecha en que se hizo el registro

    @Column(nullable = false)
    private boolean completado = false; // true si se completó

    // Relación Muchos a Uno
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habito_id", nullable = false)
    private Habito habito; // Referencia al objeto Habito completo
}
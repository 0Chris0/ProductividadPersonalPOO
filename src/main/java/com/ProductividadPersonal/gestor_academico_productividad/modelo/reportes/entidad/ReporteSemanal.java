package com.ProductividadPersonal.gestor_academico_productividad.modelo.reportes.entidad;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Evaluacion;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data // Proporciona Getters y Setters
@AllArgsConstructor // Constructor con todos los campos
@NoArgsConstructor // Constructor vacío
public class ReporteSemanal {

    // Esta clase NO lleva @Entity (no es una tabla, es un objeto de transferencia)

    // 1. Información General del Reporte
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // 2. Métricas del Módulo ACADÉMICO (Evaluaciones)
    private List<Evaluacion> evaluaciones;
    private double promedioAcademico;

    // 3. Métricas del Módulo ORGANIZACIÓN (Tareas)
    private int tareasCompletadas;
    private int tareasPendientes;

    // 4. Métricas del Módulo PRODUCTIVIDAD (Pomodoro)
    private long totalMinutosEnfoque;
    private int habitosCompletados;

    // Métodos utilitarios (pueden ser útiles para JasperReport o la Vista Swing)

    public double getTasaCompletada() {
        int total = tareasCompletadas + tareasPendientes;
        return total > 0 ? ((double) tareasCompletadas / total) * 100 : 0.0;
    }
}
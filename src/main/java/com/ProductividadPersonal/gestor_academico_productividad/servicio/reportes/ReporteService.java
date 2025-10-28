package com.ProductividadPersonal.gestor_academico_productividad.servicio.reportes;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.reportes.entidad.ReporteSemanal;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.academico.CalculadoraService;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.organizacion.TareaService;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad.HabitoService;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad.PomodoroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ReporteService {

    // Inyección de todos los servicios necesarios para construir el reporte
    private final CalculadoraService calculadoraService;
    private final TareaService tareaService;
    private final HabitoService habitoService;
    private final PomodoroService pomodoroService;

    @Autowired
    public ReporteService(CalculadoraService calculadoraService, TareaService tareaService,
                          HabitoService habitoService, PomodoroService pomodoroService) {
        this.calculadoraService = calculadoraService;
        this.tareaService = tareaService;
        this.habitoService = habitoService;
        this.pomodoroService = pomodoroService;
    }

    /**
     * Genera un Reporte Semanal consolidando métricas de todos los módulos.
     */
    public ReporteSemanal generarReporteSemanal() {
        LocalDate fechaFin = LocalDate.now();
        LocalDate fechaInicio = fechaFin.minusWeeks(1);

        // 1. Métricas ACADÉMICA
        double promedioAcademico = calculadoraService.calcularPromedioGeneral();

        // 2. Métricas ORGANIZACIÓN
        int tareasCompletadas = tareaService.contarCompletadasEnPeriodo(fechaInicio, fechaFin);
        int tareasPendientes = tareaService.contarPendientesEnPeriodo(fechaInicio, fechaFin);

        // 3. Métricas PRODUCTIVIDAD
        long minutosEnfoque = pomodoroService.contarMinutosEnfoqueEnPeriodo(fechaInicio, fechaFin);
        int habitosCompletados = habitoService.contarHabitosCompletadosEnPeriodo(fechaInicio, fechaFin);

        // 4. Crear y devolver el DTO final
        ReporteSemanal reporte = new ReporteSemanal();
        reporte.setFechaInicio(fechaInicio);
        reporte.setFechaFin(fechaFin);
        reporte.setPromedioAcademico(promedioAcademico);
        reporte.setTareasCompletadas(tareasCompletadas);
        reporte.setTareasPendientes(tareasPendientes);
        reporte.setTotalMinutosEnfoque(minutosEnfoque);
        reporte.setHabitosCompletados(habitosCompletados);

        return reporte;
    }
}
package com.ProductividadPersonal.gestor_academico_productividad.servicio.organizacion;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tablero;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tarea;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.repositorio.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service // Marca la clase como un Servicio de Spring
public class TareaService {

    private final TareaRepository tareaRepository;
    private final TableroService tableroService; // Inyección de TableroService para lógica de tableros

    @Autowired // Inyección de Repositorio y Servicio
    public TareaService(TareaRepository tareaRepository, TableroService tableroService) {
        this.tareaRepository = tareaRepository;
        this.tableroService = tableroService;
    }

    /**
     * Guarda una nueva tarea en la base de datos, asignándola a un tablero.
     * @param tarea La entidad Tarea a guardar.
     * @param tableroId El ID del tablero al que pertenece.
     * @return La Tarea guardada.
     */
    public Tarea guardarTarea(Tarea tarea, Long tableroId) {
        // Lógica de negocio: Asigna la tarea al tablero (usando el TableroService)
        Tablero tablero = tableroService.obtenerTodos().stream()
                .filter(t -> t.getId().equals(tableroId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tablero con ID " + tableroId + " no encontrado."));

        // Asignación de valores por defecto si son nulos
        if (tarea.getPrioridad() == null) {
            tarea.setPrioridad(3); // Baja
        }
        if (tarea.getEstado() == null) {
            tarea.setEstado("PENDIENTE");
        }

        tarea.setTablero(tablero);
        return tareaRepository.save(tarea);
    }

    /**
     * Mueve una tarea entre las columnas del Kanban (cambia su estado).
     * @param id ID de la tarea a mover.
     * @param nuevoEstado Nuevo estado (ej. "EN_PROCESO").
     * @return La Tarea actualizada.
     */
    public Tarea moverTarea(Long id, String nuevoEstado) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea con ID " + id + " no encontrada."));
        tarea.setEstado(nuevoEstado);
        return tareaRepository.save(tarea);
    }

    /**
     * Obtiene todas las tareas de un estado específico (columna del Kanban).
     * @param estado El estado a filtrar.
     * @return Lista de tareas ordenadas por prioridad.
     */
    public List<Tarea> obtenerTareasPorEstado(String estado) {
        return tareaRepository.findByEstadoOrderByPrioridadDesc(estado);
    }

    /**
     * CRUCIAL PARA EL REPORTE SEMANAL (Módulo 5)
     * Cuenta el número de tareas completadas en un período.
     */
    public int contarCompletadasEnPeriodo(LocalDate inicio, LocalDate fin) {
        // Implementación real: Se usaría un método personalizado del repositorio.
        // List<Tarea> completadas = tareaRepository.findByEstadoAndFechaVencimientoBetween("COMPLETADA", inicio, fin);
        // return completadas.size();
        System.out.println("DEBUG: Contando tareas completadas para reporte...");
        return 50; // Valor de prueba
    }

    /**
     * CRUCIAL PARA EL REPORTE SEMANAL (Módulo 5)
     * Cuenta el número de tareas pendientes en un período.
     */
    public int contarPendientesEnPeriodo(LocalDate inicio, LocalDate fin) {
        // Implementación real: Similar al anterior, pero filtrando por estado PENDIENTE.
        System.out.println("DEBUG: Contando tareas pendientes para reporte...");
        return 10; // Valor de prueba
    }
}
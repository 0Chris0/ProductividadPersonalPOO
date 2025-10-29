package com.ProductividadPersonal.gestor_academico_productividad.servicio.organizacion;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tablero;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tarea;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.repositorio.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;
    private final TableroService tableroService;

    @Autowired
    public TareaService(TareaRepository tareaRepository, TableroService tableroService) {
        this.tareaRepository = tareaRepository;
        this.tableroService = tableroService;
    }

    // Guardar nueva tarea en un tablero
    public Tarea guardarTarea(Tarea tarea, Long tableroId) {
        Tablero tablero = tableroService.obtenerTodos().stream()
                .filter(t -> t.getId().equals(tableroId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tablero no encontrado"));

        if (tarea.getEstado() == null) tarea.setEstado("PENDIENTE");
        if (tarea.getPrioridad() == null) tarea.setPrioridad(3);

        tarea.setTablero(tablero);
        return tareaRepository.save(tarea);
    }

    // Mover tarea entre estados
    public Tarea moverTarea(Long id, String nuevoEstado) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        tarea.setEstado(nuevoEstado);
        return tareaRepository.save(tarea);
    }

    // Borrar tarea
    public void borrarTarea(Long id) {
        if (tareaRepository.existsById(id)) {
            tareaRepository.deleteById(id);
        }
    }

    // Obtener tareas por estado
    public List<Tarea> obtenerTareasPorEstado(String estado) {
        return tareaRepository.findByEstadoOrderByPrioridadDesc(estado);
    }

    // ================= REPORTE =================
    // Contar tareas completadas en un rango de fechas
    public int contarCompletadasEnPeriodo(LocalDate inicio, LocalDate fin) {
        return (int) tareaRepository.findByEstadoOrderByPrioridadDesc("COMPLETADA").stream()
                .filter(t -> t.getFechaVencimiento() != null &&
                        !t.getFechaVencimiento().isBefore(inicio) &&
                        !t.getFechaVencimiento().isAfter(fin))
                .count();
    }

    // Contar tareas pendientes en un rango de fechas
    public int contarPendientesEnPeriodo(LocalDate inicio, LocalDate fin) {
        return (int) tareaRepository.findByEstadoOrderByPrioridadDesc("PENDIENTE").stream()
                .filter(t -> t.getFechaVencimiento() != null &&
                        !t.getFechaVencimiento().isBefore(inicio) &&
                        !t.getFechaVencimiento().isAfter(fin))
                .count();
    }
}

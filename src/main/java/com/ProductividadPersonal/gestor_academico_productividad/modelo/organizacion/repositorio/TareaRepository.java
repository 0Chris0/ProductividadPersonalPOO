package com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.repositorio;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Marca esta interfaz como un Repositorio gestionado por Spring
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // ====================================================================
    // MÉTODOS PARA EL KANBAN
    // ====================================================================

    /**
     * Busca todas las tareas que se encuentran en un estado específico (columna del Kanban),
     * y las ordena por prioridad descendente (1=Alta, 3=Baja).
     * @param estado El estado de la tarea (ej. "PENDIENTE", "EN_PROCESO").
     * @return Lista de tareas.
     */
    List<Tarea> findByEstadoOrderByPrioridadDesc(String estado);

    /**
     * Busca todas las tareas que pertenecen a un tablero específico (usando la nueva relación).
     * @param tableroId El ID de la entidad Tablero.
     * @return Lista de tareas de ese tablero.
     */
    List<Tarea> findByTableroId(Long tableroId);
}
package com.ProductividadPersonal.gestor_academico_productividad.vista.organizacion.controlador;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tablero;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tarea;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.organizacion.TableroService;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.organizacion.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TareaController {

    private final TareaService tareaService;
    private final TableroService tableroService;

    @Autowired
    public TareaController(TareaService tareaService, TableroService tableroService) {
        this.tareaService = tareaService;
        this.tableroService = tableroService;
    }

    // Métodos para la Vista:
    public Tarea crearTarea(String titulo, String descripcion, Long tableroId) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        return tareaService.guardarTarea(tarea, tableroId);
    }

    public List<Tarea> obtenerTareasPorEstado(String estado) {
        return tareaService.obtenerTareasPorEstado(estado);
    }

    public Tarea moverTarea(Long id, String nuevoEstado) {
        return tareaService.moverTarea(id, nuevoEstado);
    }

    public List<Tablero> cargarTableros() {
        return tableroService.obtenerTodos();
    }
}
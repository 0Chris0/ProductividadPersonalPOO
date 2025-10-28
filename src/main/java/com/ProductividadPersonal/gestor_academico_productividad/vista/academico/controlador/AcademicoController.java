package com.ProductividadPersonal.gestor_academico_productividad.vista.academico.controlador;

// Importaciones de Entidades
import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Clase;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Recurso;

// Importaciones de Servicios
import com.ProductividadPersonal.gestor_academico_productividad.servicio.academico.HorarioService;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.academico.RecursoService;

// Importaciones de Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // Marca esta clase como un Bean de Spring
public class AcademicoController { // Controlador consolidado para Horario y Recursos

    private final HorarioService horarioService;
    private final RecursoService recursoService;

    @Autowired // Inyección de Dependencias
    public AcademicoController(HorarioService horarioService, RecursoService recursoService) {
        this.horarioService = horarioService;
        this.recursoService = recursoService;
    }

    // --- Métodos de Horario ---
    /**
     * Guarda una nueva clase en la BD.
     * CORRECCIÓN: Usa constructor vacío y setters para evitar error de constructor.
     */
    public Clase guardarNuevaClase(String nombre, String hora) {
        // Asumiendo que la entidad Clase tiene campos 'nombre' y 'hora'
        Clase clase = new Clase(); // Constructor vacío (@NoArgsConstructor)
        clase.setNombre(nombre);
        // clase.setHora(hora); // Solo si esta Entidad tiene el campo 'hora'

        return horarioService.guardarClase(clase);
    }

    public List<Clase> cargarHorario() {
        return horarioService.obtenerHorarioCompleto();
    }

    // --- Métodos de Recursos ---
    /**
     * Guarda un nuevo recurso en la BD.
     * CORRECCIÓN: Usa constructor vacío y setters.
     */
    public Recurso guardarNuevoRecurso(String nombre, String url) {
        // Asumiendo que la entidad Recurso tiene campos 'nombre' y 'url'
        Recurso recurso = new Recurso(); // Constructor vacío (@NoArgsConstructor)
        recurso.setNombre(nombre);
        // recurso.setUrl(url); // Solo si esta Entidad tiene el campo 'url'

        // Si la entidad Recurso tiene una relación con la entidad Clase, ¡debes setearla aquí!
        // recurso.setClase(claseService.buscarClasePorNombre(nombre));

        return recursoService.guardarRecurso(recurso);
    }

    public List<Recurso> cargarRecursos(String nombreClase) {
        return recursoService.obtenerRecursosPorClase(nombreClase);
    }
}
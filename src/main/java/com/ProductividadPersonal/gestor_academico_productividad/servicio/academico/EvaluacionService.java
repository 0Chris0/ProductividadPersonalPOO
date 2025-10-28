package com.ProductividadPersonal.gestor_academico_productividad.servicio.academico;

// Importaciones de la Capa de Modelo (Datos)
import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Evaluacion;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.repositorio.EvaluacionRepository;

// Importaciones de Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Útil para métodos de búsqueda por ID

@Service // Marca esta clase como un Bean de Servicio de Spring
public class EvaluacionService {

    // Dependencia del Repositorio (Inyección de la conexión a la BD)
    private final EvaluacionRepository evaluacionRepository;

    // Constructor con Inyección de Dependencias (@Autowired)
    @Autowired
    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    /**
     * Agrega una nueva evaluación a la base de datos (o actualiza si existe el ID).
     * @param evaluacion El objeto Evaluacion a guardar.
     * @return La evaluación guardada.
     */
    public Evaluacion agregarEvaluacion(Evaluacion evaluacion) {
        // Aquí puedes agregar lógica de negocio, como validar que el valor (peso) no sea negativo.
        if (evaluacion.getValor() < 0) {
            throw new IllegalArgumentException("El valor de la evaluación no puede ser negativo.");
        }
        return evaluacionRepository.save(evaluacion);
    }

    /**
     * Lista todas las evaluaciones desde la base de datos H2.
     * @return Una lista de todas las evaluaciones.
     */
    public List<Evaluacion> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    /**
     * Obtiene una lista de evaluaciones filtrada por el módulo al que pertenecen.
     * @param modulo Nombre del módulo.
     * @return Lista de evaluaciones del módulo.
     */
    public List<Evaluacion> listarEvaluacionesPorModulo(String modulo) {
        return evaluacionRepository.findByModulo(modulo);
    }

    /**
     * Encuentra una evaluación por su ID.
     * @param id ID de la evaluación.
     * @return La evaluación si existe, o lanza una excepción.
     */
    public Evaluacion buscarPorId(Long id) {
        return evaluacionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Evaluación con ID " + id + " no encontrada."));
    }

    /**
     * Elimina una evaluación de la base de datos por su ID.
     * @param id ID de la evaluación a eliminar.
     */
    public void eliminarEvaluacion(Long id) {
        evaluacionRepository.deleteById(id);
    }
}
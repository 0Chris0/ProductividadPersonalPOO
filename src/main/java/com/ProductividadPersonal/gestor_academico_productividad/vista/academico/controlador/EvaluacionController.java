package com.ProductividadPersonal.gestor_academico_productividad.vista.academico.controlador;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Evaluacion;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.academico.EvaluacionService;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.academico.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EvaluacionController {

    private final EvaluacionService evaluacionService;
    private final CalculadoraService calculadoraService;

    @Autowired // Inyección de los servicios (Lógica de Negocio)
    public EvaluacionController(EvaluacionService evaluacionService, CalculadoraService calculadoraService) {
        this.evaluacionService = evaluacionService;
        this.calculadoraService = calculadoraService;
    }

    /**
     * Crea y guarda una nueva evaluación en la base de datos.
     * CORRECCIÓN: Usamos el constructor vacío y los setters, que son 100% seguros con Lombok/JPA.
     */
    public Evaluacion guardarEvaluacion(String nombre, double valor, double nota, String modulo) {

        // Forma MÁS SEGURA (Solución final para el error de constructor):
        Evaluacion e = new Evaluacion();
        e.setNombre(nombre);
        e.setValor(valor);
        e.setCalificacion(nota); // Usamos 'nota' para el campo 'calificacion'
        e.setModulo(modulo);

        return evaluacionService.agregarEvaluacion(e);
    }

    /**
     * Carga todas las evaluaciones de un módulo específico desde la BD.
     */
    public List<Evaluacion> cargarEvaluaciones(String modulo) {
        return evaluacionService.listarEvaluacionesPorModulo(modulo);
    }

    /**
     * Calcula el promedio ponderado de las evaluaciones para un módulo.
     */
    public double calcularPromedioModulo(String modulo) {
        List<Evaluacion> evaluaciones = evaluacionService.listarEvaluacionesPorModulo(modulo);
        return calculadoraService.calcularPromedio(evaluaciones);
    }
}
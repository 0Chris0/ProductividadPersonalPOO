package com.ProductividadPersonal.gestor_academico_productividad.servicio.academico;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Evaluacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculadoraService {

    // Asumimos que este método existe para ser invocado por el ReporteService
    public double calcularPromedioGeneral() {
        // Implementación real: Llama a EvaluacionRepository para obtener todas las evaluaciones
        // y calcular el promedio ponderado.
        System.out.println("DEBUG: Calculando promedio académico general...");
        return 4.5; // Valor de prueba
    }

    public double calcularPromedio(List<Evaluacion> evaluaciones) {
        double sumaPonderada = 0;
        double totalPesos = 0;
        for (Evaluacion e : evaluaciones) {
            sumaPonderada += e.getCalificacion() * e.getValor();
            totalPesos += e.getValor();
        }
        return totalPesos > 0 ? sumaPonderada / totalPesos : 0;
    }
}
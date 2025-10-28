package com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service // Clase que gestiona la lógica de registro de tiempo de enfoque
public class PomodoroService {

    // Si tuvieras una entidad SesionPomodoro, la inyectarías aquí.

    public void registrarSesionCompleta(int minutosEnfoque) {
        // Lógica: Guardar la sesión en la base de datos para el historial
        System.out.println("DEBUG: Sesión Pomodoro de " + minutosEnfoque + " min. registrada.");
    }

    /** CRUCIAL PARA EL REPORTE SEMANAL */
    public long contarMinutosEnfoqueEnPeriodo(LocalDate inicio, LocalDate fin) {
        // Implementación real: sumar los minutos de enfoque registrados
        System.out.println("DEBUG: Sumando minutos de enfoque para reporte...");
        return 125; // Valor de prueba
    }
}
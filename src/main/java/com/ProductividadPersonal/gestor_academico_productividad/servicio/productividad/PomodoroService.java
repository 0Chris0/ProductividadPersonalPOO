package com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PomodoroService {

    // Historial de sesiones (en memoria)
    private final List<SesionPomodoro> historial = new ArrayList<>();

    // Tiempo por defecto en minutos
    private int duracionPomodoro = 25;

    // ===================== CLASE INTERNA =====================
    public static class SesionPomodoro {
        private final LocalDate fecha;
        private final int minutos;

        public SesionPomodoro(LocalDate fecha, int minutos) {
            this.fecha = fecha;
            this.minutos = minutos;
        }

        public LocalDate getFecha() { return fecha; }
        public int getMinutos() { return minutos; }
    }

    // ===================== CONFIGURACIÓN =====================
    public void setDuracionPomodoro(int minutos) {
        if(minutos <= 0) throw new IllegalArgumentException("La duración debe ser mayor a 0.");
        duracionPomodoro = minutos;
        System.out.println("DEBUG: Duración del Pomodoro ajustada a " + duracionPomodoro + " minutos.");
    }

    public int getDuracionPomodoro() {
        return duracionPomodoro;
    }

    // ===================== REGISTRO =====================
    public void registrarSesionCompleta(Integer minutos) {
        int minutosRegistrados = minutos != null ? minutos : duracionPomodoro;
        historial.add(new SesionPomodoro(LocalDate.now(), minutosRegistrados));
        System.out.println("DEBUG: Sesión Pomodoro de " + minutosRegistrados + " min. registrada.");
    }

    // ===================== REPORTE =====================
    public long contarMinutosEnfoqueEnPeriodo(LocalDate inicio, LocalDate fin) {
        return historial.stream()
                .filter(s -> !s.getFecha().isBefore(inicio) && !s.getFecha().isAfter(fin))
                .mapToLong(SesionPomodoro::getMinutos)
                .sum();
    }

    // ===================== LISTAR HISTORIAL =====================
    public List<SesionPomodoro> obtenerHistorial() {
        return new ArrayList<>(historial);
    }
}

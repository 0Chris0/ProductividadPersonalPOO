package com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.controlador;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.Habito;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad.HabitoService;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad.PomodoroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductividadController {

    private final HabitoService habitoService;
    private final PomodoroService pomodoroService;

    @Autowired
    public ProductividadController(HabitoService habitoService, PomodoroService pomodoroService) {
        this.habitoService = habitoService;
        this.pomodoroService = pomodoroService;
    }

    // --- Métodos para el PanelHabitos ---
    public void marcarHábitoComoCompletado(Long habitoId) {
        habitoService.registrarHabitoCompletado(habitoId);
    }

    public List<Habito> cargarHabitos() {
        return habitoService.obtenerTodosLosHabitos();
    }

    // --- Métodos para el PanelPomodoro ---
    public void registrarFinSesion(int minutosEnfoque) {
        pomodoroService.registrarSesionCompleta(minutosEnfoque);
    }

    // --- Métodos para el PanelDiario ---
    public void guardarEntradaDiario(String contenido) {
        // Lógica de guardar en la BD (asumiendo que existe un servicio/repositorio para el Diario)
        System.out.println("DEBUG: Entrada de diario guardada.");
    }
}
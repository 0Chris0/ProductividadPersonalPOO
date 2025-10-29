package com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.controlador;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.Habito;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad.HabitoService;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad.PomodoroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductividadController {

    private final HabitoService habitoService;
    private final PomodoroService pomodoroService;

    // Contenido puro del diario
    private final List<String> entradasDiario = new ArrayList<>();

    @Autowired
    public ProductividadController(HabitoService habitoService, PomodoroService pomodoroService) {
        this.habitoService = habitoService;
        this.pomodoroService = pomodoroService;
    }

    // --- Métodos Habitos ---
    public void marcarHábitoComoCompletado(Long habitoId) {
        habitoService.registrarHabitoCompletado(habitoId);
    }

    public List<Habito> cargarHabitos() {
        return habitoService.obtenerTodosLosHabitos();
    }

    // --- Métodos Pomodoro ---
    public void registrarFinSesion(int minutosEnfoque) {
        pomodoroService.registrarSesionCompleta(minutosEnfoque);
    }

    // --- Métodos Diario ---
    public void guardarEntradaDiario(String contenido) {
        if (contenido != null && !contenido.trim().isEmpty()) {
            entradasDiario.add(contenido.trim());
            System.out.println("Entrada guardada -> " + contenido);
        }
    }

    public List<String> obtenerEntradasDiario() {
        return new ArrayList<>(entradasDiario); // Devuelve copia para seguridad
    }

    public void editarEntradaDiario(int index, String nuevoContenido) {
        if (index >= 0 && index < entradasDiario.size() && nuevoContenido != null && !nuevoContenido.trim().isEmpty()) {
            entradasDiario.set(index, nuevoContenido.trim());
            System.out.println("DEBUG: Entrada editada -> " + nuevoContenido);
        }
    }

    public void borrarEntradaDiario(int index) {
        if (index >= 0 && index < entradasDiario.size()) {
            String eliminado = entradasDiario.remove(index);
            System.out.println("DEBUG: Entrada borrada -> " + eliminado);
        }
    }
}

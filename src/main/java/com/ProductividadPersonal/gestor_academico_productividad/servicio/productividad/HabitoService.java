package com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.Habito;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.RegistroHabito;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.repositorio.HabitoRepository;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.repositorio.RegistroHabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitoService {

    private final HabitoRepository habitoRepository;
    private final RegistroHabitoRepository registroHabitoRepository;

    @Autowired
    public HabitoService(HabitoRepository habitoRepository, RegistroHabitoRepository registroHabitoRepository) {
        this.habitoRepository = habitoRepository;
        this.registroHabitoRepository = registroHabitoRepository;
    }

    /**
     * Registra que un hábito fue completado hoy, buscándolo por su ID.
     * @param habitoId ID del hábito a marcar.
     * @return El RegistroHabito guardado.
     */
    public RegistroHabito registrarHabitoCompletado(Long habitoId) {
        // 1. Busca el hábito en la BD
        Habito habito = habitoRepository.findById(habitoId)
                .orElseThrow(() -> new RuntimeException("Hábito con ID " + habitoId + " no encontrado."));

        // 2. Crea el nuevo registro
        RegistroHabito registro = new RegistroHabito();
        registro.setHabito(habito);
        registro.setFecha(LocalDate.now());
        registro.setCompletado(true);

        // 3. Guarda el registro en la BD
        return registroHabitoRepository.save(registro);
    }

    // Otros métodos de lógica de negocio...
    public List<Habito> obtenerTodosLosHabitos() {
        return habitoRepository.findAll();
    }

    public int contarHabitosCompletadosEnPeriodo(LocalDate inicio, LocalDate fin) {
        // Lógica de reporte (ejemplo de integración)
        return 25;
    }
}
package com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.Habito;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.CategoriaHabito;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.RegistroHabito;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.repositorio.HabitoRepository;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.repositorio.CategoriaHabitoRepository;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.repositorio.RegistroHabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitoService {

    private final HabitoRepository habitoRepository;
    private final CategoriaHabitoRepository categoriaHabitoRepository;
    private final RegistroHabitoRepository registroHabitoRepository;

    @Autowired
    public HabitoService(HabitoRepository habitoRepository,
                         CategoriaHabitoRepository categoriaHabitoRepository,
                         RegistroHabitoRepository registroHabitoRepository) {
        this.habitoRepository = habitoRepository;
        this.categoriaHabitoRepository = categoriaHabitoRepository;
        this.registroHabitoRepository = registroHabitoRepository;
    }

    /**
     * Guarda un hábito asegurando que tenga categoría.
     */
    public Habito guardarHabito(Habito habito) {
        if (habito.getCategoria() == null) {
            // Buscar categoría "General" o crearla
            CategoriaHabito general = categoriaHabitoRepository.findByNombre("General")
                    .orElseGet(() -> {
                        CategoriaHabito cat = new CategoriaHabito();
                        cat.setNombre("General");
                        return categoriaHabitoRepository.save(cat);
                    });
            habito.setCategoria(general);
        }
        return habitoRepository.save(habito);
    }

    public void borrarHabito(Long id) {
        habitoRepository.deleteById(id);
    }

    /**
     * Registra que un hábito fue completado hoy.
     */
    public RegistroHabito registrarHabitoCompletado(Long habitoId) {
        Habito habito = habitoRepository.findById(habitoId)
                .orElseThrow(() -> new RuntimeException("Hábito con ID " + habitoId + " no encontrado."));

        RegistroHabito registro = new RegistroHabito();
        registro.setHabito(habito);
        registro.setFecha(LocalDate.now());
        registro.setCompletado(true);

        return registroHabitoRepository.save(registro);
    }

    public List<Habito> obtenerTodosLosHabitos() {
        return habitoRepository.findAll();
    }

    public int contarHabitosCompletadosEnPeriodo(LocalDate inicio, LocalDate fin) {
        return registroHabitoRepository.countByFechaBetweenAndCompletadoTrue(inicio, fin);
    }

    public List<CategoriaHabito> obtenerTodasCategorias() {
        return categoriaHabitoRepository.findAll();
    }
}

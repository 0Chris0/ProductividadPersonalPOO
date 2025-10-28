package com.ProductividadPersonal.gestor_academico_productividad.servicio.academico;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Clase;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.repositorio.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Clase que gestiona la lógica de horario
public class HorarioService {

    private final ClaseRepository claseRepository;

    @Autowired
    public HorarioService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    public Clase guardarClase(Clase clase) {
        // Lógica de negocio: evitar solapamiento de horarios
        return claseRepository.save(clase);
    }

    public List<Clase> obtenerHorarioCompleto() {
        return claseRepository.findAll();
    }
}
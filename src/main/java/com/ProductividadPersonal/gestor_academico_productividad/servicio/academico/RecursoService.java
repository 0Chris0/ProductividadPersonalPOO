package com.ProductividadPersonal.gestor_academico_productividad.servicio.academico;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Recurso;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.repositorio.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Clase que gestiona la lógica de recursos de estudio
public class RecursoService {

    private final RecursoRepository recursoRepository;

    @Autowired
    public RecursoService(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    public Recurso guardarRecurso(Recurso recurso) {
        // Lógica de negocio: validar el formato del enlace
        return recursoRepository.save(recurso);
    }

    public List<Recurso> obtenerRecursosPorClase(String nombreClase) {
        // Aquí usarías un método de repositorio, asumiendo que tienes esa funcionalidad
        return recursoRepository.findAll();
    }
}
package com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.repositorio;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByModulo(String modulo);
}
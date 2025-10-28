package com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.repositorio;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
}
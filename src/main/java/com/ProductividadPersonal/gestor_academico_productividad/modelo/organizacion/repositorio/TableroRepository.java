package com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.repositorio;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tablero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableroRepository extends JpaRepository<Tablero, Long> {

}
package com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.repositorio;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.Habito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitoRepository extends JpaRepository<Habito, Long> {

    // Ejemplo de método de búsqueda: Obtener todos los hábitos por el ID de la categoría
    List<Habito> findByCategoriaId(Long categoriaId);
}
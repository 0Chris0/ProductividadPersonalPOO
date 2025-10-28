package com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.repositorio;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.CategoriaHabito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Anota esta interfaz para que Spring la gestione
public interface CategoriaHabitoRepository extends JpaRepository<CategoriaHabito, Long> {

    // Spring implementará automáticamente métodos de búsqueda específicos.
    // Ejemplo: buscar una categoría por su nombre (ya que es unique)
    Optional<CategoriaHabito> findByNombre(String nombre);
}
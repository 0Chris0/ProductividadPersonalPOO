package com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.repositorio;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.RegistroHabito;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.Habito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository
public interface RegistroHabitoRepository extends JpaRepository<RegistroHabito, Long> {

    // Ejemplo de método de búsqueda: Obtener el registro para un hábito específico en una fecha
    Optional<RegistroHabito> findByHabitoAndFecha(Habito habito, LocalDate fecha);

    // Ejemplo: Obtener todos los registros completados para un hábito
    List<RegistroHabito> findByHabitoAndCompletadoTrue(Habito habito);
}
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

    Optional<RegistroHabito> findByHabitoAndFecha(Habito habito, LocalDate fecha);

    List<RegistroHabito> findByHabitoAndCompletadoTrue(Habito habito);

    // <-- Método necesario para contar hábitos completados en un rango de fechas
    int countByFechaBetweenAndCompletadoTrue(LocalDate inicio, LocalDate fin);
}

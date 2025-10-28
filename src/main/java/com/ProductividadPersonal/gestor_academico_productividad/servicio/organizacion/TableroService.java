package com.ProductividadPersonal.gestor_academico_productividad.servicio.organizacion;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tablero;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.repositorio.TableroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marca la clase como un Servicio de Spring
public class TableroService {

    private final TableroRepository tableroRepository;

    @Autowired // Inyección de la interfaz del Repositorio
    public TableroService(TableroRepository tableroRepository) {
        this.tableroRepository = tableroRepository;
    }

    /**
     * Guarda un nuevo tablero en la base de datos.
     * CORRECCIÓN: El método ahora acepta el objeto Tablero.
     * @param tablero El objeto Tablero a guardar.
     * @return El Tablero guardado.
     */
    public Tablero guardarTablero(Tablero tablero) {
        // Lógica de negocio: Por ejemplo, verificar si el nombre ya existe.
        return tableroRepository.save(tablero); // Ahora save recibe el objeto Tablero, no una String
    }

    /**
     * Obtiene todos los tableros disponibles.
     * @return Una lista de todos los tableros.
     */
    public List<Tablero> obtenerTodos() {
        return tableroRepository.findAll();
    }

    /**
     * Busca un tablero por su ID.
     * @param id ID del tablero.
     * @return El Tablero encontrado o lanza una excepción.
     */
    public Tablero buscarPorId(Long id) {
        return tableroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tablero con ID " + id + " no encontrado."));
    }
}
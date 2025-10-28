package com.ProductividadPersonal.gestor_academico_productividad;

import com.ProductividadPersonal.gestor_academico_productividad.vista.organizacion.componentes.FramePrincipal;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.organizacion.TableroService;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.organizacion.TareaService;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tablero;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tarea;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.time.LocalDate;

@SpringBootApplication
public class GestorAcademicoProductividadApplication {

	// *******************************************************************
	// MÉTODO MAIN CORREGIDO
	// *******************************************************************
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GestorAcademicoProductividadApplication.class);
		app.setHeadless(false);
		app.run(args);
	}

	/**
	 * BEAN RUNNER: Lanza la Interfaz de Swing después de que Spring termina de cargar el Contexto.
	 */
	@Bean
	public CommandLineRunner runner(ApplicationContext ctx) {
		return args -> {
			EventQueue.invokeLater(() -> {
				try {
					FramePrincipal mainFrame = ctx.getBean(FramePrincipal.class);

					mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainFrame.setVisible(true);
				} catch (Exception e) {
					System.err.println("FATAL: Error al iniciar la interfaz gráfica.");
					e.printStackTrace();
					System.exit(1);
				}
			});
		};
	}

	/**
	 * BEAN INICIALIZADOR: Crea el Tablero Principal y las TAREAS DE PRUEBA.
	 */
	@Bean
	public CommandLineRunner inicializarDatos(TableroService tableroService, TareaService tareaService) {
		return args -> {
			// Si el Tablero principal no existe (solo se ejecuta la primera vez)
			if (tableroService.obtenerTodos().isEmpty()) {
				// 1. Crear y guardar el Tablero Principal
				Tablero tableroPrincipal = new Tablero();
				tableroPrincipal.setNombre("Principal");
				tableroService.guardarTablero(tableroPrincipal);

				// 2. CREACIÓN DE TAREAS DE PRUEBA (SEED DATA)
				Long tableroId = tableroService.obtenerTodos().get(0).getId();

				// Tarea Pendiente
				Tarea t1 = new Tarea();
				t1.setTitulo("Revisar Entregables Rúbrica");
				t1.setDescripcion("Asegurar que los puntos 6 y 7 estén listos.");
				t1.setPrioridad(1); // Alta
				tareaService.guardarTarea(t1, tableroId);

				// Tarea En Proceso
				Tarea t2 = new Tarea();
				t2.setTitulo("Diseño de Interfaz Módulo Académico");
				t2.setDescripcion("Completar PanelHorario y PanelRecursos.");
				t2.setEstado("EN_PROCESO");
				t2.setPrioridad(2); // Media
				tareaService.guardarTarea(t2, tableroId);

				// Tarea Completada
				Tarea t3 = new Tarea();
				t3.setTitulo("Organizar Repositorios (Capa Modelo)");
				t3.setDescripcion("Clases de Entidad y Repositorios están OK.");
				t3.setEstado("COMPLETADA");
				t3.setPrioridad(3); // Baja
				tareaService.guardarTarea(t3, tableroId);
			}
		};
	}
}

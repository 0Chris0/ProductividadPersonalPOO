package com.ProductividadPersonal.gestor_academico_productividad.vista.organizacion.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tarea;
import com.ProductividadPersonal.gestor_academico_productividad.vista.organizacion.controlador.TareaController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import static java.awt.Component.LEFT_ALIGNMENT; // <--- ESTA LÍNEA ES CRÍTICA

@Component
public class PanelKanban extends JPanel {

    private final TareaController tareaController;

    // Componentes del formulario
    private JTextField txtTituloTarea;
    private JButton btnAgregarTarea;

    private JPanel panelColumnas; // Contenedor para las 3 columnas
    private JPanel panelPendientes;
    private JPanel panelProceso;
    private JPanel panelCompletadas;

    @Autowired
    public PanelKanban(TareaController tareaController) {
        this.tareaController = tareaController;

        // Usamos BorderLayout para dividir entre el formulario (Norte) y las columnas (Centro)
        setLayout(new BorderLayout(10, 10));

        // --- 1. Crear el Formulario de Agregación Rápida ---
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtTituloTarea = new JTextField(25);
        btnAgregarTarea = new JButton(" + Agregar Tarea");

        panelSuperior.add(new JLabel("Nueva Tarea:"));
        panelSuperior.add(txtTituloTarea);
        panelSuperior.add(btnAgregarTarea);

        this.add(panelSuperior, BorderLayout.NORTH);

        // --- 2. Crear el Contenedor de Columnas ---
        panelColumnas = new JPanel(new GridLayout(1, 3, 10, 10));

        panelPendientes = crearColumnaKanban("PENDIENTE", "Tareas Pendientes");
        panelProceso = crearColumnaKanban("EN_PROCESO", "En Proceso");
        panelCompletadas = crearColumnaKanban("COMPLETADA", "Completadas");

        panelColumnas.add(panelPendientes);
        panelColumnas.add(panelProceso);
        panelColumnas.add(panelCompletadas);

        this.add(panelColumnas, BorderLayout.CENTER);

        // --- 3. Listener del botón de Agregar ---
        btnAgregarTarea.addActionListener(e -> agregarNuevaTarea());
    }

    @PostConstruct
    public void inicializar() {
        cargarTareas();
    }

    private JPanel crearColumnaKanban(String estado, String titulo) {
        JPanel columna = new JPanel();
        // Usamos BoxLayout para que las tarjetas se apilen verticalmente
        columna.setLayout(new BoxLayout(columna, BoxLayout.Y_AXIS));
        columna.setBorder(BorderFactory.createTitledBorder(titulo));
        return columna;
    }

    /** Lógica de agregación de la tarea */
    private void agregarNuevaTarea() {
        String titulo = txtTituloTarea.getText().trim();
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Asumimos que el primer Tablero existente es el principal (ID 1, si lo inicializamos bien)
            // Llama al servicio para obtener el ID del tablero
            Long tableroId = tareaController.cargarTableros().stream().findFirst().get().getId();

            // Guardamos la tarea, por defecto en estado PENDIENTE y Prioridad 1
            tareaController.crearTarea(titulo, "Sin descripción", tableroId);

            txtTituloTarea.setText(""); // Limpiar el campo
            cargarTareas(); // Recargar el Kanban

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crear tarea: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Recarga de la vista y botones */
    public void cargarTareas() {
        panelPendientes.removeAll();
        panelProceso.removeAll();
        panelCompletadas.removeAll();

        try {
            List<Tarea> pendientes = tareaController.obtenerTareasPorEstado("PENDIENTE");
            List<Tarea> proceso = tareaController.obtenerTareasPorEstado("EN_PROCESO");
            List<Tarea> completadas = tareaController.obtenerTareasPorEstado("COMPLETADA");

            // Lógica de visualización
            pendientes.forEach(t -> panelPendientes.add(crearTarjetaTarea(t, "EN_PROCESO")));
            proceso.forEach(t -> panelProceso.add(crearTarjetaTarea(t, "COMPLETADA")));
            completadas.forEach(t -> panelCompletadas.add(crearTarjetaTarea(t, "PENDIENTE")));

        } catch (Exception e) {
            panelPendientes.add(new JLabel("Error al cargar tareas: " + e.getMessage()));
        }

        revalidate();
        repaint();
    }

    private JButton crearTarjetaTarea(Tarea tarea, String siguienteEstado) {
        JButton tarjeta = new JButton("<html><b>" + tarea.getTitulo() + "</b><br><small>Prioridad: " + tarea.getPrioridad() + "</small></html>");

        // CORRECCIÓN FINAL: Usamos la constante que ya fue importada estáticamente
        tarjeta.setAlignmentX(LEFT_ALIGNMENT);

        tarjeta.setToolTipText(tarea.getDescripcion());

        // Listener para mover la tarjeta (interacción clave)
        tarjeta.addActionListener(e -> {
            try {
                // LLAMADA CORREGIDA: Asumimos que TareaController.moverTarea devuelve Tarea
                tareaController.moverTarea(tarea.getId(), siguienteEstado);
                JOptionPane.showMessageDialog(this, "Tarea movida a " + siguienteEstado + "!", "Movimiento Exitoso", JOptionPane.INFORMATION_MESSAGE);
                cargarTareas(); // Recargar la vista para reflejar el cambio
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al mover la tarea: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
            }
        });
        return tarjeta;
    }
}

package com.ProductividadPersonal.gestor_academico_productividad.vista.organizacion.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.organizacion.entidad.Tarea;
import com.ProductividadPersonal.gestor_academico_productividad.servicio.organizacion.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

@Component
public class PanelKanban extends JPanel {

    private final TareaService tareaService;

    private DefaultListModel<Tarea> modeloPendiente;
    private DefaultListModel<Tarea> modeloEnProceso;
    private DefaultListModel<Tarea> modeloCompletada;

    private JList<Tarea> listaPendiente;
    private JList<Tarea> listaEnProceso;
    private JList<Tarea> listaCompletada;

    @Autowired
    public PanelKanban(TareaService tareaService) {
        this.tareaService = tareaService;

        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(10,10,10,10));
        setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Organizacion", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // --- Panel columnas ---
        JPanel columnas = new JPanel(new GridLayout(1, 3, 15, 0));
        columnas.setBackground(new Color(245, 245, 245));

        // Inicializar modelos
        modeloPendiente = new DefaultListModel<>();
        modeloEnProceso = new DefaultListModel<>();
        modeloCompletada = new DefaultListModel<>();

        // Crear listas
        listaPendiente = crearLista(modeloPendiente, "PENDIENTE");
        listaEnProceso = crearLista(modeloEnProceso, "EN PROCESO");
        listaCompletada = crearLista(modeloCompletada, "COMPLETADA");

        columnas.add(new JScrollPane(listaPendiente));
        columnas.add(new JScrollPane(listaEnProceso));
        columnas.add(new JScrollPane(listaCompletada));

        add(columnas, BorderLayout.CENTER);

        // --- Panel de botones ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(245, 245, 245));

        JButton btnAgregar = crearBoton("Agregar Tarea", new Color(100,149,237));
        JButton btnMover = crearBoton("Mover Tarea", new Color(60,179,113));
        JButton btnBorrar = crearBoton("Borrar Tarea", new Color(220,20,60));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnMover);
        panelBotones.add(btnBorrar);

        add(panelBotones, BorderLayout.SOUTH);

        // --- Acciones ---
        btnAgregar.addActionListener(e -> agregarTarea());
        btnMover.addActionListener(e -> moverTarea());
        btnBorrar.addActionListener(e -> borrarTarea());

        // Cargar datos iniciales
        cargarTareas();
    }

    private JList<Tarea> crearLista(DefaultListModel<Tarea> modelo, String estado) {
        JList<Tarea> lista = new JList<>(modelo);
        lista.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getTitulo() + " (P:" + value.getPrioridad() + ")");
            label.setOpaque(true);
            label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            if (isSelected) label.setBackground(new Color(173,216,230));
            else label.setBackground(Color.WHITE);
            return label;
        });
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return lista;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setFocusPainted(false);
        return boton;
    }

    private void cargarTareas() {
        modeloPendiente.clear();
        modeloEnProceso.clear();
        modeloCompletada.clear();

        List<Tarea> pendientes = tareaService.obtenerTareasPorEstado("PENDIENTE");
        List<Tarea> enProceso = tareaService.obtenerTareasPorEstado("EN_PROCESO");
        List<Tarea> completadas = tareaService.obtenerTareasPorEstado("COMPLETADA");

        pendientes.forEach(modeloPendiente::addElement);
        enProceso.forEach(modeloEnProceso::addElement);
        completadas.forEach(modeloCompletada::addElement);
    }

    private void agregarTarea() {
        String titulo = JOptionPane.showInputDialog(this, "Título de la tarea:");
        if(titulo == null || titulo.trim().isEmpty()) return;

        Tarea nueva = new Tarea();
        nueva.setTitulo(titulo);
        nueva.setEstado("PENDIENTE");
        nueva.setPrioridad(3); // Baja por defecto

        // Guardar en BD usando el primer tablero disponible
        Tarea guardada = tareaService.guardarTarea(nueva, 1L);
        modeloPendiente.addElement(guardada);
    }

    private void moverTarea() {
        JList<Tarea> listaSeleccionada = listaPendiente.getSelectedIndex() != -1 ? listaPendiente :
                listaEnProceso.getSelectedIndex() != -1 ? listaEnProceso :
                        listaCompletada.getSelectedIndex() != -1 ? listaCompletada : null;
        if(listaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para mover.");
            return;
        }

        Tarea tarea = listaSeleccionada.getSelectedValue();
        String nuevoEstado = (String) JOptionPane.showInputDialog(this,
                "Mover tarea a:",
                "Mover",
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"PENDIENTE", "EN PROCESO", "COMPLETADA"},
                tarea.getEstado());
        if(nuevoEstado == null || nuevoEstado.equals(tarea.getEstado())) return;

        tareaService.moverTarea(tarea.getId(), nuevoEstado);
        cargarTareas();
    }

    private void borrarTarea() {
        JList<Tarea> listaSeleccionada = listaPendiente.getSelectedIndex() != -1 ? listaPendiente :
                listaEnProceso.getSelectedIndex() != -1 ? listaEnProceso :
                        listaCompletada.getSelectedIndex() != -1 ? listaCompletada : null;
        if(listaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para borrar.");
            return;
        }
        Tarea tarea = listaSeleccionada.getSelectedValue();
        int resp = JOptionPane.showConfirmDialog(this, "¿Borrar tarea \""+tarea.getTitulo()+"\"?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION) {
            tareaService.borrarTarea(tarea.getId());
            cargarTareas();
        }
    }
}

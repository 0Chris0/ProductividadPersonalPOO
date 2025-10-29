package com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.servicio.productividad.HabitoService;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.productividad.entidad.Habito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

@Component
public class PanelHabitos extends JPanel {

    private final HabitoService habitoService;

    private DefaultListModel<Habito> modeloHabitos;
    private JList<Habito> listaHabitos;
    private JButton btnAgregar, btnBorrar, btnCompletado;

    @Autowired
    public PanelHabitos(HabitoService habitoService) {
        this.habitoService = habitoService;

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión y Seguimiento de Hábitos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // Lista de hábitos
        modeloHabitos = new DefaultListModel<>();
        listaHabitos = new JList<>(modeloHabitos);
        listaHabitos.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollLista = new JScrollPane(listaHabitos);
        add(scrollLista, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAgregar = new JButton("Agregar Hábito");
        btnBorrar = new JButton("Borrar Hábito");
        btnCompletado = new JButton("Marcar Completado");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnCompletado);

        add(panelBotones, BorderLayout.SOUTH);

        // Acciones
        btnAgregar.addActionListener(e -> agregarHabito());
        btnBorrar.addActionListener(e -> borrarHabito());
        btnCompletado.addActionListener(e -> marcarCompletado());

        cargarHabitos();
    }

    private void cargarHabitos() {
        modeloHabitos.clear();
        List<Habito> habitos = habitoService.obtenerTodosLosHabitos();
        for (Habito h : habitos) {
            modeloHabitos.addElement(h);
        }
    }

    private void agregarHabito() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del hábito:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            Habito h = new Habito();
            h.setNombre(nombre.trim());
            habitoService.guardarHabito(h);
            modeloHabitos.addElement(h);
        }
    }

    private void borrarHabito() {
        Habito seleccionado = listaHabitos.getSelectedValue();
        if (seleccionado != null) {
            habitoService.borrarHabito(seleccionado.getId());
            modeloHabitos.removeElement(seleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un hábito para borrar.");
        }
    }

    private void marcarCompletado() {
        Habito seleccionado = listaHabitos.getSelectedValue();
        if (seleccionado != null) {
            habitoService.registrarHabitoCompletado(seleccionado.getId());
            JOptionPane.showMessageDialog(this, "Hábito marcado como completado ✔");
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un hábito para completar.");
        }
    }
}

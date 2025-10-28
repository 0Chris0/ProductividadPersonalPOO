package com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.controlador.ProductividadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;

@Component
public class PanelHabitos extends JPanel {

    private final ProductividadController productividadController;
    private JPanel panelListaHabitos;

    @Autowired
    public PanelHabitos(ProductividadController productividadController) {
        this.productividadController = productividadController;
        setLayout(new BorderLayout());

        add(new JLabel("GESTIÓN Y SEGUIMIENTO DE HÁBITOS", SwingConstants.CENTER), BorderLayout.NORTH);

        panelListaHabitos = new JPanel();
        panelListaHabitos.setLayout(new BoxLayout(panelListaHabitos, BoxLayout.Y_AXIS));
        add(new JScrollPane(panelListaHabitos), BorderLayout.CENTER);

        cargarHabitosVista();
    }

    private void cargarHabitosVista() {
        // Simulación de carga y creación de botones
        productividadController.cargarHabitos().forEach(habito -> {
            JButton btnHabito = new JButton("Marcar: " + habito.getNombre());
            btnHabito.addActionListener(e -> {
                // Llama al controlador para marcar como completado
                productividadController.marcarHábitoComoCompletado(habito.getId());
                JOptionPane.showMessageDialog(this, habito.getNombre() + " marcado hoy!");
            });
            panelListaHabitos.add(btnHabito);
        });
        revalidate();
        repaint();
    }
}
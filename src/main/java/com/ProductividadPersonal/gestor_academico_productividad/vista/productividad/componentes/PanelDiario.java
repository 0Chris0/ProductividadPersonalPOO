package com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.controlador.ProductividadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

@Component
public class PanelDiario extends JPanel {

    private final ProductividadController productividadController;
    private JTextArea areaDiario;
    private JButton btnGuardar;

    @Autowired
    public PanelDiario(ProductividadController productividadController) {
        this.productividadController = productividadController;
        setLayout(new BorderLayout(10, 10));

        add(new JLabel("DIARIO DE PRODUCTIVIDAD", SwingConstants.CENTER), BorderLayout.NORTH);

        areaDiario = new JTextArea("Escribe aquí tus reflexiones del día...", 10, 40);
        btnGuardar = new JButton("Guardar Entrada (" + LocalDate.now() + ")");

        add(new JScrollPane(areaDiario), BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardarEntrada());
    }

    private void guardarEntrada() {
        String contenido = areaDiario.getText();
        if (contenido.length() > 20) {
            productividadController.guardarEntradaDiario(contenido);
            JOptionPane.showMessageDialog(this, "Entrada del diario guardada con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "El diario debe tener al menos 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
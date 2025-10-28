package com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.vista.academico.controlador.EvaluacionController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

 // Importante para la carga diferida
import javax.swing.*;
import java.awt.*;

@Component
public class PanelCalculadora extends JPanel {

    private final EvaluacionController evaluacionController;
    private JTextArea areaResultados;
    private JTextField txtModulo;
    private JButton btnCalcular;

    @Autowired
    public PanelCalculadora(EvaluacionController evaluacionController) {
        this.evaluacionController = evaluacionController;

        // Configuración visual (segura en el constructor)
        setLayout(new BorderLayout(10, 10));

        // ... inicialización de txtModulo y btnCalcular ...
        txtModulo = new JTextField(15);
        btnCalcular = new JButton("Calcular Promedio");
        areaResultados = new JTextArea();

        // ... añadir componentes al panel ...

        // Listener (la lógica de cálculo se llama solo al hacer clic)
        btnCalcular.addActionListener(e -> calcularYMostrarPromedio());
    }

    // El método PostConstruct no es estrictamente necesario aquí, pero se usa para consistencia.
    @PostConstruct
    public void inicializar() {
        areaResultados.setText("Sistema de Cálculo de Notas listo.");
    }

    private void calcularYMostrarPromedio() {
        String modulo = txtModulo.getText().trim();
        // Llama al controlador para obtener el promedio
        double promedio = evaluacionController.calcularPromedioModulo(modulo);
        areaResultados.setText(String.format("Promedio ponderado para %s:\n%.2f", modulo, promedio));
    }
}
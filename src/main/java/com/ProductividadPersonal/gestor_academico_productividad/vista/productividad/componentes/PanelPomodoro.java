package com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.controlador.ProductividadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class PanelPomodoro extends JPanel {

    private final ProductividadController productividadController;
    private JLabel lblTiempo;
    private JButton btnIniciar;
    private Timer timer;
    private int segundosTotales = 25 * 60; // 25 minutos

    @Autowired
    public PanelPomodoro(ProductividadController productividadController) {
        this.productividadController = productividadController;
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Configuración del Label de Tiempo
        lblTiempo = new JLabel("25:00");
        lblTiempo.setFont(new Font("Monospaced", Font.BOLD, 48));

        btnIniciar = new JButton("Iniciar Enfoque");
        btnIniciar.addActionListener(e -> iniciarSesion());

        add(lblTiempo);
        add(btnIniciar);

        // Inicializar Timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarReloj();
            }
        });
    }

    private void iniciarSesion() {
        segundosTotales = 25 * 60; // Reinicia a 25 minutos
        timer.start();
        btnIniciar.setText("Detener");
        btnIniciar.addActionListener(e -> detenerSesion());
        JOptionPane.showMessageDialog(this, "Sesión Pomodoro Iniciada. ¡A enfocarse!");
    }

    private void detenerSesion() {
        timer.stop();
        // Llama al servicio para registrar el tiempo que se usó
        productividadController.registrarFinSesion(25);
        btnIniciar.setText("Iniciar Enfoque (Listo)");
    }

    private void actualizarReloj() {
        if (segundosTotales <= 0) {
            detenerSesion();
            JOptionPane.showMessageDialog(this, "¡Tiempo Terminado!");
            return;
        }
        segundosTotales--;
        int minutos = segundosTotales / 60;
        int segundos = segundosTotales % 60;
        lblTiempo.setText(String.format("%02d:%02d", minutos, segundos));
    }
}
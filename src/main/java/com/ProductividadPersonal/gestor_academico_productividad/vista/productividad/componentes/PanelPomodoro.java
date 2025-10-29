package com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes;

import javax.swing.*;
import java.awt.*;

import org.springframework.stereotype.Component;

@Component
public class PanelPomodoro extends JPanel {

    private JLabel labelTiempo;
    private JButton botonIniciar, botonPausa;
    private JSpinner spinnerMinutos;
    private Timer timer;
    private int segundos;

    private boolean pausado = false;

    public PanelPomodoro() {
        setLayout(new BorderLayout(20,20));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titulo = new JLabel("Temporizador Pomodoro", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(10,10));
        labelTiempo = new JLabel("25:00", SwingConstants.CENTER);
        labelTiempo.setFont(new Font("Arial", Font.BOLD, 48));
        centro.add(labelTiempo, BorderLayout.CENTER);

        spinnerMinutos = new JSpinner(new SpinnerNumberModel(25, 1, 120, 1));
        JPanel panelSpinner = new JPanel();
        panelSpinner.add(new JLabel("Minutos:"));
        panelSpinner.add(spinnerMinutos);
        centro.add(panelSpinner, BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        botonIniciar = new JButton("Iniciar");
        botonPausa = new JButton("Pausar");
        botonPausa.setEnabled(false); // Al inicio está deshabilitado
        panelBotones.add(botonIniciar);
        panelBotones.add(botonPausa);
        add(panelBotones, BorderLayout.SOUTH);

        // Timer
        timer = new Timer(1000, e -> {
            if(segundos > 0){
                segundos--;
                int min = segundos / 60;
                int sec = segundos % 60;
                labelTiempo.setText(String.format("%02d:%02d", min, sec));
            } else {
                timer.stop();
                JOptionPane.showMessageDialog(PanelPomodoro.this, "¡Tiempo terminado!", "Pomodoro", JOptionPane.INFORMATION_MESSAGE);
                botonPausa.setEnabled(false);
            }
        });

        // Acciones
        botonIniciar.addActionListener(e -> {
            int minutos = (Integer) spinnerMinutos.getValue();
            segundos = minutos * 60;
            labelTiempo.setText(String.format("%02d:%02d", minutos, 0));
            timer.restart();
            pausado = false;
            botonPausa.setText("Pausar");
            botonPausa.setEnabled(true);
        });

        botonPausa.addActionListener(e -> {
            if(pausado){
                timer.start();
                botonPausa.setText("Pausar");
                pausado = false;
            } else {
                timer.stop();
                botonPausa.setText("Continuar");
                pausado = true;
            }
        });
    }
}

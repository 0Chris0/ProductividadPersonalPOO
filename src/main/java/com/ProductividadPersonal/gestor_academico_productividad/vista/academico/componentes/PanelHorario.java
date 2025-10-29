package com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import org.springframework.stereotype.Component;

@Component
public class PanelHorario extends JPanel {

    private JTable tablaHorario;

    public PanelHorario() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Título
        JLabel titulo = new JLabel("Horario Semanal", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // Columnas: días de la semana
        String[] columnas = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

        // Filas: ejemplo de horas
        String[][] datos = new String[10][6];
        int hora = 7; // empieza desde 7:00
        for (int i = 0; i < 10; i++) {
            datos[i][0] = hora + ":00 - " + (hora + 1) + ":00";
            for (int j = 1; j < 6; j++) {
                datos[i][j] = ""; // inicialmente vacío
            }
            hora++;
        }

        // Tabla
        tablaHorario = new JTable(new DefaultTableModel(datos, columnas));
        tablaHorario.setRowHeight(40);
        tablaHorario.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaHorario.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaHorario.setFillsViewportHeight(true);

        // Scroll
        JScrollPane scrollPane = new JScrollPane(tablaHorario);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Método opcional para llenar horarios desde tu base de datos
    public void agregarClase(int fila, int columna, String clase) {
        tablaHorario.setValueAt(clase, fila, columna);
    }
}

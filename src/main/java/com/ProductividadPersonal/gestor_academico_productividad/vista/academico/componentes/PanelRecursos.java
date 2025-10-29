package com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Recurso;
import com.ProductividadPersonal.gestor_academico_productividad.vista.academico.controlador.AcademicoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.List;

@Component
public class PanelRecursos extends JPanel {

    private final AcademicoController academicoController;
    private JTable tablaRecursos;
    private DefaultTableModel modeloTabla;

    @Autowired
    public PanelRecursos(AcademicoController academicoController) {
        this.academicoController = academicoController;

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Título
        JLabel titulo = new JLabel("REPOSITORIO DE RECURSOS", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Inicialización de la tabla
        String[] columnas = {"Clase", "Nombre del Recurso", "URL"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            // Evita que se editen las celdas
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaRecursos = new JTable(modeloTabla);
        tablaRecursos.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tablaRecursos.setRowHeight(30);
        tablaRecursos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        // Scroll
        JScrollPane scrollPane = new JScrollPane(tablaRecursos);
        add(scrollPane, BorderLayout.CENTER);

        // Click en la columna URL para abrir link
        tablaRecursos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaRecursos.rowAtPoint(e.getPoint());
                int columna = tablaRecursos.columnAtPoint(e.getPoint());
                if (columna == 2) { // Columna URL
                    String url = (String) modeloTabla.getValueAt(fila, columna);
                    abrirUrl(url);
                }
            }
        });

        // Cargar datos iniciales (ejemplo)
        cargarRecursosVista("POO"); // módulo de prueba
    }

    public void cargarRecursosVista(String modulo) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        try {
            List<Recurso> recursos = academicoController.cargarRecursos(modulo);

            for (Recurso r : recursos) {
                // Para probar, usamos "Clic para abrir"
                modeloTabla.addRow(new Object[]{modulo, r.getNombre(), "Click para abrir"});
            }
        } catch (Exception e) {
            System.err.println("Error al cargar recursos: " + e.getMessage());
        }
    }

    // Abrir URL en navegador por defecto
    private void abrirUrl(String url) {
        try {
            if (url == null || url.isEmpty()) return;
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el enlace: " + url, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

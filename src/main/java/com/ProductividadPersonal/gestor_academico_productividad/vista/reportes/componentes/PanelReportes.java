package com.ProductividadPersonal.gestor_academico_productividad.vista.reportes.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.vista.reportes.controlador.ReporteController;
import com.ProductividadPersonal.gestor_academico_productividad.modelo.reportes.entidad.ReporteSemanal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

@Component
public class PanelReportes extends JPanel {

    private final ReporteController reporteController;
    private JTable tablaReportes;
    private DefaultTableModel modeloTabla;

    @Autowired
    public PanelReportes(ReporteController reporteController) {
        this.reporteController = reporteController;
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("REPORTES SEMANALES DE PRODUCTIVIDAD", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        add(titulo, BorderLayout.NORTH);

        // Columnas adaptadas a ReporteSemanal
        String[] columnas = {
                "Semana",
                "Promedio Académico",
                "Tareas Completadas",
                "Tareas Pendientes",
                "Minutos de Enfoque",
                "Hábitos Completados",
                "Tasa Completada (%)"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla no editable
            }
        };

        tablaReportes = new JTable(modeloTabla);
        tablaReportes.setRowHeight(30);
        tablaReportes.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaReportes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tablaReportes);
        add(scroll, BorderLayout.CENTER);

        // Ejemplo inicial
        cargarDatosEjemplo();
    }

    private void cargarDatosEjemplo() {
        modeloTabla.addRow(new Object[]{"01/10/2025 - 07/10/2025", 85.5, 5, 2, 120, 3, "71.43%"});
        modeloTabla.addRow(new Object[]{"08/10/2025 - 14/10/2025", 90.0, 6, 1, 150, 4, "85.71%"});
    }

    public void actualizarReportes() {
        modeloTabla.setRowCount(0); // limpiar tabla
        try {
            List<ReporteSemanal> reportes = reporteController.obtenerReportes();
            if(reportes != null && !reportes.isEmpty()) {
                for(ReporteSemanal r : reportes) {
                    modeloTabla.addRow(new Object[]{
                            r.getFechaInicio() + " - " + r.getFechaFin(),
                            r.getPromedioAcademico(),
                            r.getTareasCompletadas(),
                            r.getTareasPendientes(),
                            r.getTotalMinutosEnfoque(),
                            r.getHabitosCompletados(),
                            String.format("%.2f%%", r.getTasaCompletada())
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "No hay datos de reportes.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar reportes: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

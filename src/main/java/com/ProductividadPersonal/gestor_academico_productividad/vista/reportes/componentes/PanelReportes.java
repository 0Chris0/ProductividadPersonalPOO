package com.ProductividadPersonal.gestor_academico_productividad.vista.reportes.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.reportes.entidad.ReporteSemanal;
import com.ProductividadPersonal.gestor_academico_productividad.vista.reportes.controlador.ReporteController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.swing.*;
import java.awt.*;

@Component
public class PanelReportes extends JPanel {

    private final ReporteController reporteController;
    private JTextArea areaResumen;
    private JButton btnExportarPDF;

    @Autowired
    public PanelReportes(ReporteController reporteController) {
        this.reporteController = reporteController;
        setLayout(new BorderLayout(10, 10));

        // Inicialización visual (segura en el constructor)
        areaResumen = new JTextArea();
        areaResumen.setEditable(false);
        btnExportarPDF = new JButton("Exportar Reporte Semanal a PDF");

        add(new JLabel("ANÁLISIS DE DESEMPEÑO", SwingConstants.CENTER), BorderLayout.NORTH);
        add(new JScrollPane(areaResumen), BorderLayout.CENTER);
        add(btnExportarPDF, BorderLayout.SOUTH);

        btnExportarPDF.addActionListener(e -> reporteController.exportarReporte("PDF"));
    }

    // CRÍTICO: Ejecutar la carga de datos del reporte DESPUÉS de que todos los Services están listos.
    @PostConstruct
    public void inicializar() {
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        // Lógica de carga de datos que llama al Controller
        ReporteSemanal reporte = reporteController.generarDatosReporte();

        String resumen = String.format(
                // ... (Lógica de formato del reporte) ...
                "Promedio Académico: %.2f\n" +
                        "Tiempo de Enfoque Total: %d minutos",
                reporte.getPromedioAcademico(),
                reporte.getTotalMinutosEnfoque());

        areaResumen.setText(resumen);
    }
}
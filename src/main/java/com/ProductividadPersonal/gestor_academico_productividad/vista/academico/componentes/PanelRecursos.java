package com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.modelo.academico.entidad.Recurso;
import com.ProductividadPersonal.gestor_academico_productividad.vista.academico.controlador.AcademicoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

        JLabel titulo = new JLabel("REPOSITORIO DE RECURSOS", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Inicialización de la tabla
        String[] columnas = {"Clase", "Nombre del Recurso", "URL"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaRecursos = new JTable(modeloTabla);

        add(new JScrollPane(tablaRecursos), BorderLayout.CENTER);

        // Cargar datos iniciales (Ejemplo)
        cargarRecursosVista("POO"); // Carga recursos para un módulo de prueba
    }

    public void cargarRecursosVista(String modulo) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        try {
            List<Recurso> recursos = academicoController.cargarRecursos(modulo);

            for (Recurso r : recursos) {
                // Asumiendo que Recurso tiene métodos getClaseNombre(), getNombre(), getUrl()
                // modeloTabla.addRow(new Object[]{r.getClaseNombre(), r.getNombre(), r.getUrl()});
                // Por ahora, solo simulación ya que la Entidad Recurso no está completa.
                modeloTabla.addRow(new Object[]{modulo, r.getNombre(), "Click para abrir"});
            }
        } catch (Exception e) {
            System.err.println("Error al cargar recursos: " + e.getMessage());
        }
    }
}
package com.ProductividadPersonal.gestor_academico_productividad.vista.organizacion.componentes;

// Importaciones de los Paneles de todos los módulos
import com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes.PanelCalculadora;
import com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes.PanelHorario;
import com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes.PanelPomodoro;
import com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes.PanelHabitos;
import com.ProductividadPersonal.gestor_academico_productividad.vista.reportes.componentes.PanelReportes;

import javax.swing.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.BorderLayout;
import java.awt.Dimension;

@Component
public class FramePrincipal extends JFrame {

    @Autowired
    public FramePrincipal(
            // Módulo Organización
            PanelKanban panelKanban,
            // Módulo Académico
            PanelHorario panelHorario,
            PanelCalculadora panelCalculadora,
            // Módulo Productividad
            PanelPomodoro panelPomodoro,
            PanelHabitos panelHabitos,
            // Módulo Reportes
            PanelReportes panelReportes) {

        // Configuración básica del JFrame
        setTitle("Productividad Personal - Gestor Académico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CRÍTICO: Cierra el proceso Java
        setSize(new Dimension(1200, 800));
        setLayout(new BorderLayout());

        // Usar JTabbedPane para la navegación entre módulos
        JTabbedPane navegador = new JTabbedPane();

        // --- Integración de los Módulos (Pestañas) ---
        navegador.addTab("1. Organización (Kanban)", panelKanban);
        navegador.addTab("2. Horario/Clases", panelHorario);
        navegador.addTab("3. Calculadora de Notas", panelCalculadora);
        navegador.addTab("4. Pomodoro", panelPomodoro);
        navegador.addTab("5. Seguimiento de Hábitos", panelHabitos);
        navegador.addTab("6. Reportes", panelReportes);

        // Añadir el navegador a la ventana principal
        add(navegador, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Centrar en la pantalla
    }
}

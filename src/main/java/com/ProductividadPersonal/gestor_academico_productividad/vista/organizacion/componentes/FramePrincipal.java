package com.ProductividadPersonal.gestor_academico_productividad.vista.organizacion.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes.PanelCalculadora;
import com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes.PanelHorario;
import com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes.PanelPomodoro;
import com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes.PanelHabitos;
import com.ProductividadPersonal.gestor_academico_productividad.vista.reportes.componentes.PanelReportes;

import javax.swing.*;
import java.awt.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FramePrincipal extends JFrame {

    @Autowired
    public FramePrincipal(
            PanelKanban panelKanban,
            PanelHorario panelHorario,
            PanelCalculadora panelCalculadora,
            PanelPomodoro panelPomodoro,
            PanelHabitos panelHabitos,
            PanelReportes panelReportes) {

        setTitle("Productividad Personal - Gestor Académico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 800));
        setMinimumSize(new Dimension(1000, 700));
        setLayout(new BorderLayout());

        JTabbedPane navegador = new JTabbedPane();

        // Envolvemos cada panel en JScrollPane para scroll automático
        navegador.addTab("1. Organización", new JScrollPane(panelKanban));
        navegador.addTab("2. Horario", new JScrollPane(panelHorario));
        navegador.addTab("3. Calculadora de Notas", new JScrollPane(panelCalculadora));
        navegador.addTab("4. Pomodoro", new JScrollPane(panelPomodoro));
        navegador.addTab("5. Seguimiento de Hábitos", new JScrollPane(panelHabitos));
        navegador.addTab("6. Reportes", new JScrollPane(panelReportes));

        add(navegador, BorderLayout.CENTER);

        // Centramos la ventana
        setLocationRelativeTo(null);
    }
}

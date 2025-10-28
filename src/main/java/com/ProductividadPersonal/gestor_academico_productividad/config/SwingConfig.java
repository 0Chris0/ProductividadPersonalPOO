package com.ProductividadPersonal.gestor_academico_productividad.config;

import com.ProductividadPersonal.gestor_academico_productividad.vista.organizacion.componentes.FramePrincipal;
// Importaciones de todos los Paneles necesarios
import com.ProductividadPersonal.gestor_academico_productividad.vista.organizacion.componentes.PanelKanban;
import com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes.PanelHorario;
import com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes.PanelCalculadora;
import com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes.PanelPomodoro;
import com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes.PanelHabitos;
import com.ProductividadPersonal.gestor_academico_productividad.vista.reportes.componentes.PanelReportes;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import javax.swing.UIManager;

@Configuration
public class SwingConfig {

    // Lógica para establecer el LookAndFeel debe ir aquí si queremos usarlo.
    public SwingConfig() {
        try {
            // Establece Nimbus LookAndFeel antes de que cualquier UI de Swing se cree
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("No se pudo establecer Look and Feel: " + e.getMessage());
        }
    }

    /**
     * Define el Frame Principal como un Bean de Spring, inyectando todos los paneles.
     * Esta es la ÚNICA forma correcta de inyectar el constructor de FramePrincipal.
     */
    @Bean
    public FramePrincipal framePrincipal(
            PanelKanban panelKanban,
            PanelHorario panelHorario,
            PanelCalculadora panelCalculadora,
            PanelPomodoro panelPomodoro,
            PanelHabitos panelHabitos,
            PanelReportes panelReportes
    ) {
        // Spring ya creó los 6 paneles; ahora los usamos para construir la ventana principal.
        return new FramePrincipal(
                panelKanban,
                panelHorario,
                panelCalculadora,
                panelPomodoro,
                panelHabitos,
                panelReportes
        );
    }
}

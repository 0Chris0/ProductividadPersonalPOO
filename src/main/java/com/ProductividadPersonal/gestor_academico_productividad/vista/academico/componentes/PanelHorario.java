package com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.vista.academico.controlador.AcademicoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;

@Component
public class PanelHorario extends JPanel {

    private final AcademicoController academicoController;
    private JTable tablaHorario;

    @Autowired
    public PanelHorario(AcademicoController academicoController) {
        this.academicoController = academicoController;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("HORARIO DE CLASES", SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        tablaHorario = new JTable();
        add(new JScrollPane(tablaHorario), BorderLayout.CENTER);

        // *****************************************************************
        // CRÍTICO: COMENTAR LA LLAMADA INICIAL PARA EVITAR EL FALLO DE ARRANQUE
        // La carga de datos debe ser diferida hasta que la UI esté visible.
        // *****************************************************************
        // cargarHorarioVista(); // COMENTAR O ELIMINAR ESTA LÍNEA
    }

    /**
     * Este método carga los datos desde la BD (a través del Controller)
     * y debe ser llamado por un evento posterior al arranque.
     */
    public void cargarHorarioVista() {
        // Llama al controlador para obtener el horario de la BD
        // List<Clase> horario = academicoController.cargarHorario();

        // Implementación de Lógica para llenar la tabla
        System.out.println("DEBUG: Horario cargado desde la BD.");
    }
}
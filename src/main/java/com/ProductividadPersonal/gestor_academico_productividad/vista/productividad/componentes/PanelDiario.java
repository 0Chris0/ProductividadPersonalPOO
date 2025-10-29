package com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.componentes;

import com.ProductividadPersonal.gestor_academico_productividad.vista.productividad.controlador.ProductividadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Component
public class PanelDiario extends JPanel {

    private final ProductividadController productividadController;

    private JTextArea areaDiario;
    private JButton btnAgregar, btnEditar, btnBorrar;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaEntradas;

    @Autowired
    public PanelDiario(ProductividadController productividadController) {
        this.productividadController = productividadController;
        initUI();
        cargarEntradas();
    }

    private void initUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Título
        JLabel titulo = new JLabel("Diario de Productividad", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(60, 60, 60));
        add(titulo, BorderLayout.NORTH);

        // Panel central
        JPanel centro = new JPanel(new GridLayout(1, 2, 20, 0));
        centro.setBackground(new Color(245, 245, 245));

        // Lista de entradas
        modeloLista = new DefaultListModel<>();
        listaEntradas = new JList<>(modeloLista);
        listaEntradas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaEntradas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        listaEntradas.setBackground(Color.WHITE);
        listaEntradas.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true));
        JScrollPane scrollLista = new JScrollPane(listaEntradas);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Entradas"));
        centro.add(scrollLista);

        // Área de texto
        areaDiario = new JTextArea();
        areaDiario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        areaDiario.setLineWrap(true);
        areaDiario.setWrapStyleWord(true);
        areaDiario.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true));
        JScrollPane scrollTexto = new JScrollPane(areaDiario);
        scrollTexto.setBorder(BorderFactory.createTitledBorder("Nueva Entrada / Editar"));
        centro.add(scrollTexto);

        add(centro, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(245, 245, 245));

        btnAgregar = crearBoton("Agregar Entrada", new Color(100, 149, 237));
        btnEditar = crearBoton("Editar Selección", new Color(60, 179, 113));
        btnBorrar = crearBoton("Borrar Selección", new Color(220, 20, 60));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnBorrar);

        add(panelBotones, BorderLayout.SOUTH);

        // Acciones
        btnAgregar.addActionListener(e -> agregarEntrada());
        btnEditar.addActionListener(e -> editarEntrada());
        btnBorrar.addActionListener(e -> borrarEntrada());
        listaEntradas.addListSelectionListener(e -> mostrarEntradaSeleccionada());
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        return boton;
    }

    private String formatearEntrada(String contenido) {
        return LocalDate.now() + ": " + contenido;
    }

    private void cargarEntradas() {
        modeloLista.clear();
        List<String> entradas = productividadController.obtenerEntradasDiario();
        if (entradas != null) {
            for (String e : entradas) {
                modeloLista.addElement(formatearEntrada(e));
            }
        }
    }

    private void mostrarEntradaSeleccionada() {
        int index = listaEntradas.getSelectedIndex();
        if (index != -1) {
            areaDiario.setText(productividadController.obtenerEntradasDiario().get(index));
        }
    }

    private void agregarEntrada() {
        String texto = areaDiario.getText().trim();
        if (texto.length() < 20) {
            JOptionPane.showMessageDialog(this, "La entrada debe tener al menos 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        productividadController.guardarEntradaDiario(texto);
        modeloLista.addElement(formatearEntrada(texto));
        areaDiario.setText("");
    }

    private void editarEntrada() {
        int index = listaEntradas.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una entrada para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String texto = areaDiario.getText().trim();
        if (texto.length() < 20) {
            JOptionPane.showMessageDialog(this, "La entrada debe tener al menos 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        productividadController.editarEntradaDiario(index, texto);
        modeloLista.set(index, formatearEntrada(texto));
        areaDiario.setText("");
    }

    private void borrarEntrada() {
        int index = listaEntradas.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una entrada para borrar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        productividadController.borrarEntradaDiario(index);
        modeloLista.remove(index);
        areaDiario.setText("");
    }
}

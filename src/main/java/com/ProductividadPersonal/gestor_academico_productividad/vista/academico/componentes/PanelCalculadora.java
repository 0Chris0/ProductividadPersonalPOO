package com.ProductividadPersonal.gestor_academico_productividad.vista.academico.componentes;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component // <--- Esto permite que Spring lo detecte como bean
public class PanelCalculadora extends JPanel {

    private JTextField display;
    private double primerValor = 0;
    private String operador = "";
    private boolean nuevoNumero = true; // Indica si se debe reemplazar el display

    public PanelCalculadora() {
        setLayout(new BorderLayout(10, 10));

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] botones = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String texto : botones) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(new BotonListener());
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.CENTER);
    }

    private class BotonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = ((JButton) e.getSource()).getText();

            if ("0123456789".contains(comando)) {
                if (nuevoNumero) {
                    display.setText(comando);
                    nuevoNumero = false;
                } else {
                    display.setText(display.getText() + comando);
                }
            } else if ("+-*/".contains(comando)) {
                try {
                    primerValor = Double.parseDouble(display.getText());
                } catch (NumberFormatException ex) {
                    primerValor = 0;
                }
                operador = comando;
                nuevoNumero = true;
            } else if ("=".equals(comando)) {
                calcular();
            } else if ("C".equals(comando)) {
                display.setText("");
                primerValor = 0;
                operador = "";
                nuevoNumero = true;
            }
        }

        private void calcular() {
            try {
                double segundoValor = Double.parseDouble(display.getText());
                double resultado = 0;
                switch (operador) {
                    case "+": resultado = primerValor + segundoValor; break;
                    case "-": resultado = primerValor - segundoValor; break;
                    case "*": resultado = primerValor * segundoValor; break;
                    case "/":
                        if (segundoValor != 0) {
                            resultado = primerValor / segundoValor;
                        } else {
                            JOptionPane.showMessageDialog(null, "No se puede dividir entre 0");
                            return;
                        }
                        break;
                    default:
                        return; // No hay operador seleccionado
                }
                display.setText(String.valueOf(resultado));
                primerValor = resultado; // Para cálculos encadenados
                nuevoNumero = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Número inválido");
            }
        }
    }
}

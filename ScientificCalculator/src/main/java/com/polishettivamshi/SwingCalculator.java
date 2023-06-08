/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polishettivamshi;

/**
 *
 * @author POLISHETTI VAMSHI
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Calculator extends JFrame {
    private final JTextField textField;
    private boolean number = true;
    private String equalOp = "=";
    private final CalculatorOp op = new CalculatorOp();

    public Calculator() {
        textField = new JTextField("", 12);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        Font BIGGER_FONT = new Font("monospaced", Font.PLAIN, 20);
        textField.setFont(BIGGER_FONT);
        ActionListener numberListener = new NumberListener();
        String buttonOrder = "1234567890 ";
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 4, 4));
        for (int i = 0; i < buttonOrder.length(); i++) {
            String key = buttonOrder.substring(i, i+1);
            if (key.equals(" ")) {
                buttonPanel.add(new JLabel(""));
            } else {
                JButton button = new JButton(key);
                button.addActionListener(numberListener);
                button.setFont(BIGGER_FONT);
                buttonPanel.add(button);
            }
        }
        ActionListener operatorListener = new OperatorListener();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 4, 4));
        String[] opOrder = {"+", "-", "*", "/","=","C","sin","cos","log"};
        for (String s : opOrder) {
            JButton button = new JButton(s);
            button.addActionListener(operatorListener);
            button.setFont(BIGGER_FONT);
            panel.add(button);
        }
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout(4, 4));
        pan.add(textField, BorderLayout.NORTH );
        pan.add(buttonPanel , BorderLayout.CENTER);
        pan.add(panel , BorderLayout.EAST);
        this.setContentPane(pan);
        this.pack();
        this.setTitle("Calculator");
        this.setResizable(false);
    }
    private void action() {
        number = true;
        textField.setText("");
        equalOp = "=";
        op.setTotal("");
    }
    class OperatorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String displayText = textField.getText();
            if (e.getActionCommand().equals("sin"))
            {
                textField.setText("" + Math.sin(Double.parseDouble(displayText)));

            }else
            if (e.getActionCommand().equals("cos"))
            {
                textField.setText("" + Math.cos(Double.parseDouble(displayText)));

            }
            else
            if (e.getActionCommand().equals("log"))
            {
                textField.setText("" + Math.log(Double.parseDouble(displayText)));

            }
            else if (e.getActionCommand().equals("C"))
            {
                textField.setText("");
            }

            else
            {
                if (number)
                {

                    action();
                    textField.setText("");

                }
                else
                {
                    number = true;
                    switch (equalOp) {
                        case " = " -> op.setTotal(displayText);
                        case "+" -> op.add(displayText);
                        case "-" -> op.subtract(displayText);
                        case "*" -> op.multiply(displayText);
                        case "/" -> op.divide(displayText);
                    }

                    textField.setText("" + op.getTotalString());
                    equalOp = e.getActionCommand();
                }
            }
        }
    }
    class NumberListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String digit = event.getActionCommand();
            if (number) {
                textField.setText(digit);
                number = false;
            } else {
                textField.setText(textField.getText() + digit);
            }
        }
    }
    public static class CalculatorOp {
        private int total;
        public CalculatorOp() {
            total = 0;
        }
        public String getTotalString() {
            return ""+total;
        }
        public void setTotal(String n) {
            total = convertToNumber(n);
        }
        public void add(String n) {
            total += convertToNumber(n);
        }
        public void subtract(String n) {
            total -= convertToNumber(n);
        }
        public void multiply(String n) {
            total *= convertToNumber(n);
        }
        public void divide(String n) {
            total /= convertToNumber(n);
        }
        private int convertToNumber(String n) {
            return Integer.parseInt(n);
        }
    }
}
class SwingCalculator {
    public static void main(String[] args) {
        JFrame frame = new Calculator();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


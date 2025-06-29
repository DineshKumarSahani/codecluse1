package myApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = "";

    public calculator() {
        setTitle("Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]") || cmd.equals(".")) {
            display.setText(display.getText() + cmd);
        } else if (cmd.matches("[+\\-*/]")) {
            try {
                num1 = Double.parseDouble(display.getText());
            } catch (NumberFormatException ex) {
                num1 = 0;
            }
            operator = cmd;
            display.setText("");
        } else if (cmd.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());
            } catch (NumberFormatException ex) {
                num2 = 0;
            }
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": 
                    if (num2 == 0) {
                        display.setText("Error");
                        return;
                    }
                    result = num1 / num2; 
                    break;
                default: result = num2;
            }
            display.setText(String.valueOf(result));
            operator = "";
        } else if (cmd.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            calculator calc = new calculator();
            calc.setVisible(true);
        });
    }
}

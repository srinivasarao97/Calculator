import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends Frame implements ActionListener {
    // Components
    private TextField textField;
    private Button[] buttons;

    // Variables to store operands and operator
    private double num1, num2, result;
    private char operator;

    public Calculator() {
        // Frame setup
        setTitle("Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());

        // Text Field for displaying input and result
        textField = new TextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        // Buttons setup
        buttons = new Button[16];
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (int i = 0; i < 16; i++) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // Window close listener
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            textField.setText(textField.getText() + command);
        } else if (command.matches("[*/\\-+]")) {
            num1 = Double.parseDouble(textField.getText());
            operator = command.charAt(0);
            textField.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }
            textField.setText(Double.toString(result));
        } else if (command.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = ' ';
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }
}

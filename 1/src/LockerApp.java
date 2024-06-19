import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApp {
    private static String Password = null;
    private static StringBuilder Enterpass = new StringBuilder();
    private JFrame frame;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public LockerApp() {
        frame = new JFrame("Locker Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(3, 3));

        JButton[] numberButtons = new JButton[9];
        for (int i = 1; i <= 9; i++) {
            numberButtons[i - 1] = new JButton(String.valueOf(i));
            numberButtons[i - 1].setPreferredSize(new Dimension(50, 50));
            numberButtons[i - 1].addActionListener(new NumberButtonListener());
            numberPanel.add(numberButtons[i - 1]);
        }

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        passwordField = new JPasswordField(10);
        passwordField.setEditable(false);
        JButton enterButton = new JButton("Enter");
        JButton clearButton = new JButton("Clear");

        controlPanel.add(clearButton);
        controlPanel.add(passwordField);
        controlPanel.add(enterButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPass = new String(passwordField.getPassword());
                if (Password == null) {
                    Password = enteredPass;
                    statusLabel.setText("Password Set");
                } else {
                    if (Password.equals(enteredPass)) {
                        statusLabel.setText("Correct Password");
                    } else {
                        statusLabel.setText("Incorrect Password");
                    }
                }
                Enterpass.setLength(0);
                passwordField.setText("");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Enterpass.setLength(0);
                passwordField.setText("");
            }
        });

        frame.add(numberPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.add(statusLabel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            Enterpass.append(source.getText());
            passwordField.setText(Enterpass.toString());
        }
    }
}

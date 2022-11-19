package userlogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class UserLoginView extends JFrame implements Observer, ActionListener {

    private final UserLoginController controller;

    private JTextField usernameField;
    private JTextField passwordField;

    public UserLoginView(UserLoginController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        UserLoginViewModel viewModel = (UserLoginViewModel) o;

        setVisible(viewModel.isVisible());

        System.out.println(viewModel.getFailed() + "" + viewModel.getFailedReason());
        if (viewModel.getFailed()) {
            if (viewModel.getFailedReason() == LoginStatus.INVALID_USER) {
                JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (viewModel.getFailedReason() == LoginStatus.INCORRECT_PASSWORD) {
                JOptionPane.showMessageDialog(this, "Incorrect password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void init() {
        JLabel header = new JLabel("Inventory Management System");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        usernameField = new JTextField("Username");
        passwordField = new JTextField("Password");

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        header.setBounds(50, 0, 500, 40);
        usernameField.setBounds(50, 50 , 300, 30);
        passwordField.setBounds(50, 100, 300, 30);
        loginButton.setBounds(50, 200,100, 30);

        add(header);
        add(usernameField);
        add(passwordField);
        add(loginButton);

        setLayout(null);
        setTitle("Inventory Management System");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("test");
        controller.login(usernameField.getText(), passwordField.getText());
    }
}

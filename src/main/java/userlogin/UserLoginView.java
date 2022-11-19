package userlogin;

import javax.swing.*;
import java.awt.*;

public class UserLoginView extends JFrame {

    private final UserLoginController controller;

    public UserLoginView(UserLoginController controller) {
        this.controller = controller;

        setName("Inventory Management System");

        JLabel header = new JLabel("Inventory Management System");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JTextField usernameField = new JTextField("Username");
        JTextField passwordField = new JTextField("Password");

        JButton loginButton = new JButton("Login");

        header.setBounds(50, 0, 500, 40);
        usernameField.setBounds(50, 50 , 300, 30);
        passwordField.setBounds(50, 100, 300, 30);
        loginButton.setBounds(50, 200,100, 30);

        add(header);
        add(usernameField);
        add(passwordField);
        add(loginButton);

        setSize(800, 1000);
        setLayout(null);
        setVisible(true);
    }
}

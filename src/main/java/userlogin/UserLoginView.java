package userlogin;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class UserLoginView extends JFrame implements Observer {

    private final UserLoginController controller;

    public UserLoginView(UserLoginController controller) {
        this.controller = controller;
    }

    public void enableView() {
        setVisible(true);
    }

    public void disableView() {
        setVisible(false);
    }

    @Override
    public void update(Observable o, Object arg) {
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

        setLayout(null);
        setTitle("Inventory Management System");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

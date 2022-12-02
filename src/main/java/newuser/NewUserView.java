package newuser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
import database.FacilityDbGateway;

public class NewUserView extends JFrame implements Observer, ActionListener {

    private final NewUserController controller;
    private JTextField usernameField;
    private JTextField passwordField;
    private JRadioButton storeButton;
    private JRadioButton warehouseButton;
    private JButton registerButton;
    private JComboBox storeList;
    private JComboBox warehouseList;
    private JComboBox uuidList;
    private ButtonGroup group;
    private UUID uuid;

    public NewUserView(NewUserController controller){
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg){
        NewUserViewModel viewModel = (NewUserViewModel) o;

        setVisible(viewModel.isVisible());

        System.out.println(viewModel.getFailed() + "" + viewModel.getFailReason());
        if (viewModel.getFailed()){
            if (viewModel.getFailReason() == NewUserStatus.USERNAME_EXISTS){
                JOptionPane.showMessageDialog(this,
                        "A user with that username is already registered", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (viewModel.getFailReason() == NewUserStatus.PASSWORD_TOO_SHORT){
                JOptionPane.showMessageDialog(this, "Password must be 5 characters or longer",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (viewModel.getFailReason() == NewUserStatus.NO_FACILITIES){
                JOptionPane.showMessageDialog(this, "There are currently no registered facilities of this type",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void init(){
        Object[] storeUUIDs = controller.getFacilityUUIDLists().get(0).toArray();
        Object[] warehouseUUIDs = controller.getFacilityUUIDLists().get(1).toArray();

        uuid = (UUID) storeUUIDs[0];

        storeList = new JComboBox(storeUUIDs);
        warehouseList = new JComboBox(warehouseUUIDs);

        JLabel header = new JLabel("Inventory Management System - New User");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        usernameField = new JTextField("Username");
        // want to add a "5 characters or longer" as a subtitle under password
        passwordField = new JTextField("Password (5 characters or longer)");

        this.storeButton = new JRadioButton("Store User");
        this.warehouseButton = new JRadioButton("Warehouse User");

        storeButton.setSelected(true);

        this.group = new ButtonGroup();
        group.add(storeButton);
        group.add(warehouseButton);

        this.registerButton = new JButton("Register");


        registerButton.addActionListener(this);
        storeButton.addActionListener(this);
        warehouseButton.addActionListener(this);
        storeList.addActionListener(this);
        warehouseList.addActionListener(this);

        header.setBounds(200, 0, 500, 40);
        usernameField.setBounds(200, 50, 300, 30);
        passwordField.setBounds(200, 100, 300, 30);
        storeButton.setBounds(100, 150, 300, 30);
        warehouseButton.setBounds(400, 150, 300, 30);
        registerButton.setBounds(200, 250, 100, 30);

        add(header);
        add(usernameField);
        add(passwordField);
        add(storeButton);
        add(warehouseButton);
        add(registerButton);

        setLayout(null);
        setTitle("Create New User");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == storeButton) {
            uuidList = storeList;
        } else if (e.getSource() == warehouseButton) {
            uuidList = warehouseList;
        } else if (e.getSource() == uuidList) {
            uuid = (UUID) uuidList.getSelectedItem();
        } else if (e.getSource() == registerButton) {
            if (storeButton.isSelected()) {
                controller.createStoreUser(usernameField.getText(), passwordField.getText(), uuid);
            } else {
                controller.createWarehouseUser(usernameField.getText(), passwordField.getText(), uuid);
            }
        }
    }
}

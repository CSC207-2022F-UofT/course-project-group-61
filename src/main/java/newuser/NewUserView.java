package newuser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class NewUserView extends JFrame implements Observer, ActionListener {

    private final NewUserController controller;
    private JTextField usernameField;
    private JTextField passwordField;
    private JRadioButton storeButton;
    private JRadioButton warehouseButton;
    private JButton registerButton;
    private JComboBox<String> storeList;
    private JComboBox<String> warehouseList;
    private ButtonGroup group;
    private HashMap<String, UUID> storeMap;
    private HashMap<String, UUID> warehouseMap;
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
        this.storeMap = controller.getFacilityUUIDLists().get(0);
        this.warehouseMap = controller.getFacilityUUIDLists().get(1);

        this.storeList = new JComboBox<>();
        this.warehouseList = new JComboBox<>();

        for (String storeName : storeMap.keySet()){
            storeList.addItem(storeName);
        }
        for (String warehouseName : warehouseMap.keySet()){
            warehouseList.addItem(warehouseName);
        }

        this.uuid = storeMap.get(Objects.requireNonNull(storeList.getSelectedItem()).toString());

        JLabel header = new JLabel("Inventory Management System - New User");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JLabel dropdownSubtitle = new JLabel("Choose the user's associated facility");
        dropdownSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JLabel radioButtonSubtitle = new JLabel("Choose the facility type of the user's associated facility");
        radioButtonSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));

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

        header.setBounds(100, 0, 500, 40);
        usernameField.setBounds(100, 50, 300, 30);
        passwordField.setBounds(100, 100, 300, 30);
        radioButtonSubtitle.setBounds(100, 135, 500, 20);
        storeButton.setBounds(100, 160, 150, 30);
        warehouseButton.setBounds(250, 160, 150, 30);
        dropdownSubtitle.setBounds(100, 200, 500, 20);
        storeList.setBounds(100, 230, 150, 40);
        warehouseList.setBounds(100, 230, 150, 40);
        registerButton.setBounds(100, 285, 125, 40);

        add(header);
        add(dropdownSubtitle);
        add(radioButtonSubtitle);
        add(usernameField);
        add(passwordField);
        add(storeButton);
        add(warehouseButton);
        add(registerButton);
        add(storeList);
        add(warehouseList);

        warehouseList.setVisible(false);

        setLayout(null);
        setTitle("Create New User");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String facilityName;
        if (e.getSource() == storeButton) {
            warehouseList.setVisible(false);
            storeList.setVisible(true);
        } else if (e.getSource() == warehouseButton) {
            storeList.setVisible(false);
            warehouseList.setVisible(true);
        } else if (e.getSource() == storeList) {
             facilityName = (String) storeList.getSelectedItem();
             this.uuid = storeMap.get(facilityName);
        } else if (e.getSource() == warehouseList) {
            facilityName = (String) warehouseList.getSelectedItem();
            this.uuid = storeMap.get(facilityName);
        } else if (e.getSource() == registerButton) {
            if (storeButton.isSelected()) {
                controller.createStoreUser(usernameField.getText(), passwordField.getText(), uuid);
            } else {
                controller.createWarehouseUser(usernameField.getText(), passwordField.getText(), uuid);
            }
        }
    }
}

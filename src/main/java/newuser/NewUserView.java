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
        // Get the two maps from controller/interactor that map facility name (String) to facility UUID.
        this.storeMap = controller.getFacilityUUIDLists().get(0);
        this.warehouseMap = controller.getFacilityUUIDLists().get(1);

        /* Create dropdown menus for stores and warehouses, where the user will select one facility.
         - Only one dropdown menu is visible at a time (depending on what type of facility the user selects)
         */
        this.storeList = new JComboBox<>();
        this.warehouseList = new JComboBox<>();

        for (String storeName : storeMap.keySet()){
            storeList.addItem(storeName);
        }
        for (String warehouseName : warehouseMap.keySet()){
            warehouseList.addItem(warehouseName);
        }

        // Set the default UUID as the first store in the store dropdown menu (null if empty)
        this.uuid = storeMap.get(storeList.getItemAt(0));

        JLabel header = new JLabel("Inventory Management System - New User");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JLabel dropdownSubtitle = new JLabel("Choose the user's associated facility");
        dropdownSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JLabel radioButtonSubtitle = new JLabel("Choose the facility type of the user's associated facility");
        radioButtonSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));

        // Add input fields for username and password of the new User
        usernameField = new JTextField("Username");
        passwordField = new JTextField("Password (5 characters or longer)");

        // Add radio buttons for the admin user to choose the new user's facility type
        this.storeButton = new JRadioButton("Store User");
        this.warehouseButton = new JRadioButton("Warehouse User");

        // Set the default value of facility type to store (matching the default dropdown value defined above)
        storeButton.setSelected(true);

        // Put both radio buttons in a group
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
        // Facility name from dropdown menu - used as a key in the Name to UUID hashmaps
        String facilityName;

        // If store button is clicked, switch dropdown menu from warehouselist to storelist
        if (e.getSource() == storeButton) {
            warehouseList.setVisible(false);
            storeList.setVisible(true);

            // Set UUID to the first (default) option in the store dropdown menu
            this.uuid = storeMap.get(storeList.getItemAt(0));

        // If warehouse button is clicked, switch dropdown menu from storelist to warehouselist
        } else if (e.getSource() == warehouseButton) {
            storeList.setVisible(false);
            warehouseList.setVisible(true);

            // Set UUID to the first (default) option in the warehouse dropdown menu
            this.uuid = warehouseMap.get(warehouseList.getItemAt(0));

        /* If the store dropdown menu is clicked, update the UUID with the facility ID that corresponds to the
           facility whose name was selected in the store dropdown menu.
         */
        } else if (e.getSource() == storeList) {
             facilityName = (String) storeList.getSelectedItem();
             this.uuid = storeMap.get(facilityName);

        /* If the warehouse dropdown menu is clicked, update the UUID with the facility ID that corresponds to the
           facility whose name was selected in the warehouse dropdown menu.
         */
        } else if (e.getSource() == warehouseList) {
            facilityName = (String) warehouseList.getSelectedItem();
            this.uuid = warehouseMap.get(facilityName);

        /* If the register button is clicked, attempt to create a new user, with the facility type and UUID
           corresponding to the options the user selected.
         */
        } else if (e.getSource() == registerButton) {
            if (storeButton.isSelected()) {
                controller.createStoreUser(usernameField.getText(), passwordField.getText(), uuid);
            } else {
                controller.createWarehouseUser(usernameField.getText(), passwordField.getText(), uuid);
            }
        }
    }
}

package adminmainmenu;

import entities.UserSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AdminMainMenuView extends JFrame implements Observer, ActionListener {

    private final AdminMainMenuController controller;

    private JButton newFacilityButton;
    private JButton newItemButton;
    private  JButton newUserButton;
    private JButton itemLookupButton;
    private JButton logoutButton;

    public AdminMainMenuView(AdminMainMenuController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        AdminMainMenuViewModel viewModel = (AdminMainMenuViewModel) o;
        setVisible(viewModel.isVisible());

        /* Initialize and format user info table. */
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Username"}, 0);
        JTable table = new JTable(dtm);
        JTableHeader tableHeader = table.getTableHeader();
        String[] row = new String[1];
        row[0] = UserSession.getUserSession().getUsername();
        dtm.addRow(row);

        /* Set table bounds. */
        tableHeader.setBounds(600, 50, 100, 20);
        table.setBounds(600, 70, 100, 20);

        add(tableHeader);
        add(table);
    }

    public void init() {
        /* Pop-up window labels and font settings. */
        JLabel header = new JLabel("Inventory Management System - Admin Main Menu");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        /* Initialize all buttons on window. */
        this.newFacilityButton = new JButton("New Facility");
        this.newItemButton = new JButton("New Item");
        this.newUserButton = new JButton("New User");
        this.itemLookupButton = new JButton("Item Lookup");
        this.logoutButton = new JButton("Logout");

        /* Add action listeners to buttons. */
        newFacilityButton.addActionListener(this);
        newItemButton.addActionListener(this);
        newUserButton.addActionListener(this);
        itemLookupButton.addActionListener(this);
        logoutButton.addActionListener(this);

        /* Set bounds of all buttons/table. */
        header.setBounds(50, 0, 500, 40);
        newFacilityButton.setBounds(50, 50, 200, 40);
        newItemButton.setBounds(50, 100, 200, 40);
        newUserButton.setBounds(50, 150, 200, 40);
        itemLookupButton.setBounds(50, 200, 200, 40);
        logoutButton.setBounds(50, 250, 200, 40);

        /* Place all buttons on window so that they're visible. */
        add(newFacilityButton);
        add(newItemButton);
        add(newUserButton);
        add(itemLookupButton);
        add(logoutButton);
        add(header);

        /* Set window dimensions. */
        setSize(800, 1000);
        setTitle("Inventory Management System");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /* Runs when any button is pressed, formats call to the controller based on what button was pressed. */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFacilityButton) {
            controller.chooseAction(ButtonOption.NEW_FACILITY);
        } else if (e.getSource() == newItemButton) {
            controller.chooseAction(ButtonOption.NEW_ITEM);
        } else if (e.getSource() == newUserButton) {
            controller.chooseAction(ButtonOption.NEW_USER);
        } else if (e.getSource() == itemLookupButton) {
            controller.chooseAction(ButtonOption.ITEM_LOOKUP);
        } else if (e.getSource() == logoutButton) {
            controller.logout();
        }
    }
}

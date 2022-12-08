package warehousemainmenu;

import entities.FacilityUser;
import entities.UserSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class WarehouseMainMenuView extends JFrame implements Observer, ActionListener {

    private final WarehouseMainMenuController controller;

    private JButton fulfillOrderButton;
    private  JButton invCountButton;
    private JButton itemLookupButton;

    private JButton logoutButton;

    public WarehouseMainMenuView(WarehouseMainMenuController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        WarehouseMainMenuViewModel viewModel = (WarehouseMainMenuViewModel) o;
        setVisible(viewModel.isVisible());

        /* Initialize and format user info table. */
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Username", "Facility ID"}, 0);
        JTable table = new JTable(dtm);
        JTableHeader tableHeader = table.getTableHeader();
        String[] row = new String[2];
        row[0] = UserSession.getUserSession().getUsername();
        row[1] = ((FacilityUser) UserSession.getUserSession()).getFacilityID().toString();
        dtm.addRow(row);

        /* Set table bounds. */
        tableHeader.setBounds(450, 50, 300, 20);
        table.setBounds(450, 70, 300, 20);

        add(tableHeader);
        add(table);
    }

    public void init() {
        /* Pop-up window labels and font settings. */
        JLabel header = new JLabel("Inventory Management System - Warehouse Main Menu");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        /* Initialize all buttons on window. */
        this.fulfillOrderButton = new JButton("Fulfill Order");
        this.invCountButton = new JButton("Input Inventory");
        this.itemLookupButton = new JButton("Item Lookup");
        this.logoutButton = new JButton("Logout");

        /* Add action listeners to buttons. */
        fulfillOrderButton.addActionListener(this);
        invCountButton.addActionListener(this);
        itemLookupButton.addActionListener(this);
        logoutButton.addActionListener(this);

        /* Set bounds of all buttons. */
        header.setBounds(50, 0, 500, 40);
        fulfillOrderButton.setBounds(50, 50, 200, 40);
        invCountButton.setBounds(50, 100, 200, 40);
        itemLookupButton.setBounds(50, 150, 200, 40);
        logoutButton.setBounds(50, 250, 200, 40);

        /* Place all buttons on window so that they're visible. */
        add(fulfillOrderButton);
        add(invCountButton);
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
        if (e.getSource() == fulfillOrderButton) {
            controller.chooseAction(ButtonOption.FULFILL_ORDER);
        } else if (e.getSource() == invCountButton) {
            controller.chooseAction(ButtonOption.INV_COUNT);
        } else if (e.getSource() == itemLookupButton) {
            controller.chooseAction(ButtonOption.ITEM_LOOKUP);
        } else if (e.getSource() == logoutButton) {
            controller.logout();
        }
    }
}

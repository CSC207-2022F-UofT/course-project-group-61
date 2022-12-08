package storemainmenu;

import database.FacilityDb;
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
import java.util.UUID;

public class StoreMainMenuView extends JFrame implements Observer, ActionListener {

    private final StoreMainMenuController controller;

    private JButton placeOrderButton;
    private JButton dailySalesButton;
    private  JButton invCountButton;
    private JButton itemLookupButton;
    private JButton logoutButton;

    public StoreMainMenuView(StoreMainMenuController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        StoreMainMenuViewModel viewModel = (StoreMainMenuViewModel) o;
        setVisible(viewModel.isVisible());

        /* Initialize and format user info table. */
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Username", "Facility Name", "Facility ID"}, 0){
            @Override
            public boolean isCellEditable(int row, int column){return false;}
        };
        JTable table = new JTable(dtm);
        JTableHeader tableHeader = table.getTableHeader();
        String[] row = new String[3];
        UUID facilityID = ((FacilityUser) UserSession.getUserSession()).getFacilityID();
        row[0] = UserSession.getUserSession().getUsername();
        row[1] = controller.facilityDB.getFacility(facilityID).getName();
        row[2] = facilityID.toString();
        dtm.addRow(row);

        /* Set table bounds. */
        tableHeader.setBounds(450, 50, 300, 20);
        tableHeader.setReorderingAllowed(false);
        table.setBounds(450, 70, 300, 20);

        add(tableHeader);
        add(table);
    }

    public void init() {
        /* Pop-up window labels and font settings. */
        JLabel header = new JLabel("Inventory Management System - Store Main Menu");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        /* Initialize all buttons on window. */
        this.placeOrderButton = new JButton("Place Order");
        this.dailySalesButton = new JButton("Input Daily Sales");
        this.invCountButton = new JButton("Input Inventory");
        this.itemLookupButton = new JButton("Item Lookup");
        this.logoutButton = new JButton("Logout");

        /* Add action listeners to buttons. */
        placeOrderButton.addActionListener(this);
        dailySalesButton.addActionListener(this);
        invCountButton.addActionListener(this);
        itemLookupButton.addActionListener(this);
        logoutButton.addActionListener(this);

        /* Set bounds of all buttons. */
        header.setBounds(50, 0, 500, 40);
        placeOrderButton.setBounds(50, 50, 200, 40);
        dailySalesButton.setBounds(50, 100, 200, 40);
        invCountButton.setBounds(50, 150, 200, 40);
        itemLookupButton.setBounds(50, 200, 200, 40);
        logoutButton.setBounds(50, 250, 200, 40);

        /* Place all buttons on window so that they're visible. */
        add(placeOrderButton);
        add(dailySalesButton);
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
        if (e.getSource() == placeOrderButton) {
            controller.chooseAction(ButtonOption.PLACE_ORDER);
        } else if (e.getSource() == dailySalesButton) {
            controller.chooseAction(ButtonOption.DAILY_SALES);
        } else if (e.getSource() == invCountButton) {
            controller.chooseAction(ButtonOption.INV_COUNT);
        } else if (e.getSource() == itemLookupButton) {
            controller.chooseAction(ButtonOption.ITEM_LOOKUP);
        } else if (e.getSource() == logoutButton) {
            controller.logout();
        }
    }
}

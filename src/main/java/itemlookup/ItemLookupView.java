package itemlookup;

import userlogin.LoginStatus;
import userlogin.UserLoginController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ItemLookupView extends JFrame implements Observer, ActionListener {

    private final ItemLookupController controller;

    private JTextField inputField;
    private JRadioButton UPCButton;
    private JRadioButton NameButton;
    private JButton searchButton;
    private JTable returnTable;
    private JTable inventoryTable;
    private DefaultTableModel dtm;
    private DefaultTableModel dtmi;
    private boolean UPCPressed = false;
    private boolean NamePressed = false;

    public ItemLookupView(ItemLookupController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        ItemLookupViewModel viewModel = (ItemLookupViewModel) o;

        if (!Objects.isNull(viewModel.getInfoList())) {
            String[] row = new String[3];
            row[0] = viewModel.getInfoList().get(0);
            row[1] = viewModel.getInfoList().get(1);
            row[2] = viewModel.getInfoList().get(2);
            dtm.setRowCount(0);
            dtm.addRow(row);

            dtmi.setRowCount(0);
            for (int i = 0; i < (viewModel.getInfoList().size() - 3) / 2; i = i+2) {
                String[] invRow = new String[2];
                invRow[0] = viewModel.getInfoList().get(3 + i);
                invRow[1] = viewModel.getInfoList().get(4 + i);
                dtmi.addRow(invRow);
            }
        }

        setVisible(viewModel.isVisible());

        System.out.println(viewModel.getFailed() + "" + viewModel.getFailedReason());
        if (viewModel.getFailed()) {
            if (viewModel.getFailedReason() == FailReason.INVALID_UPC) {
                JOptionPane.showMessageDialog(this, "Item UPC not found.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (viewModel.getFailedReason() == FailReason.INVALID_NAME) {
                JOptionPane.showMessageDialog(this, "Item name not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void init() {
        JLabel header = new JLabel("Inventory Management System");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        dtm = new DefaultTableModel(new String[]{"Name", "UPC", "Price"}, 0);
        dtmi = new DefaultTableModel(new String[]{"Warehouse ID", "Quantity"}, 0);
        returnTable = new JTable(dtm);
        JTableHeader rtHeader = returnTable.getTableHeader();
        inventoryTable = new JTable(dtmi);
        JTableHeader itHeader = inventoryTable.getTableHeader();

        inputField = new JTextField("Item Search");

        this.UPCButton = new JRadioButton("Lookup by UPC");
        this.NameButton = new JRadioButton("Lookup by Name");
        this.searchButton = new JButton("Search");

        ButtonGroup group = new ButtonGroup();
        group.add(UPCButton);
        group.add(NameButton);

        UPCButton.addActionListener(this);
        NameButton.addActionListener(this);
        searchButton.addActionListener(this);

        header.setBounds(50, 0, 500, 40);
        UPCButton.setBounds(50, 50 , 300, 30);
        NameButton.setBounds(50, 100, 300, 30);
        inputField.setBounds(50, 200,300, 30);
        searchButton.setBounds(350, 200, 100, 30);
        returnTable.setBounds(50, 280, 700, 30);
        rtHeader.setBounds(50, 250, 700, 30);
        inventoryTable.setBounds(50, 340, 700, 100);
        itHeader.setBounds(50, 310, 700, 30);


        add(header);
        add(UPCButton);
        add(NameButton);
        add(inputField);
        add(searchButton);
        add(returnTable);
        add(inventoryTable);
        add(rtHeader);
        add(itHeader);

        setLayout(null);
        setTitle("Inventory Management System");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == UPCButton) {
            UPCPressed = true;
            NamePressed = false;
        } else if (e.getSource() == NameButton) {
            UPCPressed = false;
            NamePressed = true;
        } else if (e.getSource() == searchButton && !Objects.equals(inputField.getText(), "Search") && UPCPressed) {
            controller.lookupByUPC(Long.parseLong(inputField.getText()));
        } else if (e.getSource() == searchButton && !Objects.equals(inputField.getText(), "Search") && NamePressed) {
            controller.lookupByName(inputField.getText());
        }



        System.out.println("test");
    }
}

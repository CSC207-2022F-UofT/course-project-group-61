package itemlookup;

import userlogin.LoginStatus;
import userlogin.UserLoginController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private DefaultTableModel dtm;
    private boolean UPCPressed = false;
    private boolean NamePressed = false;

    public ItemLookupView(ItemLookupController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        ItemLookupViewModel viewModel = (ItemLookupViewModel) o;


        String[] row = (String[]) viewModel.getInfoList().toArray();
        dtm.setRowCount(0);
        dtm.addRow(row);

        //String[] columnNames = {"Name", "UPC", "Price"};
        /*Object[][] data = {{viewModel.getInfoList()}};
        returnTable = new JTable(data, columnNames);*/

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

        /*String[] columnNames = {"Name", "UPC", "Price"};
        Object[][] data = {{"Apple", 4001L, 5}};*/

        //ArrayList<String> columnNames = new ArrayList<String>(Arrays.asList("Name", "UPC", "Price"));
        //ArrayList<Object> data = new ArrayList<>();

        dtm = new DefaultTableModel(new Object[]{"Name", "UPC", "Price"}, 0);
        returnTable = new JTable(dtm);

        inputField = new JTextField("Item Search");

        this.UPCButton = new JRadioButton("Lookup by UPC");
        this.NameButton = new JRadioButton("Lookup by Name");
        this.searchButton = new JButton("Search");

        //UPCButton.setSelected(true);
        //UPCPressed = true;

        ButtonGroup group = new ButtonGroup();
        group.add(UPCButton);
        group.add(NameButton);

        //JButton UPCButton = new JButton("Lookup by UPC");
        //JButton NameButton = new JButton("Lookup by Name");

        UPCButton.addActionListener(this);
        NameButton.addActionListener(this);
        searchButton.addActionListener(this);


        header.setBounds(50, 0, 500, 40);
        UPCButton.setBounds(50, 50 , 300, 30);
        NameButton.setBounds(50, 100, 300, 30);
        inputField.setBounds(50, 200,300, 30);
        searchButton.setBounds(350, 200, 100, 30);
        returnTable.setBounds(50, 250, 700, 100);

        add(header);
        add(UPCButton);
        add(NameButton);
        add(inputField);
        add(searchButton);
        add(returnTable);

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

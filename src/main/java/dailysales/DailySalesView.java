package dailysales;

import utils.IntegerFilter;
import utils.UPCFilter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class DailySalesView extends JFrame implements Observer, ActionListener {

    private final DailySalesController controller;
    private DefaultTableModel rawTableData;
    private JTextField upc;
    private JTextField quantity;
    private JButton add;
    private JTable table;
    private JButton submit;
    private HashMap<Long, Integer> salesContents;
    private JButton returnToMenuButton;

    public DailySalesView(DailySalesController controller) {
        this.controller = controller;
        init();
        salesContents = new HashMap<>();
    }

    private void init() {
        JLabel header = new JLabel("Inventory Management System - Daily Sales");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));
        header.setBounds(50, 0, 500, 40);

        JLabel upcLabel = new JLabel("UPC");
        upcLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        upcLabel.setBounds(55, 35, 200, 20);
        upc = new JTextField();
        upc.setBounds(50, 50 , 300, 30);
        PlainDocument upcDoc = (PlainDocument) upc.getDocument();
        upcDoc.setDocumentFilter(new UPCFilter());

        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        quantityLabel.setBounds(360, 35, 200, 20);
        quantity = new JTextField();
        quantity.setBounds(360, 50, 50, 30);
        PlainDocument quantityDoc = (PlainDocument) quantity.getDocument();
        quantityDoc.setDocumentFilter(new IntegerFilter());

        add = new JButton("Add");
        add.setBounds(420, 50, 100, 30);
        add.addActionListener(this);

        rawTableData = new DefaultTableModel(new Object[]{"UPC", "Name", "Quantity"}, 0);
        table = new JTable(rawTableData);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 130, 700, 675);

        submit = new JButton("Submit");
        submit.setBounds(600, 850, 100, 30);
        submit.addActionListener(this);

        returnToMenuButton = new JButton("Return to Menu");
        returnToMenuButton.setBounds(50, 850, 100, 30);
        returnToMenuButton.addActionListener(this);

        add(header);
        add(upcLabel);
        add(upc);
        add(quantityLabel);
        add(quantity);
        add(add);
        add(scrollPane);
        add(submit);
        add(returnToMenuButton);

        setLayout(null);
        setTitle("Inventory Management System");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void returnToMenu(){
        rawTableData.setRowCount(0);
        upc.setText("");
        quantity.setText("");
        controller.returnToMenu();
    }

    @Override
    public void update(Observable o, Object arg) {
        DailySalesViewModel viewModel = (DailySalesViewModel) o;

        setVisible(viewModel.isVisible());

        if (viewModel.isSuccess()) {
            JOptionPane.showMessageDialog(this, "Daily Sales updated successfully.", "Daily Sales Updated", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            if (!controller.upcExists(Long.parseLong(upc.getText()))) {
                JOptionPane.showMessageDialog(this, "UPC not found.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (quantity.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Invalid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String[] row = new String[3];
                row[0] = upc.getText();
                row[1] = controller.getProductName(Long.parseLong(upc.getText()));
                row[2] = quantity.getText();

                // TODO: Add duplication checking, add to existing quantity instead of new row.
                rawTableData.addRow(row);

                salesContents.put(Long.parseLong(upc.getText()), Integer.parseInt(quantity.getText()));
                upc.setText("");
                quantity.setText("");
            }
        } else if (e.getSource() == submit) {
            controller.inputDailySales(salesContents);
        } else if (e.getSource() == returnToMenuButton){
            returnToMenu();
        }
    }
}

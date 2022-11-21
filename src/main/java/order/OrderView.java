package order;

import utils.IntegerFilter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class OrderView extends JFrame implements Observer, ActionListener {

    private final OrderController controller;
    private final OrderViewModel viewModel;

    private JTextField upc;
    private JTextField quantity;
    private JButton add;
    private JTable table;
    private JButton placeOrder;
    private HashMap<Long, Integer> orderContents;
    private DefaultTableModel rawTableData;

    public OrderView(OrderController controller, OrderViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        init();
        orderContents = new HashMap<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(viewModel.isVisible());

        if (viewModel.isOrderComplete()) {
            JOptionPane.showMessageDialog(this, "Order placed successfully!", "Order Placed", JOptionPane.OK_OPTION);
        }
    }

    private void init() {
        JLabel header = new JLabel("Inventory Management System");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        header.setBounds(50, 0, 500, 40);

        JLabel upcLabel = new JLabel("UPC");
        upcLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        upcLabel.setBounds(55, 35, 200, 20);
        upc = new JTextField();
        upc.setBounds(50, 50 , 300, 30);
        PlainDocument upcDoc = (PlainDocument) upc.getDocument();
        upcDoc.setDocumentFilter(new IntegerFilter());

        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        quantityLabel.setBounds(360, 35, 200, 20);
        quantity = new JTextField();
        quantity.setBounds(360, 50, 50, 30);
        PlainDocument quantityDoc = (PlainDocument) quantity.getDocument();
        quantityDoc.setDocumentFilter(new IntegerFilter());

        add = new JButton("Add");
        add.setBounds(420, 50, 50, 30);
        add.addActionListener(this);

        rawTableData = new DefaultTableModel(new Object[]{"UPC", "Name", "Quantity"}, 0);
        table = new JTable(rawTableData);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 130, 700, 675);

        placeOrder = new JButton("Place Order");
        placeOrder.setBounds(600, 850, 100, 30);
        placeOrder.addActionListener(this);

        add(header);
        add(upcLabel);
        add(upc);
        add(quantityLabel);
        add(quantity);
        add(add);
        add(scrollPane);
        add(placeOrder);


        setLayout(null);
        setTitle("Inventory Management System");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

                rawTableData.addRow(row);

                orderContents.put(Long.parseLong(upc.getText()), Integer.parseInt(quantity.getText()));
                upc.setText("");
                quantity.setText("");
            }
        } else if (e.getSource() == placeOrder) {
            controller.requestOrder(orderContents);
        }
    }
}

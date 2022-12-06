package newitem;


import utils.IntegerFilter;
import utils.UPCFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


public class NewItemView extends JFrame implements Observer, ActionListener {

    private final NewItemController controller;

    private JTextField nameField;

    private JTextField upcField;

    private JTextField priceField;

    public NewItemView(NewItemController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        NewItemViewModel viewModel = (NewItemViewModel) o;

        setVisible(viewModel.isVisible());

        if (viewModel.getStatusChanged()) {
            if (viewModel.getStatus() == NewItemStatus.REPEAT_UPC) {
                JOptionPane.showMessageDialog(this, "UPC already exists", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (viewModel.getStatus() == NewItemStatus.SUCCESS) {
                JOptionPane.showMessageDialog(this, "New product has been successfully added", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private void init() {
        JLabel header = new JLabel("New product");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));
        header.setBounds(160, 0, 500, 40);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        nameLabel.setBounds(50, 25, 300, 30);
        nameField = new JTextField();
        nameField.setBounds(50, 50, 300, 30);

        JLabel upcLabel = new JLabel("UPC (12 digits)");
        upcLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        upcLabel.setBounds(50, 75, 300, 30);
        upcField = new JTextField();
        upcField.setBounds(50, 100, 300, 30);
        PlainDocument upcDoc = (PlainDocument) upcField.getDocument();
        upcDoc.setDocumentFilter(new UPCFilter());

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        priceLabel.setBounds(50, 125, 300, 30);
        priceField = new JTextField();
        priceField.setBounds(50, 150, 300, 30);
        PlainDocument priceDoc = (PlainDocument) priceField.getDocument();
        priceDoc.setDocumentFilter(new IntegerFilter());


        JButton addItemButton = new JButton("Add");
        addItemButton.addActionListener(this);


        addItemButton.setBounds(50, 250, 100, 30);

        add(header);
        add(nameLabel);
        add(upcLabel);
        add(priceLabel);
        add(nameField);
        add(upcField);
        add(priceField);
        add(addItemButton);

        setLayout(null);
        setTitle("New item");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        var response = controller.newItem(nameField.getText(), upcField.getText(), priceField.getText());
        if (response.getStatus() == NewItemStatus.INVALID_INPUT) {
            JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

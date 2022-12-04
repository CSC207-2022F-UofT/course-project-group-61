package newitem;


import javax.swing.*;
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
    private JButton returnToMenuButton;

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

        nameField = new JTextField("Name");
        upcField = new JTextField("UPC (12 digits)");
        priceField = new JTextField("Price");


        JButton addItemButton = new JButton("Add");
        addItemButton.addActionListener(this);

        header.setBounds(50, 0, 500, 40);
        nameField.setBounds(50, 50, 300, 30);
        upcField.setBounds(50, 100, 300, 30);
        priceField.setBounds(50, 150, 300, 30);

        addItemButton.setBounds(50, 250, 100, 30);


        returnToMenuButton = new JButton("Return to Menu");
        returnToMenuButton.setBounds(50, 850, 100, 30);
        returnToMenuButton.addActionListener(this);

        add(header);
        add(nameField);
        add(upcField);
        add(priceField);
        add(returnToMenuButton);
        add(addItemButton);

        setLayout(null);
        setTitle("New item");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    public void resetFields(){
        nameField.setText("");
        upcField.setText("");
        priceField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var response = controller.newItem(nameField.getText(), upcField.getText(), priceField.getText());
        if (e.getSource() == returnToMenuButton){
            resetFields();
            controller.returnToMainMenu();
        }else if (response.getStatus() == NewItemStatus.INVALID_INPUT) {
            JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}

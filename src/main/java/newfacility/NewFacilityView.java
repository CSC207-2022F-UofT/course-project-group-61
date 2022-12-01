package newfacility;

import entities.FacilityType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class NewFacilityView extends JFrame implements Observer, ActionListener {

    private final NewFacilityController controller;

    private JTextField inputField;
    private JRadioButton storeButton;
    private JRadioButton warehouseButton;
    private JButton executeButton;
    private DefaultTableModel dtm;
    private boolean storePressed = false;
    private boolean warehousePressed = false;

    public NewFacilityView(NewFacilityController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        NewFacilityViewModel viewModel = (NewFacilityViewModel) o;

        if (!Objects.isNull(viewModel.getInfoList())) {
            String[] row = new String[3];
            row[0] = viewModel.getInfoList().get(0);
            row[1] = viewModel.getInfoList().get(1);
            row[2] = viewModel.getInfoList().get(2);
            dtm.setRowCount(0);
            dtm.addRow(row);
        }

        setVisible(viewModel.isVisible());

        System.out.println(viewModel.getFailed() + "" + viewModel.getFailedReason());
        if (viewModel.getFailed()) {
            if (viewModel.getFailedReason() == newfacility.FailReason.REPEAT_NAME) {
                JOptionPane.showMessageDialog(this, "Facility Name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void init() {
        JLabel header = new JLabel("Inventory Management System");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        dtm = new DefaultTableModel(new String[]{"Name", "ID", "Type"}, 0);
        JTable returnTable = new JTable(dtm);
        JTableHeader rtHeader = returnTable.getTableHeader();

        inputField = new JTextField("Facility Name");

        this.storeButton = new JRadioButton("Store");
        this.warehouseButton = new JRadioButton("Warehouse");
        this.executeButton = new JButton("Create new facility");

        ButtonGroup group = new ButtonGroup();
        group.add(storeButton);
        group.add(warehouseButton);

        storeButton.addActionListener(this);
        warehouseButton.addActionListener(this);
        executeButton.addActionListener(this);

        header.setBounds(50, 0, 500, 40);
        storeButton.setBounds(50, 50 , 300, 30);
        warehouseButton.setBounds(50, 100, 300, 30);
        inputField.setBounds(50, 200,300, 30);
        executeButton.setBounds(350, 200, 100, 30);
        returnTable.setBounds(50, 280, 700, 30);
        rtHeader.setBounds(50, 250, 700, 30);

        add(header);
        add(storeButton);
        add(warehouseButton);
        add(inputField);
        add(executeButton);
        add(returnTable);
        add(rtHeader);

        setLayout(null);
        setTitle("Inventory Management System");
        setSize(800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == storeButton) {
            storePressed = true;
            warehousePressed = false;
        } else if (e.getSource() == warehouseButton) {
            storePressed = false;
            warehousePressed = true;
        } else if (e.getSource() == executeButton && !Objects.equals(inputField.getText(), "Search") && storePressed) {
            controller.newFacility(inputField.getText(), FacilityType.STORE);
        } else if (e.getSource() == executeButton && !Objects.equals(inputField.getText(), "Search") && warehousePressed) {
            controller.newFacility(inputField.getText(), FacilityType.WAREHOUSE);
        }

        System.out.println("test");
    }
}

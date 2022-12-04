package inventorycount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class InventoryCountView extends JFrame implements Observer, ActionListener {
    private final InventoryCountController controller;
    private JButton getCountButton;
    private JButton submitNewCountButton;
    private DefaultTableModel invTableModel;
    private JButton returnToMainMenuButton;


    private final String[] columns = {"Item Code", "Count"};

    public InventoryCountView(InventoryCountController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        InventoryCountViewModel viewModel = (InventoryCountViewModel) o;

        setVisible(viewModel.isVisible());
    }

    public void init() {
        // initialize header
        JLabel header = new JLabel("Inventory Management System - Inventory Count");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // initialize elements
        this.getCountButton = new JButton("Get Current Count");
        this.submitNewCountButton = new JButton("Submit New Count");
        this.invTableModel = new DefaultTableModel(new Object[1][2], columns){
            @Override
            public boolean isCellEditable(int row, int column){
                return column > 0;
            }
        };
        JTable invTable = new JTable(this.invTableModel);
        JScrollPane scrollPane = new JScrollPane(invTable);
        this.returnToMainMenuButton = new JButton("Return to Main Menu");

        // add action listeners
        getCountButton.addActionListener(this);
        submitNewCountButton.addActionListener(this);
        returnToMainMenuButton.addActionListener(this);

        // set element bounds
        getCountButton.setBounds(50, 50, 200, 40);
        submitNewCountButton.setBounds(260, 50, 200, 40);
        invTable.setBounds(50, 100, 400, 690);
        scrollPane.setBounds(50, 100, 400, 690);
        returnToMainMenuButton.setBounds(50, 800, 200, 40);
        header.setBounds(50, 0, 500, 40);

        // add the elements
        add(getCountButton);
        add(submitNewCountButton);
        add(returnToMainMenuButton);
        add(scrollPane);
        add(header);

        setSize(800, 1000);
        setTitle("Inventory Management System");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void clearTable(){
        for (int i = 0; i < invTableModel.getRowCount(); i++){
            invTableModel.removeRow(0); // clears the table model
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == getCountButton){
            clearTable();
            Set<Map.Entry<Long, Integer>> invEntrySet = controller.getCurrentInventoryCount().entrySet();
            invTableModel.setRowCount(invEntrySet.size());
            for (Map.Entry<Long, Integer> entry : invEntrySet){
                Object[] row = new Object[2];
                row[0] = entry.getKey();
                row[1] = entry.getValue();
                invTableModel.addRow(row); // add the entire inventory
            }
            invTableModel.removeRow(0); // remove first row
        } else if(src == submitNewCountButton){
            HashMap<Long, Integer> newCount = new HashMap<>();
            for (int i = 0; i < invTableModel.getRowCount(); i++){
                Long pid = Long.parseLong(invTableModel.getValueAt(i, 0).toString());
                int count = Integer.parseInt(invTableModel.getValueAt(i, 1).toString());
                newCount.put(pid, count); // add each row to hashmap
            }
            controller.submitCount(newCount); // submit as new inventory
            JOptionPane.showMessageDialog(this, "Successfully submitted inventory count.");

        }else if (src == returnToMainMenuButton){
            controller.returnToMainMenu();
            clearTable();
        }
//    }
}



}

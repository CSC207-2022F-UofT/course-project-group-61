package inventorycount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class InventoryCountView extends JFrame implements Observer, ActionListener {



    private InventoryCountController controller;

//    private JButton placeOrderButton;
//    private JButton dailySalesButton;
//    private  JButton invCountButton;
//    private  JButton genReportButton;
//    private JButton itemLookupButton;
    private JButton getCountButton;
    private JButton submitNewCountButton;
    private DefaultTableModel invTableModel;
    private JTable invTable;
    private JButton returnToMainMenuButton;


    private String[] columns = {"Item Code", "Count"};

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
        JLabel header = new JLabel("Inventory Management System - Inventory Count");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        this.getCountButton = new JButton("Get Current Count");
        this.submitNewCountButton = new JButton("Submit New Count");
        this.invTableModel = new DefaultTableModel(new Object[1][2], columns){
            @Override
            public boolean isCellEditable(int row, int column){
                // return column > 0 && row > 0;
                return true;
            }
        };
        this.invTable = new JTable(this.invTableModel);
        this.returnToMainMenuButton = new JButton("Return to Main Menu");

        getCountButton.addActionListener(this);
        submitNewCountButton.addActionListener(this);
        returnToMainMenuButton.addActionListener(this);

        getCountButton.setBounds(50, 50, 200, 40);
        submitNewCountButton.setBounds(260, 50, 200, 40);
        invTable.setBounds(50, 100, 400, 690);
        returnToMainMenuButton.setBounds(50, 800, 200, 40);
        header.setBounds(50, 0, 500, 40);

        add(getCountButton);
        add(submitNewCountButton);
        add(invTable);
        add(returnToMainMenuButton);
        add(header);

        setSize(800, 1000);
        setTitle("Inventory Management System");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == getCountButton){

            for (int i = 0; i < invTableModel.getRowCount(); i++){
                invTableModel.removeRow(0); // clears the table model
            }
            Set<Map.Entry<Long, Integer>> invEntrySet = controller.getCurrentInventoryCount().entrySet();
            invTableModel.setRowCount(invEntrySet.size());
            System.out.println(invEntrySet.size());
            for (Map.Entry<Long, Integer> entry : invEntrySet){
                Object[] row = new Object[2];
                row[0] = entry.getKey();
                row[1] = entry.getValue();
                invTableModel.addRow(row); // add the entire inventory
            }
        } else if(src == submitNewCountButton){
            HashMap<Long, Integer> newCount = new HashMap<>();
            for (int i = 0; i < invTableModel.getColumnCount(); i++){
                Long pid = parseLong((String) invTableModel.getValueAt(i, 0));
                int count = parseInt((String) invTableModel.getValueAt(i, 1));
                newCount.put(pid, count); // add each row to hashmap
            }
            controller.submitCount(newCount); // submit as new inventory

        }else if (src == returnToMainMenuButton){
            controller.returnToMainMenu();
        }
//    }
}



}

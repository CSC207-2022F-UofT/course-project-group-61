package storemainmenu;

import javax.swing.*;
import java.awt.*;

public class StoreMainMenuView extends JFrame {

    /*
     * place order
     * daily sales
     * inventory count
     * generate report
     * item lookup
     * */

    //private final StoreMainMenuController controller;

    public StoreMainMenuView(/*StoreMainMenuController controller*/) {
        //this.controller = controller;

        setName("Inventory Management System");

        JLabel header = new JLabel("Inventory Management System");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JButton placeOrderButton = new JButton("Place Order");
        JButton dailySalesButton = new JButton("Input Daily Sales");
        JButton invCountButton = new JButton("Input Inventory");
        JButton genReportButton = new JButton("Generate Report");
        JButton itemLookupButton = new JButton("Item Lookup");

        placeOrderButton.setBounds(50, 50, 100, 30);
        dailySalesButton.setBounds(50, 100, 100, 30);
        invCountButton.setBounds(50, 150, 100, 30);
        genReportButton.setBounds(50, 200, 100, 30);
        itemLookupButton.setBounds(50, 250, 100, 30);

        add(placeOrderButton);
        add(dailySalesButton);
        add(invCountButton);
        add(genReportButton);
        add(itemLookupButton);

        setSize(800, 1000);
        setLayout(null);
        setVisible(true);

    }
}

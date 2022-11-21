package storemainmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class StoreMainMenuView extends JFrame implements Observer, ActionListener {

    private final StoreMainMenuController controller;

    private JButton placeOrderButton;
    private JButton dailySalesButton;
    private  JButton invCountButton;
    private  JButton genReportButton;
    private JButton itemLookupButton;

    public StoreMainMenuView(StoreMainMenuController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        StoreMainMenuViewModel viewModel = (StoreMainMenuViewModel) o;

        setVisible(viewModel.isVisible());

        System.out.println("update");
    }

    public void init() {
        JLabel header = new JLabel("Inventory Management System");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        this.placeOrderButton = new JButton("Place Order");
        this.dailySalesButton = new JButton("Input Daily Sales");
        this.invCountButton = new JButton("Input Inventory");
        this.genReportButton = new JButton("Generate Report");
        this.itemLookupButton = new JButton("Item Lookup");

        placeOrderButton.addActionListener(this);
        dailySalesButton.addActionListener(this);
        invCountButton.addActionListener(this);
        genReportButton.addActionListener(this);
        itemLookupButton.addActionListener(this);

        header.setBounds(50, 0, 500, 40);
        placeOrderButton.setBounds(50, 50, 200, 40);
        dailySalesButton.setBounds(50, 100, 200, 40);
        invCountButton.setBounds(50, 150, 200, 40);
        genReportButton.setBounds(50, 200, 200, 40);
        itemLookupButton.setBounds(50, 250, 200, 40);

        add(placeOrderButton);
        add(dailySalesButton);
        add(invCountButton);
        add(genReportButton);
        add(itemLookupButton);
        add(header);

        setSize(800, 1000);
        setTitle("Inventory Management System");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == placeOrderButton) {
            controller.chooseAction(ButtonOption.PLACE_ORDER);
        } else if (e.getSource() == dailySalesButton) {
            controller.chooseAction(ButtonOption.DAILY_SALES);
        } else if (e.getSource() == invCountButton) {
            controller.chooseAction(ButtonOption.INV_COUNT);
        } else if (e.getSource() == genReportButton) {
            controller.chooseAction(ButtonOption.GEN_REPORT);
        } else if (e.getSource() == itemLookupButton) {
            controller.chooseAction(ButtonOption.ITEM_LOOKUP);
        }
    }
}

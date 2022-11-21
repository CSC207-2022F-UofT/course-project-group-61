package adminmainmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AdminMainMenuView extends JFrame implements Observer, ActionListener {

    private final AdminMainMenuController controller;

    private JButton newFacilityButton;
    private JButton newItemButton;
    private  JButton newUserButton;
    private JButton itemLookupButton;

    public AdminMainMenuView(AdminMainMenuController controller) {
        this.controller = controller;
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        AdminMainMenuViewModel viewModel = (AdminMainMenuViewModel) o;

        setVisible(viewModel.isVisible());
    }

    public void init() {
        JLabel header = new JLabel("Inventory Management System");
        header.setFont(new Font("SansSerif", Font.PLAIN, 14));

        this.newFacilityButton = new JButton("New Facility");
        this.newItemButton = new JButton("New Item");
        this.newUserButton = new JButton("New User");
        this.itemLookupButton = new JButton("Item Lookup");

        newFacilityButton.addActionListener(this);
        newItemButton.addActionListener(this);
        newUserButton.addActionListener(this);
        itemLookupButton.addActionListener(this);

        header.setBounds(50, 0, 500, 40);
        newFacilityButton.setBounds(50, 50, 200, 40);
        newItemButton.setBounds(50, 100, 200, 40);
        newUserButton.setBounds(50, 150, 200, 40);
        itemLookupButton.setBounds(50, 200, 200, 40);

        add(newFacilityButton);
        add(newItemButton);
        add(newUserButton);
        add(itemLookupButton);
        add(header);

        setSize(800, 1000);
        setTitle("Inventory Management System");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFacilityButton) {
            controller.chooseAction(ButtonOption.NEW_FACILITY);
        } else if (e.getSource() == newItemButton) {
            controller.chooseAction(ButtonOption.NEW_ITEM);
        } else if (e.getSource() == newUserButton) {
            controller.chooseAction(ButtonOption.NEW_USER);
        } else if (e.getSource() == itemLookupButton) {
            controller.chooseAction(ButtonOption.ITEM_LOOKUP);
        }
    }
}

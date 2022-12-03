package fulfill;

import database.*;
import entities.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class FulfillView extends JFrame implements Observer, ActionListener {
    private final FulfillController controller;

    private final Font font = new Font("SansSerif", Font.PLAIN, 14);

    private Object[][] orderData;
    private DefaultTableModel orderModel;

    private Order currentOrder;

    private DefaultTableModel productModel;
    private JTable productTable;
    private JScrollPane productScrollPane;
    private JLabel productHeader;
    private JButton fulfillButton;

    private JButton backButton;

    public FulfillView(FulfillController controller){
        this.controller = controller;
        init();
    }

    public void init(){
        /*
        Loads all the UI initially
         */
        JLabel orderHeader = new JLabel("Fulfill Order");
        orderHeader.setFont(font);

        // Products in order menu
        String[] productColumnNames = {"Product Name", "Product UPC", "Quantity"};
        productModel = new DefaultTableModel(productColumnNames, 0);
        // No initial values as it should be invisible from beginning

        // Creates the fulfill button
        fulfillButton = new JButton("Fulfill");
        fulfillButton.addActionListener(this);
        fulfillButton.setFont(font);
        fulfillButton.setVisible(false);

        // Creates product header
        productHeader = new JLabel("Order Info");
        productHeader.setFont(font);
        productHeader.setVisible(false);

        // Sets up the back button to the warehouse main menu
        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.addActionListener(this);

        // TODO: fix magic #s
        orderHeader.setBounds(50, 100, 100, 40);
        fulfillButton.setBounds(325, 550, 80, 50);
        productHeader.setBounds(50, 350, 100, 50);
        backButton.setBounds(0, 0, 100, 50);

        add(orderHeader);
        add(fulfillButton);
        add(productHeader);
        add(backButton);

        setLayout(null);
        setSize(800, 1000); // TODO: fix magic #s
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    } // TODO: fix stuff inside

    public void update(Observable o, Object arg){
        /*
        Handles if the view model was affected, thus updating the view
         */
        FulfillViewModel viewModel = (FulfillViewModel)o;

        // Checks if the order info should be visible, if so updates it with latest info
        if(viewModel.getOrderInfoVisible()){
            updateProductInfo(viewModel.getOrder().getOrderQuantities());

            // Stores which order we are currently working with
            currentOrder = viewModel.getOrder();
        }

        // Checks if an order has successfully fulfilled
        if(viewModel.getSuccessfulFulfillment()){
            JOptionPane.showMessageDialog(this, "Successfully fulfilled!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Hides tables relating to the specific order and removes the fulfilled order from our order data
            hideProductInfo();
            removeOrder(currentOrder.getId());
            currentOrder = null;

        }else if(viewModel.getFailedFulfillment()){
            String message = outOfStockMessage(viewModel.getOutOfStock());

            int result = JOptionPane.showConfirmDialog(this, message, "Failed", JOptionPane.YES_NO_OPTION);

            // Checks which option the user chose
            if(result == JOptionPane.YES_OPTION){
                controller.confirmFulfill(currentOrder.getStoreID(), currentOrder.getWarehouseID(), currentOrder.getId());
                // Note: we do not need to hide the product info and remove the current order as when the order is
                // fulfilled the if check above is true and it gets removed anyways.
            }
        }

        setVisible(viewModel.isVisible());

        // Checks if the viewModel is currently being displayed, if so finds and renders the order table. Note that we
        // only want this to happen at the very beginning so we check if the data is null
        if(orderData == null && viewModel.isVisible()){
            setupOrderData();
        }

    }

    public void actionPerformed(ActionEvent e){
        /*
        Handles button click events
         */
        // Checks which button was pressed
        if(e.getSource() == fulfillButton){
            controller.attemptFulfill(currentOrder.getStoreID(), currentOrder.getWarehouseID(), currentOrder.getId());
        }else if(e.getSource() == backButton){
            controller.backToMainMenu();
        }
    }

    public void ListSelected(ListSelectionEvent e){
        /*
        Handles the event that a new order is selected in the orders list
         */
        // A simple check to stop this code from being run multiple times after just one click
        if(e.getValueIsAdjusting()){
            // Gathers the selection model and finds the minimum, note that because we are only allowing 1 selection
            // this minimum is equivilient to the index that is selected.
            DefaultListSelectionModel selectionModel = (DefaultListSelectionModel)e.getSource();
            controller.newSelectedOrder(orderData, selectionModel.getMinSelectionIndex());
        }
    }

    private void updateProductInfo(HashMap<Long, Integer> orderQuantities){
        /*
        Updates all the UI info for a new order select, thus displaying the new order product info and general UI
        around it
         */
        // Resets the table
        productModel.setRowCount(0);

        // Gathers the data and updates the TableModel
        String[][] productData = productDataArrayConverter(orderQuantities);
        for(String[] row: productData){
            productModel.addRow(row);
        }

        // Creates the UI around the product table
        productTable = new JTable(productModel);
        productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBounds(50, 400, 700, 100); // MAGIC #s
        add(productScrollPane);

        // Makes the button and label visible
        fulfillButton.setVisible(true);
        productHeader.setVisible(true);
    }

    private void hideProductInfo(){
        /*
        Hides all the product info. This is usually called after a product is fulfilled so it's product info shouldn't
        be shown
         */
        fulfillButton.setVisible(false);
        productScrollPane.setVisible(false);
        productTable.setVisible(false);
        productHeader.setVisible(false);
    }

    private void removeOrder(UUID orderID){
        /*
        Removes an order from the table of orders. This is to get rid of orders that are fulfilled.
         */
        Object[][] newOrderData = new Object[orderData.length - 1][2]; // TODO: fix magic #

        // Removes all previous info from orderModel
        orderModel.setRowCount(0);

        // Loops over the previous data, keeping everything accept the one removed order alongside with adding those
        // rows to the table model
        int curRow = 0;
        for(Object[] row: orderData){
            if(!row[0].equals(orderID)){
                newOrderData[curRow] = row;
                orderModel.addRow(newOrderData[curRow]);

                curRow += 1;
            }
        }

        // Resets the new order data to reflect all the orders not fulfilled
        orderData = newOrderData;
    }

    private void setupOrderData(){
        /*
        Allows for a quick setup of order data, that is formatting the tables, initalizing them and so on.
         */
        // Fetches the table data
        String[] orderColumnNames = {"Order ID", "Store Name"};
        orderData = orderDataArrayConverter(orderColumnNames.length);

        // Creates the model and adds the info to it
        orderModel = new DefaultTableModel(orderColumnNames, 0);
        for (Object[] row : orderData) {
            orderModel.addRow(row);
        }

        // Creates the table and sets it up
        JTable orderTable = new JTable(orderModel);
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderTable.getSelectionModel().addListSelectionListener(this::ListSelected);

        // Creates the scrolling table
        JScrollPane orderScrollPane = new JScrollPane(orderTable);

        // Sets up the positioning
        orderScrollPane.setBounds(50, 150, 700, 100); // TODO: fix magic #s
        add(orderScrollPane);
    } // TODO: fix magic #s

    private Object[][] orderDataArrayConverter(int columnNumbers){
        /*
        Returns data object for JTable as a 2D array object of order IDs and store names for this specific store
         */
        // Creates the order databases to access orders and stores
        OrderDb orderDb = new OrderDbGateway();
        FacilityDb facilityDb = new FacilityDbGateway();

        HashMap<UUID, Order> orderHashMap = orderDb.getAllOrders();
        Object[][] returnData = new Object[orderHashMap.size()][columnNumbers];

        FacilityUser currentUser = (FacilityUser) UserSession.getUserSession();

        // Loops over each row in our 2D array adding in the correct values of orderID and store name (respectively)
        int row = 0;
        Order currentOrder;
        Facility currentWarehouse;
        for(UUID id: orderHashMap.keySet()){
            // Finds the current warehouse and order that this loop is going over
            currentOrder = orderDb.getOrder(id);
            currentWarehouse = facilityDb.getFacility(currentOrder.getWarehouseID());

            // Checks that the order isn't already fulfilled and belongs to the warehouse we are working with
            if(currentOrder.getStatus() != OrderStatus.FULFILLED && currentWarehouse.getFacilityID() == currentUser.getFacilityID()){
                // Adds the new valid order to the data
                Object[] values = {id, facilityDb.getFacility(currentOrder.getStoreID()).getName()};
                returnData[row] = values;

                row += 1;
            }
        }

        return returnData;
    }

    private String[][] productDataArrayConverter(HashMap<Long, Integer> orderQuantities){
        /*
        Returns the 2D array for a specific order so it can be displayed
         */
        ProductDb productDb = new ProductDbGateway();

        String[][] returnValue = new String[orderQuantities.size()][productModel.getColumnCount()];

        // Over each row of the 2D returnValue array we append the correct info for each product in the order
        int row = 0;
        for(long UPC: orderQuantities.keySet()){
            String productName = productDb.getProduct(UPC).getName();

            String[] rowData = {productName, Long.toString(UPC), Integer.toString(orderQuantities.get(UPC))};
            returnValue[row] = rowData;

            row += 1;
        }

        return returnValue;
    }

    private String outOfStockMessage(HashMap<Long, Boolean> outOfStockItems){
        /*
        Creates the out of stock message inform the user which items are out of stock for a failed fulfillment
         */
        StringBuilder message = new StringBuilder("Out of stock on:\n");

        ProductDb productDb = new ProductDbGateway();

        for(long UPC: outOfStockItems.keySet()){
            if(outOfStockItems.get(UPC)){
                message.append(productDb.getProduct(UPC).getName()).append("\n");
            }
        }

        return message + "Continue with the fulfillment?";
    }
}

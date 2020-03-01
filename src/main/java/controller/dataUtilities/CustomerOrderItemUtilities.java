/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dataUtilities;

import dbConnection.DBCon;
import dbConnection.DBHandle;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.CustomerOrderItemModel;

/**
 *
 * @author nadee
 */
public class CustomerOrderItemUtilities {
     public static void addCustomerOrderItem(CustomerOrderItemModel CustomerOrderItem) 
             throws SQLException, ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO CUSTOMER_ORDER_ITEM ("
                + "customer_order_item_id, "
                + "customer_order_number, "
                + "item_id, "
                + "customer_order_item_unit_price,"
                + "customer_order_item_qty)  VALUES('"
                + CustomerOrderItem.getCustomer_order_item_id() + "','"
                + CustomerOrderItem.getCustomer_order_number() + "','"
                + CustomerOrderItem.getItem_id() + "','"
                + CustomerOrderItem.getCustomer_order_item_unit_price() + "','"
                + CustomerOrderItem.getCustomer_order_item_qty() + "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add Customer Order Item Completed......");
        }
    }

    public static void updateCustomerOrderItem(CustomerOrderItemModel CustomerOrderItem)
            throws SQLException, ClassNotFoundException, IOException, Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE CUSTOMER_ORDER_ITEM SET "
                + "',item_id='" + CustomerOrderItem.getItem_id()
                + "',customer_order_item_unit_price='" + CustomerOrderItem.getCustomer_order_item_unit_price()
                + "',customer_order_item_qty='" + CustomerOrderItem.getCustomer_order_item_qty()
                + "' WHERE customer_order_item_id='" + CustomerOrderItem.getCustomer_order_item_id() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "update Customer Order Item Completed......");
        }
    }
    
    public static void deleteCustomerOrderItem(CustomerOrderItemModel CustomerOrderItem) 
            throws SQLException, ClassNotFoundException, IOException, Exception {
      
            Connection con =  DBCon.getConnection();
            String query = "DELETE FROM CUSTOMER_ORDER_ITEM   WHERE customer_order_item_id='" + CustomerOrderItem.getCustomer_order_item_id() + "'";
            int result = DBHandle.setData(con, query);
            if (result != 0) {
                JOptionPane.showMessageDialog(null, "delete Customer Order Item Completed......");
            }
        
    }


    public static ArrayList showAllCustomerOrderItems() throws SQLException, 
            ClassNotFoundException, IOException, Exception {
        
        ArrayList<CustomerOrderItemModel> arCustomerOrderItem
                = new ArrayList<CustomerOrderItemModel>();    // java.util
       
            Connection con = DBCon.getConnection();
            ResultSet result = DBHandle.getData(con, "SELECT * FROM CUSTOMER");

            while (result.next()) {
                CustomerOrderItemModel CustomerOrderItem = new CustomerOrderItemModel();
                CustomerOrderItem.setCustomer_order_item_id(result.getString("customer_order_item_id"));
                CustomerOrderItem.setCustomer_order_number(result.getString("customer_order_number"));
                CustomerOrderItem.setItem_id(result.getString("item_id"));
                CustomerOrderItem.setCustomer_order_item_unit_price(result.getString("customer_order_item_unit_price"));
                CustomerOrderItem.setCustomer_order_item_qty(result.getString("customer_order_item_qty"));

                arCustomerOrderItem.add(CustomerOrderItem);
            }

        return arCustomerOrderItem;

    }
}

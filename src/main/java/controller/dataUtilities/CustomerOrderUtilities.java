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
import model.CustomerOrderModel;

/**
 *
 * @author nadee
 */
public class CustomerOrderUtilities {

    public static void addCustomerOrder(CustomerOrderModel customerOrder)
            throws SQLException, ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO CUSTOMER_ORDER ("
                + "customer_order_number, "
                + "customer_id, "
                + "customer_order_datetime, "
                + "customer_order_sub_total,"
                + "customer_order_discount,"
                + "customer_order_net_total)  VALUES('"
                + customerOrder.getCustomer_order_number() + "','"
                + customerOrder.getCustomer_id() + "','"
                + customerOrder.getCustomer_order_datetime() + "','"
                + customerOrder.getCustomer_order_sub_total() + "','"
                + customerOrder.getCustomer_order_discount() + "','"
                + customerOrder.getCustomer_order_net_total() + "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add Customer Order Completed......");
        }
    }

    public static void updateCustomerOrder(CustomerOrderModel customerOder)
            throws SQLException, ClassNotFoundException, IOException,
            Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE CUSTOMER_ORDER SET "
                + "',customer_id='" + customerOder.getCustomer_id()
                + "',customer_order_datetime='" + customerOder.getCustomer_order_datetime()
                + "',customer_order_sub_total='" + customerOder.getCustomer_order_sub_total()
                + "',customer_order_discount='" + customerOder.getCustomer_order_discount()
                + "',customer_order_net_total='" + customerOder.getCustomer_order_net_total()
                + "' WHERE customer_order_number='" + customerOder.getCustomer_id() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "update Customer Order Completed......");
        }
    }

    public static void deleteCustomerOrder(CustomerOrderModel customerOrder)
            throws SQLException, ClassNotFoundException, IOException,
            Exception {

        Connection con = DBCon.getConnection();
        String query = "DELETE FROM CUSTOMER_ORDER   "
                + "WHERE customer_order_number='"
                + customerOrder.getCustomer_order_number() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "delete Customer Order Completed......");
        }

    }

    public static CustomerOrderModel showCustomerOrder(String customer_order_number) throws Exception {

        CustomerOrderModel customerOrder = new CustomerOrderModel();

        Connection con = DBCon.getConnection();
        String query = "SELECT * FROM CUSTOMER_ORDER "
                + "WHERE customer_order_number='"
                + customer_order_number + "'";

        ResultSet result = DBHandle.getData(con, query);
        while (result.next()) {
            customerOrder.setCustomer_order_number(result.getString("customer_order_number"));
            customerOrder.setCustomer_id(result.getString("customer_id"));
            customerOrder.setCustomer_order_datetime(result.getString("customer_order_datetime"));
            customerOrder.setCustomer_order_sub_total(result.getString("customer_order_sub_total"));
            customerOrder.setCustomer_order_discount(result.getString("customer_order_discount"));
            customerOrder.setCustomer_order_net_total(result.getString("customer_order_net_total"));
        }

        return customerOrder;
    }

    public static ArrayList showAllCustomerOrders() throws SQLException,
            ClassNotFoundException, IOException, Exception {

        ArrayList<CustomerOrderModel> arCustomerOrder
                = new ArrayList<CustomerOrderModel>();    // java.util

        Connection con = DBCon.getConnection();
        ResultSet result = DBHandle.getData(con, "SELECT * FROM CUSTOMER_ORDER");

        while (result.next()) {
            CustomerOrderModel customerOrder = new CustomerOrderModel();
            customerOrder.setCustomer_order_number(result.getString("Customer_id"));
            customerOrder.setCustomer_id(result.getString("Customer_name"));
            customerOrder.setCustomer_order_datetime(result.getString("Customer_nic"));
            customerOrder.setCustomer_order_sub_total(result.getString("Customer_address"));
            customerOrder.setCustomer_order_discount(result.getString("Customer_bday"));
            customerOrder.setCustomer_order_net_total(result.getString("Customer_contact"));

            arCustomerOrder.add(customerOrder);
        }

        return arCustomerOrder;

    }
}

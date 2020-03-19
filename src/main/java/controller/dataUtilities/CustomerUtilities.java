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
import java.util.Collections;
import javax.swing.JOptionPane;
import model.CustomerModel;

/**
 *
 * @author nadee
 */
public class CustomerUtilities {

    public static void addCustomer(CustomerModel customer) throws SQLException,
            ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO CUSTOMER ("
                + "customer_id, "
                + "customer_title, "
                + "customer_name, "
                + "customer_nic,"
                + "customer_bday,"
                + "customer_contact,"
                + "customer_email,"
                + "customer_address, "
                + "customer_city, "
                + "customer_province, "
                + "customer_postalcode)  VALUES('"
                + customer.getCustomer_id() + "','"
                + customer.getCustomer_title() + "','"
                + customer.getCustomer_name() + "','"
                + customer.getCustomer_nic() + "','"
                + customer.getCustomer_bday() + "','"
                + customer.getCustomer_contact() + "','"
                + customer.getCustomer_email() + "','"
                + customer.getCustomer_address() + "','"
                + customer.getCustomer_city() + "','"
                + customer.getCustomer_province() + "','"
                + customer.getCustomer_postalcode() + "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add Customer Completed......");
        }
    }

    public static void updateCustomer(CustomerModel customer)
            throws SQLException, ClassNotFoundException, IOException, Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE CUSTOMER SET "
                + "customer_title='" + customer.getCustomer_title()
                + "',customer_name='" + customer.getCustomer_name()
                + "',customer_nic='" + customer.getCustomer_nic()
                + "',customer_bday='" + customer.getCustomer_bday()
                + "',customer_contact='" + customer.getCustomer_contact()
                + "',customer_email='" + customer.getCustomer_email()
                + "',customer_address='" + customer.getCustomer_address()
                + "',customer_city='" + customer.getCustomer_city()
                + "',customer_province='" + customer.getCustomer_province()
                + "',customer_postalcode='" + customer.getCustomer_postalcode()
                + " 'WHERE customer_id='" + customer.getCustomer_id() + "';";
        System.out.println("Customer utility method query " + query);
        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "update Customer Completed......");
        }
    }

    public static void deleteCustomer(CustomerModel customer)
            throws SQLException, ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "DELETE FROM CUSTOMER   WHERE customer_id='"
                + customer.getCustomer_id() + "'";
        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "delete Customer Completed......");
        } else {
            JOptionPane.showMessageDialog(null, "No Customer");
        }

    }

    public static void deleteCustomer(String customer_id)
            throws SQLException, ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "DELETE FROM CUSTOMER   WHERE customer_id='"
                + customer_id + "'";
        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "delete Customer Completed......");
        } else {
            JOptionPane.showMessageDialog(null, "No Customer with" + customer_id);
        }

    }

    public static CustomerModel getCustomer(String Customer_id) throws Exception {

        CustomerModel customer = new CustomerModel();

        Connection con = DBCon.getConnection();
        String query = "SELECT * FROM CUSTOMER WHERE Customer_id='" + Customer_id + "'";
        ResultSet result = DBHandle.getData(con, query);
        while (result.next()) {
            customer.setCustomer_id(result.getString("Customer_id"));
            customer.setCustomer_name(result.getString("Customer_name"));
            customer.setCustomer_nic(result.getString("Customer_nic"));
            customer.setCustomer_address(result.getString("Customer_address"));
            customer.setCustomer_bday(result.getString("Customer_bday"));
            customer.setCustomer_contact(result.getString("Customer_contact"));
            customer.setCustomer_email(result.getString("Customer_email"));
            customer.setCustomer_city(result.getString("Customer_city"));
            customer.setCustomer_province(result.getString("Customer_province"));
            customer.setCustomer_title(result.getString("Customer_title"));
            customer.setCustomer_postalcode(result.getString("Customer_postalcode"));
        }

        return customer;
    }
     public static CustomerModel getCustomer(String Customer_id, int i) throws Exception {
     CustomerModel customer = new CustomerModel();
         return customer;   
     }

    public static ArrayList getAllCustomers() throws SQLException,
            ClassNotFoundException, IOException, Exception {

        ArrayList<CustomerModel> arCustomer = new ArrayList<CustomerModel>();    // java.util

        Connection con = DBCon.getConnection();
        ResultSet result = DBHandle.getData(con, "SELECT * FROM CUSTOMER");

        while (result.next()) {
            CustomerModel customer = new CustomerModel();
            customer.setCustomer_id(result.getString("Customer_id"));
            customer.setCustomer_name(result.getString("Customer_name"));
            customer.setCustomer_nic(result.getString("Customer_nic"));
            customer.setCustomer_address(result.getString("Customer_address"));
            customer.setCustomer_bday(result.getString("Customer_bday"));
            customer.setCustomer_contact(result.getString("Customer_contact"));
            customer.setCustomer_email(result.getString("Customer_email"));
            customer.setCustomer_city(result.getString("Customer_city"));
            customer.setCustomer_province(result.getString("Customer_province"));
            customer.setCustomer_title(result.getString("Customer_title"));
            customer.setCustomer_postalcode(result.getString("Customer_postalcode"));

            arCustomer.add(customer);
        }

        return arCustomer;

    }

    public static String SearchCustomer(String table, String... type) {
        int x = type.length;
        String quray = null;
        if (x == 0) {
            quray = "SELECT * FROM " + table;
        } else if (x == 1) {
            quray = "SELECT " + type[0] + " FROM " + table;
        } else if (x == 2) {
            quray = "SELECT " + type[0] + "," + type[1] + " FROM " + table;
        } else if (x == 3) {
            quray = "SELECT " + type[0] + "," + type[1] + "," + type[2] + " FROM " + table;
        } else if (x == 4) {
            quray = "SELECT " + type[0] + "," + type[1] + "," + type[2] + "," + type[3] + " FROM " + table;
        } else if (x == 5) {
            quray = "SELECT " + type[0] + "," + type[1] + "," + type[2] + "," + type[3] + "," + type[4] + " FROM " + table;
        } else if (x == 6) {
            quray = "SELECT " + type[0] + "," + type[1] + "," + type[2] + "," + type[3] + "," + type[4] + "," + type[5] + " FROM " + table;
        } else if (x == 7) {
            quray = "SELECT " + type[0] + "," + type[1] + "," + type[2] + "," + type[3] + "," + type[4] + "," + type[5] + "," + type[6] + " FROM " + table;
        } else if (x == 8) {
            quray = "SELECT " + type[0] + "," + type[1] + "," + type[2] + "," + type[3] + "," + type[4] + "," + type[5] + "," + type[6] + "," + type[7] + " FROM " + table;
        } else if (x == 9) {
            quray = "SELECT " + type[0] + "," + type[1] + "," + type[2] + "," + type[3] + "," + type[4] + "," + type[5] + "," + type[6] + "," + type[7] + "," + type[8] + " FROM " + table;
        } else if (x == 10) {
            quray = "SELECT " + type[0] + "," + type[1] + "," + type[2] + "," + type[3] + "," + type[4] + "," + type[6] + "," + type[6] + "," + type[7] + "," + type[8] + "," + type[9] + " FROM " + table;
        } else if (x == 11) {
            quray = "SELECT " + type[0] + "," + type[1] + "," + type[2] + "," + type[3] + "," + type[4] + "," + type[5] + "," + type[6] + "," + type[7] + "," + type[8] + "," + type[9] + "," + type[10] + " FROM " + table;
        } else if (x == 12) {
            quray = "SELECT " + type[0] + "," + type[1] + "," + type[2] + "," + type[3] + "," + type[4] + "," + type[5] + "," + type[6] + "," + type[7] + "," + type[8] + "," + type[9] + "," + type[10] + "," + type[11] + " FROM " + table;
        }
        return quray;
    }
}

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
import model.InvoiceModel;

/**
 *
 * @author nadee
 */
public class InvoiceUtilities {
    
    public static void addInvoice(InvoiceModel invoice) throws SQLException,
            ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO INVOICE ("
                + "invoice_number, "
                + "purchase_order_number, "
                + "invoice_datetime, "
                + "invoice_total,"
                + "invoice_discount)  VALUES('"
                + invoice.getInvoice_number() + "','"
                + invoice.getPurchase_order_number() + "','"
                + invoice.getInvoice_datetime() + "','"
                + invoice.getInvoice_total() + "','"
                + invoice.getInvoice_discount() +  "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add invoice Completed......");
        }
    }

    public static void updateInvoice(InvoiceModel invoice) 
            throws SQLException, ClassNotFoundException, IOException, Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE INVOICE SET "
                + "',purchase_order_number='" + invoice.getPurchase_order_number()
                + "',invoice_datetime='" + invoice.getInvoice_datetime()
                + "',invoice_total='" + invoice.getInvoice_total()
                + "',invoice_discount='" + invoice.getInvoice_discount()
                + "' WHERE invoice_number='" + invoice.getInvoice_number()+ "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null,"update invoice Completed.....");
        }
    }
    
    public static void deleteInvoice(InvoiceModel invoice) 
            throws SQLException, ClassNotFoundException, IOException, Exception{
      
            Connection con =  DBCon.getConnection();
            String query = "DELETE FROM INVOICE WHERE invoice_number='" 
                    + invoice.getInvoice_number() + "'";
            
            int result = DBHandle.setData(con, query);
            if (result != 0) {
                JOptionPane.showMessageDialog(null, 
                        "delete invoice Completed......");
            }
        
    }

    public static InvoiceModel showInvoice(String invoice_number) 
            throws Exception {
       
        InvoiceModel invoice = new InvoiceModel();
       
            Connection con = DBCon.getConnection();
            String query = "SELECT * FROM INVOICE WHERE invoice_number='" + invoice_number + "'";
            ResultSet result = DBHandle.getData(con, query);
            while (result.next()) {
                invoice.setInvoice_number(result.getString("invoice_number"));
                invoice.setPurchase_order_number(result.getString("purchase_order_number"));
                invoice.setInvoice_datetime(result.getString("invoice_datetime"));
                invoice.setInvoice_total(result.getString("invoice_total"));
                invoice.setInvoice_discount(result.getString("invoice_discount"));
            }

        
        return invoice;
    }

    public static ArrayList showAllInvoices() throws SQLException, 
            ClassNotFoundException, IOException, Exception {
        
        ArrayList<InvoiceModel> arInvoice = new ArrayList<InvoiceModel>(); // java.util
       
            Connection con = DBCon.getConnection();
            ResultSet result = DBHandle.getData(con, "SELECT * FROM INVOICE");

            while (result.next()) {
                InvoiceModel invoice = new InvoiceModel();
                invoice.setInvoice_number(result.getString("invoice_number"));
                invoice.setPurchase_order_number(result.getString("purchase_order_number"));
                invoice.setInvoice_datetime(result.getString("invoice_datetime"));
                invoice.setInvoice_total(result.getString("invoice_total"));
                invoice.setInvoice_discount(result.getString("invoice_discount"));

                arInvoice.add(invoice);
            }

        return arInvoice;

    }
}

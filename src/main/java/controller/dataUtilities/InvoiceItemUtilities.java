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
import model.InvoiceItemModel;

/**
 *
 * @author nadee
 */
public class InvoiceItemUtilities {
    public static void addInvoiceItem(InvoiceItemModel InvoiceItem) 
             throws SQLException, ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO INVOICE_ITEM ("
                + "invoice_item_id, "
                + "invoice_number, "
                + "item_id, "
                + "invoice_item_Unit_Price,"
                + "invoice_item_Qty)  VALUES('"
                + InvoiceItem.getInvoice_item_id() + "','"
                + InvoiceItem.getInvoice_number() + "','"
                + InvoiceItem.getItem_id() + "','"
                + InvoiceItem.getInvoice_item_Unit_Price() + "','"
                + InvoiceItem.getInvoice_item_Qty() + "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add  InvoiceItem  Completed......");
        }
    }

    public static void updateInvoiceItem(InvoiceItemModel InvoiceItem)
            throws SQLException, ClassNotFoundException, IOException, Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE INVOICE_ITEM SET "
                + "',invoice_number='" + InvoiceItem.getInvoice_number()
                + "',item_id='" + InvoiceItem.getItem_id()
                + "',invoice_item_Unit_Price='" + InvoiceItem.getInvoice_item_Unit_Price()
                + "',invoice_item_Qty='" + InvoiceItem.getInvoice_item_Qty()
                + "' WHERE invoice_item_id='" + InvoiceItem.getInvoice_item_id() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "update Invoice Item Completed......");
        }
    }
    
    public static void deleteInvoiceItem(InvoiceItemModel InvoiceItem) 
            throws SQLException, ClassNotFoundException, IOException, Exception {
      
            Connection con =  DBCon.getConnection();
            String query = "DELETE FROM INVOICE_ITEM   WHERE invoice_item_id='" + InvoiceItem.getInvoice_item_id() + "'";
            int result = DBHandle.setData(con, query);
            if (result != 0) {
                JOptionPane.showMessageDialog(null, "delete Invoice Item Completed......");
            }
        
    }


    public static ArrayList showAllInvoiceItems() throws SQLException, 
            ClassNotFoundException, IOException, Exception {
        
        ArrayList<InvoiceItemModel> arInvoiceItem
                = new ArrayList<InvoiceItemModel>();    // java.util
       
            Connection con = DBCon.getConnection();
            ResultSet result = DBHandle.getData(con, "SELECT * FROM CUSTOMER");

            while (result.next()) {
                InvoiceItemModel InvoiceItem = new InvoiceItemModel();
                InvoiceItem.setInvoice_item_id(result.getString("customer_order_item_id"));
                InvoiceItem.setInvoice_number(result.getString("customer_order_number"));
                InvoiceItem.setItem_id(result.getString("item_id"));
                InvoiceItem.setInvoice_item_Unit_Price(result.getString("customer_order_item_unit_price"));
                InvoiceItem.setInvoice_item_Qty(result.getString("customer_order_item_qty"));

                arInvoiceItem.add(InvoiceItem);
            }

        return arInvoiceItem;

    }
}

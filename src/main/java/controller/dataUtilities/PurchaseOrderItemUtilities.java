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
import model.PurchaseOrderItemModel;

/**
 *
 * @author nadee
 */

public class PurchaseOrderItemUtilities {
    public static void addPurchaseOrderItem(PurchaseOrderItemModel PurchaseOrderItem) 
             throws SQLException, ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO PURCHASE_ORDER_ITEM ("
                + "purchase_order_item_id, "
                + "purchase_order_number, "
                + "item_id, "
                + "purchase_order_item_qty)  VALUES('"
                + PurchaseOrderItem.getPurchase_order_item_id() + "','"
                + PurchaseOrderItem.getPurchase_order_number() + "','"
                + PurchaseOrderItem.getItem_id() + "','"
                + PurchaseOrderItem.getPurchase_order_item_qty() +"')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add Purchase Order Item Completed......");
        }
    }

    public static void updatePurchaseOrderItem(PurchaseOrderItemModel PurchaseOrderItem)
            throws SQLException, ClassNotFoundException, IOException, Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE PURCHASE_ORDER_ITEM SET "
                + "',purchase_order_number='" + PurchaseOrderItem.getPurchase_order_number()
                + "',item_id='" + PurchaseOrderItem.getItem_id()
                + "',purchase_order_item_qty='" + PurchaseOrderItem.getPurchase_order_item_qty()
                + "' WHERE purchase_order_item_id='" + PurchaseOrderItem.getPurchase_order_item_id() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "update Purchase Order Item Completed......");
        }
    }
    
    public static void deletePurchaseOrderItem(PurchaseOrderItemModel PurchaseOrderItem) 
            throws SQLException, ClassNotFoundException, IOException, Exception {
      
            Connection con =  DBCon.getConnection();
            String query = "DELETE FROM PURCHASE_ORDER_ITEM "
                    + "WHERE purchase_order_item_id='" 
                    + PurchaseOrderItem.getPurchase_order_item_id() + "'";
            
            int result = DBHandle.setData(con, query);
            if (result != 0) {
                JOptionPane.showMessageDialog(null, "delete Purchase Order Item Completed......");
            }
        
    }


    public static ArrayList showAllPurchaseOrderItems() throws SQLException, 
            ClassNotFoundException, IOException, Exception {
        
        ArrayList<PurchaseOrderItemModel> arPurchaseOrderItem
                = new ArrayList<PurchaseOrderItemModel>();    // java.util
       
            Connection con = DBCon.getConnection();
            ResultSet result = DBHandle.getData(con, "SELECT * FROM PURCHASE_ORDER_ITEM");

            while (result.next()) {
                PurchaseOrderItemModel PurchaseOrderItem = new PurchaseOrderItemModel();
                PurchaseOrderItem.setPurchase_order_item_id(result.getString("purchase_order_item_id"));
                PurchaseOrderItem.setPurchase_order_number(result.getString("purchase_order_number"));
                PurchaseOrderItem.setItem_id(result.getString("item_id"));
                PurchaseOrderItem.setPurchase_order_item_qty(result.getString("purchase_order_item_qty"));

                arPurchaseOrderItem.add(PurchaseOrderItem);
            }

        return arPurchaseOrderItem;

    }
}

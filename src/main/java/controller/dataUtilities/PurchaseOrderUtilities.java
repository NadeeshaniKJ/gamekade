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
import model.PurchaseOrderModel;

/**
 *
 * @author nadee
 */
public class PurchaseOrderUtilities {
    public static void addPurchaseOrder(PurchaseOrderModel purchaseOrder)
            throws SQLException, ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO PURCHASE_ORDER ("
                + "purchase_order_number, "
                + "supplier_id, "
                + "purchase_order_datetime)  VALUES('"
                + purchaseOrder.getPurchase_order_number() + "','"
                + purchaseOrder.getSupplier_id() + "','"
                + purchaseOrder.getPurchase_order_datetime() + "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add purchase Order Completed......");
        }
    }

    public static void updatePurchaseOrder(PurchaseOrderModel purchaseOrder)
            throws SQLException, ClassNotFoundException, IOException,
            Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE PURCHASE_ORDER SET "
                + "',supplier_id='" + purchaseOrder.getSupplier_id()
                + "',purchase_order_datetime='" + purchaseOrder.getPurchase_order_datetime()
                + "' WHERE purchase_order_number='" + purchaseOrder.getPurchase_order_number() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "update Purchase Order Completed......");
        }
    }

    public static void deletePurchaseOrder(PurchaseOrderModel purchaseOrder)
            throws SQLException, ClassNotFoundException, IOException,
            Exception {

        Connection con = DBCon.getConnection();
        String query = "DELETE FROM PURCHASE_ORDER   "
                + "WHERE purchase_order_number='"
                + purchaseOrder.getPurchase_order_number()+ "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "delete Purchase Order Completed......");
        }

    }

    public static PurchaseOrderModel showPurchaseOrder(String purchase_order_number) throws Exception {

        PurchaseOrderModel purchaseOrder = new PurchaseOrderModel();

        Connection con = DBCon.getConnection();
        String query = "SELECT * FROM PURCHASE_ORDER "
                + "WHERE purchase_order_number='"
                + purchase_order_number + "'";

        ResultSet result = DBHandle.getData(con, query);
        while (result.next()) {
            purchaseOrder.setSupplier_id(result.getString("supplier_id"));
            purchaseOrder.setPurchase_order_datetime(result.getString("purchase_order_datetime"));
        }

        return purchaseOrder;
    }

    public static ArrayList showAllCustomerOrders() throws SQLException,
            ClassNotFoundException, IOException, Exception {

        ArrayList<PurchaseOrderModel> arpurchaseOrder
                = new ArrayList<PurchaseOrderModel>();    // java.util

        Connection con = DBCon.getConnection();
        ResultSet result = DBHandle.getData(con, "SELECT * FROM PURCHASE_ORDER");

        while (result.next()) {
            PurchaseOrderModel purchaseOrder = new PurchaseOrderModel();
            purchaseOrder.setSupplier_id(result.getString("supplier_id"));
            purchaseOrder.setPurchase_order_datetime(result.getString("purchase_order_datetime"));
            
            arpurchaseOrder.add(purchaseOrder);
        }

        return arpurchaseOrder;

    }
}

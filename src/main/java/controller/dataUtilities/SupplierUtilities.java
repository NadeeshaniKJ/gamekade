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
import model.SupplierModel;

/**
 *
 * @author nadee
 */
public class SupplierUtilities {
    
     public static void addSupplier(SupplierModel Supplier)
            throws SQLException, ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO SUPPLIER ("
                + "supplier_id, "
                + "supplier_category_id, "
                + "supplier_title, "
                + "supplier_name,"
                + "supplier_address,"
                + "supplier_contact_number)  VALUES('"
                + Supplier.getSupplier_id() + "','"
                + Supplier.getSupplier_category_id() + "','"
                + Supplier.getSupplier_title() + "','"
                + Supplier.getSupplier_name() + "','"
                + Supplier.getSupplier_address() + "','"
                + Supplier.getSupplier_contact_number() + "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add Supplier Completed......");
        }
    }

    public static void updateSupplier(SupplierModel Supplier)
            throws SQLException, ClassNotFoundException, IOException,
            Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE SUPPLIER SET "
                + "',supplier_category_id='" + Supplier.getSupplier_category_id()
                + "',supplier_title='" + Supplier.getSupplier_title()
                + "',supplier_name='" + Supplier.getSupplier_name()
                + "',supplier_address='" + Supplier.getSupplier_address()
                + "',supplier_contact_number='" + Supplier.getSupplier_contact_number()
                + "' WHERE supplier_id='" + Supplier.getSupplier_id() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "update Supplier Completed...");
        }
    }

    public static void deleteSupplier(SupplierModel Supplier)
            throws SQLException, ClassNotFoundException, IOException,
            Exception {

        Connection con = DBCon.getConnection();
        String query = "DELETE FROM SUPPLIER "
                + "WHERE supplier_id='"
                + Supplier.getSupplier_id() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "delete Supplier Completed...");
        }

    }

    public static SupplierModel showSupplier(String supplier_id) throws Exception {

        SupplierModel Supplier = new SupplierModel();

        Connection con = DBCon.getConnection();
        String query = "SELECT * FROM SUPPLIER "
                + "WHERE supplier_id='"
                + supplier_id + "'";

        ResultSet result = DBHandle.getData(con, query);
        while (result.next()) {
            Supplier.setSupplier_id(result.getString("supplier_id"));
            Supplier.setSupplier_category_id(result.getString("supplier_category_id"));
            Supplier.setSupplier_title(result.getString("supplier_title"));
            Supplier.setSupplier_name(result.getString("supplier_name"));
            Supplier.setSupplier_address(result.getString("supplier_address"));
            Supplier.setSupplier_contact_number(result.getString("supplier_contact_number"));
        }

        return Supplier;
    }

    public static ArrayList showAllSuppliers() throws SQLException,
            ClassNotFoundException, IOException, Exception {

        ArrayList<SupplierModel> arSupplier
                = new ArrayList<SupplierModel>();    // java.util

        Connection con = DBCon.getConnection();
        ResultSet result = DBHandle.getData(con, "SELECT * FROM SUPPLIER");

        while (result.next()) {
            SupplierModel Supplier = new SupplierModel();
            Supplier.setSupplier_id(result.getString("supplier_id"));
            Supplier.setSupplier_category_id(result.getString("supplier_category_id"));
            Supplier.setSupplier_title(result.getString("supplier_title"));
            Supplier.setSupplier_name(result.getString("supplier_name"));
            Supplier.setSupplier_address(result.getString("supplier_address"));
            Supplier.setSupplier_contact_number(result.getString("supplier_contact_number"));

            arSupplier.add(Supplier);
        }

        return arSupplier;

    }
}

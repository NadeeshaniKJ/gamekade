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
import model.SupplierCategoryModel;

/**
 *
 * @author nadee
 */
public class SupplierCategoryUtilities {
    public static void addSupplierCategory(SupplierCategoryModel SupplierCategory)
            throws SQLException, ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO SUPPLIER_CATEGORY ("
                + "supplier_category_id, "
                + "supplier_category_name)  VALUES('"
                + SupplierCategory.getSupplier_category_id() + "','"
                + SupplierCategory.getSupplier_category_name() + "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add Supplier Category Completed......");
        }
    }

    public static void updateSupplier(SupplierCategoryModel SupplierCategory)
            throws SQLException, ClassNotFoundException, IOException,
            Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE SUPPLIER_CATEGORY SET "
                + "',supplier_category_name='" + SupplierCategory.getSupplier_category_name()
                + "' WHERE supplier_category_id='" + SupplierCategory.getSupplier_category_id() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "update Supplier  Category Completed...");
        }
    }

    public static void deleteSupplier(SupplierCategoryModel SupplierCategory)
            throws SQLException, ClassNotFoundException, IOException,
            Exception {

        Connection con = DBCon.getConnection();
        String query = "DELETE FROM SUPPLIER_CATEGORY "
                + "WHERE supplier_category_id='"
                + SupplierCategory.getSupplier_category_id() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "delete Supplier Category Completed...");
        }

    }

    public static SupplierCategoryModel showSupplierCategory(String supplier_category_id) throws Exception {

        SupplierCategoryModel SupplierCategory = new SupplierCategoryModel();

        Connection con = DBCon.getConnection();
        String query = "SELECT * FROM SUPPLIER_CATEGORY "
                + "WHERE supplier_category_id='"
                + supplier_category_id + "'";

        ResultSet result = DBHandle.getData(con, query);
        while (result.next()) {
            SupplierCategory.setSupplier_category_id(result.getString("supplier_category_id"));
            SupplierCategory.setSupplier_category_name(result.getString("supplier_category_name"));
        }

        return SupplierCategory;
    }

    public static ArrayList showAllSupplierCategory() throws SQLException,
            ClassNotFoundException, IOException, Exception {

        ArrayList<SupplierCategoryModel> arSupplierCategory
                = new ArrayList<SupplierCategoryModel>();    // java.util

        Connection con = DBCon.getConnection();
        ResultSet result = DBHandle.getData(con, "SELECT * FROM SUPPLIER_CATEGORY");

        while (result.next()) {
            SupplierCategoryModel SupplierCategory = new SupplierCategoryModel();
            SupplierCategory.setSupplier_category_id(result.getString("supplier_category_id"));
            SupplierCategory.setSupplier_category_name(result.getString("supplier_category_name"));

            arSupplierCategory.add(SupplierCategory);
        }

        return arSupplierCategory;

    }
}

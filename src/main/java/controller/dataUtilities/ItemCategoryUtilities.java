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
import model.ItemCategoryModel;

/**
 *
 * @author nadee
 */
public class ItemCategoryUtilities {
    public static void addItemCategory(ItemCategoryModel ItemCategory) 
            throws SQLException,ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO ITEM_CATEGORY ("
                + "item_category_id, "
                + "item_category_name)  VALUES('"
                + ItemCategory.getItem_category_id() + "','"
                + ItemCategory.getItem_category_name()+  "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add Item Category Completed.");
        }
    }

    public static void updateItemCategory(ItemCategoryModel ItemCategory) 
            throws SQLException, ClassNotFoundException, IOException, Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE ITEM_CATEGORY SET "
                + "'item_category_name='" + ItemCategory.getItem_category_name()
                + "' WHERE item_category_id='" + ItemCategory.getItem_category_id()+ "'";
 
        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null,"update Item Category Completed.");
        }
    }
    
    public static void deleteItemCategory(ItemCategoryModel ItemCategory) 
            throws SQLException, ClassNotFoundException, IOException, Exception{
      
            Connection con =  DBCon.getConnection();
            String query = "DELETE FROM ITEM_CATEGORY WHERE item_category_id='" 
                    + ItemCategory.getItem_category_id() + "'";
            
            int result = DBHandle.setData(con, query);
            if (result != 0) {
                JOptionPane.showMessageDialog(null, 
                        "delete Item Category Completed......");
            }
        
    }


    public static ArrayList showAllItemCategory() throws SQLException, 
            ClassNotFoundException, IOException, Exception {
        
        ArrayList<ItemCategoryModel> arItemCategory = 
                new ArrayList<ItemCategoryModel>(); // java.util
       
            Connection con = DBCon.getConnection();
            ResultSet result = DBHandle.getData(con, "SELECT * FROM ITEM_CATEGORY");

            while (result.next()) {
                ItemCategoryModel ItemCategory = new ItemCategoryModel();
                ItemCategory.setItem_category_id(result.getString("item_category_id"));
                ItemCategory.setItem_category_name(result.getString("item_category_name"));
                
                arItemCategory.add(ItemCategory);
            }

        return arItemCategory;

    }
}

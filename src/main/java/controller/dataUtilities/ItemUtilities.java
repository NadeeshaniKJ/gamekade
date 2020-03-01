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
import model.ItemModel;

/**
 *
 * @author nadee
 */
public class ItemUtilities {
    public static void addItem(ItemModel item) throws SQLException,
            ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO ITEM ("
                + "item_id, "
                + "item_category_id, "
                + "item_name, "
                + "item_unit,"
                + "item_unit_price,"
                + "qty_onhand)  VALUES('"
                + item.getItem_id() + "','"
                + item.getItem_category_id() + "','"
                + item.getItem_name() + "','"
                + item.getItem_unit() + "','"
                + item.getItem_unit_price() + "','"
                + item.getQty_onhand() +  "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add item Completed......");
        }
    }

    public static void updateItem(ItemModel item) 
            throws SQLException, ClassNotFoundException, IOException, Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE ITEM SET "
                + "',item_category_id='" + item.getItem_category_id()
                + "',item_name='" + item.getItem_name()
                + "',item_unit='" + item.getItem_unit()
                + "',item_unit_price='" + item.getItem_unit_price()
                + "',qty_onhand='" + item.getQty_onhand()
                + "' WHERE item_id='" + item.getItem_id()+ "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null,"update item Completed.....");
        }
    }
    
    public static void deleteInvoice(ItemModel item) 
            throws SQLException, ClassNotFoundException, IOException, Exception{
      
            Connection con =  DBCon.getConnection();
            String query = "DELETE FROM ITEM WHERE item_id='" 
                    + item.getItem_id() + "'";
            
            int result = DBHandle.setData(con, query);
            if (result != 0) {
                JOptionPane.showMessageDialog(null, 
                        "delete item Completed......");
            }
        
    }

    public static ItemModel showItem(String item_id) 
            throws Exception {
       
        ItemModel item = new ItemModel();
       
            Connection con = DBCon.getConnection();
            String query = "SELECT * FROM ITEM WHERE item_id='" + item_id + "'";
            ResultSet result = DBHandle.getData(con, query);
            while (result.next()) {
                item.setItem_id(result.getString("item_id"));
                item.setItem_category_id(result.getString("item_category_id"));
                item.setItem_name(result.getString("item_name"));
                item.setItem_unit(result.getString("item_unit"));
                item.setItem_unit_price(result.getString("item_unit_price"));
                item.setQty_onhand(result.getString("qty_onhand"));
            }
        return item;
    }

    public static ArrayList showAllItems() throws SQLException, 
            ClassNotFoundException, IOException, Exception {
        
        ArrayList<ItemModel> arItem = new ArrayList<ItemModel>(); // java.util
       
            Connection con = DBCon.getConnection();
            ResultSet result = DBHandle.getData(con, "SELECT * FROM ITEM");

            while (result.next()) {
                ItemModel item = new ItemModel();
                item.setItem_id(result.getString("item_id"));
                item.setItem_category_id(result.getString("item_category_id"));
                item.setItem_name(result.getString("item_name"));
                item.setItem_unit(result.getString("item_unit"));
                item.setItem_unit_price(result.getString("item_unit_price"));
                item.setQty_onhand(result.getString("qty_onhand"));

                arItem.add(item);
            }

        return arItem;

    }
}

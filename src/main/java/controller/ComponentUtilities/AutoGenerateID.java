/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ComponentUtilities;

import dbConnection.DBCon;
import dbConnection.DBHandle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author nadee
 */
public class AutoGenerateID {
     public static String getNextID(String table,String column,String prefix,int MinimumIntegerDigits) {
        String id = prefix+"000";
        try {
            Connection con = DBCon.getConnection();
            ResultSet rst = DBHandle.getData(con,
                    "SELECT "+column+" FROM "+table+" ORDER BY 1 DESC Limit 1");
            while (rst.next()) {
                id = rst.getString(1);
            }
                System.out.println("last id"+id);
            int index = 0;
            for (int i = id.length() - 1; i >= 0; i--) {
                try {
                    Integer.parseInt(id.substring(i));
                } catch (Exception e) {
                    index = i + 1;
                    break;
                }
            }
            String newID = id.substring(index);
            int n = Integer.parseInt(newID);
            n++;
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMinimumIntegerDigits(MinimumIntegerDigits);
            nf.setGroupingUsed(false);//no more  100,000
//            nf.setMaximumFractionDigits(2);   nf.setMinimumFractionDigits(2) ;//
             id=id.substring(0, index) + nf.format(n);
             System.out.println("new id"+id);
        } catch (Exception ex) {
            ImageIcon image = new ImageIcon("src\\pictures\\mood\\50WATCH YA.png");
            int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Please set correct database", "Exit Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image);
//            if (showConfirmDialog == 0) {
//                java.awt.Frame parent=new java.awt.Frame();
//                Setings s=new Setings(parent,true);
//                s.setVisible(true);
//
//            }
        }
        return id;
    }
     
     public static void main(String[] args){
     getNextID("CUSTOMER","customer_id","C",5);
     }
}

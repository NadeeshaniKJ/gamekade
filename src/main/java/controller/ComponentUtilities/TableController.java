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
import java.sql.ResultSetMetaData;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nadee
 */
public class TableController {
    public static synchronized void addDataToTable(JTable table, String query) {
        try {
            DefaultTableModel dt = (DefaultTableModel) table.getModel();

            int rowC = table.getRowCount();
            for (int i = 0; i < rowC; i++) {
                dt.removeRow(0);
            }
            Connection con = new DBCon().getConnection();
            ResultSet rst = DBHandle.getData(con, query);
            ResultSetMetaData meta = rst.getMetaData();
            int col = meta.getColumnCount();
            while (rst.next()) {
                Object ob[] = new Object[col];
                for (int i = 0; i < ob.length; i++) {
                    ob[i] = rst.getString(i + 1);
                }
                dt.addRow(ob);
//                try {      Thread.sleep(100);      } catch (Exception e) {}
            }
        } catch (Exception ex) {
            System.out.println(325346342);
            ex.printStackTrace();
        }

    }
}

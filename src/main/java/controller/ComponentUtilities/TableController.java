/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ComponentUtilities;

import controller.dataUtilities.CustomerUtilities;
import dbConnection.DBCon;
import dbConnection.DBHandle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.CustomerModel;

/**
 *
 * @author nadee
 */
public class TableController {

    private TableColumnModel tableColumnModel;
    private String[] columnNamesArr;
    private ArrayList<String> columnNamesList;
    private String[][] data;
    private DefaultTableModel defaultTableModel;
    private JTable customerTable;

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
//    JTable table, String query
     public static synchronized void addDataToColumn() {
        try {
            
//             ArrayList<CustomerModel> arCustomer = CustomerUtilities.getAllCustomers();
//            for (CustomerModel c : arCustomer) {
//                cmb_cusID.addItem(c.getCustomer_id());
//            }
//            DefaultTableModel dt = (DefaultTableModel) table.getModel();

//            int rowC = table.getRowCount();
//            for (int i = 0; i < rowC; i++) {
//                dt.removeRow(0);
//            }
            Connection con = new DBCon().getConnection();
            ResultSet rst = DBHandle.getData(con, "Select customer_name FROM CUSTOMER");
            System.out.println(rst);
            ResultSetMetaData meta = rst.getMetaData();
            System.out.println(meta);
            int col = meta.getColumnCount();
            
            
            while (rst.next()) {
                Object ob[] = new Object[col];
                for (int i = 0; i < ob.length; i++) {
                    ob[i] = rst.getString(i + 1);
                }
//                dt.addRow(ob);
              
//                try {      Thread.sleep(100);      } catch (Exception e) {}
            }
        } catch (Exception ex) {
            System.out.println(325346342);
            ex.printStackTrace();
        }

    }
    
    
/*
    private void createTable(ArrayList<String> columnNamesList,ArrayList<JCheckBox> chk_ID, JScrollPane jScrollPane2) {
        new Thread() {
            public void run() {

//                columnNamesList = new ArrayList<String>();
//                columnNamesList.add("ID");
//                columnNamesList.add("TITLE");
//                columnNamesList.add("NAME");
//                columnNamesList.add("NIC");
//                columnNamesList.add("BIRTHDAY");
//                columnNamesList.add("EMAIL");
//                columnNamesList.add("CONTACT");
//                columnNamesList.add("ADDRESS");
//                columnNamesList.add("CITY");
//                columnNamesList.add("PROVINCE");
//                columnNamesList.add("POSTAL CODE");

                data = new String[1][columnNamesList.size()];

                columnNamesArr = new String[columnNamesList.size()];
                for (int i = 0; i < columnNamesList.size(); i++) {
                    columnNamesArr[i] = columnNamesList.get(i);
                    data[0][i] = "";
                }

                defaultTableModel = new DefaultTableModel(data, columnNamesArr);

                customerTable = new JTable(defaultTableModel);
                tableColumnModel = customerTable.getColumnModel();

                for (int i = 0; i < columnNamesList.size(); i++) {
                    tableColumnModel.getColumn(i).setPreferredWidth(columnNamesList.get(i).length());
                }
                customerTable.setPreferredScrollableViewportSize(customerTable.getPreferredSize());
                jScrollPane2.getViewport().add(customerTable);
                ;
                chk_ID.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_ID.isSelected()) {
                            TableColumn toRemove = customerTable.getColumn("ID");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("ID");
                            toAdd.setIdentifier("ID");
                            toAdd.setPreferredWidth("ID".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
                        }
                    }
                });

            }
        }.start();
        customerTable.setAutoCreateRowSorter(true);
    }

    */
    public static synchronized void getTableColumns(String tablename) {
        try {
//            DefaultTableModel dt = (DefaultTableModel) table.getModel();

            Connection con = new DBCon().getConnection();
            ResultSet rst = DBHandle.getData(con, "SHOW COLUMNS FROM CUSTOMER");
            ResultSetMetaData meta = rst.getMetaData();
            int col = meta.getColumnCount();
            String colName = meta.getColumnName(1);
            while (rst.next()) {
                for (int i = 0; i < col; i++) {
                    System.out.println(meta.getColumnLabel(1));
                }

//                Object ob[] = new Object[col];
//                for (int i = 0; i < ob.length; i++) {
//                    ob[i] = rst.getString(i + 1);
//                }
//                dt.addRow(ob);
//                try {      Thread.sleep(100);      } catch (Exception e) {}
            }
        } catch (Exception ex) {
            System.out.println(325346342);
            ex.printStackTrace();
        }

    }
}

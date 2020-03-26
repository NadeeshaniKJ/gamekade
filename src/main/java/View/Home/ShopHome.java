/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Home;

import View.Customer.AddCustomer;
import View.Customer.View_or_DeleteCustomer;
import View.Customer.SearchCustomer;
import View.CustomerOrder.*;
import View.Item.DeleteItem;
import View.Supplier.DeleteSupplier;
import controller.ComponentUtilities.TableController;
import controller.dataUtilities.CustomerUtilities;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import model.CustomerModel;

/**
 *
 * @author nadee
 */
public class ShopHome extends javax.swing.JFrame {

    /**
     * Creates new form ShopHome
     */
//        DefaultTableModel tableModel = new DefaultTableModel();
//        JTable jTbl_AllCustomers2 = new JTable(tableModel);
    public ShopHome() {
        initComponents();
        setExtendedState(ShopHome.MAXIMIZED_BOTH);

        new Thread() {
            public void run() {

                try {
                    TableController.addDataToTable(jTbl_AllSales, "Select * FROM CUSTOMER_ORDER");
                    TableController.addDataToTable(jTbl_AllCustomers, "Select * FROM CUSTOMER");
                    TableController.addDataToTable(jTbl_AllIems, "Select * FROM ITEM");
                    TableController.addDataToTable(jTbl_AllSuppliers, "Select * FROM SUPPLIER");

                    addMouseClickListeners();
                } catch (Exception e) {
                }
            }
        }.start();
        jSplitPane1.setRightComponent(jLP_CustomerOrders);
        jTbl_AllSales.setAutoCreateRowSorter(true);

    }

    private void addMouseClickListeners() {

        jTbl_AllSales.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTbl_AllSales.rowAtPoint(evt.getPoint());
                final String valueInCell = (String) jTbl_AllSales.getValueAt(row, 0);

                //for verification
                try {
                    System.out.println(valueInCell);
                    CustomerModel cus = CustomerUtilities.getCustomer(valueInCell);
                    System.out.println(cus.getCustomer_name() + " " + cus.getCustomer_title());
                } catch (Exception ex) {
                    Logger.getLogger(SearchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
                // verifiction ends

                ViewCustomerOrder view = new ViewCustomerOrder(valueInCell);
                view.setVisible(true);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

        jTbl_AllCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTbl_AllCustomers.rowAtPoint(evt.getPoint());
                final String valueInCell = (String) jTbl_AllCustomers.getValueAt(row, 0);

                //for verification
                try {
                    System.out.println(valueInCell);
                    CustomerModel cus = CustomerUtilities.getCustomer(valueInCell);
                    System.out.println(cus.getCustomer_name() + " " + cus.getCustomer_title());
                } catch (Exception ex) {
                    Logger.getLogger(SearchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
                // verifiction ends

                View_or_DeleteCustomer view = new View_or_DeleteCustomer(valueInCell);
                view.setVisible(true);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

        jTbl_AllIems.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTbl_AllIems.rowAtPoint(evt.getPoint());
                final String valueInCell = (String) jTbl_AllIems.getValueAt(row, 0);

                //for verification
                try {
                    System.out.println(valueInCell);
                    CustomerModel cus = CustomerUtilities.getCustomer(valueInCell);
                    System.out.println(cus.getCustomer_name() + " " + cus.getCustomer_title());
                } catch (Exception ex) {
                    Logger.getLogger(SearchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
                // verifiction ends

                DeleteItem view = new DeleteItem(valueInCell);
                view.setVisible(true);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
        jTbl_AllSuppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTbl_AllSuppliers.rowAtPoint(evt.getPoint());
                final String valueInCell = (String) jTbl_AllSuppliers.getValueAt(row, 0);

                //for verification
                try {
                    System.out.println(valueInCell);
                    CustomerModel cus = CustomerUtilities.getCustomer(valueInCell);
                    System.out.println(cus.getCustomer_name() + " " + cus.getCustomer_title());
                } catch (Exception ex) {
                    Logger.getLogger(SearchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
                // verifiction ends

                DeleteSupplier view = new DeleteSupplier(valueInCell);
                view.setVisible(true);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLP_CustomerOrders = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbl_AllSales = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_add1 = new javax.swing.JButton();
        btn_search1 = new javax.swing.JButton();
        btn_print1 = new javax.swing.JButton();
        btn_settings1 = new javax.swing.JButton();
        jLP_Customers = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTbl_AllCustomers = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btn_add = new javax.swing.JButton();
        btn_search = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        btn_settings = new javax.swing.JButton();
        jLP_Items = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTbl_AllIems = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btn_add2 = new javax.swing.JButton();
        btn_edit2 = new javax.swing.JButton();
        btn_search2 = new javax.swing.JButton();
        btn_delete2 = new javax.swing.JButton();
        btn_print2 = new javax.swing.JButton();
        btn_settings2 = new javax.swing.JButton();
        jLP_Suppliers = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTbl_AllSuppliers = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_add3 = new javax.swing.JButton();
        btn_edit3 = new javax.swing.JButton();
        btn_search3 = new javax.swing.JButton();
        btn_delete3 = new javax.swing.JButton();
        btn_print3 = new javax.swing.JButton();
        btn_settings3 = new javax.swing.JButton();
        jLP_Users = new javax.swing.JLayeredPane();
        jLP_Help = new javax.swing.JLayeredPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        btn_Sales = new javax.swing.JButton();
        btn_Customers = new javax.swing.JButton();
        btn_Products = new javax.swing.JButton();
        btn_Suppliers = new javax.swing.JButton();
        btn_Users = new javax.swing.JButton();
        btn_Help = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();

        jTbl_AllSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order No.", "Customer ID", "Date Time", "SubTotal", "Discount", "NetTotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTbl_AllSales);

        btn_add1.setText("+New");
        btn_add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add1ActionPerformed(evt);
            }
        });

        btn_search1.setText("Search");
        btn_search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search1ActionPerformed(evt);
            }
        });

        btn_print1.setText("Print");
        btn_print1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print1ActionPerformed(evt);
            }
        });

        btn_settings1.setText("Settings");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btn_add1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_search1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_print1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_settings1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_settings1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_print1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_search1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_add1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLP_CustomerOrders.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLP_CustomerOrders.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLP_CustomerOrdersLayout = new javax.swing.GroupLayout(jLP_CustomerOrders);
        jLP_CustomerOrders.setLayout(jLP_CustomerOrdersLayout);
        jLP_CustomerOrdersLayout.setHorizontalGroup(
            jLP_CustomerOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLP_CustomerOrdersLayout.setVerticalGroup(
            jLP_CustomerOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLP_CustomerOrdersLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
        );

        jTbl_AllCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Name", "NIC", "Birth Day", "EMail address", "Contact No.", "Address", "City", "Province", "Postal Code"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTbl_AllCustomers);

        btn_add.setText("+New");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_print.setText("Print");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });

        btn_settings.setText("Settings");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btn_add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_settings))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
            .addComponent(btn_settings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_print, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLP_Customers.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLP_Customers.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLP_CustomersLayout = new javax.swing.GroupLayout(jLP_Customers);
        jLP_Customers.setLayout(jLP_CustomersLayout);
        jLP_CustomersLayout.setHorizontalGroup(
            jLP_CustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
        );
        jLP_CustomersLayout.setVerticalGroup(
            jLP_CustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLP_CustomersLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
        );

        jTbl_AllIems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Category", "Name", "Unit", "Unit Price", "In Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTbl_AllIems);

        btn_add2.setText("+New");
        btn_add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add2ActionPerformed(evt);
            }
        });

        btn_edit2.setText("Edit");
        btn_edit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit2ActionPerformed(evt);
            }
        });

        btn_search2.setText("Search");
        btn_search2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search2ActionPerformed(evt);
            }
        });

        btn_delete2.setText("Delete");

        btn_print2.setText("Print");
        btn_print2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print2ActionPerformed(evt);
            }
        });

        btn_settings2.setText("Settings");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btn_add2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_edit2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_delete2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_print2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 387, Short.MAX_VALUE)
                .addComponent(btn_settings2))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_add2, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
            .addComponent(btn_edit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_search2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_delete2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_print2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_settings2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLP_Items.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLP_Items.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLP_ItemsLayout = new javax.swing.GroupLayout(jLP_Items);
        jLP_Items.setLayout(jLP_ItemsLayout);
        jLP_ItemsLayout.setHorizontalGroup(
            jLP_ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLP_ItemsLayout.setVerticalGroup(
            jLP_ItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLP_ItemsLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
        );

        jTbl_AllSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Category", "Title", "Name", "Address", "Contact Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTbl_AllSuppliers);

        btn_add3.setText("+New");
        btn_add3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add3ActionPerformed(evt);
            }
        });

        btn_edit3.setText("Edit");
        btn_edit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit3ActionPerformed(evt);
            }
        });

        btn_search3.setText("Search");
        btn_search3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search3ActionPerformed(evt);
            }
        });

        btn_delete3.setText("Delete");

        btn_print3.setText("Print");
        btn_print3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print3ActionPerformed(evt);
            }
        });

        btn_settings3.setText("Settings");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btn_add3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_edit3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_delete3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_print3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 387, Short.MAX_VALUE)
                .addComponent(btn_settings3))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_add3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
            .addComponent(btn_edit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_search3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_delete3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_print3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_settings3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLP_Suppliers.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLP_Suppliers.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLP_SuppliersLayout = new javax.swing.GroupLayout(jLP_Suppliers);
        jLP_Suppliers.setLayout(jLP_SuppliersLayout);
        jLP_SuppliersLayout.setHorizontalGroup(
            jLP_SuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLP_SuppliersLayout.setVerticalGroup(
            jLP_SuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLP_SuppliersLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jLP_UsersLayout = new javax.swing.GroupLayout(jLP_Users);
        jLP_Users.setLayout(jLP_UsersLayout);
        jLP_UsersLayout.setHorizontalGroup(
            jLP_UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLP_UsersLayout.setVerticalGroup(
            jLP_UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jLP_HelpLayout = new javax.swing.GroupLayout(jLP_Help);
        jLP_Help.setLayout(jLP_HelpLayout);
        jLP_HelpLayout.setHorizontalGroup(
            jLP_HelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLP_HelpLayout.setVerticalGroup(
            jLP_HelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_Sales.setText("Sales");
        btn_Sales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalesActionPerformed(evt);
            }
        });

        btn_Customers.setText("Customers");
        btn_Customers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CustomersActionPerformed(evt);
            }
        });

        btn_Products.setText("Products");
        btn_Products.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProductsActionPerformed(evt);
            }
        });

        btn_Suppliers.setText("Suppliers");
        btn_Suppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuppliersActionPerformed(evt);
            }
        });

        btn_Users.setText("Users");
        btn_Users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UsersActionPerformed(evt);
            }
        });

        btn_Help.setText("Help");

        jLayeredPane2.setLayer(btn_Sales, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btn_Customers, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btn_Products, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btn_Suppliers, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btn_Users, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btn_Help, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Sales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Help, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addComponent(btn_Users, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Suppliers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Products, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Customers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(btn_Sales, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Customers, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Products, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Help, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jLayeredPane2);

        jLayeredPane1.setLayer(jSplitPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("jMenu3");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");
        jMenu3.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("jMenu4");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalesActionPerformed
        // TODO add your handling code here:
        jSplitPane1.setRightComponent(jLP_CustomerOrders);
        jTbl_AllSales.setAutoCreateRowSorter(true);


    }//GEN-LAST:event_btn_SalesActionPerformed

    private void btn_CustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CustomersActionPerformed
        // TODO add your handling code here:
        jSplitPane1.setRightComponent(jLP_Customers);
        jTbl_AllCustomers.setAutoCreateRowSorter(true);
    }//GEN-LAST:event_btn_CustomersActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        AddCustomer addCus = new AddCustomer();
        addCus.setVisible(true);
        addCus.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_ProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProductsActionPerformed
        // TODO add your handling code here:
        jSplitPane1.setRightComponent(jLP_Items);
        jTbl_AllIems.setAutoCreateRowSorter(true);

    }//GEN-LAST:event_btn_ProductsActionPerformed

    private void btn_SuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuppliersActionPerformed
        // TODO add your handling code here:
        jSplitPane1.setRightComponent(jLP_Suppliers);
        jTbl_AllSuppliers.setAutoCreateRowSorter(true);
    }//GEN-LAST:event_btn_SuppliersActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed

        SearchCustomer search = new SearchCustomer();
        search.setVisible(true);
        search.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_printActionPerformed

    private void btn_add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add1ActionPerformed
        AddCustomerOrder newOrder = new AddCustomerOrder();
        newOrder.setVisible(true);
        newOrder.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btn_add1ActionPerformed

    private void btn_search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search1ActionPerformed
        SearchCustomerOrder searchOrder = new SearchCustomerOrder();
        searchOrder.setVisible(true);
        searchOrder.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btn_search1ActionPerformed

    private void btn_print1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_print1ActionPerformed

    private void btn_add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_add2ActionPerformed

    private void btn_edit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit2ActionPerformed

    private void btn_search2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_search2ActionPerformed

    private void btn_print2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_print2ActionPerformed

    private void btn_add3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_add3ActionPerformed

    private void btn_edit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit3ActionPerformed

    private void btn_search3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_search3ActionPerformed

    private void btn_print3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_print3ActionPerformed

    private void btn_UsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UsersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_UsersActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShopHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShopHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShopHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShopHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShopHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Customers;
    private javax.swing.JButton btn_Help;
    private javax.swing.JButton btn_Products;
    private javax.swing.JButton btn_Sales;
    private javax.swing.JButton btn_Suppliers;
    private javax.swing.JButton btn_Users;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_add1;
    private javax.swing.JButton btn_add2;
    private javax.swing.JButton btn_add3;
    private javax.swing.JButton btn_delete2;
    private javax.swing.JButton btn_delete3;
    private javax.swing.JButton btn_edit2;
    private javax.swing.JButton btn_edit3;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_print1;
    private javax.swing.JButton btn_print2;
    private javax.swing.JButton btn_print3;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_search1;
    private javax.swing.JButton btn_search2;
    private javax.swing.JButton btn_search3;
    private javax.swing.JButton btn_settings;
    private javax.swing.JButton btn_settings1;
    private javax.swing.JButton btn_settings2;
    private javax.swing.JButton btn_settings3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLayeredPane jLP_CustomerOrders;
    private javax.swing.JLayeredPane jLP_Customers;
    private javax.swing.JLayeredPane jLP_Help;
    private javax.swing.JLayeredPane jLP_Items;
    private javax.swing.JLayeredPane jLP_Suppliers;
    private javax.swing.JLayeredPane jLP_Users;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTbl_AllCustomers;
    private javax.swing.JTable jTbl_AllIems;
    private javax.swing.JTable jTbl_AllSales;
    private javax.swing.JTable jTbl_AllSuppliers;
    // End of variables declaration//GEN-END:variables
}

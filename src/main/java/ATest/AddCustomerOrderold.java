/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ATest;

import View.Customer.*;
import controller.ComponentUtilities.AutoGenerateID;
import controller.ComponentUtilities.GetBirthDay;
import controller.ComponentUtilities.SearchableCombo;
import controller.ComponentUtilities.ValidateValues;
import controller.dataUtilities.CustomerUtilities;
import controller.dataUtilities.ItemUtilities;
import dbConnection.DBCon;
import dbConnection.DBHandle;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import model.CustomerModel;
import model.ItemModel;

/**
 *
 * @author nadee
 */
public class AddCustomerOrderold extends javax.swing.JFrame implements KeyListener {

    /**
     * Creates new form AddCustomerOrderold
     */
    // Non compulsory fields initialize true
    boolean nameCheck = false;
    boolean NICCheck = true;
    boolean contactCheck = true;
    boolean emailCheck = true;

    DefaultTableModel tableModel;
    JTextField txt_item;

    public AddCustomerOrderold() {
        initComponents();
//        this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
//        this.setLocationRelativeTo(null);
//        String id = AutoGenerateID.getNextID("CUSTOMER_ORDER_ITEM", "customer_order_item_id", "CI", 4);
        createTable();
        setKeyListener();
        loadComboItemSearch();
        setExtendedState(AddCustomerOrderold.MAXIMIZED_BOTH);
    }

    private void createTable() {
        tableModel = new DefaultTableModel();
        JTable itemTable = new JTable(tableModel);

        tableModel.addColumn("");
        tableModel.addColumn("");
        tableModel.addColumn("");

        jScrollPane1.getViewport().add(itemTable);
    }

    private void setKeyListener() {

        txt_item = (JTextField) cmb_itemSearch.getEditor().getEditorComponent();

        txt_item.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent evt) {
                try {
                    //change unit price according to the selected item // possible to create a method and call in constructor??
                    ArrayList<ItemModel> arItem = ItemUtilities.showAllItems();
                    for (ItemModel c : arItem) {
                        if (c.getItem_id().equals(txt_item.getText())) {
                            jLbl_unitPriceValue.setText(c.getItem_unit_price());
                        } else if (c.getItem_name().equals(txt_item.getText())) {
                            jLbl_unitPriceValue.setText(c.getItem_unit_price());
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AddCustomerOrderold.class.getName()).log(Level.SEVERE, null, ex);
                }

                // item selecting combo
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (txt_item.getText().equals("")) {
                        jLbl_unitPriceValue.setText("0.0");
                        jLbl_totalValue.setText("0.0");
                    } else {
                        txt_QTY.requestFocus();
                        txt_QTY.selectAll();
                    }
                    System.out.println("enter cmb");
                }
                
                // item selecting combo
                if (evt.getKeyCode() == KeyEvent.VK_F1) {
                    
                    int result = JOptionPane.showConfirmDialog(null, jPanel_Customer,
                            "Please Select Customer", JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        
                    }

                    cmb_Customer.setEnabled(true);
                    cmb_Customer.setEditable(true);
                    cmb_Customer.requestFocus();
                    loadComboCustomerSearch();

                }
                if (evt.getKeyCode() == KeyEvent.VK_F2) {
                    txt_discount.requestFocus();
                    System.out.println("enter cmb add new customer");
                }
                if (evt.getKeyCode() == KeyEvent.VK_F3) {
                    txt_discount.requestFocus();
                    System.out.println("enter cmb add new item");
                }
                if (evt.getKeyCode() == KeyEvent.VK_F4) {
                    txt_discount.requestFocus();
                    System.out.println("enter cmb Discount");
                }
                if (evt.getKeyCode() == KeyEvent.VK_END) {

                    JTextField xField = new JTextField(5);
                    JTextField yField = new JTextField(5);

                    xField.addKeyListener(new KeyAdapter() {
                        public void keyReleased(KeyEvent evt) {
                            Double d_Qty = Double.parseDouble(txt_QTY.getText());
                Double d_UnitPrice = Double.parseDouble(jLbl_unitPriceValue.getText());
                Double d_Total = d_Qty * d_UnitPrice;
                Double d_FinalTotal = Double.parseDouble(lbl_finlTotalValue.getText());

                jLbl_totalValue.setText(d_Total.toString());
                        }
                    });

                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("x:"));
                    myPanel.add(xField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("y:"));
                    myPanel.add(yField);

                    int result = JOptionPane.showConfirmDialog(null, jPanel_Customer,
                            "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        System.out.println("x value: " + xField.getText());
                        System.out.println("y value: " + yField.getText());
                    }

                    txt_item.setEnabled(false);
                    SaveOrder save = new SaveOrder();
                    save.setVisible(true);
                    save.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//                    JOptionPane.showConfirmDialog(null,
//                                        " ID        : ",
//                                        "Confirm",
//                                        JOptionPane.OK_CANCEL_OPTION);
//                    txt_discount.requestFocus();
                    System.out.println("enter cmb Discount dialog");
                }
            }
        });

        txt_QTY.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {

                Double d_Qty = Double.parseDouble(txt_QTY.getText());
                Double d_UnitPrice = Double.parseDouble(jLbl_unitPriceValue.getText());
                Double d_Total = d_Qty * d_UnitPrice;
                Double d_FinalTotal = Double.parseDouble(lbl_finlTotalValue.getText());

                jLbl_totalValue.setText(d_Total.toString());

                System.out.println(d_Total);

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    String description = (String) cmb_itemSearch.getSelectedItem();
                    String Qty = txt_QTY.getText() + " x " + jLbl_unitPriceValue.getText();
                    String total = jLbl_totalValue.getText();

                    tableModel.insertRow(0, new Object[]{description, Qty, total});

                    d_FinalTotal = d_FinalTotal + d_Total;
                    lbl_finlTotalValue.setText(d_FinalTotal.toString());
                    cmb_itemSearch.requestFocus();
                }
            }
        });

        JTextField txt_customer = (JTextField) cmb_Customer.getEditor().getEditorComponent();

        txt_customer.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    try {
                        ArrayList<CustomerModel> arCustomer = CustomerUtilities.getAllCustomers();
                        arCustomer.forEach((c) -> {
                            String ID = c.getCustomer_id();
                            String Title = c.getCustomer_title();
                            String Name = c.getCustomer_name();
                            String NIC = c.getCustomer_nic();
                            String Contact = c.getCustomer_contact();
                            String Email = c.getCustomer_email();
                            String Address = c.getCustomer_address();
                            String PostalCode = c.getCustomer_postalcode();
                            String Province = c.getCustomer_province();
                            String City = c.getCustomer_city();
                            if (ID.equals(txt_customer.getText())
                                    || Name.equals(txt_customer.getText())) {
                                int res = JOptionPane.showConfirmDialog(null,
                                        " ID        : " + ID
                                        + "\n Title     : " + Title
                                        + "\n Name      : " + Name
                                        + "\n NIC       : " + NIC
                                        + "\n Contact   : " + Contact
                                        + "\n Email     : " + Email
                                        + "\n Address   : " + Address
                                        + "\n PostalCode: " + PostalCode
                                        + "\n Province  : " + Province
                                        + "\n City      : " + City,
                                        "Confirm",
                                        JOptionPane.OK_CANCEL_OPTION);
                                if (res == JOptionPane.OK_OPTION) {
                                    cmb_Customer.setSelectedItem(Name);
                                    cmb_Customer.setEnabled(false);
                                    cmb_Customer.setEditable(false);
                                    txt_item.requestFocusInWindow();
                                }
                            }
                        });
                    } catch (Exception ex) {
                        Logger.getLogger(AddCustomerOrderold.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private void loadComboItemSearch() {
        cmb_itemSearch.addItem("");
        try {
            //load data from database
            ArrayList<ItemModel> arItem = ItemUtilities.showAllItems();
            for (ItemModel c : arItem) {
                cmb_itemSearch.addItem(c.getItem_id());
                cmb_itemSearch.addItem(c.getItem_name());

                SearchableCombo.orderComboItems(cmb_itemSearch);
            }
            SearchableCombo.setSearchableCombo(
                    true, "<<<<No Results>>>>", cmb_itemSearch);
            SearchableCombo.selectedComboItems(
                    cmb_itemSearch);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchCustomer.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(SearchCustomer.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(SearchCustomer.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        cmb_itemSearch.requestFocus();
    }

    private void loadComboCustomerSearch() {

        try {
            //load data from database
            ArrayList<CustomerModel> arCustomer = CustomerUtilities.getAllCustomers();
            for (CustomerModel c : arCustomer) {
                if (!"".equals(c.getCustomer_id())) {
                    cmb_Customer.addItem(c.getCustomer_id());
                }

                if (!"".equals(c.getCustomer_name())) {
                    cmb_Customer.addItem(c.getCustomer_name());
                }

                if (!"".equals(c.getCustomer_nic())) {
                    cmb_Customer.addItem(c.getCustomer_nic());
                }

                if (!"".equals(c.getCustomer_contact())) {
                    cmb_Customer.addItem(c.getCustomer_contact());
                }

                SearchableCombo.orderComboItems(cmb_Customer);
            }
            SearchableCombo.setSearchableCombo(
                    true, "<<<<No Results>>>>", cmb_Customer);
            SearchableCombo.selectedComboItems(
                    cmb_Customer);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchCustomer.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(SearchCustomer.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(SearchCustomer.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLbl_unit = new javax.swing.JLabel();
        jPanel_Customer = new javax.swing.JPanel();
        jLbl_addNewCus1 = new javax.swing.JLabel();
        jLbl_cusID = new javax.swing.JLabel();
        jLbl_cusName = new javax.swing.JLabel();
        jLbl_cusNIC1 = new javax.swing.JLabel();
        jLbl_cusContactNum1 = new javax.swing.JLabel();
        jLbl_cusEmail = new javax.swing.JLabel();
        jLbl_cusAddress = new javax.swing.JLabel();
        jLbl_cusCity = new javax.swing.JLabel();
        jLbl_cusProvince = new javax.swing.JLabel();
        jLbl_cusPostalCode = new javax.swing.JLabel();
        btn_deleteCustomer = new javax.swing.JButton();
        btn_cancle1 = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jLbl_cusTitle = new javax.swing.JLabel();
        jLbl_cusIDValue = new javax.swing.JLabel();
        jLbl_cusNameValue = new javax.swing.JLabel();
        jLbl_cusNICValue = new javax.swing.JLabel();
        jLbl_cusPostalCodeValue = new javax.swing.JLabel();
        jLbl_cusEmailValue = new javax.swing.JLabel();
        jLbl_cusCityValue = new javax.swing.JLabel();
        jLbl_cusContactNumValue = new javax.swing.JLabel();
        jLbl_cusAddressValue = new javax.swing.JLabel();
        jLbl_cusTitleValue = new javax.swing.JLabel();
        jLbl_cusProvinceValue = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLbl_addNewCus = new javax.swing.JLabel();
        cmb_itemSearch = new javax.swing.JComboBox<>();
        jLbl_cusNIC = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLbl_unitPrice = new javax.swing.JLabel();
        txt_QTY = new javax.swing.JTextField();
        jLbl_cusContactNum = new javax.swing.JLabel();
        jLbl_item = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLbl_qty = new javax.swing.JLabel();
        jLbl_unitPriceValue = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLbl_totalValue = new javax.swing.JLabel();
        jLbl_total = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmb_Customer = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btn_logout = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_addCustomer = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btn_cancle = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbl_finlTotalValue = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_discount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLbl_unit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_unit.setText("kg");

        jPanel_Customer.setPreferredSize(new java.awt.Dimension(660, 460));

        jLbl_addNewCus1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLbl_addNewCus1.setForeground(new java.awt.Color(51, 102, 255));
        jLbl_addNewCus1.setText("CUSTOMER DETAILS");

        jLbl_cusID.setText("CUSTOMER ID :");

        jLbl_cusName.setText("NAME :");

        jLbl_cusNIC1.setText("NIC :");

        jLbl_cusContactNum1.setText("CONTACT NUMBER :");

        jLbl_cusEmail.setText("E- MAIL :");

        jLbl_cusAddress.setText("ADDRESS :");

        jLbl_cusCity.setText("CITY :");

        jLbl_cusProvince.setText("PROVINCE :");

        jLbl_cusPostalCode.setText("POSTAL CODE :");

        btn_deleteCustomer.setText("DELETE CUSTOMER");
        btn_deleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteCustomerActionPerformed(evt);
            }
        });

        btn_cancle1.setText("CANCEL");
        btn_cancle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancle1ActionPerformed(evt);
            }
        });

        btn_update.setText("UPDATE CUSTOMER");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        jLbl_cusTitle.setText("TITLE :");

        jLbl_cusIDValue.setText("ID");

        jButton7.setText("ADD ORDER");

        javax.swing.GroupLayout jPanel_CustomerLayout = new javax.swing.GroupLayout(jPanel_Customer);
        jPanel_Customer.setLayout(jPanel_CustomerLayout);
        jPanel_CustomerLayout.setHorizontalGroup(
            jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CustomerLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_addNewCus1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_CustomerLayout.createSequentialGroup()
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_deleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cancle1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_CustomerLayout.createSequentialGroup()
                        .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbl_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusNIC1)
                            .addComponent(jLbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusContactNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbl_cusProvinceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusPostalCodeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_CustomerLayout.createSequentialGroup()
                                    .addComponent(jLbl_cusCityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5))
                                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLbl_cusAddressValue, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusNICValue, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusIDValue, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusEmailValue, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusContactNumValue, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusTitleValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_CustomerLayout.setVerticalGroup(
            jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CustomerLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLbl_addNewCus1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusIDValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusTitleValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusNICValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusNIC1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusContactNumValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusContactNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusEmailValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusAddressValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLbl_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusCityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_CustomerLayout.createSequentialGroup()
                        .addComponent(jLbl_cusProvinceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbl_cusPostalCodeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_CustomerLayout.createSequentialGroup()
                        .addComponent(jLbl_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update)
                    .addComponent(btn_deleteCustomer)
                    .addComponent(btn_cancle1)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLbl_addNewCus.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLbl_addNewCus.setForeground(new java.awt.Color(51, 102, 255));
        jLbl_addNewCus.setText("NEW SALE");

        cmb_itemSearch.setEditable(true);
        cmb_itemSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmb_itemSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SEARCH BY CODE OR NAME" }));
        cmb_itemSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_itemSearchActionPerformed(evt);
            }
        });

        jLbl_cusNIC.setText("DATE :");

        jLabel1.setText("ORDER NUMBER: ");

        jLabel2.setText("Co0000");

        jLbl_unitPrice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_unitPrice.setText("UNIT PRICE : Rs.");

        txt_QTY.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_QTY.setText("1.0");
        txt_QTY.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_QTYFocusGained(evt);
            }
        });
        txt_QTY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_QTYActionPerformed(evt);
            }
        });

        jLbl_cusContactNum.setText("TIME");

        jLbl_item.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_item.setText("ITEM :");

        jLabel3.setText("CUSTOMER : ");

        jLbl_qty.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_qty.setText("QTY :");

        jLbl_unitPriceValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_unitPriceValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLbl_unitPriceValue.setText("0.0");

        jLabel13.setText("Press ENTER to add item");

        jLbl_totalValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_totalValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLbl_totalValue.setText("0.0");

        jLbl_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_total.setText("TOTAL : Rs.");

        jLabel10.setText("jLabel10");

        cmb_Customer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_Customer.setEnabled(false);

        jLabel4.setText("jLabel4");

        jLabel11.setText("jLabel11");

        btn_logout.setText("LOGOUT");

        jButton2.setText("SAVE (End)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btn_addCustomer.setText("CHOOSE CUSTOMER (F1)");
        btn_addCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addCustomerActionPerformed(evt);
            }
        });

        jButton5.setText("DISCOUNT (F4)");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("HOME (Home)");

        jButton1.setText("ADD NEW ITEM (F3) ");

        btn_cancle.setText("CANCEL (F6)");
        btn_cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancleActionPerformed(evt);
            }
        });

        jButton4.setText("ADD NEW CUSTOMER  (F2)");

        jButton3.setText("PRINT (F5)");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(1000);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        lbl_finlTotalValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_finlTotalValue.setForeground(new java.awt.Color(255, 0, 0));
        lbl_finlTotalValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_finlTotalValue.setText("0.0");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("TOTAL : Rs.");

        txt_discount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_discount.setText("0");
        txt_discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_discountActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("%");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("0.0");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField2.setText("0.0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("SUB TOTAL : Rs.");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("DISCOUNT :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(640, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_finlTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbl_finlTotalValue)
                    .addComponent(txt_discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(jLbl_addNewCus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cmb_itemSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_cusNIC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_unitPrice, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_QTY, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_cusContactNum, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_item, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_qty, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_unitPriceValue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_totalValue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_total, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cmb_Customer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_logout, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_addCustomer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_cancle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cmb_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jLbl_addNewCus, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btn_addCustomer)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton1)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLbl_item)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmb_itemSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(75, 75, 75)))
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jLbl_cusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13))
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jLbl_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txt_QTY, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLbl_unitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLbl_unitPriceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jLbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLbl_totalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jLbl_cusContactNum, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_cancle)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_logout)))))
                        .addGap(20, 20, 20))))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_logout)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton1)
                    .addComponent(jLbl_addNewCus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancle)
                    .addComponent(btn_addCustomer))
                .addGap(39, 39, 39)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbl_cusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusContactNum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cmb_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11))
                .addGap(25, 25, 25)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbl_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_QTY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_unitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_unitPriceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_totalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_itemSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_item, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jLayeredPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_discountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_discountActionPerformed

    private void btn_cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancleActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancleActionPerformed

    private void btn_addCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addCustomerActionPerformed

        try {
            if (!nameCheck) {
                JOptionPane.showMessageDialog(null, "Please enter Name");
            } else if (!NICCheck) {
                JOptionPane.showMessageDialog(null, "Please enter valid NIC");
            } else if (!contactCheck) {
                JOptionPane.showMessageDialog(null, "Please enter valid contact number");
            } else if (!emailCheck) {
                JOptionPane.showMessageDialog(null, "Please enter email");
            } else {
                String id = AutoGenerateID.getNextID("CUSTOMER", "customer_id", "C", 5);
                String birthDay = "0000-00-00";
//                if (!txt_cusNIC.getText().isEmpty()) {
//                    birthDay = GetBirthDay.getBirthDay(txt_cusNIC.getText());
//                }

                CustomerModel NewCustomer = new CustomerModel( //                        id,
                        //                        cmb_cusTitle.getSelectedItem().toString(),
                        //                        txt_cusName.getText(),
                        //                        txt_cusNIC.getText(),
                        //                        birthDay,
                        //                        txt_cusContactNumber.getText(),
                        //                        txt_cusEmailAddress.getText(),
                        //                        txt_cusAddress.getText(),
                        //                        txt_cusCity.getText(),
                        //                        txt_cusProvince.getText(),
                        //                        txt_cusPostalCode.getText()
                        );
                CustomerUtilities.addCustomer(NewCustomer);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerOrderold.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddCustomerOrderold.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddCustomerOrderold.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_addCustomerActionPerformed

    private void txt_QTYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_QTYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_QTYActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_QTYFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_QTYFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_QTYFocusGained

    private void cmb_itemSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_itemSearchActionPerformed
        // TODO add your handling code here:
        String item = (String) cmb_itemSearch.getSelectedItem();
        JTextField txtItem
                = (JTextField) cmb_itemSearch.getEditor().getEditorComponent();
        txtItem.setText(item);
    }//GEN-LAST:event_cmb_itemSearchActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btn_deleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteCustomerActionPerformed

        int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            String CusID = jLbl_cusIDValue.getText();
            try {
                CustomerUtilities.deleteCustomer(CusID);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btn_deleteCustomerActionPerformed

    private void btn_cancle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancle1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_cancle1ActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        String cusID = jLbl_cusIDValue.getText();
        UpdateCustomer update = new UpdateCustomer(cusID);
        update.setVisible(true);
        update.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btn_updateActionPerformed

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
            java.util.logging.Logger.getLogger(AddCustomerOrderold.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCustomerOrderold.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCustomerOrderold.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCustomerOrderold.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCustomerOrderold().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addCustomer;
    private javax.swing.JButton btn_cancle;
    private javax.swing.JButton btn_cancle1;
    private javax.swing.JButton btn_deleteCustomer;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmb_Customer;
    private javax.swing.JComboBox<String> cmb_itemSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel jLbl_addNewCus;
    private javax.swing.JLabel jLbl_addNewCus1;
    private javax.swing.JLabel jLbl_cusAddress;
    private javax.swing.JLabel jLbl_cusAddressValue;
    private javax.swing.JLabel jLbl_cusCity;
    private javax.swing.JLabel jLbl_cusCityValue;
    private javax.swing.JLabel jLbl_cusContactNum;
    private javax.swing.JLabel jLbl_cusContactNum1;
    private javax.swing.JLabel jLbl_cusContactNumValue;
    private javax.swing.JLabel jLbl_cusEmail;
    private javax.swing.JLabel jLbl_cusEmailValue;
    private javax.swing.JLabel jLbl_cusID;
    private javax.swing.JLabel jLbl_cusIDValue;
    private javax.swing.JLabel jLbl_cusNIC;
    private javax.swing.JLabel jLbl_cusNIC1;
    private javax.swing.JLabel jLbl_cusNICValue;
    private javax.swing.JLabel jLbl_cusName;
    private javax.swing.JLabel jLbl_cusNameValue;
    private javax.swing.JLabel jLbl_cusPostalCode;
    private javax.swing.JLabel jLbl_cusPostalCodeValue;
    private javax.swing.JLabel jLbl_cusProvince;
    private javax.swing.JLabel jLbl_cusProvinceValue;
    private javax.swing.JLabel jLbl_cusTitle;
    private javax.swing.JLabel jLbl_cusTitleValue;
    private javax.swing.JLabel jLbl_item;
    private javax.swing.JLabel jLbl_qty;
    private javax.swing.JLabel jLbl_total;
    private javax.swing.JLabel jLbl_totalValue;
    private javax.swing.JLabel jLbl_unit;
    private javax.swing.JLabel jLbl_unitPrice;
    private javax.swing.JLabel jLbl_unitPriceValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Customer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbl_finlTotalValue;
    private javax.swing.JTextField txt_QTY;
    private javax.swing.JTextField txt_discount;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        txt_item = (JTextField) cmb_itemSearch.getEditor().getEditorComponent();

        if (e.getKeyCode() == KeyEvent.VK_END) {
            txt_item.setEnabled(false);
            SaveOrder save = new SaveOrder();
            save.setVisible(true);
            save.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//                    txt_discount.requestFocus();
            System.out.println("enter cmb Discount dialog");
        }
    }
}

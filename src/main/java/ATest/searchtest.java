/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ATest;

import View.Customer.*;
import controller.ComponentUtilities.ComboSearch;
import controller.ComponentUtilities.ComboSearch2;
import controller.ComponentUtilities.GetBirthDay;
import controller.ComponentUtilities.QuerryGenerate;
import controller.ComponentUtilities.SearchableCombo;
import controller.ComponentUtilities.TableController;
import controller.ComponentUtilities.ValidateValues;
import controller.dataUtilities.CustomerUtilities;
import java.awt.Color;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.CustomerModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author nadee
 */
public class searchtest extends javax.swing.JFrame {

    /**
     * Creates new form SerchCustomer
     */
    // Non compulsory fields initialize true
    boolean nameCheck = false;
    boolean NICCheck = true;
    boolean contactCheck = true;
    boolean emailCheck = true;

    JTextField txtcusName;
    JTextField txtcusTitle;
    JTextField txtcusNIC;
    JTextField txtcusContact;
    JTextField txtcusEmail;
    JTextField txtcusAddress;
    JTextField txtcusPostalCode;
    JTextField txtcusProvince;
    JTextField txtcusCity;
    
    ArrayList<Object> tableList;// = CustomerUtilities.getAllCustomers();
    
      private Object[][] cells ;
private String[] columnNames;
JTable mytable = new JTable(cells ,columnNames);

    public searchtest() {
        initComponents();
        JTable mytable = new JTable(cells ,columnNames);
        
      
        loadTable();
        loadCombo();

    }

    private void loadTable() {
        new Thread() {
            public void run() {

                try {
                    TableController.addDataToTable(mytable, "Select * FROM CUSTOMER");
                } catch (Exception e) {
                }
            }
        }.start();
        mytable.setAutoCreateRowSorter(true);
    }

    private void loadCombo() {
        try {
            //load data from database
            ArrayList<CustomerModel> arCustomer = CustomerUtilities.getAllCustomers();
            for (CustomerModel c : arCustomer) {
                cmb_cusTitle.addItem(c.getCustomer_title());
                cmb_cusName.addItem(c.getCustomer_name());
                cmb_cusNIC.addItem(c.getCustomer_nic());
                cmb_cusContact.addItem(c.getCustomer_contact());
                cmb_cusEmail.addItem(c.getCustomer_email());
                cmb_cusAddress.addItem(c.getCustomer_address());
                cmb_cusPostalCode.addItem(c.getCustomer_postalcode());
                cmb_cusProvince.addItem(c.getCustomer_province());
                cmb_cusCity.addItem(c.getCustomer_city());

                SearchableCombo.orderComboItems(
                        cmb_cusTitle,
                        cmb_cusName,
                        cmb_cusNIC,
                        cmb_cusContact,
                        cmb_cusEmail,
                        cmb_cusAddress,
                        cmb_cusPostalCode,
                        cmb_cusProvince,
                        cmb_cusProvince);
            }
            SearchableCombo.setSearchableCombo(
                    true,
                    "<<<<No Results>>>>",
                    cmb_cusTitle,
                    cmb_cusName,
                    cmb_cusNIC,
                    cmb_cusContact,
                    cmb_cusEmail,
                    cmb_cusAddress,
                    cmb_cusPostalCode,
                    cmb_cusProvince,
                    cmb_cusCity);
            SearchableCombo.selectedComboItems(
                    cmb_cusName,
                    cmb_cusNIC,
                    cmb_cusContact,
                    cmb_cusEmail,
                    cmb_cusAddress,
                    cmb_cusPostalCode,
                    cmb_cusProvince,
                    cmb_cusCity);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(searchtest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(searchtest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(searchtest.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmb_cusTitle.requestFocus();
    }
//    private void setCombeKey(){
//
//        try {
//            txtcusName = (JTextField) cmb_cusName.getEditor().getEditorComponent();
////            txtcusTitle = (JTextField) cmbCashierGrnItemDetailItemName.getEditor().getEditorComponent();
//
//            txtcusName.addKeyListener(new KeyAdapter() {
//
//                public void keyReleased(KeyEvent evt) {
//                    String s = txtcusName.getText();
////                    s = ValidateValues.filterWordCharacter(s);
//                    txtcusName.setText(s);
//
//                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
//                        cmb_cusNIC.requestFocus();
//                    }
//                }
//            });
//
//            txtcusTitle.addKeyListener(new KeyAdapter() {
//
//                public void keyReleased(KeyEvent evt) {
//                    String s = txtcusTitle.getText();
////                    s = ValidateValues.filterWordCharacter(s);
//                    txtcusTitle.setText(s);
//
//                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
//                        cmb_cusNIC.requestFocus();
//                    }
//                }
//            });
//        } catch (Exception e) {
//        }
//
//
//
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLbl_addNewCus = new javax.swing.JLabel();
        jLbl_cusID = new javax.swing.JLabel();
        jLbl_cusTitle = new javax.swing.JLabel();
        jLbl_cusName = new javax.swing.JLabel();
        jLbl_cusNIC = new javax.swing.JLabel();
        jLbl_cusContactNum = new javax.swing.JLabel();
        jLbl_cusEmail = new javax.swing.JLabel();
        jLbl_cusAddress = new javax.swing.JLabel();
        jLbl_cusCity = new javax.swing.JLabel();
        jLbl_cusProvince = new javax.swing.JLabel();
        jLbl_cusPostalCode = new javax.swing.JLabel();
        cmb_cusID = new javax.swing.JComboBox<>();
        cmb_cusTitle = new javax.swing.JComboBox<>();
        btn_searchCustomer = new javax.swing.JButton();
        btn_cancle = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        cmb_cusName = new javax.swing.JComboBox<>();
        cmb_cusNIC = new javax.swing.JComboBox<>();
        cmb_cusContact = new javax.swing.JComboBox<>();
        cmb_cusEmail = new javax.swing.JComboBox<>();
        cmb_cusAddress = new javax.swing.JComboBox<>();
        cmb_cusPostalCode = new javax.swing.JComboBox<>();
        cmb_cusProvince = new javax.swing.JComboBox<>();
        cmb_cusCity = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        btn_searchCustomer1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLbl_addNewCus.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLbl_addNewCus.setForeground(new java.awt.Color(51, 102, 255));
        jLbl_addNewCus.setText("SEARCH CUSTOMER");

        jLbl_cusID.setText("CUSTOMER ID:");

        jLbl_cusTitle.setText("TITLE :");

        jLbl_cusName.setText("NAME :");

        jLbl_cusNIC.setText("NIC :");

        jLbl_cusContactNum.setText("CONTACT NUMBER :");

        jLbl_cusEmail.setText("E- MAIL :");

        jLbl_cusAddress.setText("ADDRESS :");

        jLbl_cusCity.setText("CITY :");

        jLbl_cusProvince.setText("PROVINCE :");

        jLbl_cusPostalCode.setText("POSTAL CODE :");

        cmb_cusID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_cusIDActionPerformed(evt);
            }
        });

        cmb_cusTitle.setEditable(true);
        cmb_cusTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_cusTitleActionPerformed(evt);
            }
        });

        btn_searchCustomer.setText("SEARCH");
        btn_searchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchCustomerActionPerformed(evt);
            }
        });

        btn_cancle.setText("CLEAR");

        btn_logout.setText("LOGOUT");

        cmb_cusName.setEditable(true);
        cmb_cusName.setToolTipText("");
        cmb_cusName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmb_cusNameFocusGained(evt);
            }
        });
        cmb_cusName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_cusNameActionPerformed(evt);
            }
        });

        cmb_cusNIC.setEditable(true);
        cmb_cusNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_cusNICActionPerformed(evt);
            }
        });

        cmb_cusContact.setEditable(true);

        cmb_cusEmail.setEditable(true);
        cmb_cusEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_cusEmailActionPerformed(evt);
            }
        });

        cmb_cusAddress.setEditable(true);
        cmb_cusAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_cusAddressActionPerformed(evt);
            }
        });

        cmb_cusPostalCode.setEditable(true);

        cmb_cusProvince.setEditable(true);

        cmb_cusCity.setEditable(true);

        btn_searchCustomer1.setText("SEARCH ALL");
        btn_searchCustomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchCustomer1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLbl_addNewCus, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_logout)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_searchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_searchCustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(653, 653, 653))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLbl_cusNIC)
                                            .addComponent(jLbl_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cmb_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmb_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(cmb_cusNIC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLbl_cusContactNum, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmb_cusContact, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLbl_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(46, 46, 46)
                                            .addComponent(cmb_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLbl_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(5, 5, 5)
                                            .addComponent(cmb_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(cmb_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLbl_cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(cmb_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cmb_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(46, 46, 46)
                                            .addComponent(cmb_cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLbl_cusAddress, jLbl_cusCity, jLbl_cusPostalCode});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_addNewCus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLbl_cusAddress)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmb_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLbl_cusName)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jLbl_cusCity))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLbl_cusProvince)
                                            .addComponent(cmb_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(cmb_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(cmb_cusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLbl_cusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmb_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmb_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_searchCustomer)
                            .addComponent(btn_searchCustomer1)
                            .addComponent(btn_cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(13, 13, 13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_cusContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusContactNum))
                        .addGap(258, 258, 258))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmb_cusCity, cmb_cusProvince});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_cancle, btn_searchCustomer, btn_searchCustomer1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLbl_cusAddress, jLbl_cusCity, jLbl_cusPostalCode});

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_searchCustomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchCustomer1ActionPerformed
        loadTable();
    }//GEN-LAST:event_btn_searchCustomer1ActionPerformed

    private void cmb_cusAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_cusAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_cusAddressActionPerformed

    private void cmb_cusEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_cusEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_cusEmailActionPerformed

    private void cmb_cusNICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_cusNICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_cusNICActionPerformed

    private void cmb_cusNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_cusNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_cusNameActionPerformed

    private void cmb_cusNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_cusNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_cusNameFocusGained

    private void btn_searchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchCustomerActionPerformed

        //        try {
            //            if (!nameCheck) {
                //                JOptionPane.showMessageDialog(null, "Please enter Name");
                //            } else if (!NICCheck) {
                //                JOptionPane.showMessageDialog(null, "Please enter valid NIC");
                //            } else if (!contactCheck) {
                //                JOptionPane.showMessageDialog(null, "Please enter valid contact number");
                //            } else if (!emailCheck) {
                //                JOptionPane.showMessageDialog(null, "Please enter email");
                //            } else {
                //                String id = AutoGenerateID.getNextID("CUSTOMER", "customer_id", "C", 5);
                //                String birthDay = "0000-00-00";
                //                if (!txt_cusNIC.getText().isEmpty()) {
                    //                    birthDay = GetBirthDay.getBirthDay(txt_cusNIC.getText());
                    //                }
                //
                //                CustomerModel NewCustomer = new CustomerModel(
                    //                    id,
                    //                    cmb_cusTitle.getSelectedItem().toString(),
                    //                    txt_cusName.getText(),
                    //                    txt_cusNIC.getText(),
                    //                    birthDay,
                    //                    txt_cusContactNumber.getText(),
                    //                    txt_cusEmailAddress.getText(),
                    //                    txt_cusAddress.getText(),
                    //                    txt_cusCity.getText(),
                    //                    txt_cusProvince.getText(),
                    //                    txt_cusPostalCode.getText()
                    //                );
                //                CustomerUtilities.addCustomer(NewCustomer);
                txtcusName = (JTextField) cmb_cusName.getEditor().getEditorComponent();
                txtcusTitle = (JTextField) cmb_cusTitle.getEditor().getEditorComponent();
                txtcusNIC = (JTextField) cmb_cusNIC.getEditor().getEditorComponent();
                txtcusContact = (JTextField) cmb_cusContact.getEditor().getEditorComponent();
                txtcusEmail = (JTextField) cmb_cusEmail.getEditor().getEditorComponent();
                txtcusAddress = (JTextField) cmb_cusAddress.getEditor().getEditorComponent();
                txtcusCity = (JTextField) cmb_cusCity.getEditor().getEditorComponent();
                txtcusProvince = (JTextField) cmb_cusProvince.getEditor().getEditorComponent();
                txtcusPostalCode = (JTextField) cmb_cusPostalCode.getEditor().getEditorComponent();

                String[] allValues = {
                    txtcusTitle.getText(),
                    txtcusName.getText(),
                    txtcusNIC.getText(),
                    txtcusContact.getText(),
                    txtcusEmail.getText(),
                    txtcusAddress.getText(),
                    txtcusCity.getText(),
                    txtcusProvince.getText(),
                    txtcusPostalCode.getText()};

                String[] allColumns = {
                    "customer_title",
                    "customer_name",
                    "customer_nic",
                    "customer_contact",
                    "customer_email",
                    "customer_address",
                    "customer_city",
                    "customer_province",
                    "customer_postalcode"};

                ArrayList<String> columns = new ArrayList<String>(); //= {"customer_name"};
                ArrayList<String> values = new ArrayList<String>(); //= {txtcusName.getText()};
                ArrayList<Integer> removeColumn = new ArrayList<Integer>();

                //        columns.add("customer_name");
                //        values.add(txtcusName.getText());
                for (int i = 0; i < allValues.length; i++) {
                    if (!allValues[i].equalsIgnoreCase("")) {
                        columns.add(allColumns[i]);
                        values.add(allValues[i]);
                    }
                    //              removeColumn.add(i);
                }
                //        for(String s:allValues){
                    //            if(s.equalsIgnoreCase("")){
                        //                 ;
                        //           }
                    //        }

                String newQuerry = QuerryGenerate.Select_All_From_Where("CUSTOMER", columns, values);
                System.out.println(newQuerry);

                new Thread() {
                    public void run() {

                        try {
                            TableController.addDataToTable(mytable, newQuerry);
                        } catch (Exception e) {
                        }
                    }
                }.start();
                //            }
            //        } catch (ClassNotFoundException ex) {
            //            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
            //        } catch (IOException ex) {
            //            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
            //        } catch (Exception ex) {
            //            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
            //        }
    }//GEN-LAST:event_btn_searchCustomerActionPerformed

    private void cmb_cusTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_cusTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_cusTitleActionPerformed

    private void cmb_cusIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_cusIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_cusIDActionPerformed

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
            java.util.logging.Logger.getLogger(searchtest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(searchtest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(searchtest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(searchtest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new searchtest().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancle;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_searchCustomer;
    private javax.swing.JButton btn_searchCustomer1;
    private javax.swing.JComboBox<String> cmb_cusAddress;
    private javax.swing.JComboBox<String> cmb_cusCity;
    private javax.swing.JComboBox<String> cmb_cusContact;
    private javax.swing.JComboBox<String> cmb_cusEmail;
    private javax.swing.JComboBox<String> cmb_cusID;
    private javax.swing.JComboBox<String> cmb_cusNIC;
    private javax.swing.JComboBox<String> cmb_cusName;
    private javax.swing.JComboBox<String> cmb_cusPostalCode;
    private javax.swing.JComboBox<String> cmb_cusProvince;
    private javax.swing.JComboBox<String> cmb_cusTitle;
    private javax.swing.JLabel jLbl_addNewCus;
    private javax.swing.JLabel jLbl_cusAddress;
    private javax.swing.JLabel jLbl_cusCity;
    private javax.swing.JLabel jLbl_cusContactNum;
    private javax.swing.JLabel jLbl_cusEmail;
    private javax.swing.JLabel jLbl_cusID;
    private javax.swing.JLabel jLbl_cusNIC;
    private javax.swing.JLabel jLbl_cusName;
    private javax.swing.JLabel jLbl_cusPostalCode;
    private javax.swing.JLabel jLbl_cusProvince;
    private javax.swing.JLabel jLbl_cusTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

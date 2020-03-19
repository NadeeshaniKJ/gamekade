/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.CustomerOrder;

import View.Customer.*;
import View.Home.ShopHome;
import View.Home.ShopLogin;
import View.Item.AddItem;
import controller.ComponentUtilities.AutoGenerateID;
import controller.ComponentUtilities.GetBirthDay;
import controller.ComponentUtilities.SearchableCombo;
import controller.ComponentUtilities.ValidateValues;
import controller.dataUtilities.CustomerOrderItemUtilities;
import controller.dataUtilities.CustomerOrderUtilities;
import controller.dataUtilities.CustomerUtilities;
import controller.dataUtilities.ItemUtilities;
import dbConnection.DBCon;
import dbConnection.DBHandle;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import model.CustomerModel;
import model.CustomerOrderItemModel;
import model.CustomerOrderModel;
import model.ItemModel;

/**
 *
 * @author nadee
 */
public class AddCustomerOrder extends javax.swing.JFrame {

    /**
     * Creates new form AddCustomerOrder
     */
    // Non compulsory fields initialize true
    boolean nameCheck = false;
    boolean NICCheck = true;
    boolean contactCheck = true;
    boolean emailCheck = true;

    DefaultTableModel tableModel;
    JTextField txt_item;
    JTextField txt_customer;
    String customer_id = "C00048";
    String discount = "0.0";
    String datetime = "";

    ArrayList<CustomerOrderItemModel> items
            = new ArrayList<CustomerOrderItemModel>();

    String newid = AutoGenerateID.getNextID("CUSTOMER_ORDER_ITEM", "customer_order_item_id", "CI", 4);
    int id = Integer.parseInt(newid.substring(2)) - 1;

    String itemid;

    public AddCustomerOrder() {
        initComponents();
        createTable();
        setKeyListener();
        loadComboItemSearch();
        setExtendedState(AddCustomerOrder.MAXIMIZED_BOTH);

        String orderNum = AutoGenerateID.getNextID("CUSTOMER_ORDER", "customer_order_number ", "Co", 4);
        jLbl_orderNum.setText(orderNum);

        DateFormat df = new SimpleDateFormat("yyyy_MM_dd");
        Date dateobj = new Date();
        jLbl_Date.setText(df.format(dateobj));

        DateFormat df_time = new SimpleDateFormat("HH.mm.ss");
        Date timeobj = new Date();
        jLbl_time.setText(df_time.format(timeobj));

        datetime = df.format(dateobj) + " " + df_time.format(timeobj);
        System.out.println("datetime " + datetime);
        System.out.println("id sub string " + newid.substring(2));

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
        txt_customer = (JTextField) cmb_Customer.getEditor().getEditorComponent();

        txt_item.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                try {
                    //change unit price while selecting item // possible to create a method and call in constructor??
                    ArrayList<ItemModel> arItem = ItemUtilities.showAllItems();
                    for (ItemModel c : arItem) {
                        if (c.getItem_id().equals(txt_item.getText())) {
                            jLbl_unitPriceValue.setText(c.getItem_unit_price());
                            itemid = c.getItem_id();
                        } else if (c.getItem_name().equals(txt_item.getText())) {
                            jLbl_unitPriceValue.setText(c.getItem_unit_price());
                            itemid = c.getItem_id();
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
                }

                // item selecting combo >>  change unit price and total price according to selected item
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txt_item.getText().equals("")) {
                        jLbl_unitPriceValue.setText("0.0");
                        jLbl_totalValue.setText("0.0");
                    } else {
                        txt_QTY.requestFocus();
                        txt_QTY.selectAll();
                    }
                }

                // item selecting combo >> Select Customer // Jdialog with JPanel
                if (evt.getKeyCode() == KeyEvent.VK_F1) {
                    loadComboCustomerSearch();

                    Object[] cusOptions = {"OK", "CANCEL"};

                    int result = JOptionPane.showOptionDialog(null, jPanel_Customer,
                            "Please Select Customer",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            cusOptions,
                            null);

                    if (result == JOptionPane.OK_OPTION) {
                        customer_id = jLbl_cusIDValue.getText();
                        txt_Customer.setText(jLbl_cusNameValue.getText());
                        txt_item.requestFocusInWindow();
                        System.out.println("customer id VKF1" + customer_id);
                    }
                }

                // item selecting combo >> add new customer
                if (evt.getKeyCode() == KeyEvent.VK_F2) {
                    AddCustomer newCustomer = new AddCustomer();
                    newCustomer.setVisible(true);
                }

                // item selecting combo >> add new item
                if (evt.getKeyCode() == KeyEvent.VK_F3) {
                    AddItem newItem = new AddItem();
                    newItem.setVisible(true);
                }

                // item selecting combo >> add discount in main addOrder frame
                if (evt.getKeyCode() == KeyEvent.VK_F4) {
                    txt_Discount.requestFocus();
                }

                // item selecting combo >> add discount in main addOrder frame
                if (evt.getKeyCode() == KeyEvent.VK_END) {
                    save();
                }

            }
        });

        txt_QTY.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {

                Double d_Qty = Double.parseDouble(txt_QTY.getText());
                Double d_UnitPrice = Double.parseDouble(jLbl_unitPriceValue.getText());
                Double d_Total = d_Qty * d_UnitPrice;
                Double d_SubTotal = Double.parseDouble(jLbl_SubTotalValue.getText());;
                Double d_FinalTotal = Double.parseDouble(jLbl_finlTotalValue.getText());
                Double d_discount = Double.parseDouble(txt_Discount.getText());

                jLbl_totalValue.setText(d_Total.toString());

                System.out.println(d_Total);

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    String description = (String) cmb_itemSearch.getSelectedItem();
                    String Qty = txt_QTY.getText() + " x " + jLbl_unitPriceValue.getText();
                    String total = jLbl_totalValue.getText();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    id = id + 1;

                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMinimumIntegerDigits(4);
                    nf.setGroupingUsed(false);
                    String nextID = "CI" + nf.format(id);

                    CustomerOrderItemModel orderItem = new CustomerOrderItemModel();
                    orderItem.setCustomer_order_item_id(nextID);
                    orderItem.setCustomer_order_item_qty(txt_QTY.getText());
                    orderItem.setCustomer_order_item_unit_price(jLbl_unitPriceValue.getText());
                    orderItem.setItem_id(itemid);
                    orderItem.setCustomer_order_number(jLbl_orderNum.getText());

                    System.out.println("id: " + nextID);
                    System.out.println("Qty: " + txt_QTY.getText());
                    System.out.println("unit price: " + jLbl_unitPriceValue.getText());
                    System.out.println("item id: " + itemid);
                    System.out.println(" order num: " + jLbl_orderNum.getText());

                    items.add(orderItem);
                    tableModel.insertRow(0, new Object[]{description, Qty, total});

                    d_SubTotal = d_SubTotal + d_Total;
                    d_FinalTotal = d_SubTotal - d_discount;
                    jLbl_finlTotalValue.setText(d_FinalTotal.toString());
                    jLbl_SubTotalValue.setText(d_SubTotal.toString());
                    cmb_itemSearch.requestFocus();

                }
                if (evt.getKeyCode() == KeyEvent.VK_END) {
                    save();
                }
            }
        });

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

                                jLbl_cusIDValue.setText(ID);
                                jLbl_cusTitleValue.setText(Title);
                                jLbl_cusNameValue.setText(Name);

                            }
                        });
                    } catch (Exception ex) {
                        Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        txt_Discount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {

                Double subTotal = Double.parseDouble(jLbl_SubTotalValue.getText());
                Double discount = Double.parseDouble(txt_Discount.getText());
                System.out.println(" empty string :" + discount);
                Double finalTotal = subTotal - discount;

                if (subTotal != 0) {
                    Double discountPercent = (discount * 100) / (subTotal);
                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    txt_discountPercent.setText(numberFormat.format(discountPercent));
                    jLbl_finlTotalValue.setText(finalTotal.toString());
                }
                if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
                    txt_item.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
                    txt_discountPercent.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_END) {
                    save();
                }
            }
        });
        txt_discountPercent.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
                    txt_Discount.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_END) {
                    save();
                }
            }
        });
    }

    private void save() {
        jLbl_finalSubTotal.setText(jLbl_SubTotalValue.getText());
        txt_finalDiscount.setText(txt_Discount.getText());
        txt_finalDiscountPercent.setText(txt_discountPercent.getText());
        jLbl_Total.setText(jLbl_finlTotalValue.getText());

        Double balance = Double.parseDouble(txt_Payment.getText()) - Double.parseDouble(jLbl_Total.getText());
        txt_balance.setText(balance.toString());

        Object[] options = {"SAVE", "PRINT", "CANCEL"};

        int result = JOptionPane.showOptionDialog(null, jPanel_Save,
                "Please Check Order Details",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);

        if (result == JOptionPane.YES_OPTION) {
            //send to database
            discount = txt_finalDiscount.getText();
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            CustomerOrderModel newOrder = new CustomerOrderModel();

            newOrder.setCustomer_order_number(jLbl_orderNum.getText());
            newOrder.setCustomer_id(customer_id);
            newOrder.setCustomer_order_datetime(datetime);
            newOrder.setCustomer_order_sub_total(jLbl_finalSubTotal.getText());
            newOrder.setCustomer_order_discount(discount);
            newOrder.setCustomer_order_net_total(jLbl_Total.getText());

            System.out.println("order number: " + jLbl_orderNum.getText());
            System.out.println("customer id " + customer_id);
            System.out.println("sub total " + jLbl_finalSubTotal.getText());
            System.out.println(" discount " + discount);
            System.out.println("net total " + jLbl_Total.getText());

            try {
                CustomerOrderUtilities.addCustomerOrder(newOrder);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            CustomerOrderItemModel orderItem = new CustomerOrderItemModel();

//            orderItem.setCustomer_order_item_id(customer_id);
//            orderItem.setCustomer_order_item_qty(customer_id);
//            orderItem.setCustomer_order_item_unit_price(customer_id);
//            orderItem.setItem_id(customer_id);
//            orderItem.setCustomer_order_number(customer_id);
            //create item model array , each time table entry added, 
            //add element to array, then add each item of array to database
            for (CustomerOrderItemModel item : items) {
                try {
                    CustomerOrderItemUtilities.addCustomerOrderItem(item);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (result == JOptionPane.NO_OPTION) {
            //send to database
            //print

        }
        if (result == JOptionPane.CANCEL_OPTION) {

        }

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

            SearchableCombo.setSearchableCombo(true, "<<<<No Results>>>>", cmb_Customer);
            SearchableCombo.selectedComboItems(cmb_Customer);

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
        jPanel_Discount = new javax.swing.JPanel();
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
        cmb_Customer = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jPanel_Save = new javax.swing.JPanel();
        jLbl_addNewCus2 = new javax.swing.JLabel();
        jLbl_cusID1 = new javax.swing.JLabel();
        jLbl_cusName1 = new javax.swing.JLabel();
        jLbl_cusNIC2 = new javax.swing.JLabel();
        jLbl_cusContactNum2 = new javax.swing.JLabel();
        jLbl_cusEmail1 = new javax.swing.JLabel();
        jLbl_cusTitle1 = new javax.swing.JLabel();
        jLbl_finalSubTotal = new javax.swing.JLabel();
        jLbl_Total = new javax.swing.JLabel();
        cmb_PaymentMode = new javax.swing.JComboBox<>();
        txt_Payment = new javax.swing.JTextField();
        txt_finalDiscount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_finalDiscountPercent = new javax.swing.JTextField();
        txt_balance = new javax.swing.JTextField();
        jLbl_cusEmail2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_notes = new javax.swing.JTextArea();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLbl_addNewCus = new javax.swing.JLabel();
        cmb_itemSearch = new javax.swing.JComboBox<>();
        jLbl_cusNIC = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLbl_orderNum = new javax.swing.JLabel();
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
        jLbl_Date = new javax.swing.JLabel();
        jLbl_time = new javax.swing.JLabel();
        btn_logout = new javax.swing.JButton();
        btn_Save = new javax.swing.JButton();
        btn_addCustomer = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btn_Home = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btn_cancle = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLbl_finlTotalValue = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_discountPercent = new javax.swing.JTextField();
        jLbl_Percent = new javax.swing.JLabel();
        jLbl_SubTotalValue = new javax.swing.JLabel();
        txt_Discount = new javax.swing.JTextField();
        jLbl_SubTotal = new javax.swing.JLabel();
        jLbl_Discount = new javax.swing.JLabel();
        txt_Customer = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLbl_unit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_unit.setText("kg");

        javax.swing.GroupLayout jPanel_DiscountLayout = new javax.swing.GroupLayout(jPanel_Discount);
        jPanel_Discount.setLayout(jPanel_DiscountLayout);
        jPanel_DiscountLayout.setHorizontalGroup(
            jPanel_DiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel_DiscountLayout.setVerticalGroup(
            jPanel_DiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

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

        cmb_Customer.setEditable(true);
        cmb_Customer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_Customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_CustomerActionPerformed(evt);
            }
        });

        jLabel12.setText("SELECT CUSTOMER :");

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
                            .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbl_cusPostalCodeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLbl_cusAddressValue, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusNICValue, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusIDValue, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusEmailValue, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusContactNumValue, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusTitleValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmb_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel_CustomerLayout.createSequentialGroup()
                                    .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLbl_cusProvinceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLbl_cusCityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(305, 305, 305))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_CustomerLayout.setVerticalGroup(
            jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CustomerLayout.createSequentialGroup()
                .addComponent(jLbl_addNewCus1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
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
                .addGap(25, 25, 25)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_CustomerLayout.createSequentialGroup()
                        .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLbl_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusCityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLbl_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_CustomerLayout.createSequentialGroup()
                        .addComponent(jLbl_cusProvinceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbl_cusPostalCodeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel_CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update)
                    .addComponent(btn_deleteCustomer)
                    .addComponent(btn_cancle1)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        jLbl_addNewCus2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLbl_addNewCus2.setForeground(new java.awt.Color(51, 102, 255));
        jLbl_addNewCus2.setText("ORDER DETAILS");

        jLbl_cusID1.setText("Sub Total :");

        jLbl_cusName1.setText("Total :");

        jLbl_cusNIC2.setText("Payment Mode:");

        jLbl_cusContactNum2.setText("Payment :");

        jLbl_cusEmail1.setText("Balance :");

        jLbl_cusTitle1.setText("Discount :");

        jLbl_finalSubTotal.setText("0.0");

        jLbl_Total.setText("0.0");

        cmb_PaymentMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Credit Card", "Debit Card", "Cheque" }));
        cmb_PaymentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_PaymentModeActionPerformed(evt);
            }
        });

        txt_Payment.setText("0.0");

        txt_finalDiscount.setText("0.0");
        txt_finalDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_finalDiscountActionPerformed(evt);
            }
        });

        jLabel5.setText("%");

        txt_finalDiscountPercent.setText("0.0");

        txt_balance.setText("0.0");

        jLbl_cusEmail2.setText("Notes :");

        txt_notes.setColumns(20);
        txt_notes.setRows(5);
        jScrollPane3.setViewportView(txt_notes);

        javax.swing.GroupLayout jPanel_SaveLayout = new javax.swing.GroupLayout(jPanel_Save);
        jPanel_Save.setLayout(jPanel_SaveLayout);
        jPanel_SaveLayout.setHorizontalGroup(
            jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_addNewCus2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_SaveLayout.createSequentialGroup()
                        .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLbl_cusEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLbl_cusName1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLbl_cusID1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLbl_cusContactNum2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(jLbl_cusTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLbl_cusNIC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLbl_cusEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmb_PaymentMode, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_SaveLayout.createSequentialGroup()
                                .addComponent(txt_finalDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txt_finalDiscountPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLbl_finalSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLbl_Total, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_Payment)
                            .addComponent(txt_balance)
                            .addComponent(jScrollPane3))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel_SaveLayout.setVerticalGroup(
            jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLbl_addNewCus2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbl_cusID1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_finalSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLbl_cusTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_finalDiscount)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_finalDiscountPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusName1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmb_PaymentMode)
                    .addComponent(jLbl_cusNIC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLbl_cusContactNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_SaveLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txt_Payment)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbl_cusEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_balance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_SaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SaveLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLbl_cusEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_SaveLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLbl_addNewCus.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
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

        jLbl_cusNIC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_cusNIC.setText("DATE :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ORDER NUMBER: ");

        jLbl_orderNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_orderNum.setText("Co0000");

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

        jLbl_cusContactNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_cusContactNum.setText("TIME :");

        jLbl_item.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_item.setText("ITEM :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jLbl_Date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_Date.setText("jLabel4");

        jLbl_time.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_time.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLbl_time.setText("jLabel11");

        btn_logout.setText("LOGOUT");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        btn_Save.setText("SAVE (End)");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        btn_addCustomer.setText("SELECT CUSTOMER (F1)");
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

        btn_Home.setText("HOME (Home)");
        btn_Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HomeActionPerformed(evt);
            }
        });

        jButton1.setText("ADD NEW ITEM (F3) ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_cancle.setText("CANCEL (F6)");
        btn_cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancleActionPerformed(evt);
            }
        });

        jButton4.setText("ADD NEW CUSTOMER  (F2)");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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

        jLbl_finlTotalValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_finlTotalValue.setForeground(new java.awt.Color(255, 0, 0));
        jLbl_finlTotalValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLbl_finlTotalValue.setText("0.0");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("TOTAL : Rs.");

        txt_discountPercent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_discountPercent.setText("0");
        txt_discountPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_discountPercentActionPerformed(evt);
            }
        });

        jLbl_Percent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_Percent.setText("%");

        jLbl_SubTotalValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_SubTotalValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLbl_SubTotalValue.setText("0.0");

        txt_Discount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_Discount.setText("0.0");

        jLbl_SubTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_SubTotal.setText("SUB TOTAL : Rs.");

        jLbl_Discount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLbl_Discount.setText("DISCOUNT :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(640, Short.MAX_VALUE)
                .addComponent(jLbl_SubTotal)
                .addGap(18, 18, 18)
                .addComponent(jLbl_SubTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jLbl_Discount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbl_Percent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_discountPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLbl_finlTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLbl_finlTotalValue)
                    .addComponent(txt_discountPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_Percent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_SubTotalValue)
                    .addComponent(jLbl_SubTotal)
                    .addComponent(jLbl_Discount)
                    .addComponent(txt_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_Customer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_Customer.setText("Not Selected");

        jLayeredPane1.setLayer(jLbl_addNewCus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cmb_itemSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_cusNIC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_orderNum, javax.swing.JLayeredPane.DEFAULT_LAYER);
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
        jLayeredPane1.setLayer(jLbl_Date, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLbl_time, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_logout, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_Save, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_addCustomer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_Home, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_cancle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_Customer, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLbl_orderNum, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(jLbl_addNewCus, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_addCustomer)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLbl_item)
                                .addGap(18, 18, 18)
                                .addComponent(cmb_itemSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Save)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cancle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Home)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_logout))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLbl_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_QTY, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLbl_unitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLbl_unitPriceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLbl_totalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLbl_cusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLbl_cusContactNum)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLbl_time, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel13)))))
                .addGap(20, 20, 20))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_logout)
                    .addComponent(btn_Home)
                    .addComponent(btn_Save)
                    .addComponent(jButton3)
                    .addComponent(jLbl_addNewCus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancle)
                    .addComponent(jButton5)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(btn_addCustomer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLbl_orderNum, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txt_Customer)
                    .addComponent(jLbl_time)
                    .addComponent(jLbl_cusContactNum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_Date)
                    .addComponent(jLbl_cusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbl_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_QTY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_unitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_unitPriceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_totalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_itemSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_item, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jLayeredPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void cmb_PaymentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_PaymentModeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_PaymentModeActionPerformed

    private void txt_finalDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_finalDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_finalDiscountActionPerformed

    private void txt_discountPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_discountPercentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_discountPercentActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancleActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
            Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddCustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_addCustomerActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed


    }//GEN-LAST:event_btn_SaveActionPerformed

    private void txt_QTYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_QTYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_QTYActionPerformed

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

    private void btn_HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HomeActionPerformed
        ShopHome home = new ShopHome();
        home.setVisible(true);
    }//GEN-LAST:event_btn_HomeActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        ShopLogin login = new ShopLogin();
        login.setVisible(true);
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void cmb_CustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_CustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_CustomerActionPerformed

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
            java.util.logging.Logger.getLogger(AddCustomerOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCustomerOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCustomerOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCustomerOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new AddCustomerOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Home;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_addCustomer;
    private javax.swing.JButton btn_cancle;
    private javax.swing.JButton btn_cancle1;
    private javax.swing.JButton btn_deleteCustomer;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmb_Customer;
    private javax.swing.JComboBox<String> cmb_PaymentMode;
    private javax.swing.JComboBox<String> cmb_itemSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel jLbl_Date;
    private javax.swing.JLabel jLbl_Discount;
    private javax.swing.JLabel jLbl_Percent;
    private javax.swing.JLabel jLbl_SubTotal;
    private javax.swing.JLabel jLbl_SubTotalValue;
    private javax.swing.JLabel jLbl_Total;
    private javax.swing.JLabel jLbl_addNewCus;
    private javax.swing.JLabel jLbl_addNewCus1;
    private javax.swing.JLabel jLbl_addNewCus2;
    private javax.swing.JLabel jLbl_cusAddress;
    private javax.swing.JLabel jLbl_cusAddressValue;
    private javax.swing.JLabel jLbl_cusCity;
    private javax.swing.JLabel jLbl_cusCityValue;
    private javax.swing.JLabel jLbl_cusContactNum;
    private javax.swing.JLabel jLbl_cusContactNum1;
    private javax.swing.JLabel jLbl_cusContactNum2;
    private javax.swing.JLabel jLbl_cusContactNumValue;
    private javax.swing.JLabel jLbl_cusEmail;
    private javax.swing.JLabel jLbl_cusEmail1;
    private javax.swing.JLabel jLbl_cusEmail2;
    private javax.swing.JLabel jLbl_cusEmailValue;
    private javax.swing.JLabel jLbl_cusID;
    private javax.swing.JLabel jLbl_cusID1;
    private javax.swing.JLabel jLbl_cusIDValue;
    private javax.swing.JLabel jLbl_cusNIC;
    private javax.swing.JLabel jLbl_cusNIC1;
    private javax.swing.JLabel jLbl_cusNIC2;
    private javax.swing.JLabel jLbl_cusNICValue;
    private javax.swing.JLabel jLbl_cusName;
    private javax.swing.JLabel jLbl_cusName1;
    private javax.swing.JLabel jLbl_cusNameValue;
    private javax.swing.JLabel jLbl_cusPostalCode;
    private javax.swing.JLabel jLbl_cusPostalCodeValue;
    private javax.swing.JLabel jLbl_cusProvince;
    private javax.swing.JLabel jLbl_cusProvinceValue;
    private javax.swing.JLabel jLbl_cusTitle;
    private javax.swing.JLabel jLbl_cusTitle1;
    private javax.swing.JLabel jLbl_cusTitleValue;
    private javax.swing.JLabel jLbl_finalSubTotal;
    private javax.swing.JLabel jLbl_finlTotalValue;
    private javax.swing.JLabel jLbl_item;
    private javax.swing.JLabel jLbl_orderNum;
    private javax.swing.JLabel jLbl_qty;
    private javax.swing.JLabel jLbl_time;
    private javax.swing.JLabel jLbl_total;
    private javax.swing.JLabel jLbl_totalValue;
    private javax.swing.JLabel jLbl_unit;
    private javax.swing.JLabel jLbl_unitPrice;
    private javax.swing.JLabel jLbl_unitPriceValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Customer;
    private javax.swing.JPanel jPanel_Discount;
    private javax.swing.JPanel jPanel_Save;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel txt_Customer;
    private javax.swing.JTextField txt_Discount;
    private javax.swing.JTextField txt_Payment;
    private javax.swing.JTextField txt_QTY;
    private javax.swing.JTextField txt_balance;
    private javax.swing.JTextField txt_discountPercent;
    private javax.swing.JTextField txt_finalDiscount;
    private javax.swing.JTextField txt_finalDiscountPercent;
    private javax.swing.JTextArea txt_notes;
    // End of variables declaration//GEN-END:variables

}

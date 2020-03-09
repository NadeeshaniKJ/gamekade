/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Customer;

import controller.ComponentUtilities.ComboSearch;
import controller.ComponentUtilities.ComboSearch2;
import controller.ComponentUtilities.GetBirthDay;
import controller.ComponentUtilities.QuerryGenerate;
import controller.ComponentUtilities.SearchableCombo;
import controller.ComponentUtilities.TableController;
import controller.ComponentUtilities.ValidateValues;
import controller.dataUtilities.CustomerUtilities;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.CustomerModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author nadee
 */
public class SearchCustomer extends javax.swing.JFrame {

    /**
     * Creates new form SearchCustomer
     */
    ArrayList<CustomerModel> arCustomer;
    private JTable customerTable;
    private TableColumnModel tableColumnModel;
    private String[] columnNamesArr;
    private ArrayList<String> columnNamesList;
    private ArrayList<String> cellsList;
    private Object[][] data;
    private DefaultTableModel defaultTableModel;
    private Object[][] cells2;

    private String[] cellsArr;
    private String[] namesArr;
    private String[] NICArr;
    private String[] TitleArr;
    private String[] AddressArr;
    private Object[][] cells = {
        {"Amal", "Walisara", new Integer(38), "Male"},
        {"Kamal", "Miliya", new Integer(55), "Male"},
        {"Wasana", "Panadura", new Integer(66), "Female"},
        {"Gihan", "Wel", new Integer(18), "Male"},
        {"Nimal", "Kalutara", new Integer(25), "Male"},};
    private String[] columnNames = {
        "Name", "Address", "Age", "Sex"
    };

    // Non compulsory fields initialize true
    boolean nameCheck = false;
    boolean NICCheck = true;
    boolean contactCheck = true;
    boolean emailCheck = true;

    JTextField txtcusID, txtcusName, txtcusTitle, txtcusNIC, txtcusContact, txtcusEmail,
            txtcusAddress, txtcusPostalCode, txtcusProvince, txtcusCity;

//    private final JTextField[] cus_textfield = {
//        txtcusName, txtcusTitle, txtcusNIC, txtcusContact, txtcusEmail,
//        txtcusAddress, txtcusPostalCode, txtcusProvince, txtcusCity};
//    private int currentIndex = 0;
    public SearchCustomer() {
        initComponents();
//        loadTable();
        loadCombo();
        setCombeKey();
        createTable();
//        TableController.addDataToColumn();

    }

    private void createTable() {

        new Thread() {
            public void run() {

                columnNamesList = new ArrayList<String>();
                columnNamesList.add("ID");
                columnNamesList.add("TITLE");
                columnNamesList.add("NAME");
                columnNamesList.add("NIC");
                columnNamesList.add("BIRTHDAY");
                columnNamesList.add("EMAIL");
                columnNamesList.add("CONTACT");
                columnNamesList.add("ADDRESS");
                columnNamesList.add("CITY");
                columnNamesList.add("PROVINCE");
                columnNamesList.add("POSTAL CODE");
                try {
                    arCustomer = CustomerUtilities.getAllCustomers();
//                    cellsArr = new String[arCustomer.size()];
                    namesArr = new String[arCustomer.size()];
                    cells2 = new String[arCustomer.size()][columnNamesList.size()];
                    for (int i = 0; i < arCustomer.size(); i++) {
                        cells2[i][0] = arCustomer.get(i).getCustomer_id();
                        cells2[i][1] = arCustomer.get(i).getCustomer_title();
//                        TitleArr[i] = arCustomer.get(i).getCustomer_title();
                        cells2[i][2] = arCustomer.get(i).getCustomer_name();
//                        namesArr[i] = arCustomer.get(i).getCustomer_name();
                        cells2[i][3] = arCustomer.get(i).getCustomer_nic();
//                        NICArr[i] = arCustomer.get(i).getCustomer_nic();
                        cells2[i][4] = arCustomer.get(i).getCustomer_bday();
                        cells2[i][5] = arCustomer.get(i).getCustomer_email();
                        cells2[i][6] = arCustomer.get(i).getCustomer_contact();
                        cells2[i][7] = arCustomer.get(i).getCustomer_address();
                        cells2[i][8] = arCustomer.get(i).getCustomer_city();
                        cells2[i][9] = arCustomer.get(i).getCustomer_province();
                        cells2[i][10] = arCustomer.get(i).getCustomer_postalcode();
                    }

//                    for (int i = 0; i < arCustomer.size(); i++) {
//                        for (int j = 0; j < columnNamesList.size(); j++) {
//                            cells2[i][j] = arCustomer.get(i).getCustomer_id();
//                            System.out.println(cells2[i][j]);
//                        }
//                        System.out.println("  ");
//                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SearchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SearchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SearchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }

                data = new String[1][columnNamesList.size()];
                columnNamesArr = new String[columnNamesList.size()];
                for (int i = 0; i < columnNamesList.size(); i++) {
                    columnNamesArr[i] = columnNamesList.get(i);
                    data[0][i] = "";
                }

                defaultTableModel = new DefaultTableModel(cells2, columnNamesArr);
//                defaultTableModel = new DefaultTableModel(arCustomer.size(),columnNamesList.size());
//                defaultTableModel.addColumn("NAME", namesArr);
//                defaultTableModel.addColumn("NIC", NICArr);
//                defaultTableModel.addColumn("TITLE", TitleArr);
//                defaultTableModel.addColumn("TITLE", TitleArr);
//                defaultTableModel.addColumn("TITLE", TitleArr);

                customerTable = new JTable(defaultTableModel);

                tableColumnModel = customerTable.getColumnModel();

                for (int i = 0; i < columnNamesList.size(); i++) {
                    tableColumnModel.getColumn(i).setPreferredWidth(columnNamesList.get(i).length());
                }
                customerTable.setPreferredScrollableViewportSize(customerTable.getPreferredSize());
                jScrollPane2.getViewport().add(customerTable);

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
                chk_Title.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_Title.isSelected()) {
                            TableColumn toRemove = customerTable.getColumn("TITLE");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("TITLE");
                            toAdd.setIdentifier("TITLE");
                            toAdd.setPreferredWidth("TITLE".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
                        }
                    }
                });
                chk_Name.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_Name.isSelected()) {

                            TableColumn toRemove = customerTable.getColumn("NAME");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();

//                            customerTable.setModel(defaultTableModel);
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("NAME");
                            toAdd.setIdentifier("NAME");
                            toAdd.setPreferredWidth("NAME".length());
//                            customerTable.addColumn(new TableColumn(2, 2, cellRenderer, cellEditor));
                            customerTable.validate();
                            
                   
//                            customerTable.addColumn(toAdd);
//                            customerTable.validate();
//                            defaultTableModel.addColumn("NAME", namesArr);
//                            customerTable = new JTable(defaultTableModel);
//                            customerTable.setModel(defaultTableModel);

                        }
                    }
                });

                chk_NIC.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_NIC.isSelected()) {
                            TableColumn toRemove = customerTable.getColumn("NIC");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("NIC");
                            toAdd.setIdentifier("NIC");
                            toAdd.setPreferredWidth("NIC".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
                        }
                    }
                });
                chk_Birthday.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_Birthday.isSelected()) {
                            TableColumn toRemove = customerTable.getColumn("BIRTHDAY");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("BIRTHDAY");
                            toAdd.setIdentifier("BIRTHDAY");
                            toAdd.setPreferredWidth("BIRTHDAY".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
                        }
                    }
                });
                chk_Contact.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_Contact.isSelected()) {
                            TableColumn toRemove = customerTable.getColumn("CONTACT");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("CONTACT");
                            toAdd.setIdentifier("CONTACT");
                            toAdd.setPreferredWidth("CONTACT".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
                        }
                    }
                });
                chk_Email.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_Email.isSelected()) {
                            TableColumn toRemove = customerTable.getColumn("EMAIL");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("EMAIL");
                            toAdd.setIdentifier("EMAIL");
                            toAdd.setPreferredWidth("EMAIL".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
                        }
                    }
                });
                chk_Address.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_Address.isSelected()) {
                            TableColumn toRemove = customerTable.getColumn("ADDRESS");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("ADDRESS");
                            toAdd.setIdentifier("ADDRESS");
                            toAdd.setPreferredWidth("ADDRESS".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
                        }
                    }
                });
                chk_City.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_City.isSelected()) {
                            TableColumn toRemove = customerTable.getColumn("CITY");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("CITY");
                            toAdd.setIdentifier("CITY");
                            toAdd.setPreferredWidth("CITY".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
                        }
                    }
                });
                chk_Province.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_Province.isSelected()) {
                            TableColumn toRemove = customerTable.getColumn("PROVINCE");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("PROVINCE");
                            toAdd.setIdentifier("PROVINCE");
                            toAdd.setPreferredWidth("PROVINCE".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
                        }
                    }
                });
                chk_Postal.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!chk_Postal.isSelected()) {
                            TableColumn toRemove = customerTable.getColumn("POSTAL CODE");
                            customerTable.removeColumn(toRemove);
                            customerTable.validate();
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("POSTAL CODE");
                            toAdd.setIdentifier("POSTAL CODE");
                            toAdd.setPreferredWidth("POSTAL CODE".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
                        }
                    }
                });

            }
        }.start();
        jTbl_SearchCustomers.setAutoCreateRowSorter(true);
    }

    private void loadTable() {
        new Thread() {
            public void run() {

                try {
                    TableController.addDataToTable(customerTable, "Select * FROM CUSTOMER");
                } catch (Exception e) {
                }
            }
        }.start();
        customerTable.setAutoCreateRowSorter(true);
    }

    private void loadCombo() {
        try {
            //load data from database
            ArrayList<CustomerModel> arCustomer = CustomerUtilities.getAllCustomers();
            for (CustomerModel c : arCustomer) {
                cmb_cusID.addItem(c.getCustomer_id());
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
                        cmb_cusID,
                        cmb_cusTitle,
                        cmb_cusName,
                        cmb_cusNIC,
                        cmb_cusContact,
                        cmb_cusEmail,
                        cmb_cusAddress,
                        cmb_cusPostalCode,
                        cmb_cusProvince,
                        cmb_cusCity);
            }
            SearchableCombo.setSearchableCombo(
                    true,
                    "<<<<No Results>>>>",
                    cmb_cusID,
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
                    cmb_cusID,
                    cmb_cusTitle,
                    cmb_cusName,
                    cmb_cusNIC,
                    cmb_cusContact,
                    cmb_cusEmail,
                    cmb_cusAddress,
                    cmb_cusPostalCode,
                    cmb_cusProvince,
                    cmb_cusCity);

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
        cmb_cusID.requestFocus();
    }

//    private void requestFocusForIndex(int index) {
//        if (index < 0 || index > (cus_textfield.length - 1)) {
//            return;
//        }
//        cus_textfield[index].requestFocus();
//        currentIndex = index;
//        System.out.println(currentIndex);
//    }
    private void setCombeKey() {

        try {
//            for (int i = 0; i < cus_textfield.length; i++) {
//                cus_textfield[i].addKeyListener(new KeyAdapter() {
//                    public void keyReleased(KeyEvent evt) {
//                        if (evt.getKeyCode() == KeyEvent.VK_F1) {
//                            requestFocusForIndex(currentIndex - 1);
//                        }
//                        if (evt.getKeyCode() == KeyEvent.VK_F2) {
//                            requestFocusForIndex(currentIndex + 1);
//                        }
//                    }
//                });
//            }
            txtcusID
                    = (JTextField) cmb_cusID.getEditor().getEditorComponent();
            txtcusTitle
                    = (JTextField) cmb_cusTitle.getEditor().getEditorComponent();
            txtcusName
                    = (JTextField) cmb_cusName.getEditor().getEditorComponent();
            txtcusNIC
                    = (JTextField) cmb_cusNIC.getEditor().getEditorComponent();
            txtcusContact
                    = (JTextField) cmb_cusContact.getEditor().getEditorComponent();
            txtcusEmail
                    = (JTextField) cmb_cusEmail.getEditor().getEditorComponent();
            txtcusAddress
                    = (JTextField) cmb_cusAddress.getEditor().getEditorComponent();
            txtcusPostalCode
                    = (JTextField) cmb_cusPostalCode.getEditor().getEditorComponent();
            txtcusProvince
                    = (JTextField) cmb_cusProvince.getEditor().getEditorComponent();
            txtcusCity
                    = (JTextField) cmb_cusCity.getEditor().getEditorComponent();

            cmb_cusID.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        txtcusTitle.requestFocus();
                    }
                }
            });
            txtcusTitle.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        cmb_cusID.requestFocus();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        txtcusName.requestFocus();
                    }
                }
            });
            txtcusName.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        txtcusTitle.requestFocus();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        txtcusNIC.requestFocus();
                    }
                }
            });
            txtcusNIC.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        txtcusName.requestFocus();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        txtcusContact.requestFocus();
                    }
                }
            });
            txtcusContact.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        txtcusNIC.requestFocus();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        txtcusEmail.requestFocus();
                    }
                }
            });
            txtcusEmail.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        txtcusContact.requestFocus();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        txtcusAddress.requestFocus();
                    }
                }
            });
            txtcusAddress.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        txtcusEmail.requestFocus();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        txtcusPostalCode.requestFocus();
                    }
                }
            });
            txtcusPostalCode.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        txtcusAddress.requestFocus();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        txtcusProvince.requestFocus();
                    }
                }
            });
            txtcusProvince.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        txtcusPostalCode.requestFocus();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        txtcusCity.requestFocus();
                    }
                }
            });
            txtcusCity.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        txtcusProvince.requestFocus();
                    }
                }
            });
        } catch (Exception e) {
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbl_SearchCustomers = new javax.swing.JTable();
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
        btn_searchCustomer1 = new javax.swing.JButton();
        chk_ID = new javax.swing.JCheckBox();
        chk_Postal = new javax.swing.JCheckBox();
        chk_NIC = new javax.swing.JCheckBox();
        chk_Title = new javax.swing.JCheckBox();
        chk_Address = new javax.swing.JCheckBox();
        chk_Contact = new javax.swing.JCheckBox();
        chk_Name = new javax.swing.JCheckBox();
        chk_City = new javax.swing.JCheckBox();
        chk_Province = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        chk_Birthday = new javax.swing.JCheckBox();
        chk_Email = new javax.swing.JCheckBox();

        jTbl_SearchCustomers.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTbl_SearchCustomers);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 920));

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

        chk_ID.setSelected(true);
        chk_ID.setText("ID");
        chk_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_IDActionPerformed(evt);
            }
        });

        chk_Postal.setSelected(true);
        chk_Postal.setText("Postal Code");
        chk_Postal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_PostalActionPerformed(evt);
            }
        });

        chk_NIC.setSelected(true);
        chk_NIC.setText("NIC");
        chk_NIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_NICActionPerformed(evt);
            }
        });

        chk_Title.setSelected(true);
        chk_Title.setText("Title");
        chk_Title.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_TitleActionPerformed(evt);
            }
        });

        chk_Address.setSelected(true);
        chk_Address.setText("Address");
        chk_Address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_AddressActionPerformed(evt);
            }
        });

        chk_Contact.setSelected(true);
        chk_Contact.setText("Contact Number");
        chk_Contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_ContactActionPerformed(evt);
            }
        });

        chk_Name.setSelected(true);
        chk_Name.setText("Name");
        chk_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_NameActionPerformed(evt);
            }
        });

        chk_City.setSelected(true);
        chk_City.setText("City");
        chk_City.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_CityActionPerformed(evt);
            }
        });

        chk_Province.setSelected(true);
        chk_Province.setText("Province");
        chk_Province.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_ProvinceActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Column:");

        chk_Birthday.setSelected(true);
        chk_Birthday.setText("Birth Day");

        chk_Email.setSelected(true);
        chk_Email.setText("Email");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(53, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbl_cusNIC)
                            .addComponent(jLbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(cmb_cusNIC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLbl_cusContactNum, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmb_cusContact, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cmb_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cmb_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(213, 213, 213)
                                .addComponent(jLbl_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_cusProvince, 0, 1, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 52, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbl_cusAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusCity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLbl_addNewCus, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_logout)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_searchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_searchCustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_ID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chk_NIC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_Contact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chk_Address)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chk_City)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_Province)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_Postal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_Birthday)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chk_Email)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusName))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_cusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusContactNum)
                            .addComponent(cmb_cusContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmb_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmb_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLbl_cusProvince)
                                .addComponent(cmb_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(8, 8, 8))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmb_cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLbl_cusAddress))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmb_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLbl_cusCity)))))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_searchCustomer)
                    .addComponent(btn_searchCustomer1)
                    .addComponent(btn_cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chk_ID)
                                .addComponent(jLabel1))
                            .addComponent(chk_Title)
                            .addComponent(chk_Name)
                            .addComponent(chk_Contact)
                            .addComponent(chk_Address)
                            .addComponent(chk_City)
                            .addComponent(chk_Province)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chk_Postal)
                                .addComponent(chk_Birthday)
                                .addComponent(chk_Email)))
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chk_NIC)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmb_cusAddress, cmb_cusCity, cmb_cusPostalCode, cmb_cusProvince});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_cancle, btn_searchCustomer, btn_searchCustomer1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLbl_cusAddress, jLbl_cusCity, jLbl_cusPostalCode, jLbl_cusProvince});

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
                    TableController.addDataToTable(jTbl_SearchCustomers, newQuerry);
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

    private void chk_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_IDActionPerformed

    private void chk_PostalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_PostalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_PostalActionPerformed

    private void chk_NICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_NICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_NICActionPerformed

    private void chk_TitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_TitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_TitleActionPerformed

    private void chk_AddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_AddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_AddressActionPerformed

    private void chk_ContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_ContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_ContactActionPerformed

    private void chk_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_NameActionPerformed

    private void chk_CityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_CityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_CityActionPerformed

    private void chk_ProvinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_ProvinceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_ProvinceActionPerformed

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
            java.util.logging.Logger.getLogger(SearchCustomer.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchCustomer.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchCustomer.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchCustomer.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchCustomer().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancle;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_searchCustomer;
    private javax.swing.JButton btn_searchCustomer1;
    private javax.swing.JCheckBox chk_Address;
    private javax.swing.JCheckBox chk_Birthday;
    private javax.swing.JCheckBox chk_City;
    private javax.swing.JCheckBox chk_Contact;
    private javax.swing.JCheckBox chk_Email;
    private javax.swing.JCheckBox chk_ID;
    private javax.swing.JCheckBox chk_NIC;
    private javax.swing.JCheckBox chk_Name;
    private javax.swing.JCheckBox chk_Postal;
    private javax.swing.JCheckBox chk_Province;
    private javax.swing.JCheckBox chk_Title;
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
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTbl_SearchCustomers;
    // End of variables declaration//GEN-END:variables
}

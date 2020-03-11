/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.User;

import View.Customer.*;
import ATest.ButtonEditor;
import ATest.ButtonRenderer;
import controller.ComponentUtilities.ComboSearch;
import controller.ComponentUtilities.ComboSearch2;
import controller.ComponentUtilities.GetBirthDay;
import controller.ComponentUtilities.QuerryGenerate;
import controller.ComponentUtilities.SearchableCombo;
import controller.ComponentUtilities.TableController;
import controller.ComponentUtilities.ValidateValues;
import controller.dataUtilities.CustomerUtilities;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
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
        loadTable();
        loadCombo();
        setCombeKey();
        
    }
    
    private void loadTable() {
        new Thread() {
            public void run() {
                
                try {
                    TableController.addDataToTable(jTbl_SearchCustomers, "Select * FROM CUSTOMER");
                    jTbl_SearchCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            int row = jTbl_SearchCustomers.rowAtPoint(evt.getPoint());
                            int col = jTbl_SearchCustomers.columnAtPoint(evt.getPoint());
                            final String valueInCell = (String) jTbl_SearchCustomers.getValueAt(row, col);

                            //for verification
                            try {
                                System.out.println(valueInCell);
                                CustomerModel cus = CustomerUtilities.getCustomer(valueInCell);
                                System.out.println(cus.getCustomer_name() + " " + cus.getCustomer_title());
                            } catch (Exception ex) {
                                Logger.getLogger(SearchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            // verifiction ends
                            // verifiction ends

                            DeleteCustomer Delete = new DeleteCustomer(valueInCell);
                            Delete.setVisible(true);
                            Delete.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            
                        }
                    });
                } catch (Exception e) {
                }
            }
        }.start();
        jTbl_SearchCustomers.setAutoCreateRowSorter(true);
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
            txtcusID.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F2) {
                        txtcusTitle.requestFocus();
                    }
                }
            });
            txtcusTitle.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_F1) {
                        txtcusID.requestFocus();
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
        btn_clear = new javax.swing.JButton();
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
        jTbl_SearchCustomers = new javax.swing.JTable();
        btn_searchCustomer1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 920));
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

        cmb_cusID.setEditable(true);
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

        btn_clear.setText("CLEAR");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

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

        btn_searchCustomer1.setText("SEARCH ALL");
        btn_searchCustomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchCustomer1ActionPerformed(evt);
            }
        });

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });

        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });

        jLabel1.setText("F1 : Next edit Text Field");

        jLabel2.setText("F2 : Previous edit Text Field");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addGap(23, 23, 23)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cmb_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLbl_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_cusProvince, 0, 1, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(jLbl_cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLbl_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
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
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146)
                        .addComponent(jLabel1)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel2)
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
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmb_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmb_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBox5)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmb_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLbl_cusName))
                                    .addComponent(jCheckBox4))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cmb_cusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLbl_cusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLbl_cusContactNum)
                                                .addComponent(cmb_cusContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jCheckBox8, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(cmb_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jCheckBox9, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jCheckBox6))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cmb_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(jCheckBox7)))
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
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cmb_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLbl_cusCity))))))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_searchCustomer)
                            .addComponent(btn_searchCustomer1)
                            .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addGap(13, 13, 13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmb_cusAddress, cmb_cusCity, cmb_cusPostalCode, cmb_cusProvince});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_clear, btn_searchCustomer, btn_searchCustomer1});

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
        
        txtcusID = (JTextField) cmb_cusID.getEditor().getEditorComponent();
        txtcusTitle = (JTextField) cmb_cusTitle.getEditor().getEditorComponent();
        txtcusName = (JTextField) cmb_cusName.getEditor().getEditorComponent();
        txtcusNIC = (JTextField) cmb_cusNIC.getEditor().getEditorComponent();
        txtcusContact = (JTextField) cmb_cusContact.getEditor().getEditorComponent();
        txtcusEmail = (JTextField) cmb_cusEmail.getEditor().getEditorComponent();
        txtcusAddress = (JTextField) cmb_cusAddress.getEditor().getEditorComponent();
        txtcusCity = (JTextField) cmb_cusCity.getEditor().getEditorComponent();
        txtcusProvince = (JTextField) cmb_cusProvince.getEditor().getEditorComponent();
        txtcusPostalCode = (JTextField) cmb_cusPostalCode.getEditor().getEditorComponent();
        
        String[] allValues = {
            txtcusID.getText(),
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
            "customer_id",
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

        for (int i = 0; i < allValues.length; i++) {
            if (!allValues[i].equalsIgnoreCase("")) {
                columns.add(allColumns[i]);
                values.add(allValues[i]);
            }
        }
        
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
    }//GEN-LAST:event_btn_searchCustomerActionPerformed

    private void cmb_cusTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_cusTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_cusTitleActionPerformed

    private void cmb_cusIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_cusIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_cusIDActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        txtcusID.setText("");
        txtcusTitle.setText("");
        txtcusName.setText("");
        txtcusNIC.setText("");
        txtcusContact.setText("");
        txtcusEmail.setText("");
        txtcusAddress.setText("");
        txtcusCity.setText("");
        txtcusProvince.setText("");
        txtcusPostalCode.setText("");
    }//GEN-LAST:event_btn_clearActionPerformed

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
    private javax.swing.JButton btn_clear;
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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTable jTbl_SearchCustomers;
    // End of variables declaration//GEN-END:variables
}

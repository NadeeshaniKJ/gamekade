/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANote;

/**
 *
 * @author nadee
 */
public class Note {
    
    /*
    
    1 comiit changes as key combination added
    2 check box for table column selection added
    3 GET CUSTOMER DETAILS WHEN CLICKED ON A CUSTOMER added
    4 GET CUSTOMER DETAILS WHEN CLICKED ON A CUSTOMER added with update and delete options
    5 home button
    6 cancel button to clean selections added
    7 logout added
    
    
    customer home>>
    show>>> combo boxes to select what columns to display
    order by>> edit order by function for columns with numbers,there is an error in order
    
    search customer>>>
    allow search from each field
    upon click on cusomer name open window to delete or update customer>> you may make fields editable upon click on update and give a "save changes" button
    load data to combo box and  editable combo bx
    
    **** check same Nic numbers when add customers
    **** load data to combo box( when search in add customerOrders )>>https://www.youtube.com/watch?v=coDKSf45WMo  // https://www.algosome.com/articles/java-jcombobox-autocomplete.html
    **** when clicked on customer name go to display all his purchases in customerORders
    
    
    addcustomer>>>
    remove  default text
    focus gained>> do nothing // font black
    focus lost>> empty>> >> check false for not null fields
                no empty>> validate >> error>>color red>> check false
                                    >>no error>>check true
    
    add button>> if all checks true>> add data
                if not>> display fist error
    
    */
    /*
    24022020
    add  lombok to customer -done >>> add dependancy to a project on intellij to download it, netbeanas does not support
    create model classes -done
    create data utility classes
    view and component controll
    
    
    1.crerate and complete show al data panels of home
    2.create button click panels of home
    2.1 add select all table
    2.2 add new data panels
    2.3 edit data >>> press edit >> make table editable >>> try to get changed value
    2.4 search / sort data >>> cascade tables and show more details >>> add options to select columns to show
    
    
    fill the JFrame to Window size >>>>>>>>>>>
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    (Select all components of JFrame, right click, select 'Auto Resizing', check for both Horizontal and Vertical, close . 
    Calling pack() will usually result in the window being resized to fit the contents' preferred size.)
    
    ****Set Layout to Boarder Layout to set the size of inside component (JtabbedPane) change with JFrame
    
    ***jSplitPane1.setRightComponent(addCustomerJLPane); to add jpanels to jsplit pane rightcomponenet on button click
    
    ****jTbl_AllSales.setAutoCreateRowSorter(true); to add sorter to table 
    
    QQQ..............??????????????????.
    why new thread to load data to table
    
    
    
    
    **************************************************************************************************************************************
     try {
                    ArrayList<CustomerModel> arCustomer =CustomerUtilities.getAllCustomers();
                    for (CustomerModel c:arCustomer){
                        System.out.println(arCustomer.get(1).getCustomer_name());
                    }
                      
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SerchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SerchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SerchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
    */
    
    /*
    private void createTable() {
        new Thread() {
            public void run() {
                columnNamesList = new ArrayList<String>();
                columnNamesList.add("ID");
                columnNamesList.add("TITLE");
                columnNamesList.add("NAME");

                cellsList = new ArrayList<String>();
                cellsList.add("ID1");
                cellsList.add("TITLE1");
                cellsList.add("NAME1");

                cells2 = new String[5][4];

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        cells2[i][j] = new String("g");
                        System.out.println(i + " " + j + " " + cells[i][j]);
                    }
                    System.out.println("    ");
                }
                System.out.println("asasasas  " + cells[1][1]);
                customerTable = new JTable(cells2, columnNames);
                jScrollPane2.getViewport().add(customerTable);

                /*
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

                //*************************************************               
                cellsList = new ArrayList<String>();
                data = new String[cellsList.size()][columnNamesList.size()];
                for (int i = 0; i < columnNamesList.size(); i++) {
                    for (int j = 0; j < cellsList.size(); j++) {
//                    columnNamesArr[i] = columnNamesList.get(i);
                        data[j][i] = "g";

                    }
                }
//******************************************************************

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
                        } else {
                            TableColumn toAdd = new TableColumn();
                            toAdd.setHeaderValue("NAME");
                            toAdd.setIdentifier("NAME");
                            toAdd.setPreferredWidth("NAME".length());
                            customerTable.addColumn(toAdd);
                            customerTable.validate();
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

    */
}

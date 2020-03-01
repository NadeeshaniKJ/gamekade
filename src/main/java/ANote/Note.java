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
    
    **** check same Nic numbers
    **** load data to combo box when search in add customerOrders >>https://www.youtube.com/watch?v=coDKSf45WMo  // https://www.algosome.com/articles/java-jcombobox-autocomplete.html
    **** when clicked on customer name go to display all his purchases in customerORders
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
    */
}

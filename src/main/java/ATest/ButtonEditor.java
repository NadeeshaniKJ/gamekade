/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ATest;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author nadee
 */
public class ButtonEditor extends DefaultCellEditor{
    
    protected JButton btn;
    private String lbl;
    private Boolean clicked=true;
    
    public ButtonEditor(JTextField textField) {
        super(textField);
        
        btn=new JButton();
        btn.setOpaque(true);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
//                if(e.getSource()==btn){
//                    btn.setText("clicked");
//                    System.out.println("clicked");   
//                }
//            JOptionPane.showMessageDialog(btn,"Clicked");
            
//            System.out.println("clicked");   
            fireEditingStopped(); 
            }
        });
    }
      @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        lbl= ((value == null) ? "" : value.toString());
        btn.setText(lbl);
        clicked=true;
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        
        if(clicked){
            JOptionPane.showMessageDialog(btn,"Clicked");
            
            System.out.println("clicked");
        }
        clicked=false;
        return new String(lbl); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped(); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ComponentUtilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author nadee
 */
public class ComboSearch2 {
    static  JTextField txtName;
static  ArrayList<String> ar;
    public static void comboSerch(final JComboBox cmbName){

                ar=new ArrayList<String>();
        for (int i = 0; i < cmbName.getItemCount(); i++) {
            ar.add(cmbName.getItemAt(i)+"" );
        }
        txtName = (JTextField) cmbName.getEditor().getEditorComponent();
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if ( !(evt.getKeyCode()==KeyEvent.VK_DOWN  || evt.getKeyCode()==KeyEvent.VK_UP )    ) {
                    String key = txtName.getText();
                    cmbName.hidePopup();
                    cmbName.removeAllItems();
                    for (int i = 0; i < ar.size(); i++) {
                        String name = ar.get(i);
                        if (name.toLowerCase().startsWith(key.toLowerCase())) {
                            cmbName.addItem(name);
                        }
                    }
                    txtName.setText(key);
                    cmbName.showPopup();
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    cmbName.hidePopup();
                }
            }
        });
    }
}

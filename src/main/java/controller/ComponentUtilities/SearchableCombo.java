/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ComponentUtilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author nadee
 */
public class SearchableCombo {

//    private ArrayList<String> ar = new ArrayList<String>();
    

    public static void setSearchableCombo(final JComboBox cmb, boolean mustSort, final String noReultsText) {
        
        ArrayList<String> ar = new ArrayList<String>();
        final int s = cmb.getItemCount();
        JTextField txt;
        
//        cmb.setSelectedItem("");

        for (int i = 0; i < s; i++) {
            boolean exists = false;
            for (int j = 0; j < ar.size(); j++) {
                if (ar.get(j).equalsIgnoreCase(cmb.getItemAt(i) + "")) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                ar.add(cmb.getItemAt(i) + "");
            }
        }
        if (mustSort) {
            Collections.sort(ar);
        }

        txt = (JTextField) cmb.getEditor().getEditorComponent();
        txt.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evt) {
                int key = evt.getKeyCode();
                if (!(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP)) {
                    String s = txt.getText();
                    int caret = txt.getCaretPosition();
                    cmb.hidePopup();
                    cmb.removeAllItems();
                    for (int i = 0; i < ar.size(); i++) {
                        if (ar.get(i).toUpperCase().startsWith(s.substring(0, caret).toUpperCase())) {
                            cmb.addItem(ar.get(i));
                        }
                    }
                    cmb.showPopup();
                    if (cmb.getItemCount() == 0) {
                        cmb.addItem(noReultsText);
//                        txt.setText(noReultsText);
                    }
                    txt.setText(s);
                    txt.setCaretPosition(caret);
                } else if (key == KeyEvent.VK_ESCAPE) {
                    cmb.setSelectedItem(txt.getText());
                    cmb.hidePopup();
                } else if (key == KeyEvent.VK_ENTER && cmb.getSelectedIndex() == -1) {
//                    if (cmb.getItemCount() == 1 && !cmb.getItemAt(0).equals(noReultsText)) {
                    if (cmb.getItemCount() == 1) {
                        cmb.setSelectedIndex(0);
                    } else if (cmb.getItemCount() > 1) {
                        cmb.setSelectedIndex(0);
                    }
                }
            }
        });

    }
    
  

    public static void setSearchableCombo(boolean mustSort, final String noReultsText,final JComboBox... cmb) {
        
        for (JComboBox cmb1:cmb){
            
        ArrayList<String> ar = new ArrayList<String>();
        final int s = cmb1.getItemCount();
        JTextField txt;
        
//        cmb1.setSelectedItem("");

        for (int i = 0; i < s; i++) {
            boolean exists = false;
            for (int j = 0; j < ar.size(); j++) {
                if (ar.get(j).equalsIgnoreCase(cmb1.getItemAt(i) + "")) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                ar.add(cmb1.getItemAt(i) + "");
            }
        }
        if (mustSort) {
            Collections.sort(ar);
        }

        txt = (JTextField) cmb1.getEditor().getEditorComponent();
        txt.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evt) {
                int key = evt.getKeyCode();
                if (!(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP)) {
                    String s = txt.getText();
                    int caret = txt.getCaretPosition();
                    cmb1.hidePopup();
                    cmb1.removeAllItems();
                    for (int i = 0; i < ar.size(); i++) {
                        if (ar.get(i).toUpperCase().startsWith(s.substring(0, caret).toUpperCase())) {
                            cmb1.addItem(ar.get(i));
                        }
                    }
                    cmb1.showPopup();
                    if (cmb1.getItemCount() == 0) {
                        cmb1.addItem(noReultsText);
//                        txt.setText(noReultsText);
                    }
                    txt.setText(s);
                    txt.setCaretPosition(caret);
                } else if (key == KeyEvent.VK_ESCAPE) {
                    cmb1.setSelectedItem(txt.getText());
                    cmb1.hidePopup();
                } else if (key == KeyEvent.VK_ENTER && cmb1.getSelectedIndex() == -1) {
//                    if (cmb.getItemCount() == 1 && !cmb.getItemAt(0).equals(noReultsText)) {
                    if (cmb1.getItemCount() == 1) {
                        cmb1.setSelectedIndex(0);
                    } else if (cmb1.getItemCount() > 1) {
                        cmb1.setSelectedIndex(0);
                    }
                }
            }
        });
        }
    }

    public static void orderComboItems(final JComboBox cmb) {

        ArrayList<String> ar = new ArrayList<String>();
        final int s = cmb.getItemCount();
        
//        cmb.setSelectedItem("");

        for (int i = 0; i < s; i++) {
            boolean exists = false;
            for (int j = 0; j < ar.size(); j++) {
                if (ar.get(j).equalsIgnoreCase(cmb.getItemAt(i) + "")) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                ar.add(cmb.getItemAt(i) + "");

            }
        }
        cmb.removeAllItems();
        Collections.sort(ar);
        for (int j = 0; j < ar.size(); j++) {
            cmb.addItem(ar.get(j));
        }

    }
    
    public static void orderComboItems(final JComboBox... cmb) {
// JComboBox cmb1;
        for (JComboBox cmb1:cmb){
        
        ArrayList<String> ar = new ArrayList<String>();
        final int s = cmb1.getItemCount();
        
//        cmb1.setSelectedItem("");

        for (int i = 0; i < s; i++) {
            boolean exists = false;
            for (int j = 0; j < ar.size(); j++) {
                if (ar.get(j).equalsIgnoreCase(cmb1.getItemAt(i) + "")) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                ar.add(cmb1.getItemAt(i) + "");

            }
        }
//         ar.removeIf(item -> item == null || "".equals(item));
        cmb1.removeAllItems();
        Collections.sort(ar);
        for (int j = 0; j < ar.size(); j++) {
            
           if(!"".equals(ar.get(j))){
                 cmb1.addItem(ar.get(j));
           }
               
        }
        
            
    }
    }
    public static void selectedComboItems(final JComboBox... cmb) {
        
        for (JComboBox cmb1 : cmb) {
            cmb1.setSelectedItem("");
        }
        
    }
     public static void setSelectedComboItem(String s, final JComboBox... cmb) {
        
        for (JComboBox cmb1 : cmb) {
            cmb1.setSelectedItem(s);
        }
        
    }
}

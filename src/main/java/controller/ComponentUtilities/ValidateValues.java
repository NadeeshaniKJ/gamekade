/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ComponentUtilities;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nadee
 */
public class ValidateValues {

    public static boolean emailValidation(JTextField e) {
        boolean emailValidation = true;
        String email = e.getText();
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (!email.matches(EMAIL_PATTERN)) {
            e.setForeground(Color.red);
            emailValidation = false;
        }
        return emailValidation;
    }

    public static boolean contactNumberValidation(JTextField e) {
        boolean contactNumberValidation = true;
        String contact = e.getText();
        final String CONTACT_NUMBER_PATTERN = "0\\d{9}";
        final String CONTACT_NUMBER_PATTERN2 = "\\+94\\d{9}";

        boolean contactCheck = contact.matches(CONTACT_NUMBER_PATTERN)
                || contact.matches(CONTACT_NUMBER_PATTERN2);

        if (!contactCheck) {
            e.setForeground(Color.red);
            contactNumberValidation = false;
        }
        return contactNumberValidation;
    }
    
    public static boolean NICValidation(JTextField e) {
        boolean NICValidation = true;
        String nic = e.getText();
        final String NIC_PATTERN_1 = "\\d{9}+V";
        final String NIC_PATTERN_2 = "\\d{9}+v";
        final String NIC_PATTERN_3 = "\\d{9}+X";
        final String NIC_PATTERN_4 = "\\d{9}+x";
        final String NIC_PATTERN_5 = "\\d{12}";

        boolean nicCheck = nic.matches(NIC_PATTERN_1) || nic.matches(NIC_PATTERN_2)
                || nic.matches(NIC_PATTERN_3) || nic.matches(NIC_PATTERN_4)
                || nic.matches(NIC_PATTERN_5);

        if (!nicCheck) {
            e.setForeground(Color.red);
            NICValidation = false;
        };

        return NICValidation;
    }

}

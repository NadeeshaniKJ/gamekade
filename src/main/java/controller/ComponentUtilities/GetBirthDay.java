/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ComponentUtilities;

import dbConnection.DBCon;
import dbConnection.DBHandle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author nadee
 */
public class GetBirthDay {

    public static String getBirthDay(String nic) {
        String birthday = "0000-00-00";
        int newIDLength = 12;
        int oldIDLength = 10;

        if (oldIDLength == nic.length()) {
            String sYear = nic.substring(0, 2);
            String sDays = nic.substring(2, 5);

            int[] months = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int days = Integer.parseInt(sDays);

            String Gender;
            if (days > 500) {
                Gender = "Female";
                days = days - 500;
            } else {
                Gender = "Male";
            }

            int i = 0;
            while (days > months[i]) {
                days = days - months[i];
                i++;
            }
            if (i <= 10) {
                birthday = "19" + sYear + "-0" + (i+1) + "-" + days;
            } else {
                birthday = "19" + sYear + "-" + (i+1) + "-" + days;
            }
        } else if (newIDLength == nic.length()) {
            String sYear = nic.substring(0, 4);
            String sDays = nic.substring(4, 7);

            int[] months = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int days = Integer.parseInt(sDays);

            String Gender;
            if (days > 500) {
                Gender = "Female";
                days = days - 500;
            } else {
                Gender = "Male";
            }

            int i = 0;
            while (days > months[i]) {
                days = days - months[i];
                i++;
            }
            if (i <= 10) {
                birthday = sYear + "-0" + (i+1)+ "-" + days;
            } else {
                birthday = sYear + "-" + (i+1) + "-" + days;
            }
        }
        return birthday;
    }

    public static void main(String[] args) {
        
        System.out.println("bday "+getBirthDay("199602235434"));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ComponentUtilities;

import java.util.ArrayList;

/**
 *
 * @author nadee
 */
public class QuerryGenerate {

    public static String Select_All_From_Where(String table, String[] columns, String[] values) {

        String quray = "SELECT * From " + table + " where ";
        quray = quray + columns[0] + "='" + values[0];

        for (int i = 1; columns.length > 1 && i < columns.length; i++) {
            for (int j = 1; j < values.length; j++) {
                if (i == j) {
                    quray = quray + "' AND " + columns[i] + "='" + values[j];
                }

            }
        }
        quray = quray + "';";
        return quray;
    }
    ArrayList<String> ar = new ArrayList<String>();

    public static String Select_All_From_Where(String table, ArrayList<String> columns, ArrayList<String> values) {

        String quray = "SELECT * From " + table + " where ";
        quray = quray + columns.get(0) + "='" + values.get(0);

        for (int i = 1; columns.size() > 1 && i < columns.size(); i++) {
            for (int j = 1; j < values.size(); j++) {
                if (i == j) {
                    quray = quray + "' AND " + columns.get(i) + "='" + values.get(j);
                }

            }
        }
        quray = quray + "';";
        return quray;
    }
//    public static void main(String args[]) {
//        String[] vvv = {"a", "c"};
//        String[] nnn = {"b", "d"};
//        Select_All_From_Where("customer", vvv, nnn);
//        System.out.println(Select_All_From_Where("customer", vvv, nnn));
//    }
}

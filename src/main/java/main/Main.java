/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dbConnection.DBCon;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nadee
 */
public class Main {

    public static void main(String args[]) {
        Connection con = null;
        Statement myStatement = null;
        ResultSet result = null;
        String statement = "select * from customer";

        try {
            con = DBCon.getConnection();
            myStatement = con.createStatement();
            result = myStatement.executeQuery(statement);
            while (result.next()) {
                System.out.println(result.getString("customer_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

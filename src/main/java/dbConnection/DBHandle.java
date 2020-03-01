/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author nadee
 */
public class DBHandle {
    public static int setData(Connection con, String query) throws Exception {
        Statement statement = con.createStatement();
        int result = statement.executeUpdate(query);
        return result;
    }

    public static ResultSet getData(Connection con, String query) throws Exception {
        Statement statement = con.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        return resultset;
    }
}

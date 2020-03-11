/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dataUtilities;

import dbConnection.DBCon;
import dbConnection.DBHandle;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.UserModel;

/**
 *
 * @author nadee
 */
public class UserUtilities {

    public static void adduser(UserModel user) throws SQLException,
            ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "INSERT INTO USER ("
                + "user_ID, "
                + "user_level, "
                + "user_name, "
                + "password, "
                + "name,"
                + "Q1,"
                + "Q2, "
                + "Q1_ans,"
                + "Q2_ans)  VALUES('"
                + user.getUser_ID() + "','"
                + user.getUser_level() + "','"
                + user.getUser_name() + "','"
                + user.getPassword() + "','"
                + user.getName() + "','"
                + user.getQ1() + "','"
                + user.getQ2() + "','"
                + user.getQ1_ans() + "','"
                + user.getQ2_ans() + "')";

        int result = DBHandle.setData(con, query);

        if (result != 0) {
            JOptionPane.showMessageDialog(null, "Add user Completed......");
        }
    }

    public static void updateItem(UserModel user)
            throws SQLException, ClassNotFoundException, IOException, Exception {
        Connection con = DBCon.getConnection();
        String query = "UPDATE USER SET "
                + "user_name='" + user.getUser_name()
                + "',password='" + user.getPassword()
                + "',user_level='" + user.getUser_level()
                + "',name='" + user.getName()
                + "',Q1='" + user.getQ1()
                + "',Q2='" + user.getQ2()
                + "',Q1_ans='" + user.getQ1_ans()
                + "',Q2_ans='" + user.getQ2_ans()
                + "' WHERE user_ID='" + user.getUser_ID() + "';";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null, "update user Completed.....");
        }
    }

    public static void deleteUser(UserModel user)
            throws SQLException, ClassNotFoundException, IOException, Exception {

        Connection con = DBCon.getConnection();
        String query = "DELETE FROM USER WHERE user_ID='"
                + user.getUser_ID() + "'";

        int result = DBHandle.setData(con, query);
        if (result != 0) {
            JOptionPane.showMessageDialog(null,
                    "delete user Completed......");
        }

    }

    public static UserModel showUser(String user_ID)
            throws Exception {

        UserModel user = new UserModel();

        Connection con = DBCon.getConnection();
        String query = "SELECT * FROM USER WHERE user_ID='" + user_ID + "'";
        ResultSet result = DBHandle.getData(con, query);
        while (result.next()) {
            user.setUser_ID(result.getString("user_ID"));
            user.setUser_level(result.getString("user_level"));
            user.setName(result.getString("name"));
            user.setUser_name(result.getString("user_name"));
            user.setPassword(result.getString("password"));
            user.setQ1(result.getString("Q1"));
            user.setQ1_ans(result.getString("Q1_ans"));
            user.setQ2(result.getString("Q2"));
            user.setQ2_ans(result.getString("Q2_ans"));
        }
        return user;
    }

    public static ArrayList showAllItems() throws SQLException,
            ClassNotFoundException, IOException, Exception {

        ArrayList<UserModel> aruser = new ArrayList<UserModel>(); // java.util

        Connection con = DBCon.getConnection();
        ResultSet result = DBHandle.getData(con, "SELECT * FROM ITEM");

        while (result.next()) {
            UserModel user = new UserModel();
            user.setUser_ID(result.getString("user_ID"));
            user.setUser_level(result.getString("user_level"));
            user.setName(result.getString("name"));
            user.setUser_name(result.getString("user_name"));
            user.setPassword(result.getString("password"));
            user.setQ1(result.getString("Q1"));
            user.setQ1_ans(result.getString("Q1_ans"));
            user.setQ2(result.getString("Q2"));
            user.setQ2_ans(result.getString("Q2_ans"));
            aruser.add(user);
        }

        return aruser;

    }
}

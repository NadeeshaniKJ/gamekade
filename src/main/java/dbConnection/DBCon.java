/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nadee
 */
public class DBCon {
    static String[]data=new String[4];
    
    private static void setAttribute() throws FileNotFoundException, IOException{
        
        File f1 = new File("ConnectionData.txt");
        
        int i=0;
     
            FileReader fr=new FileReader(f1);
            BufferedReader br=new BufferedReader(fr);
            String d=br.readLine();
            while(d!=null){
                data[i]=d;
                i++;
                d=br.readLine();
            }
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException{
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307 /gamekadedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
        setAttribute();
        Class.forName(data[0]);
        Connection con=DriverManager.getConnection(data[1],data[2],data[3]);
        return con; 
         
    }
}

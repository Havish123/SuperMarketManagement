package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDb {
    Connection con=null;
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/supermarket","root","");
        }catch (Exception e){
            System.out.println(e);
        }
        return con;
    }
    public void conClose(){
        try {
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

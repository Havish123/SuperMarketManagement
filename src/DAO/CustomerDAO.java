package DAO;

import Modal.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<Customer> customer=new ArrayList<Customer>();
    Connection con=null;
    ConnectDb connectDb=new ConnectDb();
    public void connect(){
        con=connectDb.connect();
    }
    public void conClose(){
        connectDb.conClose();
    }
    public void createTable(){
        String sql="CREATE TABLE IF NOT EXISTS customer(cust_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,cust_name VARCHAR(50) NOT NULL,cust_email VARCHAR(50) UNIQUE,cust_phone_no VARCHAR(10) UNIQUE)";
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int insertData(String cust_name,String cust_email,String cust_phone_no){
        String sql="INSERT INTO customer(cust_name,cust_email,cust_phone_no) VALUES(?,?,?);";
        String rsSql="SELECT LAST_INSERT_ID();";
        try{
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1,cust_name);
            pstmt.setString(2,cust_email);
            pstmt.setString(3,cust_phone_no);
            int rs=pstmt.executeUpdate();
            Statement stmt=con.createStatement();

            if(rs<1){
                System.out.println("Not inserted");
            }else{

                ResultSet id=stmt.executeQuery(rsSql);
                id.next();
                return id.getInt(1);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public int updateData(){
        String sql="";
        try{

        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public List<Customer> retriveData(){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM customer");
            while(rs.next()){
                Customer cust=new Customer();
                cust.setCust_id(rs.getInt("cust_id"));
                cust.setCust_name(rs.getString("cust_name"));
                cust.setEmail(rs.getString("cust_email"));
                cust.setPhone_no(rs.getString("cust_phone_no"));
                customer.add(cust);
            }

            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return customer;
    }
}

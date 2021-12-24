package DAO;


import Modal.Dealer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealerDAO {
    public List<Dealer> dealerList=new ArrayList<Dealer>();
    Connection con=null;
    ConnectDb connectDb=new ConnectDb();
    public void connect(){
        con=connectDb.connect();
    }
    public void conClose(){
        connectDb.conClose();
    }
    public void createTable(){
        String sql="CREATE TABLE IF NOT EXISTS dealer(dealer_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,dealer_name VARCHAR(50) NOT NULL,dealer_email VARCHAR(50) UNIQUE,dealer_phone_no VARCHAR(10) UNIQUE)";
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int insertData(String dealer_name,String dealer_email,String dealer_phone_no){
        String sql="INSERT INTO dealer(dealer_name,dealer_email,dealer_phone_no) VALUES(?,?,?);";
        String rsSql="SELECT LAST_INSERT_ID();";
        try{
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1,dealer_name);
            pstmt.setString(2,dealer_email);
            pstmt.setString(3,dealer_phone_no);
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
    public List<Dealer> retriveData(){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM dealer");
            while(rs.next()){
                Dealer dealer=new Dealer();
                dealer.setDealer_id(rs.getInt("dealer_id"));
                dealer.setDealer_name(rs.getString("dealer_name"));
                dealer.setEmail(rs.getString("dealer_email"));
                dealer.setPhone_no(rs.getString("dealer_phone_no"));
                dealerList.add(dealer);
            }

            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return dealerList;
    }
}

package DAO;


import Modal.Purchase;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {
    public List<Purchase> purchaseList=new ArrayList<Purchase>();
    Connection con=null;
    ConnectDb connectDb=new ConnectDb();
    public void connect(){
        con=connectDb.connect();
    }
    public void conClose(){
        connectDb.conClose();
    }
    public void createTable(){
        String sql="CREATE TABLE IF NOT EXISTS purchase(purchase_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,dealer_id INT,purchase_date DATE NOT NULL,purchase_amount FLOAT,FOREIGN KEY (dealer_id) REFERENCES dealer(dealer_id))";
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int insertData(int dealer_id,float tot_amount){
        String sql="INSERT INTO purchase(dealer_id,purchase_date,purchase_amount) VALUES(?,?,?);";
        String rsSql="SELECT LAST_INSERT_ID();";
        try{
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,dealer_id);
            java.util.Date date=new java.util.Date();
            SimpleDateFormat ft =
                    new SimpleDateFormat("yyyy.MM.dd");
            String date1 = ft.format(date).toString();
            pstmt.setString(2,date1);
            pstmt.setFloat(3,tot_amount);
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
    public List<Purchase> retriveData(){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM purchase");
            while(rs.next()){
                Purchase purchase=new Purchase();
                purchase.setPurchase_id(rs.getInt("purchase_id"));
                purchase.setDealer_id(rs.getInt("dealer_id"));
                purchase.setPurchase_date(rs.getString("purchase_date"));
                purchase.setPurchase_amount(rs.getFloat("purchase_amount"));
                purchaseList.add(purchase);
            }

            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return purchaseList;
    }
}

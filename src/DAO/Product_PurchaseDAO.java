package DAO;


import Modal.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product_PurchaseDAO {
    public List<product_purchase> orderDetails=new ArrayList<product_purchase>();
    Connection con=null;
    ConnectDb connectDb=new ConnectDb();
    public void connect(){
        con=connectDb.connect();
    }
    public void conClose(){
        connectDb.conClose();
    }
    public void createTable(){
        String sql="CREATE TABLE IF NOT EXISTS purchase_orders(order_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,purchase_id INT,dealer_id INT,product_id INT,quantity INT,net_rate FLOAT,amount FLOAT,FOREIGN KEY (purchase_id) REFERENCES purchase(purchase_id),FOREIGN KEY (dealer_id) REFERENCES dealer(dealer_id),FOREIGN KEY (product_id) REFERENCES product(product_id))";
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int insertData(int purchase_id,int dealer_id,List<product_purchase> purchases){
        String sql="INSERT INTO purchase_orders(purchase_id,dealer_id,product_id,quantity,net_rate,amount) VALUES(?,?,?,?,?,?);";
        //String rsSql="SELECT LAST_INSERT_ID();";
        for (product_purchase p :
                purchases) {
            try{
                PreparedStatement pstmt=con.prepareStatement(sql);
                pstmt.setInt(1,purchase_id);
                pstmt.setInt(2,dealer_id);
                pstmt.setInt(3,p.getProduct_id());
                pstmt.setInt(4,p.getQuantity());
                pstmt.setFloat(5,p.getNetrate());
                pstmt.setFloat(6,p.getAmount());
                int rs=pstmt.executeUpdate();
                pstmt.close();
            }catch (Exception e){
                System.out.println(e);

            }
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
    public List<product_purchase> retriveData(){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM orders");
            while(rs.next()){
                product_purchase order=new product_purchase();
                order.setOrder_id(rs.getInt("order_id"));
                order.setPurchase_id(rs.getInt("purchase_id"));
                order.setDealer_id(rs.getInt("dealer_id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setProduct_id(rs.getInt("product_id"));
                order.setNetrate(rs.getFloat("net_rate"));
                order.setAmount(rs.getFloat("amount"));
                orderDetails.add(order);
            }

            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return orderDetails;
    }
}

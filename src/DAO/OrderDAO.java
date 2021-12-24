package DAO;


import Modal.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<Order> orderDetails=new ArrayList<Order>();
    Connection con=null;
    ConnectDb connectDb=new ConnectDb();
    public void connect(){
        con=connectDb.connect();
    }
    public void conClose(){
        connectDb.conClose();
    }
    public void createTable(){
        String sql="CREATE TABLE IF NOT EXISTS orders(order_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,bill_id INT,cust_id INT,product_id INT,quantity INT,amount FLOAT,FOREIGN KEY (cust_id) REFERENCES customer(cust_id),FOREIGN KEY (bill_id) REFERENCES billdetails(bill_id),FOREIGN KEY (product_id) REFERENCES product(product_id))";
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int insertData(int bill_id,int cust_id,List<product_sales> purchases){
        String sql="INSERT INTO orders(bill_id,cust_id,product_id,quantity,amount) VALUES(?,?,?,?,?);";
        //String rsSql="SELECT LAST_INSERT_ID();";
        for (product_sales p :
                purchases) {
            try{
                PreparedStatement pstmt=con.prepareStatement(sql);
                pstmt.setInt(1,bill_id);
                pstmt.setInt(2,cust_id);
                pstmt.setInt(3,p.getProduct_id());
                pstmt.setInt(4,p.getQuantity());
                pstmt.setFloat(5,p.getAmount());
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
    public List<Order> retriveData(){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM orders");
            while(rs.next()){
                Order order=new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setBill_id(rs.getInt("bill_id"));
                order.setCust_id(rs.getInt("cust_id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setProduct_id(rs.getInt("product_id"));
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

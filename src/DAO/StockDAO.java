package DAO;

import Modal.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    public List<ProductStock> stockList=new ArrayList<ProductStock>();
    Connection con=null;
    ConnectDb connectDb=new ConnectDb();
    public void connect(){
        con=connectDb.connect();
    }
    public void conClose(){
        connectDb.conClose();
    }
    public void createTable(){
        String sql="CREATE TABLE IF NOT EXISTS stock(product_id INT NOT NULL PRIMARY KEY,updateDate DATE,sales INT,available INT,FOREIGN KEY (product_id) REFERENCES product(product_id))";
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int insertData(int product_id,String updateDate,int sales,int available){
        String sql="INSERT INTO stock(product_id,updateDate,sales,available) VALUES(?,?,?,?);";
        //String rsSql="SELECT LAST_INSERT_ID();";
        try{
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,product_id);
            pstmt.setString(2,updateDate);
            pstmt.setInt(3,sales);
            pstmt.setInt(4,available);
            int rs=pstmt.executeUpdate();
            pstmt.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return 0;
    }
    public int updatestock(List<product_sales> orderList){
        String sql="UPDATE stock SET available=available-?,sales=sales+? where product_id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(sql);
            for (product_sales order :
                    orderList) {
                stmt.setInt(1, order.getQuantity());
                stmt.setInt(2,order.getQuantity());
                stmt.setInt(3,order.getProduct_id());
                stmt.executeUpdate();
            }
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public int updateStock(List<product_purchase> purchaseList){
        String sql="UPDATE stock SET available=available+? where product_id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(sql);
            for (product_purchase purchase :
                    purchaseList) {
                stmt.setInt(1, purchase.getQuantity());
                stmt.setInt(2,purchase.getProduct_id());
                stmt.executeUpdate();
            }
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public List<ProductStock> retriveData(){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM stock");
            while(rs.next()){
                ProductStock stock=new ProductStock();
                stock.setProduct_id(rs.getInt("product_id"));
                stock.setAvailable(rs.getInt("available"));
                stock.setProductUpdateDate(rs.getString("updateDate"));
                stock.setProduct_sales(rs.getInt("sales"));
                stockList.add(stock);
            }

            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return stockList;
    }
}

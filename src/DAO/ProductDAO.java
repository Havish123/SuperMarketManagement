package DAO;

import Modal.Product;
import Modal.product_purchase;
import Modal.product_sales;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> products=new ArrayList<Product>();
    Connection con=null;
    ConnectDb connectDb=new ConnectDb();
    public void connect(){
        con=connectDb.connect();
    }
    public void conClose(){
        connectDb.conClose();
    }
    public void createTable(){
        String sql="CREATE TABLE IF NOT EXISTS product(product_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,product_name VARCHAR(50) NOT NULL,product_mrp FLOAT NOT NULL,product_srate FLOAT NOT NULL,product_netrate FLOAT NOT NULL,available INT )";
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int insertData(String product_name,float product_mrp,float product_srate,float product_netrate,int available){
        String sql="INSERT INTO product(product_name,product_mrp,product_srate,product_netrate,available) VALUES(?,?,?,?,?);";
        String rsSql="SELECT LAST_INSERT_ID();";
        try{
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1,product_name);
            pstmt.setFloat(2,product_mrp);
            pstmt.setFloat(3,product_srate);
            pstmt.setFloat(4,product_netrate);
            pstmt.setInt(5,available);
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
    public int updatestock(List<product_sales> orderList){
        String sql="UPDATE product SET available=available-? where product_id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(sql);
            for (product_sales order :
                    orderList) {
                stmt.setInt(1, order.getQuantity());
                stmt.setInt(2,order.getProduct_id());
                stmt.executeUpdate();
            }
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public int updateStock(List<product_purchase> purchaseList){
        String sql="UPDATE product SET available=available+?,product_netrate=? where product_id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(sql);
            for (product_purchase purchase :
                    purchaseList) {
                stmt.setInt(1, purchase.getQuantity());
                stmt.setFloat(2,purchase.getNetrate());
                stmt.setInt(3,purchase.getProduct_id());
                stmt.executeUpdate();
            }
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public int updateMrp(int product_id,float mrp){
        String sql="UPDATE product SET product_mrp=? where product_id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setFloat(1, mrp);
            stmt.setInt(2,product_id);
            stmt.executeUpdate();
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public int updateSaleRate(int product_id,float srate){
        String sql="UPDATE product SET product_srate=? where product_id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setFloat(1, srate);
            stmt.setInt(2,product_id);
            stmt.executeUpdate();
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public int updateNetrate(int product_id,float netrate){
        String sql="UPDATE product SET product_netrate=? where product_id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setFloat(1, netrate);
            stmt.setInt(2,product_id);
            stmt.executeUpdate();
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public List<Product> retriveData(){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM product");
            while(rs.next()){
                Product product=new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_mrp(rs.getFloat("product_mrp"));
                product.setProduct_srate(rs.getFloat("product_srate"));
                product.setProduct_netrate(rs.getFloat("product_netrate"));
                product.setAvailable(rs.getInt("available"));
                products.add(product);
            }

            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return products;
    }
}

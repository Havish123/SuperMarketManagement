package DAO;
import Modal.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public List<BillDetail> billDetails=new ArrayList<BillDetail>();
    ConnectDb connectDb=new ConnectDb();
    Connection con=null;
    public void connect(){
        con=connectDb.connect();
    }
    public void conClose(){
        connectDb.conClose();
    }
    public void createTable(){
        String sql="CREATE TABLE IF NOT EXISTS billdetails(bill_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,bill_date DATE NOT NULL,cust_id INT ,tot_amount FLOAT NOT NULL,payment_mode_id INT NOT NULL,discount FLOAT,FOREIGN KEY (cust_id) REFERENCES customer(cust_id))";
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int insertData(int cust_id,float tot_amount,int payment_mode_id,float discount){
        String sql="INSERT INTO billdetails(cust_id,bill_date,tot_amount,payment_mode_id,discount) VALUES(?,?,?,?,?);";
        String rsSql="SELECT LAST_INSERT_ID();";
        try{
            PreparedStatement pstmt=con.prepareStatement(sql);
            java.util.Date date=new java.util.Date();
          SimpleDateFormat ft =
                new SimpleDateFormat("yyyy.MM.dd");
            String str = ft.format(date).toString();
            pstmt.setInt(1,cust_id);
            pstmt.setString(2,str);
            pstmt.setFloat(3,tot_amount);
            pstmt.setInt(4,payment_mode_id);
            pstmt.setFloat(5,discount);
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
    public List<BillDetail> retriveData(){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM billdetails");
            while(rs.next()){
                BillDetail billDetail=new BillDetail();
                billDetail.setBill_id(rs.getInt("bill_id"));
                billDetail.setCust_id(rs.getInt("cust_id"));
                billDetail.setBill_date(rs.getString("bill_date"));
                billDetail.setPayment_mode_id(rs.getInt("payment_mode_id"));
                billDetail.setDiscount(rs.getFloat("discount"));
                billDetail.setTot_amount(rs.getFloat("tot_amount"));
                billDetails.add(billDetail);
            }

            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return billDetails;
    }
}

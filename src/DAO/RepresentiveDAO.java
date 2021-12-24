package DAO;
import Modal.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepresentiveDAO {
    public List<Representative> representatives=new ArrayList<Representative>();
    Connection con=null;
    ConnectDb connectDb=new ConnectDb();
    public void connect(){
        con=connectDb.connect();
    }
    public void conClose(){
        connectDb.conClose();
    }
    public void createTable(){
        String sql="CREATE TABLE IF NOT EXISTS representative(rep_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,rep_name VARCHAR(50) NOT NULL,age INT NOT NULL,salary int NOT NULL,rep_phoneno VARCHAR(10) UNIQUE,rep_email VARCHAR(50) UNIQUE,rep_type VARCHAR(20) NOT NULL,passcode VARCHAR(20) NOT NULL);";
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int insertData(String rep_name,int rep_age,String rep_email,String rep_phone_no,int salary,String rep_type,String passcode){
        String sql="INSERT INTO representative(rep_name,age,rep_email,rep_phoneno,salary,rep_type,passcode) VALUES(?,?,?,?,?,?,?);";
        String rsSql="SELECT LAST_INSERT_ID();";
        try{
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1,rep_name);
            pstmt.setInt(2,rep_age);
            pstmt.setString(3,rep_email);
            pstmt.setString(4,rep_phone_no);
            pstmt.setInt(5,salary);
            pstmt.setString(6,rep_type);
            pstmt.setString(7,passcode);
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

    public int updateSalary(int rep_id,int salary){
        String sql="UPDATE representative SET salary=? WHERE rep_id=?";
        try{
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1,salary);
            stmt.setInt(2,rep_id);
            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public int updatePhoneNumber(int rep_id,String phone_no){
        String sql="UPDATE representative SET rep_phoneno=? WHERE rep_id=?";
        try{
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,phone_no);
            stmt.setInt(2,rep_id);
            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public int updateDesignation(int rep_id,String type){
        String sql="UPDATE representative SET rep_type=? WHERE rep_id=?";
        try{
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,type);
            stmt.setInt(2,rep_id);
            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public List<Representative> retriveData(){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM representative");
            while(rs.next()){
                Representative rep=new Representative();
                rep.setRep_id(rs.getInt("rep_id"));
                rep.setRep_name(rs.getString("rep_name"));
                rep.setRep_email(rs.getString("rep_email"));
                rep.setRep_phoneno(rs.getString("rep_phoneno"));
                rep.setAge(rs.getInt("age"));
                rep.setSalary(rs.getInt("salary"));
                rep.setRep_type(rs.getString("rep_type"));
                rep.setPasscode(rs.getString("passcode"));
                representatives.add(rep);
            }

            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return representatives;
    }
}

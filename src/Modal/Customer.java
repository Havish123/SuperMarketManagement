package Modal;
import DAO.*;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<Customer> customerList=new ArrayList<Customer>();
    private int cust_id;
    private String cust_name;
    private String cust_email;
    private String cust_phone_no;

    public Customer(){

    }
    public Customer(int cust_id,String cust_name,String cust_email,String cust_phone_no){
        this.cust_id=cust_id;
        this.cust_name=cust_name;
        this.cust_email=cust_email;
        this.cust_phone_no=cust_phone_no;
    }
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        CustomerDAO custDao=new CustomerDAO();
        custDao.connect();
        setCustomerList(custDao.retriveData());
        custDao.conClose();
        return customerList;
    }

    public void setCust_id(int cust_id){
        this.cust_id=cust_id;
    }
    public void setCust_name(String cust_name){
        this.cust_name=cust_name;
    }
    public void setEmail(String email){
        this.cust_email=email;
    }
    public  void setPhone_no(String phone_no){
        this.cust_phone_no=phone_no;
    }
    public int getCust_id(){
        return cust_id;
    }
    public String getCust_name(){
        return cust_name;
    }
    public String getCust_email(){
        return cust_email;
    }
    public String getCust_phone_no(){
        return cust_phone_no;
    }

}


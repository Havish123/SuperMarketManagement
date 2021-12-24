package Modal;

import DAO.BillDAO;
import DAO.CustomerDAO;

import java.util.ArrayList;
import java.util.List;

public class BillDetail {
    private int bill_id;
    private String bill_date;
    private int cust_id;
    private float tot_amount;
    private int payment_mode_id;
    private float discount;
    private List<product_sales> product_sales =new ArrayList<product_sales>();
    private List<BillDetail> billDetails=new ArrayList<BillDetail>();

    public BillDetail(){

    }
    public BillDetail(int bill_id,String bill_date,int cust_id,float tot_amount,int payment_mode_id,float discount){
        this.bill_id=bill_id;
        this.bill_date=bill_date;
        this.cust_id=cust_id;
        this.tot_amount=tot_amount;
        this.payment_mode_id=payment_mode_id;
        this.discount=discount;
    }


    public List<BillDetail> getBillDetails() {
        BillDAO billDAO=new BillDAO();
        billDAO.connect();
        setBillDetails(billDAO.retriveData());
        billDAO.conClose();
        return billDetails;
    }

    public void setProduct_sales(List<Modal.product_sales> product_sales) {
        this.product_sales = product_sales;
    }

    public List<Modal.product_sales> getProduct_sales() {
        return product_sales;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public void setProduct_purchases(List<product_sales> product_sales) {
        this.product_sales = product_sales;
    }

    public List<product_sales> getProduct_purchases() {
        return product_sales;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setPayment_mode_id(int payment_mode_id) {
        this.payment_mode_id = payment_mode_id;
    }


    public void setTot_amount(float tot_amount) {
        this.tot_amount = tot_amount;
    }

    public float getDiscount() {
        return discount;
    }

    public float getTot_amount() {
        return tot_amount;
    }

    public int getBill_id() {
        return bill_id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public int getPayment_mode_id() {
        return payment_mode_id;
    }

    public String getBill_date() {
        return bill_date;
    }


}

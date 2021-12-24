package Modal;

import DAO.PurchaseDAO;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private int purchase_id;
    private int dealer_id;
    private String purchase_date;
    private float purchase_amount;
    private  List<product_purchase> product=new ArrayList<product_purchase>();
    List<Purchase> purchaseList=new ArrayList<>();

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public List<Purchase> getPurchaseList() {
        PurchaseDAO purchaseDAO=new PurchaseDAO();
        purchaseDAO.connect();
        setPurchaseList(purchaseDAO.retriveData());
        purchaseDAO.conClose();
        return purchaseList;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_amount(float purchase_amount) {
        this.purchase_amount = purchase_amount;
    }

    public float getPurchase_amount() {
        return purchase_amount;
    }

    public void setDealer_id(int dealer_id) {
        this.dealer_id = dealer_id;
    }

    public int getDealer_id() {
        return dealer_id;
    }



    public void setProduct(List<product_purchase> product) {
        this.product = product;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getPurchase_id() {
        return purchase_id;
    }



}



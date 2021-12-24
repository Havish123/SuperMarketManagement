package Modal;

import DAO.CustomerDAO;
import DAO.OrderDAO;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int order_id;
    private int bill_id;
    private int cust_id;
    private float amount;
    private int quantity;
    private int product_id;
    private List<Order> orderList=new ArrayList<Order>();

    public Order(){

    }

    public Order(int order_id, int bill_id, int cust_id, float amount, int quantity, int product_id) {
        this.order_id = order_id;
        this.bill_id = bill_id;
        this.cust_id = cust_id;
        this.amount = amount;
        this.quantity = quantity;
        this.product_id = product_id;
    }

    public List<Order> getOrderList() {
        OrderDAO orderDAO=new OrderDAO();
        orderDAO.connect();
        setOrderList(orderDAO.retriveData());
        orderDAO.conClose();
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public float getAmount() {
        return amount;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getQuantity() {
        return quantity;
    }
}

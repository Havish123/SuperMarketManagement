package Modal;

public class product_purchase {
    private int order_id;
    private int purchase_id;
    private int dealer_id;
    private int quantity;
    private int product_id;
    private float netrate;
    private float amount;

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setDealer_id(int dealer_id) {
        this.dealer_id = dealer_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getDealer_id() {
        return dealer_id;
    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setNetrate(float netrate) {
        this.netrate = netrate;
    }

    public float getNetrate() {
        return netrate;
    }



    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }
}

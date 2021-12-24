package Modal;
import DAO.*;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private List<Product> productList=new ArrayList<Product>();
    private int product_id;
    private String product_name;
    private float product_mrp;
    private float product_srate;
    private float product_netrate;
    private int available;
    public Product(){

    }
    public Product(int product_id,String product_name,float product_mrp,float product_srate,float product_netrate){
        this.product_id=product_id;
        this.product_name=product_name;
        this.product_mrp=product_mrp;
        this.product_srate=product_srate;
        this.product_netrate=product_netrate;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getAvailable() {
        return available;
    }

    public List<Product> addProduct(Product product){
        productList.add(product);
        return productList;
    }
    public List<Product> getProductList() {
        ProductDAO dao=new ProductDAO();
        dao.connect();
        setProductList(dao.retriveData());
        dao.conClose();
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setProduct_netrate(float product_netrate) {
        this.product_netrate = product_netrate;
    }

    public float getProduct_netrate() {
        return product_netrate;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setProduct_mrp(float product_mrp) {
        this.product_mrp = product_mrp;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_srate(float product_srate) {
        this.product_srate = product_srate;
    }

    public int getProduct_id() {
        return product_id;
    }

    public float getProduct_mrp() {
        return product_mrp;
    }

    public float getProduct_srate() {
        return product_srate;
    }

    public String getProduct_name() {
        return product_name;
    }
}

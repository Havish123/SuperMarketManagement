package Modal;

import DAO.StockDAO;

import java.util.ArrayList;
import java.util.List;

public class ProductStock {

    private int product_id;
    private int product_sales;
    private int available;
    private String productUpdateDate;
    private List<ProductStock> stockList=new ArrayList<ProductStock>();
    public ProductStock(){

    }

    public ProductStock( int product_id, int product_sales, int available,String updateDate) {

        this.product_id = product_id;
        this.productUpdateDate=updateDate;
        this.product_sales = product_sales;
        this.available = available;
    }


    public List<ProductStock> getStockList() {
        StockDAO stockDAO=new StockDAO();
        stockDAO.connect();
        setStockList(stockDAO.retriveData());
        stockDAO.conClose();
        return stockList;
    }

    public void setStockList(List<ProductStock> stockList) {
        this.stockList = stockList;
    }

    public String getProductUpdateDate() {

        return productUpdateDate;
    }

    public void setProductUpdateDate(String productUpdateDate) {
        this.productUpdateDate = productUpdateDate;
    }

    public int getProduct_sales() {
        return product_sales;
    }

    public void setProduct_sales(int product_sales) {
        this.product_sales = product_sales;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setAvailable(int available) {
        this.available = available;
    }


    public int getProduct_id() {
        return product_id;
    }

    public int getAvailable() {
        return available;
    }


}

import DAO.*;
import Modal.*;

import java.util.List;

public class SuperMarketManagement {
    private static SuperMarketController controller;

    public static void main(String[] args) {
        SuperMarketView view=new SuperMarketView();

        Customer customer=new Customer();
        Representative representative=new Representative();
        Product product=new Product();
        Order order=new Order();
        BillDetail billDetail=new BillDetail();
        Dealer dealer=new Dealer();
        Purchase purchase=new Purchase();
        product_purchase product_purchase=new product_purchase();
        ProductStock productStock=new ProductStock();

        CustomerDAO dao=new CustomerDAO();
        RepresentiveDAO repDAO=new RepresentiveDAO();
        ProductDAO productDAO=new ProductDAO();
        BillDAO billDAO= new BillDAO();
        OrderDAO orderDAO=new OrderDAO();
        DealerDAO dealerDAO=new DealerDAO();
        PurchaseDAO purchaseDAO=new PurchaseDAO();
        Product_PurchaseDAO product_purchaseDAO=new Product_PurchaseDAO();
        StockDAO stockDAO=new StockDAO();


        dao.connect();
        dao.createTable();
        dao.conClose();
        repDAO.connect();
        repDAO.createTable();
        repDAO.conClose();
        productDAO.connect();
        productDAO.createTable();
        productDAO.conClose();
        dealerDAO.connect();
        dealerDAO.createTable();
        dealerDAO.conClose();
        billDAO.connect();
        billDAO.createTable();
        billDAO.conClose();
        orderDAO.connect();
        orderDAO.createTable();
        orderDAO.conClose();
        purchaseDAO.connect();
        purchaseDAO.createTable();
        purchaseDAO.conClose();
        product_purchaseDAO.connect();
        product_purchaseDAO.createTable();
        product_purchaseDAO.conClose();
        stockDAO.connect();
        stockDAO.createTable();
        stockDAO.conClose();

        List<Customer> customerList=customer.getCustomerList();
        List<Representative> representatives=representative.getRepList();
        List<Product> products=product.getProductList();
        List<BillDetail> billDetails=billDetail.getBillDetails();
        List<Dealer> dealerList=dealer.getDealerList();
        List<Order> orderList=order.getOrderList();
        List<Purchase> purchaseList=purchase.getPurchaseList();
        List<ProductStock> productStocks=productStock.getStockList();


        controller=new SuperMarketController(view,customerList,representatives,products,dealerList,orderList,billDetails,purchaseList,productStocks);
        controller.login();
    }

}

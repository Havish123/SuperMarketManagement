import DAO.StockDAO;
import Modal.*;

import java.util.List;

public class SuperMarketController {
    private SuperMarketView view;
    private static List<Customer> customerList;
    private static List<Representative> repList;
    private static List<Product> productList;
    private static List<Dealer> dealerList;
    private static List<Order> orderList;
    private static List<BillDetail> billDetails;
    private static List<ProductStock> productStockList;
    private static int adminOptions;
    private static int repOptions;
    private static int userType;
    private static String repType;
    private static List<Purchase> purchases;
    private static int updateRepOptions;
    private static int updateProductOptions;

    public SuperMarketController(){

    }
    public SuperMarketController(SuperMarketView view){
        this.view=view;
    }
    public SuperMarketController(SuperMarketView view, List<Customer> customerList, List<Representative> repList, List<Product> productList,List<Dealer> dealerList,List<Order> orderList,List<BillDetail> billDetails,List<Purchase> purchases,List<ProductStock> productStockList){
        this.view=view;
        this.customerList=customerList;
        this.repList=repList;
        this.productList=productList;
        this.dealerList=dealerList;
        this.orderList=orderList;
        this.billDetails=billDetails;
        this.purchases=purchases;
        this.productStockList=productStockList;
    }

    public void login(){
        getUser();
        verifyUser();
        showOptions();
    }
    public void getUser(){
        this.userType=view.getUserType();
    }
    public void verifyUser(){
        if(userType==1){
            view.verifyAdmin();
        }else if(userType==2){
            repType=view.verifyRep(repList);
            showOptions();
        }else{
            System.exit(0);
        }
    }
    public void showOptions(){
        if(userType==1){
            adminOptions=view.getAdminOptions();
            adminFun();
        }else{
            if(repType.equals("biller")){
                System.out.println("New Bill #-close");
                billDetails.add(view.AddBill(productList));
                showOptions();
            }else{
                repOptions= view.getRepresentiveOptions();
                RepFun();
            }

        }
    }

    public void addCustomer(Customer cust){
        customerList.add(cust);
    }
    public void addDealer(Dealer dealer){
        dealerList.add(dealer);
    }

    public List<Dealer> getDealerList(){
        return dealerList;
    }
    public List<Representative> getRepList(){
        return repList;
    }
    public List<Customer> getCustomerList(){
        return customerList;
    }
    public List<ProductStock> getProductStockList(){
        return productStockList;
    }
    public List<Product> getProductList(){
        return productList;
    }

    public void setProductStockList(List<ProductStock> productStocks){
        SuperMarketController.productStockList=productStocks;
    }
    public void setProductList(List<Product> productList){
        SuperMarketController.productList=productList;
    }

    public void adminFun(){
        switch (adminOptions){
            case 1:
                repList.add(view.addRepresentatives());
                showOptions();
                break;
            case 2:
                updateRepDetailsOptions();
                showUpdateRepOprions();
                break;
            case 3:
                Customer customer=view.addCustomer();
                customerList.add(customer);
                showOptions();
                break;
            case 4:
                Dealer dealer=view.addDealer();
                dealerList.add(dealer);
                showOptions();
                break;
            case 5:
                view.Cust_Details(customerList);
                showOptions();
                break;
            case 6:
                view.Rep_Details(repList);
                showOptions();
                break;
            case 7:
                view.dealerDetails(dealerList);
                showOptions();
                break;
            case 8:
                view.billDetails(billDetails);
                showOptions();
                break;
            case 9:
                view.purchaseDetails(purchases);
                showOptions();
                break;
            case 10:
                login();
                break;
            default:
                System.out.println("enter the correct option");
                showOptions();
                break;
        }
    }
    public void RepFun(){
        switch (repOptions){
            case 1:
                updateProductOptions();
                showUpdateOptions();
                break;
            case 2:
                purchases.add(view.AddPurchase(productList));
                showOptions();
                break;
            case 3:
                productList.add(view.addProduct());
                showOptions();
                break;
            case 4:
                view.productDetails(productList);
                showOptions();
                break;
            case 5:
                view.stockDetails(productStockList);
                showOptions();
                break;
            case 6:
                login();
                break;
            default:
                System.out.println("Enter the correct options");
                showOptions();
                break;
        }
    }

    public void updateRepDetailsOptions(){
        updateRepOptions=view.updateRepOption();
    }
    public void updateProductOptions(){
        updateProductOptions=view.updateProductOption();
    }

    public void setRepList(List<Representative> repList) {
        SuperMarketController.repList = repList;
    }

    public void showUpdateRepOprions(){
        switch (updateRepOptions){
            case 1:
                view.updateRepSalary();
                showOptions();
                break;
            case 2:
                view.updateRepPhone();
                showOptions();
                break;
            case 3:
                view.updateRepType();
                showOptions();
                break;
            case 4:
                showOptions();
                break;
            default:
                System.out.println("Please Enter the Correct Options");
                showOptions();
                break;
        }
    }
    public void showUpdateOptions() {
        switch (updateProductOptions){
            case 1:
                view.updateMRP();
                showOptions();
                break;
            case 2:
                view.updateSaleRate();
                showOptions();
                break;
            case 3:
                view.updateNetRate();
                showOptions();
                break;
            case 4:
                showOptions();
                break;
            default:
                System.out.println("Please Enter the Correct Option");
                showOptions();
                break;
        }
    }
}

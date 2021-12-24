

import DAO.*;
import Modal.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuperMarketView {
    Scanner sc=new Scanner(System.in);
    CustomerDAO custDAO=new CustomerDAO();
    RepresentiveDAO repDAO=new RepresentiveDAO();
    ProductDAO proDAO=new ProductDAO();
    BillDAO billDAO=new BillDAO();
    OrderDAO orderDAO=new OrderDAO();
    DealerDAO dealerDAO=new DealerDAO();
    StockDAO stockDAO=new StockDAO();

    ProductStock productStock=new ProductStock();
    Product product=new Product();
    Representative rep=new Representative();

    List<product_sales> product_sales =new ArrayList<product_sales>();
    SuperMarketController controller=new SuperMarketController(this);
    List<product_purchase> product_purchases=new ArrayList<>();


    public int getUserType(){
        System.out.println("Select the user");
        System.out.println("1.Admin\n2.Representative\n3.Exit");
        int ch=sc.nextInt();
        return ch;
    }


    public boolean verifyAdmin(){
        boolean flag=true;
        while (flag){
            System.out.println("Enter Admin Id");
            int id=sc.nextInt();
            if(id==1){
                System.out.println("Enter Passcode");
                String passcode=sc.next();
                if(passcode.equals("1234")){
                    return true;
                }else{
                    System.out.println("Password Not correct");
                }
            }else{
                System.out.println("Invalid Id");
            }
        }

        return true;
    }
    public String verifyRep(List<Representative> representatives){

        String repType="";

        boolean flag=true;
        while(flag){

            System.out.println();
            System.out.println("Enter the Representative ID");
            int repId=sc.nextInt();
            System.out.println("Enter the Passcode");
            String password=sc.next();
            for (Representative rep :
                    representatives) {
                String pass=rep.getPasscode();
                if (repId == rep.getRep_id()) {
                    if(password.equals(pass)){
                        repType=rep.getRep_type();
                        flag=false;
                        return repType;
                    }else{
                        System.out.println("Passcode not correct! \nPlease Enter correct passcode");
                        break;
                    }
                }
            }
        }
        return repType;
    }


    public int getAdminOptions(){
        System.out.println("Select your option");
        System.out.println("1.Add representative");
        System.out.println("2.Modify Representative Details\n3.Add Customer Details\n4.Add Dealer\n5.View Customer Details\n6.View Representative Details\n7.View Dealer Details\n8.View Bill Details\n9.View Purchase Details\n10.Exit");
        int ch=sc.nextInt();
        return ch;
    }
    public int getRepresentiveOptions(){
        System.out.println("Select your option\n1.Product Update\n2.Purchase stock\n3.Add product\n4.View Products\n5.View Stock\n6.Exit");
        int ch=sc.nextInt();
        return ch;
    }
    public int updateRepOption(){
        int option;
        System.out.println("1.Update Salary\n2.Update Mobile Number\n3.Update Designation\n4.Exit");
        option=sc.nextInt();
        return option;
    }
    public int updateProductOption(){
        int option;
        System.out.println("1.Update MRP\n2.Update Sale Rate\n3.Update NetRate\n4.Exit");
        option=sc.nextInt();
        return option;
    }


    public Customer addCustomer(){
        custDAO.connect();
        System.out.println("Enter the customer name");
        String cust_name=sc.next();
        System.out.println("Enter the customer email");
        String cust_email=sc.next();
        System.out.println("Enter the customer phone no");
        String cust_phone=sc.next();
        int result=custDAO.insertData(cust_name,cust_email,cust_phone);
        System.out.println("The Customer id is: "+result);
        Customer customer=new Customer(result,cust_name,cust_email,cust_phone);
        custDAO.conClose();
        return customer;
    }
    public Product addProduct(){
        proDAO.connect();
        System.out.println("Enter the product name");
        String product_name=sc.next();
        System.out.println("Enter the MRP");
        float product_mrp=sc.nextFloat();
        System.out.println("Enter the Sale Rate");
        float product_srate=sc.nextFloat();
        System.out.println("Enter the Net Rate");
        float product_netrate=sc.nextFloat();
        System.out.println("Enter Available stock");
        int available=sc.nextInt();
        int result=proDAO.insertData(product_name,product_mrp,product_srate,product_netrate,available);
        System.out.println("Product Id is:"+result);
        Product product=new Product(result,product_name,product_mrp,product_srate,product_netrate);
        proDAO.conClose();
        StockDAO stockDAO=new StockDAO();
        stockDAO.connect();
        java.util.Date date=new java.util.Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy.MM.dd");
        String stockDate = ft.format(date).toString();
        stockDAO.insertData(result,stockDate,0,available);
        stockDAO.conClose();
        ProductStock productStock=new ProductStock(result,0,available,stockDate);
        List<ProductStock> productStocks=controller.getProductStockList();
        productStocks.add(productStock);
        return product;
    }
    public Representative addRepresentatives(){
        System.out.println("Enter the name");
        String rep_name=sc.next();
        System.out.println("Enter the Age");
        int rep_age=sc.nextInt();
        System.out.println("Enter Email id");
        String rep_email=sc.next();
        System.out.println("Enter Phone number");
        String rep_phoneno=sc.next();
        System.out.println("Enter salary");
        int salary=sc.nextInt();
        System.out.println("Enter the Representative type\nbiller\nprod_manage");
        String rep_type=sc.next();
        System.out.println("Enter the Representative passcode");
        String passcode=sc.next();
        repDAO.connect();
        int result=repDAO.insertData(rep_name,rep_age,rep_email,rep_phoneno,salary,rep_type,passcode);
        System.out.println("The Representative id is: "+result);
        Representative representative=new Representative(result,rep_name,rep_age,salary,rep_phoneno,rep_email,rep_type,passcode);
        repDAO.conClose();
        return representative;
    }
    public Dealer addDealer(){
        System.out.println("Enter the Dealer Name");
        String dealer_name=sc.next();
        System.out.println("Enter the Dealer Email");
        String dealer_email=sc.next();
        System.out.println("Enter the Dealer Phone No");
        String dealer_phoneno=sc.next();
        dealerDAO.connect();
        int id=dealerDAO.insertData(dealer_name,dealer_email,dealer_phoneno);
        System.out.println("Dealer id is:"+id);
        Dealer dealer=new Dealer(id,dealer_name,dealer_email,dealer_phoneno);
        dealerDAO.conClose();
        return dealer;
    }
    public BillDetail AddBill(List<Product> products){
        float amount;
        boolean flag=true;
        boolean isFind=false;
        boolean exit=false;
        BillDetail billDetail=new BillDetail();
        float tot_amount=0;
        int product_id=0;
        String op="";
        System.out.println();
        while (flag){
            System.out.println("Enter the product id ");
            op=sc.next();
            if(op.equals("#")){
                exit=true;
                break;
            }else{
                product_id=Integer.parseInt(op);
            }
            for (Product product :
                    products) {
                if (product.getProduct_id() == product_id) {
                    System.out.println("Enter the quantity");
                    int quantity=sc.nextInt();
                    amount=product.getProduct_srate()*quantity;
                    tot_amount+=amount;
                    product_sales product_p=new product_sales();
                    product_p.setProduct_id(product_id);
                    product_p.setProduct_name(product.getProduct_name());
                    product_p.setQuantity(quantity);
                    product_p.setAmount(amount);
                    product_sales.add(product_p);
                    isFind=true;
                    break;
                }
            }
            if(!isFind){
                System.out.println("Invalid Product id...");
            }else{
                System.out.println("1.Add\n2.Print Bill");
                int ch=sc.nextInt();
                if(ch==1){
                    isFind=false;
                    continue;
                }else{
                    flag=false;
                }
            }
        }
        if(exit){
            controller.login();
        }else{
            billDetail=generateBill(tot_amount);
        }
        return billDetail;
    }
    public Purchase AddPurchase(List<Product> products){
        Purchase purchase=new Purchase();
        float net_rate;
        float product_amount;
        boolean flag=true;
        boolean isFind=false;
        boolean exit=false;
        float tot_amount=0;
        int product_id=0;
        String op="";
        System.out.println("Enter the Dealer id");
        int dealer_id=sc.nextInt();
        if(isDealer(dealer_id)){

        }else{
            Dealer dealer=addDealer();
            dealer_id=dealer.getDealer_id();
            controller.addDealer(dealer);
        }

        System.out.println("Enter the products  #-to finish");
        System.out.println();
        while (flag){
            System.out.println("Enter the product id ");
            op=sc.next();
            if(op.equals("#")){
                exit=true;
                break;
            }else{
                product_id=Integer.parseInt(op);
            }
            for (Product product :
                    products) {
                if (product.getProduct_id() == product_id) {
                    System.out.println("Enter the quantity");
                    int quantity=sc.nextInt();
                    System.out.println("Enter the Net Rate");
                    net_rate=sc.nextFloat();
                    product_amount=quantity*net_rate;
                    tot_amount+=product_amount;
                    product_purchase product_p=new product_purchase();
                    product_p.setProduct_id(product_id);
                    product_p.setNetrate(net_rate);
                    product_p.setAmount(product_amount);
                    product_p.setQuantity(quantity);
                    product_purchases.add(product_p);
                    isFind=true;
                    break;
                }
            }
            if(!isFind){
                System.out.println("Invalid Product id...\n" +
                        "1.add product\n2.enter product id");
                int ch=sc.nextInt();
                if(ch==1){
                    products.add(addProduct());

                }else{
                    continue;
                }
            }else{
                System.out.println("1.Add\n2.Finish Purchase bill");
                int ch=sc.nextInt();
                if(ch==1){
                    isFind=false;
                    continue;
                }else{
                    flag=false;
                }
            }
        }
        if(exit){
            controller.login();
        }else{
            PurchaseDAO purchaseDAO=new PurchaseDAO();
            purchaseDAO.connect();
            int purchase_id=purchaseDAO.insertData(dealer_id,tot_amount);
            System.out.println("Purchase Id is:"+purchase_id);
            purchaseDAO.conClose();
            Product_PurchaseDAO product_purchaseDAO=new Product_PurchaseDAO();
            product_purchaseDAO.connect();
            product_purchaseDAO.insertData(purchase_id,dealer_id,product_purchases);
            product_purchaseDAO.conClose();
            stockDAO.connect();
            stockDAO.updateStock(product_purchases);
            stockDAO.conClose();
            proDAO.connect();
            proDAO.updateStock(product_purchases);
            product_purchases.clear();
            controller.setProductStockList(productStock.getStockList());
            controller.setProductList(product.getProductList());
        }
        return purchase;
    }


    public BillDetail generateBill(float tot_amount){
        int cust_id;
        System.out.println("Enter Customer id");
        cust_id=sc.nextInt();
        if(isCustomer(cust_id)){

        }else{
            Customer cust=addCustomer();
            controller.addCustomer(cust);
            cust_id=cust.getCust_id();
        }
        System.out.println("Enter the payment mode\n1.Google Pay\n2.Cash");
        int payment_mode=sc.nextInt();
        System.out.println("Enter the discount");
        float discount=sc.nextFloat();
        float discountAmount=tot_amount*(discount/100);
//        tot_amount=tot_amount-discountAmount;
        billDAO.connect();
        int bill_id=billDAO.insertData(cust_id,tot_amount-discountAmount,payment_mode,discount);
        billDAO.conClose();
        java.util.Date date=new java.util.Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy.MM.dd");
        String bill_date = ft.format(date).toString();
        BillDetail billDetail=new BillDetail(bill_id,bill_date,cust_id,tot_amount-discountAmount,payment_mode,discount);
        orderDAO.connect();
        orderDAO.insertData(bill_id,cust_id, product_sales);
        orderDAO.conClose();
        stockDAO.connect();
        stockDAO.updatestock(product_sales);
        stockDAO.conClose();
        proDAO.connect();
        proDAO.updatestock(product_sales);
        proDAO.conClose();
        controller.setProductStockList(productStock.getStockList());
        controller.setProductList(product.getProductList());
        System.out.println("_______Super Market_______");
        System.out.println("Bill_id:"+bill_id);
        System.out.println("Customer Id:"+cust_id);
        System.out.println(String.format("%30s %25s %10s %25s %10s", "Item", "|", "Qty", "|", "Price($)"));
        for (product_sales p :
                product_sales) {
            System.out.println(String.format("%30s %25s %10d %25s %10.2f", p.getProduct_name(), "|", p.getQuantity(), "|", p.getAmount()));
        }

        System.out.println("Discount:"+(int)discount+"%");
        System.out.println("Discount Amount:"+discountAmount);
        System.out.println("Total Amount:"+(tot_amount-(discountAmount)));
        System.out.println("Payment mode:"+payment_mode);
        System.out.println();
        product_sales.clear();
        return billDetail;
    }
    public boolean isCustomer(int id){
        List<Customer> Cust_Details=controller.getCustomerList();
        for (Customer cust :
                Cust_Details) {
            if(cust.getCust_id()==id){
                return true;
            }
        }
        return false;
    }
    public boolean isDealer(int id) {
        List<Dealer> dealerList = controller.getDealerList();
        for (Dealer dealer :
                dealerList) {
            if (dealer.getDealer_id() == id) {
                return true;
            }

        }
        return false;
    }
    public boolean isRep(int id){
        List<Representative> RepList=controller.getRepList();
        for (Representative rep :
                RepList) {

            if (id == rep.getRep_id()) {
                return true;
            }
            }
        return false;
    }
    public boolean isProduct(int id){
        List<Product> productList=controller.getProductList();
        for (Product product :
                productList) {
            if(id==product.getProduct_id()){
                return true;
            }
        }
        return false;
    }

    public void updateRepSalary() {
        System.out.println("Enter Representative Id");
        int repId=sc.nextInt();
        if(isRep(repId)){
            System.out.println("Enter the new Salary");
            int salary=sc.nextInt();
            RepresentiveDAO representiveDAO=new RepresentiveDAO();
            representiveDAO.connect();
            representiveDAO.updateSalary(repId,salary);
            representiveDAO.conClose();
            controller.setRepList(rep.getRepList());
            System.out.println("Successfully Updated");
        }
    }
    public void updateRepPhone(){
        System.out.println("Enter Representative Id");
        int repId=sc.nextInt();
        if(isRep(repId)){
            System.out.println("Enter the new Phone Number");
            String phone_no=sc.next();
            RepresentiveDAO representiveDAO=new RepresentiveDAO();
            representiveDAO.connect();
            representiveDAO.updatePhoneNumber(repId,phone_no);
            representiveDAO.conClose();
            controller.setRepList(rep.getRepList());
            System.out.println("Successfully Updated");
        }
    }
    public void updateRepType(){
        System.out.println("Enter Representative Id");
        int repId=sc.nextInt();
        if(isRep(repId)){
            System.out.println("Enter the new Designation\nbiller\nprod_manage");
            String type=sc.next();
            RepresentiveDAO representiveDAO=new RepresentiveDAO();
            representiveDAO.connect();
            representiveDAO.updateDesignation(repId,type);
            representiveDAO.conClose();
            controller.setRepList(rep.getRepList());
            System.out.println("Successfully Updated");
        }
    }
    public void updateMRP(){
        System.out.println("Enter Product Id");
        int product_Id=sc.nextInt();
        if(isProduct(product_Id)){
            System.out.println("Enter the MRP Price");
            float mrp=sc.nextFloat();
            proDAO.connect();
            proDAO.updateMrp(product_Id,mrp);
            proDAO.conClose();
            controller.setProductList(product.getProductList());
            System.out.println("Successfully Updated");
        }
    }
    public void updateSaleRate(){
        System.out.println("Enter Product Id");
        int product_Id=sc.nextInt();
        if(isProduct(product_Id)){
            System.out.println("Enter the Sale Rate");
            float srate=sc.nextFloat();
            proDAO.connect();
            proDAO.updateSaleRate(product_Id,srate);
            proDAO.conClose();
            controller.setProductList(product.getProductList());
            System.out.println("Successfully Updated");
        }
    }
    public void updateNetRate(){
        System.out.println("Enter Product Id");
        int product_Id=sc.nextInt();
        if(isProduct(product_Id)){
            System.out.println("Enter the Net rate");
            float netrate=sc.nextFloat();
            proDAO.connect();
            proDAO.updateNetrate(product_Id,netrate);
            proDAO.conClose();
            controller.setProductList(product.getProductList());
            System.out.println("Successfully Updated");
        }
    }

    public void Cust_Details(List<Customer> customerList){
        System.out.println("Customer Details");
        System.out.println(String.format("%15s %10s %10s %10s %18s %10s %10s", "ID", "|", "Name", "|", "Email","|","Phone Number"));
        for (Customer cust :
                customerList) {
            System.out.println(String.format("%15d %10s %-10s %10s %-18s %10s %10s", cust.getCust_id(), "|", cust.getCust_name(), "|", cust.getCust_email(),"|",cust.getCust_phone_no()));
        }
        System.out.println();
    }
    public void Rep_Details(List<Representative> representatives){
        System.out.println("Representative List");
        System.out.println(String.format("%8s %10s %10s %10s %8s %10s %18s %10s %10s %10s %8s %10s %11s %10s %8s", "ID", "|", "Name", "|", "Age","|","Email","|","Phone","|","Salary","|","Designation","|","Passcode"));
        for (Representative rep :
                representatives) {
            System.out.println(String.format("%8d %10s %10s %10s %8d %10s %18s %10s %10s %10s %8d %10s %11s %10s %8s", rep.getRep_id(), "|", rep.getRep_name(), "|", rep.getAge(),"|",rep.getRep_email(),"|",rep.getRep_phoneno(),"|",rep.getSalary(),"|",rep.getRep_type(),"|",rep.getPasscode()));
        }
        System.out.println();
    }
    public void dealerDetails(List<Dealer> dealerList){
        System.out.println("Dealer List");
        System.out.println(String.format("%15s %10s %10s %10s %18s %10s %10s", "ID", "|", "Name", "|", "Email","|","Phone Number"));
        for (Dealer dealer :
                dealerList) {
            System.out.println(String.format("%15d %10s %-10s %10s %-18s %10s %10s", dealer.getDealer_id(), "|", dealer.getDealer_name(), "|", dealer.getEmail(),"|",dealer.getPhone_no()));
        }
        System.out.println();
    }
    public void billDetails(List<BillDetail> billDetails){
        System.out.println("Bill Details");
        System.out.println(String.format("%8s %10s %10s %10s %10s %10s %18s %10s %14s %10s %8s ", "ID", "|", "CustomerId", "|", "Bill Date","|","Amount","|","Payment_modeID","|","Discount(%)"));
        for (BillDetail detail :
                billDetails) {
            System.out.println(String.format("%8d %10s %10d %10s %10s %10s %18.2f %10s %14d %10s %8.0f ", detail.getBill_id(), "|", detail.getCust_id(), "|", detail.getBill_date(),"|",detail.getTot_amount(),"|",detail.getPayment_mode_id(),"|",detail.getDiscount()));
        }
        System.out.println();
    }
    public void purchaseDetails(List<Purchase> purchases){
        System.out.println("Purchase Details");
        System.out.println(String.format("%15s %10s %10s %10s %18s %10s %10s", "ID", "|", "DealerId", "|", "Date","|","Amount"));
        for (Purchase purchase :
                purchases) {
            System.out.println(String.format("%15d %10s %10d %10s %18s %10s %10.0f", purchase.getPurchase_id(), "|", purchase.getDealer_id(), "|", purchase.getPurchase_date(),"|",purchase.getPurchase_amount()));
        }
    }
    public void productDetails(List<Product> productList){
        System.out.println("Product List");
        System.out.println(String.format("%8s %10s %28s %10s %10s %10s %18s %10s %14s %10s %8s ", "ID", "|", "Name", "|", "MRP","|","Sale_Rate","|","Net_Rate","|","Available"));
        for (Product detail :
                productList) {
            System.out.println(String.format("%8d %10s %28s %10s %10.0f %10s %18.2f %10s %14.2f %10s %8d ", detail.getProduct_id(), "|", detail.getProduct_name(), "|", detail.getProduct_mrp(),"|",detail.getProduct_srate(),"|",detail.getProduct_netrate(),"|",detail.getAvailable()));
        }
        System.out.println();
    }
    public void stockDetails(List<ProductStock> productStockList){
        System.out.println("Stock Details");
        System.out.println(String.format("%15s %10s %20s %10s %10s %10s %10s", "Product_ID", "|", "Update_Date", "|", "Sales","|","Available"));
        for (ProductStock details :
                productStockList) {
            System.out.println(String.format("%15d %10s %20s %10s %-10s %10s %10s", details.getProduct_id(), "|", details.getProductUpdateDate(), "|", details.getProduct_sales(),"|",details.getAvailable()));
        }
        System.out.println();
    }

}



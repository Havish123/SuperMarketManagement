package Modal;

public class PaymentMode {
    private int payment_mode_id;
    private String payment_mode;


    public void setPayment_mode_id(int payment_mode_id) {
        this.payment_mode_id = payment_mode_id;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public int getPayment_mode_id() {
        return payment_mode_id;
    }

    public String getPayment_mode() {
        return payment_mode;
    }
}

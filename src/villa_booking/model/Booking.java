package villabooking.model;

public class Booking {

    public int id;
    public int customer;
    public int roomType;
    public String checkinDate;   // Format: "YYYY-MM-DD hh:mm:ss"
    public String checkoutDate;
    public int price;
    public Integer voucher;      // nullable
    public int finalPrice;
    public PaymentStatus paymentStatus; // "waiting", "failed", "success"
    public boolean hasCheckedin;
    public boolean hasCheckedout;
}
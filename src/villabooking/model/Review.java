package villabooking.model;

public class Review {

    public int booking;      // Primary key, 1-to-1 with Booking
    public int star;
    public String title;
    public String content;
}

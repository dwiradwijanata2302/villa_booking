package villabooking.model;

public class RoomType {

    public int id;
    public int villa; // foreign key to Villa
    public String name;
    public int quantity;
    public int capacity;
    public int price;
    public BedSize bedSize;
    public boolean hasDesk;
    public boolean hasAc;
    public boolean hasTv;
    public boolean hasWifi;
    public boolean hasShower;
    public boolean hasHotwater;
    public boolean hasFridge;
}

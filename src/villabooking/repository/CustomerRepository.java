package villabooking.repository;

import java.sql.*;
import java.util.*;
import villabooking.config.Database;
import villabooking.model.*;

public class CustomerRepository {

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer c = new Customer();
                c.id = rs.getInt("id");
                c.name = rs.getString("name");
                c.email = rs.getString("email");
                c.phone = rs.getString("phone");
                customers.add(c);
            }
        }

        return customers;
    }

    public Customer getCustomerById(int id) throws SQLException {
        String query = "SELECT * FROM customers WHERE id = ?";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer();
                    c.id = rs.getInt("id");
                    c.name = rs.getString("name");
                    c.email = rs.getString("email");
                    c.phone = rs.getString("phone");
                    return c;
                }
            }
        }

        return null;
    }

    public List<Booking> getCustomerBookings(int customerId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE customer = ?";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Booking b = new Booking();
                    b.id = rs.getInt("id");
                    b.customer = rs.getInt("customer");
                    b.roomType = rs.getInt("room_type");
                    b.checkinDate = rs.getString("checkin_date");
                    b.checkoutDate = rs.getString("checkout_date");
                    b.price = rs.getInt("price");
                    b.voucher = rs.getInt("voucher");
                    b.finalPrice = rs.getInt("final_price");
                    try {
                        b.paymentStatus = PaymentStatus.valueOf(rs.getString("payment_status").toUpperCase());
                    } catch (IllegalArgumentException e) {
                        b.paymentStatus = PaymentStatus.WAITING; // Default fallback
                    }
                    b.hasCheckedin = rs.getBoolean("has_checkedin");
                    b.hasCheckedout = rs.getBoolean("has_checkedout");
                    bookings.add(b);
                }
            }
        }

        return bookings;
    }

    public List<Review> getCustomerReviews(int customerId) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String query = """
            SELECT r.* FROM reviews r
            JOIN bookings b ON r.booking = b.id
            WHERE b.customer = ?
        """;

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Review r = new Review();
                    r.booking = rs.getInt("booking");
                    r.star = rs.getInt("star");
                    r.title = rs.getString("title");
                    r.content = rs.getString("content");
                    reviews.add(r);
                }
            }
        }

        return reviews;
    }

    public static void insertCustomer(Customer c) throws SQLException {
        String query = "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, c.name);
            stmt.setString(2, c.email);
            stmt.setString(3, c.phone);
            stmt.executeUpdate();
        }
    }

    public static void updateCustomer(Customer c) throws SQLException {
        String query = "UPDATE customers SET name = ?, email = ?, phone = ? WHERE id = ?";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, c.name);
            stmt.setString(2, c.email);
            stmt.setString(3, c.phone);
            stmt.setInt(4, c.id);
            stmt.executeUpdate();
        }
    }

    public static void insertReview(Review review) throws SQLException {
        String query = "INSERT INTO reviews (booking, star, title, content) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setInt(1, review.booking);
            stmt.setInt(2, review.star);
            stmt.setString(3, review.title);
            stmt.setString(4, review.content);

            int rowsAffected = stmt.executeUpdate();

            // Debug: print hasil
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Review inserted for booking: " + review.booking);


        } catch (SQLException e) {
            System.err.println("Error inserting review: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}

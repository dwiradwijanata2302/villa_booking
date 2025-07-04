package villabooking.repository;

import villabooking.config.Database;
import villabooking.model.Booking;
import villabooking.model.Booking;
import villabooking.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {

    public void insertBooking(Booking b) throws SQLException {
        String query = "INSERT INTO bookings (customer, room_type, checkin_date, checkout_date, price, voucher, final_price, payment_status, has_checkedin, has_checkedout) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, b.customer);
            stmt.setInt(2, b.roomType);
            stmt.setString(3, b.checkinDate);

            stmt.setString(4, b.checkoutDate);
            stmt.setInt(5, b.price);
            stmt.setInt(6, b.voucher);

            stmt.setInt(7, b.finalPrice);
            stmt.setString(8, b.paymentStatus.name().toLowerCase());
            stmt.setBoolean(9, b.hasCheckedin);

            stmt.setBoolean(10, b.hasCheckedout);

            stmt.executeUpdate();
        }
    }

}

package villabooking.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import villabooking.config.Database;
import villabooking.model.*;

public class VillaRepository {

    public List<Villa> getAllVillas() throws SQLException {
        List<Villa> villas = new ArrayList<>();
        String query = "SELECT * FROM villas";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Villa villa = new Villa();
                villa.id = rs.getInt("id");
                villa.name = rs.getString("name");
                villa.description = rs.getString("description");
                villa.address = rs.getString("address");
                villas.add(villa);
            }
        }

        return villas;
    }

    public Villa getVillaById(int id) throws SQLException {
        String query = "SELECT * FROM villas WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Villa villa = new Villa();
                    villa.id = rs.getInt("id");
                    villa.name = rs.getString("name");
                    villa.description = rs.getString("description");
                    villa.address = rs.getString("address");
                    return villa;
                }
            }
        }

        return null;
    }

    public List<RoomType> getRoomsByVillaId(int villaId) throws SQLException {
        List<RoomType> rooms = new ArrayList<>();
        String query = "SELECT * FROM room_types WHERE villa = ?";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, villaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RoomType room = new RoomType();
                    room.id = rs.getInt("id");
                    room.villa = rs.getInt("villa");
                    room.name = rs.getString("name");
                    room.quantity = rs.getInt("quantity");
                    room.capacity = rs.getInt("capacity");
                    room.price = rs.getInt("price");

                    try {
                        room.bedSize = BedSize.valueOf(rs.getString("bed_size"));
                    } catch (IllegalArgumentException e) {
                        room.bedSize = BedSize.KING; // default fallback
                    }

                    room.hasDesk = rs.getBoolean("has_desk");
                    room.hasAc = rs.getBoolean("has_ac");
                    room.hasTv = rs.getBoolean("has_tv");
                    room.hasWifi = rs.getBoolean("has_wifi");
                    room.hasShower = rs.getBoolean("has_shower");
                    room.hasHotwater = rs.getBoolean("has_hotwater");
                    room.hasFridge = rs.getBoolean("has_fridge");
                    rooms.add(room);
                }
            }
        }

        return rooms;
    }

    public List<Booking> getVillaBookings(int villaId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = """
            SELECT b.* FROM bookings b
            JOIN room_types r ON b.room_type = r.id
            WHERE r.villa = ?
            """;

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, villaId);
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

    public List<Review> getVillaReviews(int villaId) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String query = """
            SELECT r.* FROM reviews r
            JOIN bookings b ON r.booking = b.id
            JOIN room_types rt ON b.room_type = rt.id
            WHERE rt.villa = ?
            """;

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, villaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Review rev = new Review();
                    rev.booking = rs.getInt("booking");
                    rev.star = rs.getInt("star");
                    rev.title = rs.getString("title");
                    rev.content = rs.getString("content");
                    reviews.add(rev);
                }
            }
        }

        return reviews;
    }

    public List<VillaAvailability> getAvailableVillas(String checkin, String checkout) throws SQLException {
        String query = """
SELECT 
    v.id AS villa_id,
    v.name AS villa_name,
    v.description AS villa_description,
    v.address AS villa_address,
    rt.id AS room_type_id,
    rt.name AS room_type_name,
    rt.quantity AS room_type_quantity,
    rt.price AS room_type_price,
    (rt.quantity - IFNULL(br.booked_count, 0)) AS available_rooms
FROM 
    villas v
JOIN 
    room_types rt ON v.id = rt.villa
LEFT JOIN (
    SELECT 
        CAST(b.room_type AS INTEGER) AS room_type_id,  -- 💡 penting!
        COUNT(*) AS booked_count
    FROM 
        bookings b
    WHERE 
        b.payment_status = 'success'
        AND (
            b.checkin_date < ? AND b.checkout_date > ?
        )
    GROUP BY 
        b.room_type
) AS br ON rt.id = br.room_type_id  -- 💡 harus cocok dengan alias!
WHERE 
    (rt.quantity - IFNULL(br.booked_count, 0)) > 0
ORDER BY 
    v.id, rt.id;
"""
                ;

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Bind 6 parameters (check-in/check-out pairs)
            stmt.setString(1, checkout);
            stmt.setString(2, checkin);

            ResultSet rs = stmt.executeQuery();
            List<VillaAvailability> results = new ArrayList<>();

            while (rs.next()) {
                VillaAvailability va = new VillaAvailability();
                va.villaId = rs.getInt("villa_id");
                va.villaName = rs.getString("villa_name");
                va.villaDescription = rs.getString("villa_description");
                va.villaAddress = rs.getString("villa_address");
                va.roomTypeId = rs.getInt("room_type_id");
                va.roomTypeName = rs.getString("room_type_name");
                va.roomTypeQuantity = rs.getInt("room_type_quantity");
                va.roomTypePrice = rs.getInt("room_type_price");
                va.availableRooms = rs.getInt("available_rooms");
                results.add(va);
            }

            return results;
        }
    }

    public void insertVilla(Villa villa) throws SQLException {
        String query = "INSERT INTO villas (name, description, address) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, villa.name);
            stmt.setString(2, villa.description);
            stmt.setString(3, villa.address);
            stmt.executeUpdate();
        }
    }

    public void insertRoomType(RoomType room) throws SQLException {
        String query = """
            INSERT INTO room_types (villa, name, quantity, capacity, price, bed_size,
            has_desk, has_ac, has_tv, has_wifi, has_shower, has_hotwater, has_fridge)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, room.villa);
            stmt.setString(2, room.name);
            stmt.setInt(3, room.quantity);
            stmt.setInt(4, room.capacity);
            stmt.setInt(5, room.price);
            stmt.setString(6, room.bedSize.name());
            stmt.setBoolean(7, room.hasDesk);
            stmt.setBoolean(8, room.hasAc);
            stmt.setBoolean(9, room.hasTv);
            stmt.setBoolean(10, room.hasWifi);
            stmt.setBoolean(11, room.hasShower);
            stmt.setBoolean(12, room.hasHotwater);
            stmt.setBoolean(13, room.hasFridge);
            stmt.executeUpdate();
        }
    }

    public void updateVilla(Villa villa) throws SQLException {
        String query = "UPDATE villas SET name = ?, description = ?, address = ? WHERE id = ?";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, villa.name);
            stmt.setString(2, villa.description);
            stmt.setString(3, villa.address);
            stmt.setInt(4, villa.id);
            stmt.executeUpdate();
        }
    }

    public void updateRoomType(RoomType room) throws SQLException {
        String query = """
            UPDATE room_types SET name=?, quantity=?, capacity=?, price=?, bed_size=?,
            has_desk=?, has_ac=?, has_tv=?, has_wifi=?, has_shower=?, has_hotwater=?, has_fridge=?
            WHERE id=? AND villa=?
            """;

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, room.name);
            stmt.setInt(2, room.quantity);
            stmt.setInt(3, room.capacity);
            stmt.setInt(4, room.price);
            stmt.setString(5, room.bedSize.name());
            stmt.setBoolean(6, room.hasDesk);
            stmt.setBoolean(7, room.hasAc);
            stmt.setBoolean(8, room.hasTv);
            stmt.setBoolean(9, room.hasWifi);
            stmt.setBoolean(10, room.hasShower);
            stmt.setBoolean(11, room.hasHotwater);
            stmt.setBoolean(12, room.hasFridge);
            stmt.setInt(13, room.id);
            stmt.setInt(14, room.villa);
            stmt.executeUpdate();
        }
    }

    public void deleteRoomType(int roomId) throws SQLException {
        String query = "DELETE FROM room_types WHERE id = ?";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, roomId);
            stmt.executeUpdate();
        }
    }

    public void deleteVilla(int id) throws SQLException {
        String query = "DELETE FROM villas WHERE id = ?";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

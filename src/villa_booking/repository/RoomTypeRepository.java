package villabooking.repository;

import villabooking.config.Database;
import villabooking.model.RoomType;
import villabooking.model.BedSize;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeRepository {

    /**
     * Mengambil semua tipe kamar dari tabel room_types.
     */
    public List<RoomType> getAllRoomTypes() throws SQLException {
        List<RoomType> roomTypes = new ArrayList<>();
        String query = "SELECT * FROM room_types";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                RoomType room = new RoomType();
                room.id = rs.getInt("id");
                room.villa = rs.getInt("villa");
                room.name = rs.getString("name");
                room.quantity = rs.getInt("quantity");
                room.capacity = rs.getInt("capacity");
                room.price = rs.getInt("price");
                try {
                    room.bedSize = BedSize.valueOf(rs.getString("bed_size").toUpperCase());
                } catch (IllegalArgumentException e) {
                    room.bedSize = BedSize.KING; // Default fallback
                }
                room.hasDesk = rs.getBoolean("has_desk");
                room.hasAc = rs.getBoolean("has_ac");
                room.hasTv = rs.getBoolean("has_tv");
                room.hasWifi = rs.getBoolean("has_wifi");
                room.hasShower = rs.getBoolean("has_shower");
                room.hasHotwater = rs.getBoolean("has_hotwater");
                room.hasFridge = rs.getBoolean("has_fridge");
                roomTypes.add(room);
            }
        }
        return roomTypes;
    }

    /**
     * Mengambil tipe kamar berdasarkan ID.
     */
    public RoomType getRoomTypeById(int id) throws SQLException {
        String query = "SELECT * FROM room_types WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    RoomType room = new RoomType();
                    room.id = rs.getInt("id");
                    room.villa = rs.getInt("villa");
                    room.name = rs.getString("name");
                    room.quantity = rs.getInt("quantity");
                    room.capacity = rs.getInt("capacity");
                    room.price = rs.getInt("price");
                    try {
                        room.bedSize = BedSize.valueOf(rs.getString("bed_size").toUpperCase());
                    } catch (IllegalArgumentException e) {
                        room.bedSize = BedSize.KING; // Default fallback
                    }
                    room.hasDesk = rs.getBoolean("has_desk");
                    room.hasAc = rs.getBoolean("has_ac");
                    room.hasTv = rs.getBoolean("has_tv");
                    room.hasWifi = rs.getBoolean("has_wifi");
                    room.hasShower = rs.getBoolean("has_shower");
                    room.hasHotwater = rs.getBoolean("has_hotwater");
                    room.hasFridge = rs.getBoolean("has_fridge");
                    return room;
                }
            }
        }
        return null;
    }

    /**
     * Mengambil semua tipe kamar berdasarkan ID vila.
     */
    public List<RoomType> getRoomTypesByVillaId(int villaId) throws SQLException {
        List<RoomType> roomTypes = new ArrayList<>();
        String query = "SELECT * FROM room_types WHERE villa = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
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
                        room.bedSize = BedSize.valueOf(rs.getString("bed_size").toUpperCase());
                    } catch (IllegalArgumentException e) {
                        room.bedSize = BedSize.KING; // Default fallback
                    }
                    room.hasDesk = rs.getBoolean("has_desk");
                    room.hasAc = rs.getBoolean("has_ac");
                    room.hasTv = rs.getBoolean("has_tv");
                    room.hasWifi = rs.getBoolean("has_wifi");
                    room.hasShower = rs.getBoolean("has_shower");
                    room.hasHotwater = rs.getBoolean("has_hotwater");
                    room.hasFridge = rs.getBoolean("has_fridge");
                    roomTypes.add(room);
                }
            }
        }
        return roomTypes;
    }

    /**
     * Menambahkan tipe kamar baru ke tabel room_types.
     */
    public void insertRoomType(RoomType room) throws SQLException {
        // Validasi apakah villa ada
        String villaCheckQuery = "SELECT id FROM villas WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(villaCheckQuery)) {
            stmt.setInt(1, room.villa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Villa ID " + room.villa + " not found");
                }
            }
        }

        String query = """
            INSERT INTO room_types (villa, name, quantity, capacity, price, bed_size,
            has_desk, has_ac, has_tv, has_wifi, has_shower, has_hotwater, has_fridge)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, room.villa);
            stmt.setString(2, room.name);
            stmt.setInt(3, room.quantity);
            stmt.setInt(4, room.capacity);
            stmt.setInt(5, room.price);
            stmt.setString(6, room.bedSize.name().toLowerCase());
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

    /**
     * Memperbarui tipe kamar berdasarkan ID.
     */
    public void updateRoomType(RoomType room) throws SQLException {
        // Validasi apakah villa ada
        String villaCheckQuery = "SELECT id FROM villas WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(villaCheckQuery)) {
            stmt.setInt(1, room.villa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Villa ID " + room.villa + " not found");
                }
            }
        }

        String query = """
            UPDATE room_types SET villa = ?, name = ?, quantity = ?, capacity = ?, price = ?, bed_size = ?,
            has_desk = ?, has_ac = ?, has_tv = ?, has_wifi = ?, has_shower = ?, has_hotwater = ?, has_fridge = ?
            WHERE id = ?
            """;
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, room.villa);
            stmt.setString(2, room.name);
            stmt.setInt(3, room.quantity);
            stmt.setInt(4, room.capacity);
            stmt.setInt(5, room.price);
            stmt.setString(6, room.bedSize.name().toLowerCase());
            stmt.setBoolean(7, room.hasDesk);
            stmt.setBoolean(8, room.hasAc);
            stmt.setBoolean(9, room.hasTv);
            stmt.setBoolean(10, room.hasWifi);
            stmt.setBoolean(11, room.hasShower);
            stmt.setBoolean(12, room.hasHotwater);
            stmt.setBoolean(13, room.hasFridge);
            stmt.setInt(14, room.id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Room type ID " + room.id + " not found");
            }
        }
    }

    /**
     * Menghapus tipe kamar berdasarkan ID.
     */
    public void deleteRoomType(int roomId) throws SQLException {
        String query = "DELETE FROM room_types WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Room type ID " + roomId + " not found");
            }
        }
    }
}

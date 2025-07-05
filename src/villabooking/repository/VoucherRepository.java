package villabooking.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import villabooking.config.Database;
import villabooking.model.Voucher;

public class VoucherRepository {

    public List<Voucher> getAllVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        String query = "SELECT * FROM vouchers";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Voucher v = new Voucher();
                v.id = rs.getInt("id");
                v.code = rs.getString("code");
                v.description = rs.getString("description");
                v.discount = rs.getDouble("discount");
                v.startDate = rs.getString("start_date");
                v.endDate = rs.getString("end_date");
                vouchers.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vouchers;
    }

    public Voucher getVoucherById(int id) {
        String query = "SELECT * FROM vouchers WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Voucher v = new Voucher();
                    v.id = rs.getInt("id");
                    v.code = rs.getString("code");
                    v.description = rs.getString("description");
                    v.discount = rs.getDouble("discount");
                    v.startDate = rs.getString("start_date");
                    v.endDate = rs.getString("end_date");
                    return v;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertVoucher(Voucher v) throws SQLException{
        String query = "INSERT INTO vouchers (code, description, discount, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, v.code);
            stmt.setString(2, v.description);
            stmt.setDouble(3, v.discount);
            stmt.setString(4, v.startDate);
            stmt.setString(5, v.endDate);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean updateVoucher(Voucher v) {
        String query = "UPDATE vouchers SET code=?, description=?, discount=?, start_date=?, end_date=? WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, v.code);
            stmt.setString(2, v.description);
            stmt.setDouble(3, v.discount);
            stmt.setString(4, v.startDate);
            stmt.setString(5, v.endDate);
            stmt.setInt(6, v.id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVoucher(int id) {
        String query = "DELETE FROM vouchers WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

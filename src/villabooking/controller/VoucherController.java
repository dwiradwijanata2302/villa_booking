package villabooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import villabooking.http.Request;
import villabooking.http.Response;
import villabooking.model.Voucher;
import villabooking.repository.VoucherRepository;

import java.util.List;
import java.util.Map;

public class VoucherController {

    private final VoucherRepository repository = new VoucherRepository();

    public void getAll(Request req, Response res) {
        try {
            List<Voucher> vouchers = repository.getAllVouchers();
            String json = new ObjectMapper().writeValueAsString(vouchers);
            res.setBody(json);
            res.send(200);
        } catch (Exception e) {
            res.setBody("{\"error\":\"Internal server error\"}");
            res.send(500);
        }
    }

    public void getById(Request req, Response res, int id) {
        try {
            Voucher voucher = repository.getVoucherById(id);
            if (voucher == null) {
                res.setBody("{\"error\":\"Voucher not found\"}");
                res.send(404);
                return;
            }
            String json = new ObjectMapper().writeValueAsString(voucher);
            res.setBody(json);
            res.send(200);
        } catch (Exception e) {
            res.setBody("{\"error\":\"Internal server error\"}");
            res.send(500);
        }
    }

    public void create(Request req, Response res) {
        try {
            Map<String, Object> body = req.getJSON();
            Voucher v = new Voucher();
            v.code = (String) body.get("code");
            v.description = (String) body.get("description");
            v.discount = ((Number) body.get("discount")).doubleValue();
            v.startDate = (String) body.get("start_date");
            v.endDate = (String) body.get("end_date");

            repository.insertVoucher(v);
            res.setBody("{\"message\":\"Voucher created\"}");
            res.send(201);
        } catch (ClassCastException | NullPointerException e) {
            res.setBody("{\"error\":\"Invalid input data\"}");
            res.send(400);
        } catch (Exception e) {
            res.setBody("{\"error\":\"Unexpected error occurred\"}");
            res.send(500);
        }
    }

    public void update(Request req, Response res, int id) {
        try {
            Map<String, Object> body = req.getJSON();
            Voucher v = new Voucher();
            v.id = id;
            v.code = (String) body.get("code");
            v.description = (String) body.get("description");
            v.discount = ((Number) body.get("discount")).doubleValue();
            v.startDate = (String) body.get("start_date");
            v.endDate = (String) body.get("end_date");

            repository.updateVoucher(v);
            res.setBody("{\"message\":\"Voucher updated\"}");
            res.send(200);
        } catch (ClassCastException | NullPointerException e) {
            res.setBody("{\"error\":\"Invalid input data\"}");
            res.send(400);
        } catch (Exception e) {
            res.setBody("{\"error\":\"Unexpected error occurred\"}");
            res.send(500);
        }
    }

    public void delete(Request req, Response res, int id) {
        try {
            repository.deleteVoucher(id);
            res.setBody("{\"message\":\"Voucher deleted\"}");
            res.send(200);
        } catch (Exception e) {
            res.setBody("{\"error\":\"Unexpected error occurred\"}");
            res.send(500);
        }
    }
}

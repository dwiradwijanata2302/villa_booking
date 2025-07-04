package villabooking.router;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import villabooking.controller.CustomerController;
import villabooking.controller.VoucherController;
import villabooking.controller.VillaController;
import villabooking.http.Request;
import villabooking.http.Response;

import java.net.URI;

public class Router {

    private static final CustomerController customerController = new CustomerController();
    private static final VoucherController voucherController = new VoucherController();
    private static final VillaController villaController = new VillaController();

    public static void register(HttpServer server) {
        server.createContext("/", Router::handle);
    }

    public static void handle(HttpExchange exchange) {
        Request req = new Request(exchange);
        Response res = new Response(exchange);

        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String method = exchange.getRequestMethod();

        try {
            // ================= VILLAS =================
            if (method.equals("GET") && path.equals("/villas")) {
                villaController.getAll(req, res);
                return;
            } else if (method.equals("GET") && path.matches("/villas/\\d+")) {
                villaController.getById(req, res, extractId(path));
                return;
            } else if (method.equals("GET") && path.matches("/villas/\\d+/rooms")) {
                villaController.getRooms(req, res, extractId(path));
                return;
            } else if (method.equals("GET") && path.matches("/villas/\\d+/bookings")) {
                villaController.getBookings(req, res, extractId(path));
                return;
            } else if (method.equals("GET") && path.matches("/villas/\\d+/reviews")) {
                villaController.getReviews(req, res, extractId(path));
                return;
            } else if (method.equals("GET") && path.startsWith("/villas") && path.contains("/available")) {
                villaController.getAvailable(req, res);
                return;
            } else if (method.equals("POST") && path.equals("/villas")) {
                villaController.createVilla(req, res);
                return;
            } else if (method.equals("POST") && path.matches("/villas/\\d+/rooms")) {
                villaController.createRoom(req, res, extractId(path));
                return;
            } else if (method.equals("PUT") && path.matches("/villas/\\d+")) {
                villaController.updateVilla(req, res, extractId(path));
                return;
            } else if (method.equals("PUT") && path.matches("/villas/\\d+/rooms/\\d+")) {
                String[] parts = path.split("/");
                villaController.updateRoom(req, res, Integer.parseInt(parts[2]), Integer.parseInt(parts[4]));
                return;
            } else if (method.equals("DELETE") && path.matches("/villas/\\d+")) {
                villaController.deleteVilla(req, res, extractId(path));
                return;
            } else if (method.equals("DELETE") && path.matches("/villas/\\d+/rooms/\\d+")) {
                String[] parts = path.split("/");
                villaController.deleteRoom(req, res, Integer.parseInt(parts[4]));
                return;
            } // ================= CUSTOMERS =================
            else if (method.equals("GET") && path.equals("/customers")) {
                customerController.getAll(req, res);
                return;
            } else if (method.equals("GET") && path.matches("/customers/\\d+")) {
                customerController.getById(req, res, extractId(path));
                return;
            } else if (method.equals("GET") && path.matches("/customers/\\d+/bookings")) {
                customerController.getBookings(req, res, extractId(path));
                return;
            } else if (method.equals("GET") && path.matches("/customers/\\d+/reviews")) {
                customerController.getReviews(req, res, extractId(path));
                return;
            } else if (method.equals("POST") && path.equals("/customers")) {
                customerController.createCustomer(req, res);
                return;
            } else if (method.equals("POST") && path.matches("/customers/\\d+/bookings")) {
                customerController.createBooking(req, res, extractId(path));
                return;
            } else if (method.equals("POST") && path.matches("/customers/\\d+/bookings/\\d+/reviews")) {
                String[] parts = path.split("/");
                customerController.createReview(req, res, Integer.parseInt(parts[4]));
                return;
            } else if (method.equals("PUT") && path.matches("/customers/\\d+")) {
                customerController.updateCustomer(req, res, extractId(path));
                return;
            } // ================= VOUCHERS =================
            else if (method.equals("GET") && path.equals("/vouchers")) {
                voucherController.getAll(req, res);
                return;
            } else if (method.equals("GET") && path.matches("/vouchers/\\d+")) {
                voucherController.getById(req, res, extractId(path));
                return;
            } else if (method.equals("POST") && path.equals("/vouchers")) {
                voucherController.create(req, res);
                return;
            } else if (method.equals("PUT") && path.matches("/vouchers/\\d+")) {
                voucherController.update(req, res, extractId(path));
                return;
            } else if (method.equals("DELETE") && path.matches("/vouchers/\\d+")) {
                voucherController.delete(req, res, extractId(path));
                return;
            } // ========== NOT FOUND ==========
            else {
                res.setBody("{\"error\": \"Not found\"}");
                res.send(404);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setBody("{\"error\": \"Internal server error\"}");
            res.send(500);
        }
    }

    private static int extractId(String path) {
        String[] parts = path.split("/");
        for (int i = parts.length - 1; i >= 0; i--) {
            if (parts[i].matches("\\d+")) {
                return Integer.parseInt(parts[i]);
            }
        }
        return -1;
    }
}

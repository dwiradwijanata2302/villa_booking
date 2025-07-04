package villabooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import villabooking.model.*;
import villabooking.repository.VillaRepository;
import villabooking.http.Request;
import villabooking.http.Response;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class  VillaController {

    private final VillaRepository repository = new VillaRepository();

    public void createVilla(Request req, Response res) {
        try {
            Map<String, Object> data = req.getJSON();
            Villa villa = new Villa();
            villa.name = (String) data.get("name");
            villa.description = (String) data.get("description");
            villa.address = (String) data.get("address");

            repository.insertVilla(villa);
            res.setBody("{\"message\": \"Villa created\"}");
            res.send(201);
        } catch (ClassCastException | NullPointerException e) {
            res.setBody("{\"error\": \"Invalid input format\"}");
            res.send(400);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage().replace("\"", "\\\"") + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void createRoom(Request req, Response res, int villaId) {
        try {
            Map<String, Object> data = req.getJSON();
            RoomType room = new RoomType();
            room.villa = villaId;
            room.name = (String) data.get("name");
            room.quantity = (int) data.get("quantity");
            room.capacity = (int) data.get("capacity");
            room.price = (int) data.get("price");
            room.bedSize = BedSize.fromString((String) data.get("bed_size"));
            room.hasDesk = (boolean) data.get("has_desk");
            room.hasAc = (boolean) data.get("has_ac");
            room.hasTv = (boolean) data.get("has_tv");
            room.hasWifi = (boolean) data.get("has_wifi");
            room.hasShower = (boolean) data.get("has_shower");
            room.hasHotwater = (boolean) data.get("has_hotwater");
            room.hasFridge = (boolean) data.get("has_fridge");

            repository.insertRoomType(room);
            res.setBody("{\"message\": \"Room type added\"}");
            res.send(201);
        } catch (ClassCastException | NullPointerException e) {
            res.setBody("{\"error\": \"Invalid input format\"}");
            res.send(400);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage().replace("\"", "\\\"") + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void updateVilla(Request req, Response res, int id) {
        try {
            Map<String, Object> data = req.getJSON();
            Villa villa = new Villa();
            villa.id = id;
            villa.name = (String) data.get("name");
            villa.description = (String) data.get("description");
            villa.address = (String) data.get("address");

            repository.updateVilla(villa);
            res.setBody("{\"message\": \"Villa updated\"}");
            res.send(200);
        } catch (ClassCastException | NullPointerException e) {
            res.setBody("{\"error\": \"Invalid input format\"}");
            res.send(400);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage().replace("\"", "\\\"") + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void updateRoom(Request req, Response res, int villaId, int roomId) {
        try {
            Map<String, Object> data = req.getJSON();
            RoomType room = new RoomType();
            room.id = roomId;
            room.villa = villaId;
            room.name = (String) data.get("name");
            room.quantity = (int) data.get("quantity");
            room.capacity = (int) data.get("capacity");
            room.price = (int) data.get("price");
            room.bedSize = BedSize.fromString((String) data.get("bed_size"));
            room.hasDesk = (boolean) data.get("has_desk");
            room.hasAc = (boolean) data.get("has_ac");
            room.hasTv = (boolean) data.get("has_tv");
            room.hasWifi = (boolean) data.get("has_wifi");
            room.hasShower = (boolean) data.get("has_shower");
            room.hasHotwater = (boolean) data.get("has_hotwater");
            room.hasFridge = (boolean) data.get("has_fridge");

            repository.updateRoomType(room);
            res.setBody("{\"message\": \"Room updated\"}");
            res.send(200);
        } catch (ClassCastException | NullPointerException e) {
            res.setBody("{\"error\": \"Invalid input format\"}");
            res.send(400);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage().replace("\"", "\\\"") + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void deleteRoom(Request req, Response res, int roomId) {
        try {
            repository.deleteRoomType(roomId);
            res.setBody("{\"message\": \"Room deleted\"}");
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage().replace("\"", "\\\"") + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void deleteVilla(Request req, Response res, int id) {
        try {
            repository.deleteVilla(id);
            res.setBody("{\"message\": \"Villa deleted\"}");
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage().replace("\"", "\\\"") + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void getAll(Request req, Response res) {
        try {
            List<Villa> villas = repository.getAllVillas();
            String json = new ObjectMapper().writeValueAsString(villas);
            res.setBody(json);
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void getById(Request req, Response res, int id) {
        try {
            Villa villa = repository.getVillaById(id);
            if (villa == null) {
                res.setBody("{\"error\": \"Villa not found\"}");
                res.send(404);
                return;
            }
            String json = new ObjectMapper().writeValueAsString(villa);
            res.setBody(json);
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void getRooms(Request req, Response res, int villaId) {
        try {
            List<RoomType> rooms = repository.getRoomsByVillaId(villaId);
            String json = new ObjectMapper().writeValueAsString(rooms);
            res.setBody(json);
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void getBookings(Request req, Response res, int villaId) {
        try {
            List<Booking> bookings = repository.getVillaBookings(villaId);
            String json = new ObjectMapper().writeValueAsString(bookings);
            res.setBody(json);
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void getReviews(Request req, Response res, int villaId) {
        try {
            List<Review> reviews = repository.getVillaReviews(villaId);
            String json = new ObjectMapper().writeValueAsString(reviews);
            res.setBody(json);
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void getAvailable(Request req, Response res) {
        try {
            String checkin = req.getQueryParam("checkin");
            String checkout = req.getQueryParam("checkout");
            List<VillaAvailability> villas = repository.getAvailableVillas(checkin, checkout);
            String json = new ObjectMapper().writeValueAsString(villas);
            res.setBody(json);
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error\"}");
            res.send(500);
        } catch (Exception e) {
            String errorMessage = e.toString();
            res.setBody("{\"error\": \"" + errorMessage.replace("\"", "\\\"") + "\"}");
            res.send(500);
        }
    }

}

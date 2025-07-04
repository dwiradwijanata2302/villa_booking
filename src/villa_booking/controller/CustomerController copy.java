package villabooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import villabooking.http.Request;
import villabooking.http.Response;
import villabooking.model.*;
import villabooking.repository.BookingRepository;
import villabooking.repository.CustomerRepository;
import villabooking.repository.RoomTypeRepository;
import villabooking.repository.VoucherRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CustomerController {

    private final CustomerRepository customerRepository = new CustomerRepository();
    private final RoomTypeRepository roomTypeRepository = new RoomTypeRepository();
    private final VoucherRepository voucherRepository = new VoucherRepository();
    private final BookingRepository bookingRepository = new BookingRepository();

    public void getAll(Request req, Response res) {
        try {
            List<Customer> customers = customerRepository.getAllCustomers();
            String json = new ObjectMapper().writeValueAsString(customers);
            res.setBody(json);
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage() + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Internal server error\"}");
            res.send(500);
        }
    }

    public void getById(Request req, Response res, int id) {
        try {
            Customer customer = customerRepository.getCustomerById(id);
            if (customer == null) {
                res.setBody("{\"error\": \"Customer not found\"}");
                res.send(404);
                return;
            }
            String json = new ObjectMapper().writeValueAsString(customer);
            res.setBody(json);
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage() + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Internal server error\"}");
            res.send(500);
        }
    }

    public void getBookings(Request req, Response res, int id) {
        try {
            List<Booking> bookings = customerRepository.getCustomerBookings(id);
            String json = new ObjectMapper().writeValueAsString(bookings);
            res.setBody(json);
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage() + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Internal server error\"}");
            res.send(500);
        }
    }

    public void getReviews(Request req, Response res, int id) {
        try {
            List<Review> reviews = customerRepository.getCustomerReviews(id);
            String json = new ObjectMapper().writeValueAsString(reviews);
            res.setBody(json);
            res.send(200);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage() + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Internal server error\"}");
            res.send(500);
        }
    }

    public void createCustomer(Request req, Response res) {
        try {
            Map<String, Object> body = req.getJSON();
            Customer customer = new Customer();
            customer.name = (String) body.get("name");
            customer.email = (String) body.get("email");
            customer.phone = (String) body.get("phone");
            customerRepository.insertCustomer(customer);
            res.setBody("{\"message\": \"Customer created\"}");
            res.send(201);
        } catch (ClassCastException | NullPointerException e) {
            res.setBody("{\"error\": \"Invalid input: " + e.getMessage() + "\"}");
            res.send(400);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage() + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void updateCustomer(Request req, Response res, int id) {
        try {
            Map<String, Object> body = req.getJSON();
            Customer customer = new Customer();
            customer.id = id;
            customer.name = (String) body.get("name");
            customer.email = (String) body.get("email");
            customer.phone = (String) body.get("phone");
            customerRepository.updateCustomer(customer);
            res.setBody("{\"message\": \"Customer updated\"}");
            res.send(200);
        } catch (ClassCastException | NullPointerException e) {
            res.setBody("{\"error\": \"Invalid input: " + e.getMessage() + "\"}");
            res.send(400);
        } catch (SQLException e) {
            res.setBody("{\"error\": \"Database error: " + e.getMessage() + "\"}");
            res.send(500);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }

    public void createBooking(Request req, Response res, int customerId) {
        try {
            Map<String, Object> body = req.getJSON();
            Booking booking = new Booking();
            booking.customer = customerId;
            booking.roomType = (int) body.get("room_type");
            booking.checkinDate = (String) body.get("checkin_date");
            booking.checkoutDate = (String) body.get("checkout_date");
            booking.voucher = (int) body.get("voucher");
            booking.paymentStatus = PaymentStatus.fromString((String) body.get("payment_status"));
            // TODO: add insertBooking in repository
            //get room type by id
            RoomType room = roomTypeRepository.getRoomTypeById(booking.roomType);
            if (room == null) {
                res.setBody("{\"error\": \"RoomType not found\"}");
                res.send(404);
                return;
            }
            //get voucher by id
            Voucher voucher = voucherRepository.getVoucherById(booking.voucher);
            if (voucher == null) {
                res.setBody("{\"error\": \"Voucher not found\"}");
                res.send(404);
                return;
            }

            //calculate final price
            double final_price = (double) room.price - ((double)room.price * voucher.discount);

            //insert data to booking
            booking.price = room.price;
            booking.finalPrice = (int)final_price;

            //check if checkin_date is today, value of has_checkedin = true
            String today = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE);
            String checkinDateOnly = booking.checkinDate.substring(0, 10);
            booking.hasCheckedin = checkinDateOnly.equals(today);
            booking.hasCheckedout = false;
            bookingRepository.insertBooking(booking);

            res.setBody("{\"message\": \"Booking created\"}");
            res.send(201);
        } catch (ClassCastException | NullPointerException e) {
            res.setBody("{\"error\": \"Invalid input: " + e.getMessage() + "\"}");
            res.send(400);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error" + e.getMessage()+ "\"}");
            res.send(500);
        }
    }

    public void createReview(Request req, Response res, int bookingId) {
        try {
            Map<String, Object> body = req.getJSON();
            Review review = new Review();
            review.booking = bookingId;
            review.star = (int) body.get("star");
            review.title = (String) body.get("title");
            review.content = (String) body.get("content");
            // TODO: add insertReview in repository
            res.setBody("{\"message\": \"Review submitted\"}");
            res.send(201);
        } catch (ClassCastException | NullPointerException e) {
            res.setBody("{\"error\": \"Invalid input: " + e.getMessage() + "\"}");
            res.send(400);
        } catch (Exception e) {
            res.setBody("{\"error\": \"Unexpected server error\"}");
            res.send(500);
        }
    }
}

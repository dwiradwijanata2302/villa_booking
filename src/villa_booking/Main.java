package villabooking;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpContext;
import villabooking.router.Router;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) {
        try {
            int port = 8080;
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("Server running at http://localhost:" + port);

            Router.register(server);

            server.setExecutor(null); // Use default executor
            server.start();
        } catch (IOException e) {
            System.err.println("Failed to start server:");
            e.printStackTrace();
        }
    }
}

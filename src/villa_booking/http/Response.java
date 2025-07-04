package villabooking.http;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Response {

    private final HttpExchange httpExchange;
    private final Headers headers;
    private final StringBuilder stringBuilder;
    private boolean isSent;

    public Response(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        this.headers = httpExchange.getResponseHeaders();
        this.stringBuilder = new StringBuilder();
        this.isSent = false;
    }

    public void setBody(String string) {
        stringBuilder.setLength(0);
        stringBuilder.append(string);
    }

    public void send(int status) {
        try {
            headers.add("Content-Type", "application/json; charset=utf-8");
            httpExchange.sendResponseHeaders(status, 0);

            String body = stringBuilder.toString();
            PrintStream out = new PrintStream(httpExchange.getResponseBody());
            out.write(body.getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (IOException ioe) {
            System.err.println("Problem encountered when sending response.");
            ioe.printStackTrace();
            return;
        } finally {
            httpExchange.close();
        }
        this.isSent = true;
    }

    public boolean isSent() {
        if (this.httpExchange.getResponseCode() != -1) {
            this.isSent = true;
        }
        return isSent;
    }
}

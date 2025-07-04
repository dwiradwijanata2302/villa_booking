package villabooking.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Request {

    private final HttpExchange httpExchange;
    private final Headers headers;
    private String rawBody;
    private String jsonBody;

    public Request(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        this.headers = httpExchange.getRequestHeaders();
    }

    public String getBody() {
        if (this.rawBody == null) {
            this.rawBody = new BufferedReader(
                    new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8)
            ).lines().collect(Collectors.joining("\n"));
        }
        return this.rawBody;
    }

    public String getRequestMethod() {
        return httpExchange.getRequestMethod();
    }

    public String getContentType() {
        return headers.getFirst("Content-Type");
    }

    public Map<String, Object> getJSON() throws JsonProcessingException {
        if (!"application/json".equalsIgnoreCase(getContentType())) {
            return null;
        }
        if (jsonBody == null) {
            jsonBody = this.getBody();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonBody, new TypeReference<>() {
        });
    }

    public String getQueryParam(String key) {
        String query = httpExchange.getRequestURI().getQuery();
        if (query == null) {
            return null;
        }
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length == 2 && pair[0].equals(key)) {
                return pair[1];
            }
        }
        return null;
    }

    public HttpExchange getHttpExchange() {
        return httpExchange;
    }
}

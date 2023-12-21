package com.kinsta.helloworld;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class App {

    public static void main(String[] args) throws Exception {
        Integer port = Integer.parseInt(
                Optional.ofNullable(System.getenv("PORT")).orElse("8080")
        );
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Get the path to the HTML file in the root folder
            Path filePath = Paths.get("index.html");

            if (Files.exists(filePath) && Files.isReadable(filePath)) {
                // Read the content of the HTML file
                byte[] fileContent = Files.readAllBytes(filePath);

                // Set the response headers
                t.sendResponseHeaders(200, fileContent.length);
                OutputStream os = t.getResponseBody();
                os.write(fileContent);
                os.close();
            } else {
                // If the HTML file is not found or not readable, send a 404 response
                String response = "Hello World";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

}


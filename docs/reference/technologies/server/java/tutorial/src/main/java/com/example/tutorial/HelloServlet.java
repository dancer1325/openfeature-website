package com.example.tutorial;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.OpenFeatureAPI;
import dev.openfeature.sdk.providers.memory.Flag;
import dev.openfeature.sdk.providers.memory.InMemoryProvider;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        // flags defined in memory
        Map<String, Flag<?>> myFlags = new HashMap<>();
        myFlags.put("v2_enabled", Flag.builder()
                .variant("on", true)
                .variant("off", false)
                .defaultVariant("on")
                .build());

        // configure a provider
        OpenFeatureAPI api = OpenFeatureAPI.getInstance();
        api.setProviderAndWait(new InMemoryProvider(myFlags));
        // async way to register the provider
//        api.setProvider(new InMemoryProvider(myFlags));

        // create a client
        Client client = api.getClient();

        // get a bool flag value
        boolean flagValue = client.getBooleanValue("v2_enabled", false);
        message = flagValue ? "Hello World! - v2_enabled" : "Bye World! - v2_enabled";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
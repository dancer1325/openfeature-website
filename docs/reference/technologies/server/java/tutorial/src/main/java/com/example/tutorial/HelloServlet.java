package com.example.tutorial;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dev.openfeature.contrib.hooks.otel.TracesHook;
import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.FlagEvaluationOptions;
import dev.openfeature.sdk.ImmutableContext;
import dev.openfeature.sdk.OpenFeatureAPI;
import dev.openfeature.sdk.Value;
import dev.openfeature.sdk.providers.memory.Flag;
import dev.openfeature.sdk.providers.memory.InMemoryProvider;
import io.opentelemetry.api.trace.TracerProvider;

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

        // set EvaluationContext
        Map<String, Value> apiAttrs = new HashMap<>();
        apiAttrs.put("region", new Value(System.getenv("us-east-1")));
        EvaluationContext apiCtx = new ImmutableContext(apiAttrs);


        OpenFeatureAPI api = OpenFeatureAPI.getInstance();
        // 1. configure a provider
        // 1.1 syn
        api.setProviderAndWait(new InMemoryProvider(myFlags));
        // 1.2 async
        // api.setProvider(new InMemoryProvider(myFlags));
        // 1.3 with an associated named client
        api.setProvider("clientForCache", new InMemoryProvider(myFlags));
        // 2. pas the EvaluationContext
        api.setEvaluationContext(apiCtx);
        // add a hook globally, to run on all evaluations
        api.addHooks(new TracesHook());

        // create a client
        Client client = api.getClient();
        // 1. set EvaluationContext
        client.setEvaluationContext(apiCtx);
        // add a hook on this client, to run on all evaluations made by this client
        client.addHooks(new TracesHook());
        // 2. namedClient
        Client clientNamed = api.getClient("clientForCache");

        // set a value to the invocation context
        // TODO: How to get session?
        /*Map<String, Value> requestAttrs = new HashMap<>();
        requestAttrs.put("email", new Value(Session.getAttribute("email")));
        requestAttrs.put("product", new Value("productId"));
        String targetingKey = session.getId();
        EvaluationContext reqCtx = new ImmutableContext(targetingKey, requestAttrs);
        */

        // get a bool flag value
        boolean flagValue = client.getBooleanValue("v2_enabled", false);
        // TODO: Once I get where session comes from
        //boolean flagValue = client.getBooleanValue("v2_enabled", false, reqCtx);
        // add hook at flag invocation level
        //boolean flagValue = client.getBooleanValue("v2_enabled", false, null,  FlagEvaluationOptions.builder().hook(new TracesHook()).build());
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
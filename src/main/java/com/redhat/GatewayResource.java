package com.redhat;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("/gateway")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "gateway", description = "API Gateway for Triviahhh")
public class GatewayResource {
    @RestClient
    MessageClient client;

    @GET
    @Path("/quotes")
    @Timeout(1000)
    @CircuitBreaker
    (
        requestVolumeThreshold = 4,
        failureRatio = 0.5,
        delay = 5000,
        successThreshold = 4
    )
    @Fallback(fallbackMethod = "QuotesFallbackMessage")
    public List<Quote> message() {
        return client.get("/quotes");
    }
    private List<Quote> QuotesFallbackMessage() {
        Quote q = new Quote();
        List<Quote> l = new ArrayList<Quote>();
        q.id=0;
        q.author="CIRCUIT_BREAKER";
        q.quotation="Quotes service is not responding; Circuit Breaker is open.";
        return l;
    }

    @GET
    @Path("/quotes/random")
    public Quote message() {
        return client.get("/quotes/random");
    }
}
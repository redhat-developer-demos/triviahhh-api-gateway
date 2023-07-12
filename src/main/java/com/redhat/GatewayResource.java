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
public class GatewayResource {
    @RestClient
    MessageClient client;

    @RestClient
    CustomerClient customerClient;

    @RestClient
    AlbumClient albumClient;

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
        return client.get();
    }

    private List<Quote> QuotesFallbackMessage() {
        Quote q = new Quote();
        List<Quote> l = new ArrayList<Quote>();
        q.quoteID=0;
        q.author="CIRCUIT_BREAKER";
        q.quotation="Quotes service is not responding; Circuit Breaker is open.";
        return l;
    }

    @GET
    @Path("/quotes/random")
    public Quote quote() {
        return client.getRandom();
    }

    @GET
    @Path("/albums")
    @Timeout(1000)
    @CircuitBreaker
    (
        requestVolumeThreshold = 4,
        failureRatio = 0.5,
        delay = 5000,
        successThreshold = 4
    )
    @Fallback(fallbackMethod = "AlbumsFallbackMessage")
    public List<Album> album() {
        return albumClient.get();
    }

    private List<Album> AlbumsFallbackMessage() {
        Album a = new Album();
        List<Album> l = new ArrayList<Album>();
        a.albumID=0;
        a.albumTitle = "Albums Service is unavailable; Circuit Breaker is open.";
        l.add(a);
        return l;
    }



    @GET
    @Path("/customers")
    @Timeout(1000)
    @CircuitBreaker
    (
        requestVolumeThreshold = 4,
        failureRatio = 0.5,
        delay = 5000,
        successThreshold = 4
    )
    @Fallback(fallbackMethod = "CustomersFallbackMessage")
    public List<Customer> customer() {
        return customerClient.get();
    }

    private List<Customer> CustomersFallbackMessage() {
        Customer c = new Customer();
        List<Customer> l = new ArrayList<Customer>();
        c.id=0;
        c.customername="Customers service is not responding; Circuit Breaker is open.";
        l.add(c);
        return l;
    }
}
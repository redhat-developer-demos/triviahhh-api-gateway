package com.redhat;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface MessageClient {

    @GET
    @Path("/quotes")
    List<Quote> get();

    @GET
    @Path("/quotes/random")
    Quote getRandom();
    
}

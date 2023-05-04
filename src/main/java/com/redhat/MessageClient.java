package com.redhat;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/quotes")
@RegisterRestClient
public interface MessageClient {

    @GET
    List<Quote> get();
    
}

@Path("quotes/random")
@RegisterRestClient
public interface MessageClient {
    @GET
    Quote get(/"random");
}

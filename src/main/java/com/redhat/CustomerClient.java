package com.redhat;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/customers")
@RegisterRestClient
public interface CustomerClient {

    @GET
    List<Customer> get();
}

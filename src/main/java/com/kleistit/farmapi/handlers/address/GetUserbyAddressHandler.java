package com.kleistit.farmapi.handlers.address;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.AddressService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class GetUserbyAddressHandler implements Handler {

    private AddressService addressService;

    @Inject
    public GetUserbyAddressHandler(AddressService addressService){
        this.addressService = addressService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        var username  = ctx.getRequest().getHeaders().get("email");

       var adr =  this.addressService.getAddreseByUser(username);
     ctx.render(json(adr.blockingIterable()));
    }
}

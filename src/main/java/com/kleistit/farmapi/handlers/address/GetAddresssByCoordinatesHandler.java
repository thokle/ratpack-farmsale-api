package com.kleistit.farmapi.handlers.address;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.AddressService;
import lombok.var;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;

import static ratpack.core.jackson.Jackson.json;

public class GetAddresssByCoordinatesHandler implements Handler {

    private AddressService addressService;

    @Inject
    public GetAddresssByCoordinatesHandler(AddressService addressService){
        this.addressService = addressService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
       var lattitude = Long.parseLong(ctx.getRequest().getQueryParams().get("lattitude"));
       var longitude = Long.parseLong(ctx.getRequest().getQueryParams().get("longitude"));

       addressService.getAddressesByCoordinates(lattitude, longitude).blockingForEach(address -> {
           ctx.render(json(address));
       });

    }
}

package com.kleistit.farmapi.handlers.address;

import com.google.inject.Inject;
import com.kleistit.farmapi.helperEntities.UserAddress;
import com.kleistit.farmapi.services.impl.AddressService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;
public class AddAddressHandler implements Handler
{
    private AddressService addressService;

    @Inject
    public AddAddressHandler(AddressService addressService){
        this.addressService = addressService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.parse(UserAddress.class).then(userAddress -> {

          var res =   this.addressService.addAddress(userAddress.getUser(), userAddress.getAddress());
            ctx.render(json(res.blockingGet()));
        });
    }
}

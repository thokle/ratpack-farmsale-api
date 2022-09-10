package com.kleistit.farmapi.chanis;

import com.kleistit.farmapi.handlers.address.AddAddressHandler;
import com.kleistit.farmapi.handlers.address.GetAddresssByCoordinatesHandler;
import com.kleistit.farmapi.handlers.address.GetUserbyAddressHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;


public class AddressChain implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {
        chain.post("addAddress", AddAddressHandler.class).get("{lat}/{long}", GetAddresssByCoordinatesHandler.class).get("user", GetUserbyAddressHandler.class);
    }
}

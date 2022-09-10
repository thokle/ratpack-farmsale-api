package com.kleistit.farmapi.chanis;

import com.kleistit.farmapi.handlers.groocery.*;
import com.kleistit.farmapi.handlers.saleplace.AllCloseSalePlaceHandler;
import com.kleistit.farmapi.handlers.saleplace.AllOpenSalePlaceHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

public class GrooceryChain implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {
        chain.post(AddGrooceryHandler.class)
                .get("byPlace", GetGroocriesBySalePlace.class)
                .get("byid/:id", GetGrooceryByIdHandler.class)
                .post("salelace", GetGroocriesBySalePlace.class)
                .get("soldout", SoldOutHandlerSocket.class)
                .get("allsoldout", SoldoutHandler.class);

    }
}

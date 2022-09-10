package com.kleistit.farmapi.chanis;

import com.kleistit.farmapi.handlers.saleplace.*;
import ratpack.func.Action;
import ratpack.handling.Chain;

public class SalePlaceChain implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {
      chain.post("AddSalePlace",AddSalePlaceHandler.class).get("saleplaces", GetSalePlacesByAddressHandler.class)
              .get("byId/:id", GetSalePlaceByIdHandler.class)
              .get("close/:id", CloseShopHandler.class)
              .get("open/:id", OpenSalePlaceHandler.class)
              .get("opensocket", SalePalceOpenHandlerSocket.class)
              .get("closesocket", CloseSalePlacesSocket.class)
              .get("allopen", AllOpenSalePlaceHandler.class)
              .get("allclose", AllCloseSalePlaceHandler.class)
              .get("addposition/:id/:lat/:lng", AddPositionToSalePlaceHandler.class);

    }
}

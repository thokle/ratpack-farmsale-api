package com.kleistit.farmapi.handlers.saleplace;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.SalePlaceService;
import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class CloseShopHandler implements Handler {

    private SalePlaceService salePlaceService;
    @Inject
    public CloseShopHandler(SalePlaceService salePlaceService){
        this.salePlaceService = salePlaceService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        var shopId =Long.parseLong(ctx.getPathTokens().get("id"));
      var res =   this.salePlaceService.closeSalePlace(shopId);
      ctx.render(json(res.blockingGet()));

    }
}

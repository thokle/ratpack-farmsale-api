package com.kleistit.farmapi.chanis;

import com.kleistit.farmapi.handlers.position.UpdatePositionHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

public class PositionChain implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
        chain.get("update/:id/:lat/:lng", UpdatePositionHandler.class);
    }
}

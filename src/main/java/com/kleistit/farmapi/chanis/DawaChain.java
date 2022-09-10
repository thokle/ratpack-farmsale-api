package com.kleistit.farmapi.chanis;

import com.kleistit.farmapi.handlers.dawa.DawaHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

public class DawaChain implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {
        chain.get(":postnr", DawaHandler.class);
    }
}

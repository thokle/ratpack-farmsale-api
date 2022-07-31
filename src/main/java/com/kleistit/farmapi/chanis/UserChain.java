package com.kleistit.farmapi.chanis;

import com.kleistit.farmapi.handlers.user.CreateUserHandler;
import ratpack.core.handling.Chain;
import ratpack.func.Action;

public class UserChain implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
        chain.post("creae", CreateUserHandler.class);
    }
}

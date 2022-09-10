package com.kleistit.farmapi.chanis;

import com.kleistit.farmapi.handlers.user.CreateUserHandler;
import com.kleistit.farmapi.handlers.user.DoesUserExcistHandler;
import com.kleistit.farmapi.handlers.user.LoginUserHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;
public class UserChain implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
        chain.post("create", CreateUserHandler.class).get("login", LoginUserHandler.class).get("does/:username", DoesUserExcistHandler.class);
    }
}

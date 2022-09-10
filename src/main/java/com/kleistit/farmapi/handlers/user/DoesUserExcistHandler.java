package com.kleistit.farmapi.handlers.user;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.IUserService;
import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class DoesUserExcistHandler implements Handler {
    private IUserService userService;
    @Inject
    public DoesUserExcistHandler(IUserService userService){
        this.userService = userService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        var username = ctx.getPathTokens().get("username");
        var aBoolean = this.userService.doesUserExcist(username).blockingGet();

        ctx.render(json(aBoolean));
    }
}

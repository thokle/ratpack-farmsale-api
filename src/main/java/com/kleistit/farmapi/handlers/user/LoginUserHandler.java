package com.kleistit.farmapi.handlers.user;

import com.google.inject.Inject;
import com.kleistit.farmapi.config.AEService;
import com.kleistit.farmapi.services.impl.UserService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class LoginUserHandler implements Handler
{
    private UserService userService;
    private  AEService aeService;

    @Inject
    public  LoginUserHandler(UserService userService, AEService aeService){
        this.userService = userService;
        this.aeService = aeService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        var username  = ctx.getRequest().getHeaders().get("username");
        var password = ctx.getRequest().getHeaders().get("password");
     var res =    this.userService.getByCredentials(username,password);
     var user = res.blockingGet();
     if(user.getUsername().equals(username)){
         ctx.render(json(res.blockingGet()));
     } else {
         ctx.render(json("{\"No User\"}"));
     }
    }
}

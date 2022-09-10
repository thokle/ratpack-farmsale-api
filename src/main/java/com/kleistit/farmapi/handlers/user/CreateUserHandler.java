package com.kleistit.farmapi.handlers.user;

import com.google.inject.Inject;
import com.kleistit.farmapi.config.AEService;
import com.kleistit.farmapi.nodes.User;
import com.kleistit.farmapi.services.impl.IUserService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;
public class CreateUserHandler implements Handler {
    private IUserService userService;
    private AEService aeService;


    @Inject
    public CreateUserHandler(IUserService userService, AEService aeService){
    this.userService = userService;
    this.aeService = aeService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.parse(User.class).then(user -> {
            var encrypt = this.aeService.encrypt(user.getPassword());
            user.setPassword(encrypt);

            var res =  this.userService.save(user).blockingGet();
                   ctx.render(json(res));
        });
    }
}

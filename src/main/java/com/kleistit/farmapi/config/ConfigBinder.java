package com.kleistit.farmapi.config;


import com.google.inject.AbstractModule;
import com.kleistit.farmapi.chanis.AddressChain;
import com.kleistit.farmapi.chanis.GrooceryChain;
import com.kleistit.farmapi.chanis.SalePlaceChain;
import com.kleistit.farmapi.chanis.UserChain;
import com.kleistit.farmapi.handlers.user.CreateUserHandler;
import com.kleistit.farmapi.services.impl.*;


/**
 * Created by thokle on 01/10/2016.
 */
public class ConfigBinder extends AbstractModule {
    @Override
    protected void configure() {


        bind(JsonUtil.class).asEagerSingleton();

        bind(CORSHandler.class).asEagerSingleton();
        bind(TokenService.class).asEagerSingleton();
        bind(AEService.class).asEagerSingleton();
        bind(DateUtil.class).asEagerSingleton();


        bind(CORSHandler.class).asEagerSingleton();
        bind(AEService.class).asEagerSingleton();

        bind(BeachHealthCheck.class).asEagerSingleton();


//Chains
        bind(UserChain.class).asEagerSingleton();
        bind(AddressChain.class).asEagerSingleton();
        bind(GrooceryChain.class).asEagerSingleton();
        bind(SalePlaceChain.class).asEagerSingleton();
        //Handlers
        bind(CreateUserHandler.class).asEagerSingleton();


        //Services
        bind(IUserService.class).to(UserService.class).asEagerSingleton();
        bind(AddressService.class).to(AddressServiceImpl.class).asEagerSingleton();
        bind(SalePlaceService.class).to(SaleServiceImpl.class).asEagerSingleton();
        bind(GrooceryService.class).to(GrooceryServiceImpl.class).asEagerSingleton();
    }

}

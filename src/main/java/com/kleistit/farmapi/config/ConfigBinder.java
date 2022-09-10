package com.kleistit.farmapi.config;


import com.google.inject.AbstractModule;
import com.kleistit.farmapi.chanis.*;
import com.kleistit.farmapi.handlers.address.AddAddressHandler;
import com.kleistit.farmapi.handlers.address.GetAddresssByCoordinatesHandler;
import com.kleistit.farmapi.handlers.address.GetUserbyAddressHandler;
import com.kleistit.farmapi.handlers.dawa.DawaHandler;
import com.kleistit.farmapi.handlers.groocery.*;
import com.kleistit.farmapi.handlers.position.UpdatePositionHandler;
import com.kleistit.farmapi.handlers.saleplace.*;
import com.kleistit.farmapi.handlers.user.CreateUserHandler;
import com.kleistit.farmapi.handlers.user.DoesUserExcistHandler;
import com.kleistit.farmapi.handlers.user.LoginUserHandler;
import com.kleistit.farmapi.services.impl.*;
import ratpack.handling.Context;


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

bind(IRatPackKafkaProducer.class).to(RatPackRatPackKafkaProducer.class);

//Chains
        bind(UserChain.class).asEagerSingleton();
        bind(AddressChain.class).asEagerSingleton();
        bind(GrooceryChain.class).asEagerSingleton();
        bind(SalePlaceChain.class).asEagerSingleton();
        bind(DawaChain.class).asEagerSingleton();
        bind(PositionChain.class).asEagerSingleton();
bind(KafkaConsumer.class).to(KafaConsumerImpl.class).asEagerSingleton();;

        //Handlers
        bind(CreateUserHandler.class).asEagerSingleton();
        bind(AddAddressHandler.class).asEagerSingleton();
        bind(AddGrooceryHandler.class).asEagerSingleton();
        bind(GetGroocriesBySalePlace.class).asEagerSingleton();
        bind(SoldOutHandlerSocket.class).asEagerSingleton();
        bind(AddSalePlaceHandler.class).asEagerSingleton();
        bind(CreateUserHandler.class).asEagerSingleton();
        bind(LoginUserHandler.class).asEagerSingleton();
        bind(DawaHandler.class).asEagerSingleton();
         bind(GetAddresssByCoordinatesHandler.class).asEagerSingleton();
         bind(GetUserbyAddressHandler.class).asEagerSingleton();
         bind(GetSalePlacesByAddressHandler.class).asEagerSingleton();
         bind(GetSalePlaceByIdHandler.class).asEagerSingleton();
         bind(GetGrooceryByIdHandler.class).asEagerSingleton();
         bind(CloseShopHandler.class).asEagerSingleton();
          bind(OpenSalePlaceHandler.class).asEagerSingleton();
          bind(IOpenCloseKafkaProducker.class).to(OpenCloseKafkaProducker.class);
 bind(SalePalceOpenHandlerSocket.class).asEagerSingleton();
 bind(KafkaCloseConsumer.class).to(KafkaCloseConsumerImpl.class);
 bind(CloseSalePlacesSocket.class).asEagerSingleton();
 bind(SoldoutHandler.class).asEagerSingleton();
 bind(AllOpenSalePlaceHandler.class).asEagerSingleton();
 bind(AllCloseSalePlaceHandler.class).asEagerSingleton();
 bind(AddPositionToSalePlaceHandler.class).asEagerSingleton();
 bind(UpdatePositionHandler.class).asEagerSingleton();
 bind(DoesUserExcistHandler.class).asEagerSingleton();
        //Services
        bind(IUserService.class).to(UserService.class).asEagerSingleton();
        bind(AddressService.class).to(AddressServiceImpl.class).asEagerSingleton();
        bind(SalePlaceService.class).to(SaleServiceImpl.class).asEagerSingleton();
        bind(GrooceryService.class).to(GrooceryServiceImpl.class).asEagerSingleton();
         bind(DawaService.class).to(DawaServiceImpl.class).asEagerSingleton();
        bind(KafkaOpenConsumer.class).to(KafkaOpenConsumerImpl.class).asEagerSingleton();
        bind(PositionService.class).to(PositionServiceImpl.class).asEagerSingleton();
    }

}

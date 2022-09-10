package com.kleistit.farmapi;

import com.kleistit.farmapi.chanis.*;
import com.kleistit.farmapi.config.CORSHandler;
import com.kleistit.farmapi.config.ConfigBinder;
import com.kleistit.farmapi.handlers.dawa.DawaHandler;
import com.kleistit.farmapi.handlers.groocery.SoldOutHandlerSocket;
import com.kleistit.farmapi.modules.Config;
import com.kleistit.farmapi.modules.KafkaConfiguarableModule;
import com.kleistit.farmapi.modules.ServiceCommonConfigModule;
import com.kleistit.farmapi.services.impl.OpenCloseService;
import com.kleistit.farmapi.services.impl.SoldOutConsumerService;
import com.kleistit.farmapi.services.impl.SoldOutService;

import ratpack.guice.Guice;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

public class Main {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.serverConfig(sfb -> sfb.baseDir(BaseDir.find())
                .props("ratpack.properties").maxMessagesPerRead(30000).maxChunkSize(1000000).maxContentLength(600000)
                .yaml("datasource.yaml")
                .require("/db", Config.class)
                .env().development(true)
                .sysProps()
                .build())
                .registry(Guice.registry(bindingsSpec ->
                        bindingsSpec.module(ConfigBinder.class).module(ServiceCommonConfigModule.class)
                                .module(KafkaConfiguarableModule.class)
                                .bind(SoldOutService.class)
                                .bind(OpenCloseService.class).bind(SoldOutConsumerService.class)))
                .handlers(chain -> chain.all(CORSHandler.class)
                        .prefix("user", UserChain.class)
                        .prefix("address", AddressChain.class)
                        .prefix("sale", SalePlaceChain.class)
                        .prefix("groocery", GrooceryChain.class)
                        .prefix("dawa", DawaChain.class).prefix("position",PositionChain.class)
                ));
    }
}
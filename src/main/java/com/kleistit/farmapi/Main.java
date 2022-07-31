package com.kleistit.farmapi;

import com.kleistit.farmapi.config.ConfigBinder;
import com.kleistit.farmapi.modules.Config;
import com.kleistit.farmapi.modules.ServiceCommonConfigModule;
import ratpack.core.server.BaseDir;
import ratpack.core.server.RatpackServer;
import ratpack.guice.Guice;

public class Main {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.serverConfig(sfb -> sfb.baseDir(BaseDir.find())
                .props("ratpack.properties").maxMessagesPerRead(30000).maxChunkSize(1000000).maxContentLength(600000)
                .yaml("datasource.yaml")
                .require("/db", Config.class)
                .env().development(true)
                .sysProps()
                .build())
                .registry(Guice.registry(bindingsSpec -> bindingsSpec.module(ConfigBinder.class).module(ServiceCommonConfigModule.class))));
    }
}
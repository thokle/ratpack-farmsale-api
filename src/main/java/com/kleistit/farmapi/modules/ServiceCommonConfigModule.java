package com.kleistit.farmapi.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.neo4j.ogm.session.Session;

import java.io.IOException;

/**
 * Created by thokle on 14/10/2016.
 */
public class ServiceCommonConfigModule extends AbstractModule {





    @Override
    protected void configure() {

    }

    @Singleton
    @Provides
    public Session connectionSource(Config config) throws IOException{
        return new ConnectionSource(config).session();
    }




}

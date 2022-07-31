package com.kleistit.farmapi.modules;




import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import com.kleistit.farmapi.config.AEService;
import com.kleistit.farmapi.services.impl.AuthorazationService;
import com.kleistit.farmapi.services.impl.IAuthorazationService;
import org.neo4j.ogm.session.Session;


/**
 * Created by thokle on 05/01/2017.
 */
public class AuthConfigurableModule extends AbstractModule {


    @Override
    protected void configure() {

    }


    @Provides
    public IAuthorazationService authorazationService(Session configure, AEService aeService){
        return new AuthorazationService(configure, aeService);



    }
}

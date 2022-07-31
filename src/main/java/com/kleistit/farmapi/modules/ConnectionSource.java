package com.kleistit.farmapi.modules;

import com.google.inject.Inject;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.io.IOException;


public class ConnectionSource {



    SessionFactory sessionFactory;



@Inject
    public ConnectionSource(Config config) throws  IOException {
    sessionFactory = new SessionFactory(configuration(config),"com.kleistit.entities");
        configuration(config);
    }

    private Configuration configuration(Config config) throws IOException {
        Configuration.Builder configuration = new Configuration.Builder();
        configuration.uri(config.getNeo4Url()).credentials(config.getUsername(),config.getPassword());
       return configuration.build();

    }

    public Session session() {

        return sessionFactory.openSession();

    }







}

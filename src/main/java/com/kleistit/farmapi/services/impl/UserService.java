package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.config.AEService;
import com.kleistit.farmapi.nodes.User;
import io.reactivex.Single;
import lombok.var;
import org.neo4j.ogm.session.Session;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Objects;

public class UserService implements IUserService {

    private Session session;
    private AEService aeService;
    @Inject
    public UserService(final Session session, final  AEService service){
        this.session = session;
        this.aeService = service;
    }
    @Override
    public Single<User> save(User user) {
        this.session.save(user);
        return Single.just(user);
    }

    @Override
    public Single<User> getByCredentials(String username, String email, String password) {
        var map = new HashMap<String, Object>();
        String statement = "MATCH (u:User) where u.username=$username and u.email=$email";
        var res = this.session.queryForObject(User.class, statement,map );


        return Single.just(res);
    }


}

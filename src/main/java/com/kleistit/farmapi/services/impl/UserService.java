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
    public UserService( Session session,   AEService service){
        this.session = session;
        this.aeService = service;
    }
    @Override
    public Single<User> save(User user) {


        this.session.save(user);
        return Single.just(user);
    }

    @Override
    public Single<User> getByCredentials(String username, String password) throws Exception {
        try {
            var map = new HashMap<String, Object>();
            map.put("username", username);
            var encryptetPassword = this.aeService.encrypt(password);
            map.put("password", encryptetPassword);
            String statement = "MATCH (u:User) where u.username=$username and u.password=$password return u";
            var res = this.session.queryForObject(User.class, statement, map);


            return Single.just(res);
        } catch (Exception ex) {
            throw  new Exception(ex.getCause());
        }
    }

    @Override
    public Single<Boolean> doesUserExcist(String username) {
        var map = new HashMap<String, Object>();
        map.put("username", username);

        String statement = "MATCH (u:User) where u.username=$username return u";
        var res = this.session.query(User.class, statement,map );
       if(res.iterator().hasNext()){
           return Single.just(true);
       } else {
           return Single.just(false);
       }
    }
}

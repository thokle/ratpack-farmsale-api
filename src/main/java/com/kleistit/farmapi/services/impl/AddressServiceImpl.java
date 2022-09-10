package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.nodes.Address;
import com.kleistit.farmapi.nodes.User;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.var;
import org.neo4j.ogm.session.Session;

import java.util.HashMap;

public class AddressServiceImpl implements  AddressService
{

    private final Session session;
   @Inject
   public AddressServiceImpl(final Session session){
       this.session = session;
   }
    @Override
    public Single<Address> addAddress(User user, Address address) throws  Exception{
       try {
        var map = new HashMap<String, Object>();
        map.put("username", user.getUsername());
        map.put("email", user.getEmail());
        var res = this.session.queryForObject(User.class, "match (user:User) where user.username=$username and user.email=$email return user", map);
        res.getAddress().add(address);
        this.session.save(res);
        return Single.just(address);
       }catch (Exception e)
       {
           throw  new Exception(e.getCause());
       }

    }

    @Override
    public Flowable<Address> getAddressesByCoordinates(Long lattitude, Long longitude) throws Exception{
       try {
           var map = new HashMap<String, Object>();
           map.put("lattitude", lattitude);
           map.put("longitude", longitude);
         return   Flowable.fromIterable(this.session.query(Address.class, "match (a:Address) where a.lattitude=$lattitude and s.longitude=$longitude return u", map));
       }catch (Exception e) {
           throw  new Exception(e.getCause());
       }

    }


    @Override
    public Flowable<Address> getAddreseByUser(String username) throws Exception {
       var map = new HashMap<String, Object>();
       map.put("email", username);

      var res =  this.session.query(Address.class, "Match (u:User)-[ru:USER_HAS_ADDRESS]->(a:Address) where u.email=$email return a",map);
        return Flowable.fromIterable(res);
    }
}

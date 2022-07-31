package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.nodes.Address;
import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.var;
import org.neo4j.ogm.session.Session;

import java.util.HashMap;

public class SaleServiceImpl implements  SalePlaceService{

    private Session session;
    @Inject
    public SaleServiceImpl(Session session){
        this.session = session;
    }
    @Override
    public Single<SalePlace> addSalePlaceToAddress(Address address, SalePlace salePlace) throws  Exception{
     try {
         var map = new HashMap<String,Object>();
    map.put("streetname", address.getStreetname());
    map.put("postalCode", address.getPostalCode());
         var  adr = this.session.queryForObject(Address.class, "match (a:Address) where a.streetname=$streetname and a.postalCode=$postalcode", map);
         adr.getSalePlaceSet().add(salePlace);
         this.session.save(adr);
         return Single.just(salePlace);

     }catch (Exception e){
         throw new Exception(e.getCause());
     }
    }

    @Override
    public Flowable<SalePlace> getSalePlacesByAddess(Address address) throws  Exception{
        try {
            var map = new HashMap<String, Object>();
            map.put("streetname", address.getStreetname());
            map.put("postalCode", address.getPostalCode());
            var res = this.session.query(SalePlace.class, "",map);

            return Flowable.fromIterable(res);
        }catch (Exception e){
            throw new Exception(e.getCause());
        }
    }
}

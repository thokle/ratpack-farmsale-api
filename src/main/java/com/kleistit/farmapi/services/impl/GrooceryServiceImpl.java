package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.nodes.Groocery;
import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.var;
import org.neo4j.ogm.session.Session;

import java.util.HashMap;

public class GrooceryServiceImpl implements  GrooceryService{

    private final Session session;

    @Inject
    public GrooceryServiceImpl(final Session session){
        this.session = session;
    }
    @Override
    public Single<Groocery> addGrooceryToSalePlace(SalePlace salePlace, Groocery groocery) throws Exception {
        try {
            var map = new HashMap<String, Object>();
            map.put("name", salePlace.getName());
           var res = this.session.queryForObject(SalePlace.class ,"match (s:SalePlace) s.name=$name", map);
           res.getGroocerySet().add(groocery);
           this.session.save(salePlace);
           return Single.just(groocery);
        }catch (Exception e){
            throw  new Exception(e.getCause());
        }

    }

    @Override
    public Flowable<Groocery> getGrooceryBySalePlace(SalePlace salePlace) throws Exception{
        try {
            var map  = new HashMap<String,Object>();
            map.put("name", salePlace.getName());
            var res = session.query(Groocery.class, "match (s:SalePlace) - [r:SalePlace_has_Grocery]-> (g:Groocery) where return s,r,g",map);

            return Flowable.fromIterable(res);
        }catch (Exception e){
            throw  new Exception(e.getCause());
        }

    }

    @Override
    public Single<Groocery> soldOut(SalePlace salePlace, Groocery groocery) {
        return null;
    }
}

package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.nodes.Groocery;
import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Flowable;
import io.reactivex.Single;

import lombok.var;
import org.neo4j.ogm.session.Session;

import java.util.Collections;
import java.util.HashMap;

public class GrooceryServiceImpl implements GrooceryService {

    private final Session session;

    @Inject
    public GrooceryServiceImpl(final Session session) {
        this.session = session;
    }

    @Override
    public Single<Groocery> addGrooceryToSalePlace(Long id, Groocery groocery) throws Exception {
        try {


            var res = this.session.load(SalePlace.class, id);
            res.getGroocerySet().add(groocery);
            this.session.save(res);
            return Single.just(groocery);
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }

    }

    @Override
    public Flowable<Groocery> getGrooceryBySalePlace(SalePlace salePlace) throws Exception {
        try {
            var map = new HashMap<String, Object>();
            map.put("name", salePlace.getName());
            var res = session.query(Groocery.class, "match (s:SalePlace) - [r:SalePlace_has_Grocery]-> (g:Groocery) where s.name=$name return s,r,g", map);

            return Flowable.fromIterable(res);
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }

    }

    @Override
    public Single<Groocery> soldOut(Long id) throws Exception {
        try {
            var groocery = this.session.load(Groocery.class, id);
            groocery.setIsSoldOut(true);
            this.session.save(groocery);
            return Single.just(groocery);
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }

    }


    @Override
    public Single<Groocery> getGrooceryById(Long id) throws Exception {
        try {
            return Single.just(this.session.load(Groocery.class, id));
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Flowable<Groocery> getAllSoldOut() throws Exception {
        try {
            var grooceryIterable = this.session.query(Groocery.class, "match (g:Groovery) where g.isSoldOut=true return g", Collections.emptyMap());
            return Flowable.fromIterable(grooceryIterable);
        } catch (Exception e){
            throw  new Exception(e.getCause());
        }
    }
}

package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.nodes.Address;
import com.kleistit.farmapi.nodes.Position;
import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Flowable;
import io.reactivex.Single;

import lombok.var;
import org.neo4j.ogm.session.Session;

import java.util.Collections;
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
         var  adr = this.session.queryForObject(Address.class, "match (a:Address) where a.streetname=$streetname and a.postalCode=$postalCode return a", map);
         adr.getSalePlaceSet().add(salePlace);
         this.session.save(adr);
         return Single.just(salePlace);

     }catch (Exception e){
         throw new Exception(e.getCause());
     }
    }

    @Override
    public Flowable<SalePlace> getSalePlacesByAddess(String streetname, Integer postcalOde) throws  Exception{
        try {
            var map = new HashMap<String, Object>();
            map.put("streetname", streetname);
            map.put("postalCode", postcalOde);
            var res = this.session.query(SalePlace.class, "match (a:Address)-[ra:Address_has_saleplaces]->(sp:SalePlace) where a.streetname=$streetname and a.postalCode=$postalCode return a, ra, sp",map);

            return Flowable.fromIterable(res);
        }catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Single<SalePlace> getSalePlaceById(Long id) throws Exception {
       try {
        var res =    session.load(SalePlace.class, id);
        return  Single.just(res);
       }catch (Exception e) {
throw new Exception(e.getCause());
       }
    }

    @Override
    public Flowable<SalePlace> getSalePlaceWithGrooceryByAddres(String streetname, int number, int postcode) throws Exception {
        try {
            var map = new HashMap<String, Object>();
            map.put("streetname", streetname);
            map.put("number", number);
            map.put("postcode", postcode);
            var res = session.query(SalePlace.class ,"match (a:Address)-[ap:Address_has_saleplaces]->(sp:SalePlace)-[sg:SalePlace_has_Grocery]->(gr:Groocery) where a.streetname=$streetname and a.streetnumber=$number and a.postalCode=$postcode return a, ap, sp,sg, gr",map);
            return Flowable.fromIterable(res);
        } catch (Exception e) {
                throw new Exception(e.getCause());
        }
    }

    @Override
    public Single<SalePlace> closeSalePlace(Long id) throws Exception {
       try {
           var salePlace = this.session.load(SalePlace.class, id);
           salePlace.setIsClosed(true);
           this.session.save(salePlace);
           return  Single.just(salePlace);
       }   catch ( Exception e) {
        throw new Exception(e.getCause());
    }
    }

    @Override
    public Single<SalePlace> openSalePalace(Long id) throws Exception {

        try{
               var saleplace = this.session.load(SalePlace.class, id);
               saleplace.setIsClosed(false);
               this.session.save(saleplace);

               return Single.just(saleplace);
        }   catch (Exception e) {
               throw  new Exception(e.getCause());
        }
    }


    @Override
    public Flowable<SalePlace> allCloseSalePlaces() throws Exception {
       try {
           var query = this.session.query(SalePlace.class, "Match (s:SalePlace) -[pr:SalePlace_has_position]->(p:Position) where s.isClosed=true return s, pr, p", Collections.emptyMap());
           return Flowable.fromIterable(query);
       }catch (Exception e) {
       throw new Exception(e.getCause());
       }
    }

    @Override
    public Flowable<SalePlace> allOpenSalePlacess() throws Exception {
        try {
            var query = this.session.query(SalePlace.class, "Match (s:SalePlace)-[pr:SalePlace_has_position]->(p:Position) where s.isClosed=false return s, pr ,p", Collections.emptyMap());
            return Flowable.fromIterable(query);
        }catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Single<SalePlace> addPosition(long id, double lat, double lng) throws Exception {
try {
    var pos = new Position(lat, lng);
    var sp =this.session.load(SalePlace.class, id);
  sp.getPosition().add(pos);
  this.session.save(pos);
    this.session.save(sp);

    return Single.just(sp);
} catch (Exception e){
    throw new Exception(e.getCause());
}

    }
}

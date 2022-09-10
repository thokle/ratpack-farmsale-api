package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.nodes.Groocery;
import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface GrooceryService {
    public Single<Groocery> addGrooceryToSalePlace(Long id, Groocery groocery ) throws Exception;
    public Flowable<Groocery> getGrooceryBySalePlace(SalePlace salePlace) throws Exception;
    public Single<Groocery> soldOut(Long id) throws Exception;

    public Single<Groocery> getGrooceryById(Long id) throws  Exception;

    public Flowable<Groocery> getAllSoldOut() throws  Exception;
}

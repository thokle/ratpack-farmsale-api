package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.nodes.Groocery;
import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface GrooceryService {
    public Single<Groocery> addGrooceryToSalePlace(SalePlace salePlace, Groocery groocery ) throws Exception;
    public Flowable<Groocery> getGrooceryBySalePlace(SalePlace salePlace) throws Exception;
    public Single<Groocery> soldOut(SalePlace salePlace , Groocery groocery) throws Exception;
}

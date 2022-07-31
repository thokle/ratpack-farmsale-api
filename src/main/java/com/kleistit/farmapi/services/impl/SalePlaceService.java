package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.nodes.Address;
import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface SalePlaceService {
    public Single<SalePlace> addSalePlaceToAddress(Address address, SalePlace salePlace) throws  Exception;
    public Flowable<SalePlace> getSalePlacesByAddess(Address address) throws  Exception;
}

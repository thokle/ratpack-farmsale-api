package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.nodes.Address;
import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface SalePlaceService {
    public Single<SalePlace> addSalePlaceToAddress(Address address, SalePlace salePlace) throws  Exception;
    public Flowable<SalePlace> getSalePlacesByAddess(String streetname , Integer postalCode) throws  Exception;

    public Single<SalePlace> getSalePlaceById(Long id) throws  Exception;

    public Flowable<SalePlace> getSalePlaceWithGrooceryByAddres(String streetname, int number, int postcode) throws  Exception;

    public Single<SalePlace> closeSalePlace(Long id) throws Exception;

    public  Single<SalePlace> openSalePalace(Long id) throws  Exception;

    public  Flowable<SalePlace> allCloseSalePlaces() throws  Exception;

    public Flowable<SalePlace> allOpenSalePlacess() throws Exception;

    public Single<SalePlace> addPosition(long id, double lat, double lng) throws  Exception;
}

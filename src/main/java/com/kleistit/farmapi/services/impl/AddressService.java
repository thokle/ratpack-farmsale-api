package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.nodes.Address;
import com.kleistit.farmapi.nodes.User;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface AddressService {
    public Single<Address> addAddress(User user , Address address) throws Exception;
    public Flowable<Address> getAddressesByCoordinates(Long lattitude, Long Longitude) throws Exception;
}

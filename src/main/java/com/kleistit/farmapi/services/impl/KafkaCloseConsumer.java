package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Single;

public interface KafkaCloseConsumer {
    public Single<SalePlace> consumeClosedSalePlace() throws  Exception;
}

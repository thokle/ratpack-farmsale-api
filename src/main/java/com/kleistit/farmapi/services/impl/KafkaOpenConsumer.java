package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Single;

public interface KafkaOpenConsumer {
    Single<SalePlace> consumerOpenSalePlace() throws Exception;

}

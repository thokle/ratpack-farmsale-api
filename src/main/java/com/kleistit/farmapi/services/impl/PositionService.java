package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.nodes.Position;
import io.reactivex.Single;

public interface PositionService {
    Single<Position> updatePosition(long id, double lat, double lng) throws  Exception;
}

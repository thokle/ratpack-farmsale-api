package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.nodes.Position;
import io.reactivex.Single;
import lombok.val;
import org.neo4j.ogm.session.Session;

public class PositionServiceImpl implements PositionService{

    private Session session;
@Inject
    public PositionServiceImpl(Session session){
        this.session = session;
    }

    @Override
    public Single<Position> updatePosition(long id, double lat, double lng) throws Exception {
        try{
            val position = session.load(Position.class, id);
            position.setLat(lat);
            position.setLng(lng);
            this.session.save(position);
            return Single.just(position);
        }catch (Exception e){
            throw new Exception(e.getCause());
        }
    }
}

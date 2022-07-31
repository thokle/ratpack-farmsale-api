package com.kleistit.farmapi.nodes;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by thokle on 24/08/2016.
 */
public abstract class Entity {



    @JsonProperty("id")
    private Long id;

    public Entity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || id == null || getClass() != obj.getClass()) return false;
        Entity entity = (Entity) obj;

        if(!id.equals(entity.getId()))return false;

        return true;


    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

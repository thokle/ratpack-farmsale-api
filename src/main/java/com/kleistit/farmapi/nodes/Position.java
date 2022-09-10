package com.kleistit.farmapi.nodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity

public class Position extends  Entity{

    private double lat;
    private  double lng;
}

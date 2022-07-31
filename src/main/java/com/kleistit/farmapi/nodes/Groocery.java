package com.kleistit.farmapi.nodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@AllArgsConstructor

public class Groocery extends Entity
{
    private String name;
    private String type;
    private Long price;
    private  String picture;
}

package com.kleistit.farmapi.nodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class SalePlace extends Entity {

    private String type;
    private  String name;

    @Relationship(direction = Relationship.OUTGOING, value = "SalePlace_has_Grocery")
    private Set<Groocery> groocerySet;
}

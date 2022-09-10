package com.kleistit.farmapi.nodes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class Address extends Entity {


    private String streetname;
    private Integer streetnumber;
    private String city;
    private Integer postalCode;
    private String country;
    private Long lattitude;
    private Long longitude;
    @Relationship(direction = Relationship.OUTGOING, value = "Address_has_saleplaces")
    private Set<SalePlace> salePlaceSet = new LinkedHashSet<SalePlace>();

}

package com.kleistit.farmapi.helperEntities;

import com.kleistit.farmapi.nodes.Address;
import com.kleistit.farmapi.nodes.Entity;
import com.kleistit.farmapi.nodes.SalePlace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class AddressSalePlace extends Entity {

    private Address address;

    private SalePlace salePlace;
}

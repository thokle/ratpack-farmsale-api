package com.kleistit.farmapi.helperEntities;

import com.kleistit.farmapi.nodes.Address;
import com.kleistit.farmapi.nodes.SalePlace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressSalePlace {
    private Address address;
    private SalePlace salePlace;
}

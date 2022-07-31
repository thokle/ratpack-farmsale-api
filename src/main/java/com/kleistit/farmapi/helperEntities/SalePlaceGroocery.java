package com.kleistit.farmapi.helperEntities;

import com.kleistit.farmapi.nodes.Groocery;
import com.kleistit.farmapi.nodes.SalePlace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalePlaceGroocery {
    private SalePlace salePlace;
    private Groocery groocery;
}

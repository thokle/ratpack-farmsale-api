package com.kleistit.farmapi.helperEntities;

import com.kleistit.farmapi.nodes.Address;
import com.kleistit.farmapi.nodes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
private User user;
private Address address;
}

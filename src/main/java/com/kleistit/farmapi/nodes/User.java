package com.kleistit.farmapi.nodes;

import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class User extends  Entity{


    private String firstname;

    private  String lastname;

    private  String email;

    private  String username;

    private  String password;

    @Relationship(direction = Relationship.OUTGOING, value = "USER_HAS_ADDRESS")
    private Set<Address> address = new HashSet<>();
}

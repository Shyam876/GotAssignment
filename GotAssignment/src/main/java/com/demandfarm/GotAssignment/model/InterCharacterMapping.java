package com.demandfarm.GotAssignment.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "InterCharacterMapping")
@Data
public class InterCharacterMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int primaryCharacterId;

    private int secondaryCharacterId;

    @Enumerated(value = EnumType.STRING)
    private RelationShip relationship;

    private Timestamp created;

}

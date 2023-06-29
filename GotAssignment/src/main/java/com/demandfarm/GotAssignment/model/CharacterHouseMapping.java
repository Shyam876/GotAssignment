package com.demandfarm.GotAssignment.model;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "CharacterHouseMapping")
@Data
public class CharacterHouseMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int houseId;

    private int characterId;

    private Timestamp created;

}

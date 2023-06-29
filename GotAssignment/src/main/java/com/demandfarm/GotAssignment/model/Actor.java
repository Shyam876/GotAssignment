package com.demandfarm.GotAssignment.model;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "Actor")
@Data
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int characterId;

    private String actorName;

    private String actorLink;

    private String seasons;

    private Timestamp created;


}

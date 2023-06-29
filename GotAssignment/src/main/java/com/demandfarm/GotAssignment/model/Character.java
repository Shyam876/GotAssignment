package com.demandfarm.GotAssignment.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Character")
@Data
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String characterName;
    private String characterImageThumb;
    private String characterImageFull;
    private String characterLink;

    private Boolean favorite;

    private Timestamp created;
    private Timestamp updated;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "characterId")
    private List<Actor> actors;

}

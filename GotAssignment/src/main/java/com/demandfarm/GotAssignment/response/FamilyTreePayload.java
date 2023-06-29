package com.demandfarm.GotAssignment.response;

import com.demandfarm.GotAssignment.model.Actor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FamilyTreePayload {

    private int id;

    private List<Relation> relations;


    private String characterName;
    private String characterImageFull;
    private String characterImageThumb;
    private String characterLink;

    private Boolean favorite;
    private List<Actor> actors;

    private List<FamilyTreePayload> children;
    private List<FamilyTreePayload> spouse;


    public FamilyTreePayload() {
        this.setChildren(new ArrayList<>());
        this.setSpouse(new ArrayList<>());
        this.setRelations(new ArrayList<>());
    }
}

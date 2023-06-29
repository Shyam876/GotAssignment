package com.demandfarm.GotAssignment.payload;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterPayload {

    @JsonProperty("characterName")
    private String characterName;

    @JsonProperty("houseName")
    private Object houseName;

    @JsonProperty("characterImageThumb")
    private String characterImageThumb;

    @JsonProperty("characterImageFull")
    private String characterImageFull;

    @JsonProperty("characterLink")
    private String characterLink;

    @JsonProperty("actorName")
    private String actorName;

    @JsonProperty("actorLink")
    private String actorLink;

    @JsonProperty("marriedEngaged")
    private List<String> marriedEngaged;

    @JsonProperty("killedBy")
    private List<String> killedBy;

    @JsonProperty("killed")
    private List<String> killed;

    @JsonProperty("siblings")
    private List<String> siblings;

    @JsonProperty("parents")
    private List<String> parents;

    @JsonProperty("parentOf")
    private List<String> parentOf;

    @JsonProperty("guardianOf")
    private List<String> guardianOf;

    @JsonProperty("guardedBy")
    private List<String> guardedBy;


    @JsonProperty("allies")
    private List<String> allies;


    @JsonProperty("actors")
    private List<ActorPayload> actors;


}
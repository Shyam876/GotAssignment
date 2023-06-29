package com.demandfarm.GotAssignment.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActorPayload {

    private String actorName;
    private String actorLink;
    private List<Integer> seasonsActive;

}
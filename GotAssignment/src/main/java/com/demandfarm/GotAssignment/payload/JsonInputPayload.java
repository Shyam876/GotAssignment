package com.demandfarm.GotAssignment.payload;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JsonInputPayload {

    @JsonProperty("characters")
    private CharacterPayload[] characters;

}

package com.demandfarm.GotAssignment.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Relation {

    private String key;
    private List<String> value;

    public Relation() {
    }

    public Relation(String key, List<String> value) {
        this.key = key;
        this.value = value;
    }
}

package com.demandfarm.GotAssignment.payload;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePayload<T> {

    private String status;

    private String message;

    private T data;

    public ResponsePayload(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponsePayload(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

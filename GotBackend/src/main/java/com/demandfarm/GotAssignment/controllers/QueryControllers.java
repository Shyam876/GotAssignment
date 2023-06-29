package com.demandfarm.GotAssignment.controllers;


import com.demandfarm.GotAssignment.payload.ResponsePayload;
import com.demandfarm.GotAssignment.services.QueryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryControllers {


    @Autowired
    private QueryServices queryServices;

    @GetMapping("/api/characters/houses")
    public ResponsePayload getHouses(){
        return queryServices.executeGetHouseNames();
    }


    @GetMapping("/api/characters/{id}/favorite")
    public ResponsePayload toggleFavorite(@PathVariable("id") int id){
        return queryServices.executeToggleCharacterFavorite(id);
    }

}

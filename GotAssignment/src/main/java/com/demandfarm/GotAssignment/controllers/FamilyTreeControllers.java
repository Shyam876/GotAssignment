package com.demandfarm.GotAssignment.controllers;


import com.demandfarm.GotAssignment.payload.ResponsePayload;
import com.demandfarm.GotAssignment.services.FamilyTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FamilyTreeControllers {

    @Autowired
    private FamilyTreeService familyTreeService;


    @GetMapping("/api/characters/familytree/{houseName}")
    public ResponsePayload constructFamilyTreePerHouse(@PathVariable("houseName") String houseName){

        return familyTreeService.executeConstructFamilyTreePerHouse(houseName);

    }

}

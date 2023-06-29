package com.demandfarm.GotAssignment.services;

import com.demandfarm.GotAssignment.payload.ResponsePayload;
import org.springframework.stereotype.Service;

@Service
public interface FamilyTreeService {

    public ResponsePayload executeConstructFamilyTreePerHouse(String houseName);

}

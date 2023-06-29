package com.demandfarm.GotAssignment.services.factory;

import com.demandfarm.GotAssignment.response.FamilyTreePayload;
import org.springframework.stereotype.Service;

@Service
public interface FamilyTreeFactoryService {
    void generateAdditionalRelationsList(FamilyTreePayload headNode, int characterId);
}

package com.demandfarm.GotAssignment.services.factory;

import com.demandfarm.GotAssignment.constants.RelationshipType;
import com.demandfarm.GotAssignment.model.Character;
import com.demandfarm.GotAssignment.model.InterCharacterMapping;
import com.demandfarm.GotAssignment.model.RelationShip;
import com.demandfarm.GotAssignment.repository.CharacterRepository;
import com.demandfarm.GotAssignment.repository.InterCharacterMappingRepository;
import com.demandfarm.GotAssignment.response.FamilyTreePayload;
import com.demandfarm.GotAssignment.response.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyTreeFactoryServiceImpl implements FamilyTreeFactoryService{

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private InterCharacterMappingRepository interCharacterMappingRepository;


    @Override
    public void generateAdditionalRelationsList(FamilyTreePayload headNode, int characterId){

        //Adding parents details
        List<String> parentRelations = generateCharacterListForRelation(characterId, RelationshipType.Secondary, RelationShip.Parent);
        if(parentRelations.size()>0)
            headNode.getRelations().add(new Relation("Parents", parentRelations));

        //Adding sibling details
        List<String> siblingRelations = generateCharacterListForRelation(characterId, RelationshipType.Mutual, RelationShip.Sibling);
        if(siblingRelations.size()>0)
            headNode.getRelations().add(new Relation("Siblings", siblingRelations));

        //Adding ally details
        List<String> allyRelations = generateCharacterListForRelation(characterId, RelationshipType.Mutual, RelationShip.Ally);
        if(allyRelations.size()>0)
            headNode.getRelations().add(new Relation("Ally", allyRelations));

        //Adding killed characters
        List<String> killedRelations = generateCharacterListForRelation(characterId, RelationshipType.Primary, RelationShip.Murder);
        if(killedRelations.size()>0)
            headNode.getRelations().add(new Relation("Killed", killedRelations));

        //Adding killed by characters
        List<String> killedByRelations = generateCharacterListForRelation(characterId, RelationshipType.Secondary, RelationShip.Murder);
        if(killedByRelations.size()>0)
            headNode.getRelations().add(new Relation("Killed", killedByRelations));

        //Adding Guardian of characters
        List<String> guardianOfRelations = generateCharacterListForRelation(characterId, RelationshipType.Primary, RelationShip.Guardian);
        if(guardianOfRelations.size()>0)
            headNode.getRelations().add(new Relation("Guardian of", guardianOfRelations));

        //Adding Guarded by by characters
        List<String> guardedByRelations = generateCharacterListForRelation(characterId, RelationshipType.Secondary, RelationShip.Guardian);
        if(guardedByRelations.size()>0)
            headNode.getRelations().add(new Relation("Guarded by", guardedByRelations));

    }


    private List<String> generateCharacterListForRelation(int characterId, RelationshipType relationshipType, RelationShip relationShip){

        List<String> result = new ArrayList<>();
        switch (relationshipType){

            case Primary -> {
                List<InterCharacterMapping> mappingAsPrimaryCharacter = interCharacterMappingRepository.findByPrimaryCharacterIdAndRelationship(characterId, relationShip);
                return generateCharacterList(mappingAsPrimaryCharacter, true, result);
            }

            case Secondary -> {
                List<InterCharacterMapping> mappingAsSecondaryCharacter = interCharacterMappingRepository.findBySecondaryCharacterIdAndRelationship(characterId, relationShip);
                return generateCharacterList(mappingAsSecondaryCharacter, false, result);
            }

            case Mutual -> {
                List<InterCharacterMapping> mappingAsPrimaryCharacter = interCharacterMappingRepository.findByPrimaryCharacterIdAndRelationship(characterId, relationShip);
                List<InterCharacterMapping> mappingAsSecondaryCharacter = interCharacterMappingRepository.findBySecondaryCharacterIdAndRelationship(characterId, relationShip);
                generateCharacterList(mappingAsPrimaryCharacter, true, result);
                return generateCharacterList(mappingAsSecondaryCharacter, false, result);
            }

            default -> {
                return result;
            }

        }

    }


    private List<String> generateCharacterList(List<InterCharacterMapping> mappings, Boolean primary, List<String> characterList){
        for (InterCharacterMapping parentMap : mappings){
            Character parentCharacter = characterRepository.findById(primary ? parentMap.getSecondaryCharacterId() : parentMap.getPrimaryCharacterId()).orElse(null);
            characterList.add(parentCharacter.getCharacterName());
        }

        return characterList;
    }

}

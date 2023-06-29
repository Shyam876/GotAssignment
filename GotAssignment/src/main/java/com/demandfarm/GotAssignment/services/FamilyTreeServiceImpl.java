package com.demandfarm.GotAssignment.services;

import com.demandfarm.GotAssignment.constants.RelationshipType;
import com.demandfarm.GotAssignment.model.*;
import com.demandfarm.GotAssignment.model.Character;
import com.demandfarm.GotAssignment.response.FamilyTreePayload;
import com.demandfarm.GotAssignment.payload.ResponsePayload;
import com.demandfarm.GotAssignment.repository.*;
import com.demandfarm.GotAssignment.response.Relation;
import com.demandfarm.GotAssignment.services.factory.FamilyTreeFactoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class FamilyTreeServiceImpl implements FamilyTreeService {


    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private InterCharacterMappingRepository interCharacterMappingRepository;

    @Autowired
    private CharacterHouseMappingRepository characterHouseMappingRepository;

    @Autowired
    private FamilyTreeFactoryService familyTreeFactoryService;

    @Override
    public ResponsePayload executeConstructFamilyTreePerHouse(String houseName) {

        try {

            List<House> houseList = houseRepository.findByName(houseName);
            if(houseList.size()==0)
                return new ResponsePayload("failed", "No house with given name");

            House house = houseList.get(0);
            List<CharacterHouseMapping> characterHouseMappings = characterHouseMappingRepository.findByHouseId(house.getId());

            //Creating a root characters list and then creating family tree for each of them
            List<Integer> rootCharacters = new ArrayList<>();
            for(CharacterHouseMapping mapping: characterHouseMappings){
                if(interCharacterMappingRepository.findBySecondaryCharacterIdAndRelationship(mapping.getCharacterId(), RelationShip.Parent).size()==0)
                    rootCharacters.add(mapping.getCharacterId());
            }

            //Initializing a single root headnode with housename
            Set<Integer> visited = new HashSet<>();
            FamilyTreePayload headNode = new FamilyTreePayload();
            headNode.setId(0);
            headNode.setCharacterName(houseName);
            for (Integer characterId: rootCharacters){
                FamilyTreePayload childNode = constructTree(characterId, visited);
                if(childNode!=null)
                    headNode.getChildren().add(childNode);
            }

            return new ResponsePayload("success", "", headNode);

        }catch (Exception e){
            log.error("Error occurred ",e);
            return new ResponsePayload("failed", e.getMessage());
        }

    }

    public FamilyTreePayload constructTree(int characterId, Set<Integer> visited){

        if(visited.contains(characterId))
            return null;

        Character character = characterRepository.findById(characterId).orElse(null);
        FamilyTreePayload headNode = new FamilyTreePayload();
        headNode.setId(characterId);
        headNode.setCharacterName(character.getCharacterName());
        headNode.setCharacterImageFull(character.getCharacterImageFull());
        headNode.setCharacterImageThumb(character.getCharacterImageThumb());
        headNode.setCharacterLink(character.getCharacterLink());
        headNode.setFavorite(character.getFavorite());
        headNode.setActors(character.getActors());

        visited.add(characterId);

        //Adding spouse
        List<InterCharacterMapping> marriageMappings = interCharacterMappingRepository.findByPrimaryCharacterIdAndRelationship(characterId, RelationShip.Marriage);
        for(InterCharacterMapping mapping: marriageMappings){
            FamilyTreePayload spouse = constructTree(mapping.getSecondaryCharacterId(), visited);
            if (spouse != null)
                headNode.getSpouse().add(spouse);
        }

        //Adding children
        List<InterCharacterMapping> childMappings = interCharacterMappingRepository.findByPrimaryCharacterIdAndRelationship(characterId, RelationShip.Parent);
        for(InterCharacterMapping mapping: childMappings){
            FamilyTreePayload child = constructTree(mapping.getSecondaryCharacterId(), visited);
            if(child != null)
                headNode.getChildren().add(child);
        }


        //For remaining relations, return a list of relations from factory service
        familyTreeFactoryService.generateAdditionalRelationsList(headNode, characterId);

        return headNode;

    }




}

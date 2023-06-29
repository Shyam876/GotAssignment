package com.demandfarm.GotAssignment.services.factory;


import com.demandfarm.GotAssignment.model.*;
import com.demandfarm.GotAssignment.model.Character;
import com.demandfarm.GotAssignment.payload.CharacterPayload;
import com.demandfarm.GotAssignment.payload.JsonInputPayload;
import com.demandfarm.GotAssignment.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataPersistenceServiceFactoryImpl implements DataPersistenceServiceFactory{

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private InterCharacterMappingRepository interCharacterMappingRepository;

    @Autowired
    private CharacterHouseMappingRepository characterHouseMappingRepository;

    @Autowired
    private HouseRepository houseRepository;


    @Override
    public JsonInputPayload readDataFromJson(String fileName) throws IOException {

        ClassPathResource staticDataResource = new ClassPathResource(fileName);
        String staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonInputPayload payload = objectMapper.readValue(staticDataString, JsonInputPayload.class);

        return payload;

    }

    @Override
    public Character persistCharacters(CharacterPayload characterPayload) {

        Character character = new Character();
        character.setCharacterName(characterPayload.getCharacterName());
        character.setCharacterImageThumb(characterPayload.getCharacterName());
        character.setCharacterLink(characterPayload.getCharacterLink());
        character.setCharacterImageFull(characterPayload.getCharacterImageFull());
        character.setFavorite(false);

        character.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        character.setUpdated(Timestamp.valueOf(LocalDateTime.now()));

        return characterRepository.save(character);

    }


    @Override
    public void persistHouses(Object houseObject){

        if(houseObject!=null)
            if(houseObject instanceof String){
                String houseName = (String) houseObject;
                if(houseRepository.findByName(houseName).size()==0){
                    House house = new House();
                    house.setName(houseName);
                    house.setCreated(Timestamp.valueOf(LocalDateTime.now()));

                    houseRepository.save(house);
                }
            }else{
                ArrayList<String> houseNames = (ArrayList<String>) houseObject;
                for(String houseName: houseNames){
                    if(houseRepository.findByName(houseName).size()==0){
                        House house = new House();
                        house.setName(houseName);
                        house.setCreated(Timestamp.valueOf(LocalDateTime.now()));

                        houseRepository.save(house);
                    }
                }
            }

    }


    @Override
    public void persistActor(int characterId, String actorName, String actorLink, String seasons){
        Actor actor = new Actor();
        actor.setCharacterId(characterId);
        actor.setActorName(actorName);
        actor.setActorLink(actorLink);
        actor.setSeasons(seasons);
        actor.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        actorRepository.save(actor);
    }


    @Override
    public void persistCharacterHouseMapping(Character character, Object HouseObject){

        if(HouseObject!=null)
            if(HouseObject instanceof String){

                String houseName = (String) HouseObject;
                House house = houseRepository.findByName(houseName).get(0);
                if(characterHouseMappingRepository.findByHouseIdAndCharacterId(house.getId(), character.getId()).size()==0)
                    addResidenceMapping(house.getId(), character.getId());

            }else{
                ArrayList<String> houseNames = (ArrayList<String>) HouseObject;
                for(String houseName: houseNames){
                    House house = houseRepository.findByName(houseName).get(0);
                    if(interCharacterMappingRepository.findByPrimaryCharacterIdAndSecondaryCharacterIdAndRelationship(house.getId(), character.getId(), RelationShip.Residence).size()==0)
                        addResidenceMapping(house.getId(), character.getId());
                }
            }

    }


    private void addResidenceMapping(int houseId, int characterId){

        CharacterHouseMapping mapping = new CharacterHouseMapping();
        mapping.setHouseId(houseId);
        mapping.setCharacterId(characterId);
        mapping.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        characterHouseMappingRepository.save(mapping);

    }



    @Override
    public void addInterCharacterMapping(String primaryCharacterName, String secondaryCharacterName, RelationShip relationShip){

        List<Character> primaryCharacter = characterRepository.findByCharacterName(primaryCharacterName);
        List<Character> secondaryCharacter = characterRepository.findByCharacterName(secondaryCharacterName);

        if(primaryCharacter.size()==0 || secondaryCharacter.size()==0)
            return;

        switch (relationShip){
            case Sibling:
            case Marriage:
            case Ally:
                addMutualCharacterMapping(primaryCharacter.get(0).getId(), secondaryCharacter.get(0).getId(), relationShip);
                break;
            case Murder:
            case Parent:
            case Guardian:
                addOneWayCharacterMapping(primaryCharacter.get(0).getId(), secondaryCharacter.get(0).getId(), relationShip);
                break;
            default:
                break;

        }

    }


    private void addMutualCharacterMapping(int primaryCharacterId, int secondaryCharacterId, RelationShip relationShip){

        if (interCharacterMappingRepository.findByPrimaryCharacterIdAndSecondaryCharacterIdAndRelationship(primaryCharacterId, secondaryCharacterId, relationShip).size()==0 && interCharacterMappingRepository.findByPrimaryCharacterIdAndSecondaryCharacterIdAndRelationship(secondaryCharacterId, primaryCharacterId, relationShip).size()==0) {
            InterCharacterMapping interCharacterMapping = new InterCharacterMapping();
            interCharacterMapping.setPrimaryCharacterId(primaryCharacterId);
            interCharacterMapping.setSecondaryCharacterId(secondaryCharacterId);
            interCharacterMapping.setRelationship(relationShip);
            interCharacterMapping.setCreated(Timestamp.valueOf(LocalDateTime.now()));

            interCharacterMappingRepository.save(interCharacterMapping);
        }

    }

    private void addOneWayCharacterMapping(int primaryCharacterId, int secondaryCharacterId, RelationShip relationShip){

        if (interCharacterMappingRepository.findByPrimaryCharacterIdAndSecondaryCharacterIdAndRelationship(primaryCharacterId, secondaryCharacterId, relationShip).size()==0) {
            InterCharacterMapping interCharacterMapping = new InterCharacterMapping();
            interCharacterMapping.setPrimaryCharacterId(primaryCharacterId);
            interCharacterMapping.setSecondaryCharacterId(secondaryCharacterId);
            interCharacterMapping.setRelationship(relationShip);
            interCharacterMapping.setCreated(Timestamp.valueOf(LocalDateTime.now()));

            interCharacterMappingRepository.save(interCharacterMapping);
        }

    }

}

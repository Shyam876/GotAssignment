package com.demandfarm.GotAssignment.services;


import com.demandfarm.GotAssignment.model.*;
import com.demandfarm.GotAssignment.model.Character;
import com.demandfarm.GotAssignment.payload.ActorPayload;
import com.demandfarm.GotAssignment.payload.CharacterPayload;
import com.demandfarm.GotAssignment.payload.JsonInputPayload;
import com.demandfarm.GotAssignment.repository.*;
import com.demandfarm.GotAssignment.services.factory.DataPersistenceServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DataPersistenceServiceImpl implements DataPersistenceService {

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

    @Autowired
    private DataPersistenceServiceFactory dataPersistenceServiceFactory;


    @Override
    public void persistCharacters() {

        try {

            JsonInputPayload payload = dataPersistenceServiceFactory.readDataFromJson("Characters.json");

            for(CharacterPayload characterPayload : payload.getCharacters()){

                //Persist Characters into database
                Character createdCharacter = dataPersistenceServiceFactory.persistCharacters(characterPayload);

                //Persist houses
                dataPersistenceServiceFactory.persistHouses(characterPayload.getHouseName());


                //Persist houses
                if(characterPayload.getActorName()!=null){
                    dataPersistenceServiceFactory.persistActor(createdCharacter.getId(), characterPayload.getActorName(), characterPayload.getActorLink(), "1-8");
                }

                if(characterPayload.getActors()!=null)
                    for(ActorPayload actorPayload: characterPayload.getActors()){
                        dataPersistenceServiceFactory.persistActor(createdCharacter.getId(), actorPayload.getActorName(), actorPayload.getActorLink(), actorPayload.getSeasonsActive().toString().replace("[", "").replace("]", ""));
                    }

            }



            //Creating relations
            for(CharacterPayload characterPayload : payload.getCharacters()){

                Character character = characterRepository.findByCharacterName(characterPayload.getCharacterName()).get(0);

                //Add Residences
                dataPersistenceServiceFactory.persistCharacterHouseMapping(character, characterPayload.getHouseName());

                //To add sibling
                if(characterPayload.getSiblings()!=null)
                    for(String secondaryCharName : characterPayload.getSiblings()) {
                        dataPersistenceServiceFactory.addInterCharacterMapping(characterPayload.getCharacterName(), secondaryCharName, RelationShip.Sibling);
                    }

                //Add guardian
                if(characterPayload.getGuardianOf()!=null)
                    for (String secondaryCharName : characterPayload.getGuardianOf()){
                        dataPersistenceServiceFactory.addInterCharacterMapping(characterPayload.getCharacterName(), secondaryCharName, RelationShip.Guardian);
                    }

                if(characterPayload.getGuardedBy()!=null)
                    for (String guardedBy: characterPayload.getGuardedBy()){
                        dataPersistenceServiceFactory.addInterCharacterMapping(guardedBy, characterPayload.getCharacterName(), RelationShip.Guardian);
                    }

                //Add ally
                if(characterPayload.getAllies()!=null)
                    for (String ally : characterPayload.getAllies()){
                        dataPersistenceServiceFactory.addInterCharacterMapping(characterPayload.getCharacterName(), ally, RelationShip.Ally);
                    }

                //Add Parent
                if(characterPayload.getParentOf()!=null)
                    for (String parentOf : characterPayload.getParentOf()){
                        dataPersistenceServiceFactory.addInterCharacterMapping(characterPayload.getCharacterName(), parentOf, RelationShip.Parent);
                    }

                if(characterPayload.getParents()!=null)
                    for (String parent: characterPayload.getParents()){
                        dataPersistenceServiceFactory.addInterCharacterMapping(parent, characterPayload.getCharacterName(), RelationShip.Parent);
                    }


                //Add Murder relation
                if(characterPayload.getKilled()!=null)
                    for (String killed : characterPayload.getKilled()){
                        dataPersistenceServiceFactory.addInterCharacterMapping(characterPayload.getCharacterName(), killed, RelationShip.Murder);
                    }

                if(characterPayload.getKilledBy()!=null)
                    for (String killedBy: characterPayload.getKilledBy()){
                        dataPersistenceServiceFactory.addInterCharacterMapping(killedBy, characterPayload.getCharacterName(), RelationShip.Sibling);
                    }

                //Add marriage relationship
                if(characterPayload.getMarriedEngaged()!=null)
                    for(String marriedTo : characterPayload.getMarriedEngaged() ){
                        dataPersistenceServiceFactory.addInterCharacterMapping(characterPayload.getCharacterName(), marriedTo, RelationShip.Marriage);
                    }



            }


        }catch (IOException e){
            log.info("Exception while reading json file ",e);
        }

    }
}

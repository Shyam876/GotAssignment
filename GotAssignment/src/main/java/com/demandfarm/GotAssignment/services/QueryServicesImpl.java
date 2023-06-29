package com.demandfarm.GotAssignment.services;


import com.demandfarm.GotAssignment.model.Character;
import com.demandfarm.GotAssignment.model.House;
import com.demandfarm.GotAssignment.payload.ResponsePayload;
import com.demandfarm.GotAssignment.repository.ActorRepository;
import com.demandfarm.GotAssignment.repository.CharacterRepository;
import com.demandfarm.GotAssignment.repository.HouseRepository;
import com.demandfarm.GotAssignment.repository.InterCharacterMappingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class QueryServicesImpl implements QueryServices {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private InterCharacterMappingRepository interCharacterMappingRepository;

    @Override
    public ResponsePayload executeGetHouseNames() {

        try{

            List<House> houseNames = houseRepository.findAll();
            return new ResponsePayload("success", "house names fetch successful", houseNames);

        }catch (Exception e){
            log.error("Error occurred ",e);
            return new ResponsePayload("failed", "Error occurred");
        }

    }

    @Override
    public ResponsePayload executeToggleCharacterFavorite(int characterId) {
        try{

            log.info("Request to toggel favorite "+characterId);
            Character character = characterRepository.findById(characterId).orElseThrow(() -> new Exception("Character not found"));

            character.setFavorite(!character.getFavorite());
            characterRepository.save(character);

            return new ResponsePayload("success", "house names fetch successful");

        }catch (Exception e){
            log.error("Error occurred ",e);
            return new ResponsePayload("failed", e.getMessage());
        }
    }




}

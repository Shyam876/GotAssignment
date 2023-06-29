package com.demandfarm.GotAssignment.services.factory;

import com.demandfarm.GotAssignment.model.Character;
import com.demandfarm.GotAssignment.model.RelationShip;
import com.demandfarm.GotAssignment.payload.CharacterPayload;
import com.demandfarm.GotAssignment.payload.JsonInputPayload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface DataPersistenceServiceFactory {
    JsonInputPayload readDataFromJson(String fileName) throws IOException;

    Character persistCharacters(CharacterPayload characterPayload);

    void persistHouses(Object houseObject);

    void persistActor(int characterId, String actorName, String actorLink, String seasons);

    void persistCharacterHouseMapping(Character character, Object HouseObject);

    void addInterCharacterMapping(String primaryCharacterName, String secondaryCharacterName, RelationShip relationShip);
}

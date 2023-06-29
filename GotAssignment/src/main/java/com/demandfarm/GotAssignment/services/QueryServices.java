package com.demandfarm.GotAssignment.services;

import com.demandfarm.GotAssignment.payload.ResponsePayload;
import com.demandfarm.GotAssignment.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QueryServices {

    public ResponsePayload executeGetHouseNames();

    public ResponsePayload executeToggleCharacterFavorite(int characterId);

}

package com.demandfarm.GotAssignment;

import com.demandfarm.GotAssignment.services.DataPersistenceService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartupRunService {

    @Autowired
    private DataPersistenceService dataPersistenceService;

    @PostConstruct
    public void init(){
        dataPersistenceService.persistCharacters();
    }
}

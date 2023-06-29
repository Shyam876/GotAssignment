package com.demandfarm.GotAssignment.repository;


import com.demandfarm.GotAssignment.model.InterCharacterMapping;
import com.demandfarm.GotAssignment.model.RelationShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterCharacterMappingRepository extends JpaRepository<InterCharacterMapping, Long> {

    public List<InterCharacterMapping> findByPrimaryCharacterIdAndSecondaryCharacterIdAndRelationship(int primaryCharacterId, int secondaryCharacterId, RelationShip relationShip);

    public List<InterCharacterMapping> findByPrimaryCharacterIdAndRelationship(int primaryCharacterId, RelationShip relationShip);

    public List<InterCharacterMapping> findBySecondaryCharacterIdAndRelationship(int secondaryCharacterId, RelationShip relationShip);

}
